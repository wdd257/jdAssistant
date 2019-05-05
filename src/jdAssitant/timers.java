package jdAssitant;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import javax.swing.Timer;

import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.cookie.Cookie;

public class timers {
    // 判断二维码是否扫描
    static Timer QrCodeLoginTimer = new Timer(1000 * 1, new ActionListener() {
	@Override
	public void actionPerformed(ActionEvent e) {
	    try {
		Random rand = new Random(System.currentTimeMillis());
		String checkLoginurl = "https://qr.m.jd.com/check?callback=jQuery" + (rand.nextInt(8999999) + 1000000)
			+ "&appid=133&token=" + loginByQRcodeOrFile.wlfstk_smdl + "&_=" + +new Date().getTime();
		variables.httpGet = new HttpGet(checkLoginurl);
		variables.httpGet.addHeader("Referer",
			"https://passport.jd.com/new/login.aspx?ReturnUrl=https%3A%2F%2Fwww.jd.com%2F");
		variables.httpGet.addHeader("User-Agent",
			"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/66.0.3359.181 Safari/537.36");
		// 执行请求
		variables.response = variables.httpClient.execute(variables.httpGet);
		// 获得响应的实体对象
		variables.entity = variables.response.getEntity();
		/*
		 * 获取到响应信息的流
		 */
		InputStream is = variables.entity.getContent();

		InputStreamReader isr = new InputStreamReader(is, "utf-8");
		BufferedReader br = new BufferedReader(isr);
		StringBuffer buffer = new StringBuffer();
		String line;
		while ((line = br.readLine()) != null) {
		    buffer.append(line);
		}
		// 关闭流
		isr.close();
		br.close();

		String str = buffer.toString();
		loginByQRcodeOrFile.getQrCode.setText(str.substring(str.indexOf("msg") + 8, str.indexOf("\"})")));
		if (str.contains("\"code\" : 200,   \"ticket\" : ")) {
		    String ticket = str.substring(str.indexOf("ticket\" : \"") + 11, str.length() - 3);
		    Thread.sleep(2000);
		    System.out.println(ticket);
		    String urlck = "https://passport.jd.com/uc/qrCodeTicketValidation?t=" + ticket;
		    variables.httpGet = new HttpGet(urlck);
		    variables.httpGet.addHeader("Referer", "https://passport.jd.com/uc/login?ltype=logout");
		    // 执行请求
		    variables.response = variables.httpClient.execute(variables.httpGet);
		    if (variables.response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
			for (Cookie cookie : variables.cookieStore.getCookies()) {
			    if (cookie.getName().equals("unick")) {
				window.label_8.setText(cookie.getValue());
				variables.cookieFile = new File("D:/" + cookie.getValue());
				break;
			    }
			}
			// 保存cookie到文件
			FileOutputStream fos = new FileOutputStream(variables.cookieFile);
			ObjectOutputStream oos = new ObjectOutputStream(fos);// 写入的文件是以二进制文件存储

			System.out.println("cookie:" + variables.cookieStore);
			oos.writeObject(variables.cookieStore);
			oos.close();
			fos.close();
			loginByQRcodeOrFile.timer.stop();
			loginByQRcodeOrFile.frame.dispose();
		    }
		}

	    } catch (Exception e1) {
		e1.printStackTrace();
	    }

	}

    });

    static Timer qiangquandingshi = new Timer(1, new ActionListener() {
	@Override
	public void actionPerformed(ActionEvent e) {
	    System.out.println("抢券开始");
	    variables.countDownLatch.countDown();
	    variables.cd = new CountDown();
	    variables.cd.start();
	    qiangquandingshi.stop();
	}

    });

    // 时间timer
    static Timer localTimer = new Timer(100, new ActionListener() {
	@Override
	public void actionPerformed(ActionEvent e) {
	    Date date = new Date(System.currentTimeMillis());
	    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    String formatDate = dateFormat.format(date);// 格式化
	    window.label_12.setText(formatDate);
	}

    });

    //倒计时timer
    static Timer daojishi = new Timer(100, new ActionListener() {
	@Override
	public void actionPerformed(ActionEvent e) {
	    Date date = new Date(variables.date.getTime() - System.currentTimeMillis());
	    window.label_13.setText("" + date.getTime() / 1000 + "秒");
	}

    });

}
