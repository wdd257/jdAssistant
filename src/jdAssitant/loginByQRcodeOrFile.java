package jdAssitant;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.ObjectInputStream;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import org.apache.http.HttpEntity;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClients;

public class loginByQRcodeOrFile implements ActionListener {

    static JLabel qrCode;
    static JButton getQrCode;
    static JButton getCookieFile;
    static Timer timer = timers.QrCodeLoginTimer;
    static JFrame frame;
    static JPanel jp;
    static JPanel jp2;
    static String wlfstk_smdl;
    static String QRCodeKey;

    @Override
    public void actionPerformed(ActionEvent e) {
	frame = new JFrame();// 构造一个新的JFrame，作为新窗口。
	frame.setLayout(new GridLayout(2, 1));
	jp = new JPanel();
	jp2 = new JPanel();
	jp.setLayout(new CardLayout());
	jp2.setLayout(new GridLayout(1,2));
	getQrCode = new JButton("获取二维码");
	
	frame.add(jp);
	frame.add(jp2);
	Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
	int width = 400;
	int height = 400;
	frame.setBounds((d.width - width) / 2, (d.height - height) / 2, width, height);
	qrCode = new JLabel(new ImageIcon());

	jp.add(qrCode);
	jp2.add(getQrCode);

	qrCode.setVerticalAlignment(JLabel.CENTER);
	qrCode.setHorizontalAlignment(JLabel.CENTER);// 注意方法名别写错了。

	getQrCode.setVerticalAlignment(JLabel.CENTER);
	getQrCode.setHorizontalAlignment(JLabel.CENTER);

	getQrCode.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		try {
		    String url = "https://passport.jd.com/new/login.aspx";
		    variables.httpGet = new HttpGet(url);
		    variables.httpGet.addHeader("User-Agent",
			    "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/66.0.3359.181 Safari/537.36");

		    // 执行请求
		    variables.response = variables.httpClient.execute(variables.httpGet);

		    variables.httpGet.addHeader("Referer", url);
		    // 由于GET请求的参数都是拼装在URL地址后方，所以我们要构建一个URL，带参数
		    url = "https://qr.m.jd.com/show";
		    URIBuilder uriBuilder = new URIBuilder(url);
		    uriBuilder.addParameter("appid", "133");
		    uriBuilder.addParameter("size", "147");
		    uriBuilder.addParameter("t", Long.toString(System.currentTimeMillis()));
		    variables.httpGet.setURI(uriBuilder.build());

		    variables.response = variables.httpClient.execute(variables.httpGet);

		    for (int i = 0; i < variables.response.getAllHeaders().length; i++) {
			// 有两个Set-cookie
			if (variables.response.getAllHeaders()[i].getName().equals("Set-cookie")) {
			    String str = variables.response.getAllHeaders()[i].getValue();
			    if (str.contains("QRCodeKey")) {
				str = str.replaceAll("QRCodeKey=", "");
				String s[] = str.split(";");
				QRCodeKey = s[0];
			    }
			    if (str.contains("wlfstk_smdl")) {

				str = str.replaceAll("wlfstk_smdl=", "");
				String s[] = str.split(";");
				wlfstk_smdl = s[0];
			    }
			}
		    }

		    // 获得响应的实体对象
		    HttpEntity entity = variables.response.getEntity();
		    /*
		     * 获取到响应信息的流
		     */
		    InputStream is = entity.getContent();
		    // 包装成高效流
		    BufferedInputStream bis = new BufferedInputStream(is);
		    // 数据字节数组
		    byte[] img = new byte[1024];
		    bis.read(img);
		    bis.close();
		    qrCode.setIcon(new ImageIcon(img));

		    getQrCode.setText("二维码未扫描");

		    timer.start();

		    // 关闭流
		    bis.close();
		    is.close();

		} catch (Exception e1) {
		    e1.printStackTrace();
		}

	    }

	});

	getCookieFile = new JButton("读取Cookie文件");
	jp2.add(getCookieFile);
	getCookieFile.setVerticalAlignment(JLabel.CENTER);
	getCookieFile.setHorizontalAlignment(JLabel.CENTER);// 注意方法名别写错了。
	getCookieFile.addActionListener(new ActionListener() {

	    @Override
	    public void actionPerformed(ActionEvent e) {
		JFileChooser chooser = new JFileChooser();
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		chooser.setCurrentDirectory(new File("D:/"));
		for (int i = 0; i < 3; i++) {
		    chooser.showDialog(new JLabel(), "选择");
		    variables.cookieFile = chooser.getSelectedFile();
		    if (variables.cookieFile != null) {
			break;
		    }
		}
		if (variables.cookieFile == null) {
		    return;
		}
		FileInputStream fin = null;
		try {
		    fin = new FileInputStream(variables.cookieFile);
		} catch (FileNotFoundException e1) {
		    e1.printStackTrace();
		}
		ObjectInputStream in;
		try {
		    in = new ObjectInputStream(fin);
		    variables.cookieStore = (CookieStore) in.readObject();
		    System.out.println("Cookie:" + variables.cookieStore.toString());
		    variables.httpClient = HttpClients.custom().setDefaultCookieStore(variables.cookieStore).build();
		    window.label_8.setText(variables.cookieFile.getName());
		    in.close();
		} catch (Exception e1) {
		    e1.printStackTrace();
		}
		frame.dispose();

	    }

	});

	frame.setVisible(true);
	frame.addWindowListener(new WindowListener() {

	    @Override
	    public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		//System.out.println("打开二维码窗口");

	    }

	    @Override
	    public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub

	    }

	    @Override
	    public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub

	    }

	    @Override
	    public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
	    }

	    @Override
	    public void windowClosing(WindowEvent e) {
		timer.stop();
	    }

	    @Override
	    public void windowClosed(WindowEvent e) {
	    }

	    @Override
	    public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
	    }
	});
    }

}
