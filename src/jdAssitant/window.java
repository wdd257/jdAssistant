package jdAssitant;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.util.Date;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import javax.swing.text.DateFormatter;

import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.awt.Font;

public class window extends JFrame {

    static JPanel contentPane;
    static JTextField textField;
    static JTextField textField_1;

    static JLabel label;
    static JLabel label_1;
    static JLabel label_2;
    static JLabel label_3;
    static JLabel label_4;
    static JLabel label_5;
    static JLabel label_6;
    static JLabel label_7;
    static JLabel label_8;
    static JLabel label_9;
    static JLabel label_10;
    static JLabel label_11;
    static JLabel label_12;
    static JLabel label_13;

    static JButton btnNewButton;
    static JButton btnNewButton_1;
    static JButton btnNewButton_2;

    static JSpinner spinner;//时
    static JSpinner spinner_1;//分
    static JSpinner spinner_2;//秒
    static JSpinner spinner_3;//频率
    static JSpinner spinner_4;//持续时间
    static JSpinner spinner_5;//抢券1线程数
    
    static JTextPane textPane;
    
    static JScrollPane jsp;
    
    private final Action lingQuYouHuiQuan = new actions.lingQuYouHuiQuan();
    private final Action dingShiQiangQuan = new actions.dingShiQiangQuan();
    private JButton button;
    private JButton btnNewButton_3;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
	EventQueue.invokeLater(new Runnable() {
	    public void run() {
		try {
		    window frame = new window();
		    frame.setVisible(true);
		} catch (Exception e) {
		    e.printStackTrace();
		}
	    }
	});
    }

    /**
     * Create the frame.
     * 
     * @throws UnsupportedLookAndFeelException
     * @throws IllegalAccessException
     * @throws InstantiationException
     * @throws ClassNotFoundException
     */
    public window() throws ClassNotFoundException, InstantiationException, IllegalAccessException,
	    UnsupportedLookAndFeelException {

	UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
	int width = 889;
	int height = 573;
	setBounds((d.width - width) / 2, (d.height - height) / 2, width, height);

	contentPane = new JPanel();
	contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	setContentPane(contentPane);
	contentPane.setLayout(null);

	label = new JLabel("优惠券领取栏1：");
	label.setFont(new Font("SimSun", Font.BOLD, 12));
	label.setBounds(8, 121, 129, 18);
	contentPane.add(label);

	label_1 = new JLabel("优惠券领取栏2：");
	label_1.setFont(new Font("SimSun", Font.BOLD, 12));
	label_1.setBounds(8, 155, 136, 18);
	contentPane.add(label_1);

	textField = new JTextField();
	textField.setBounds(115, 151, 500, 24);
	contentPane.add(textField);
	textField.setColumns(10);

	textField_1 = new JTextField();
	textField_1.setBounds(115, 118, 500, 24);
	contentPane.add(textField_1);
	textField_1.setColumns(10);

	btnNewButton = new JButton("测试优惠券");
	btnNewButton.setFont(new Font("SimSun", Font.BOLD, 12));
	btnNewButton.setText("测试优惠券");
	btnNewButton.setAction(lingQuYouHuiQuan);
	btnNewButton.setBounds(110, 194, 113, 27);
	contentPane.add(btnNewButton);

	btnNewButton_1 = new JButton("定时领取");
	btnNewButton_1.setFont(new Font("SimSun", Font.BOLD, 12));
	btnNewButton_1.setBounds(246, 194, 113, 27);
	btnNewButton_1.setAction(dingShiQiangQuan);
	contentPane.add(btnNewButton_1);

	btnNewButton_2 = new JButton("登录");
	btnNewButton_2.setFont(new Font("SimSun", Font.BOLD, 12));
	btnNewButton_2.addActionListener(new loginByQRcodeOrFile());
	btnNewButton_2.setBounds(499, 16, 113, 27);
	contentPane.add(btnNewButton_2);

	spinner = new JSpinner();
	spinner.setBounds(114, 66, 38, 24);
	contentPane.add(spinner);

	spinner_1 = new JSpinner();
	spinner_1.setBounds(180, 66, 38, 24);
	contentPane.add(spinner_1);

	spinner_2 = new JSpinner();
	spinner_2.setBounds(246, 66, 38, 24);
	contentPane.add(spinner_2);
	
	spinner_3 = new JSpinner();
	spinner_3.setBounds(399, 66, 38, 24);
	contentPane.add(spinner_3);
	
	spinner_4 = new JSpinner();
	spinner_4.setBounds(471, 66, 38, 24);
	contentPane.add(spinner_4);
	
	//定时时间 小时/分钟/秒
	spinner.setValue(new Date().getHours());
	spinner_1.setValue(59);
	spinner_2.setValue(57);
	//频率
	spinner_3.setValue(50);
	//持续时间 
	spinner_4.setValue(8);
	

	label_2 = new JLabel("时");
	label_2.setFont(new Font("SimSun", Font.BOLD, 12));
	label_2.setBounds(159, 70, 72, 18);
	contentPane.add(label_2);

	label_3 = new JLabel("分");
	label_3.setFont(new Font("SimSun", Font.BOLD, 12));
	label_3.setBounds(225, 70, 72, 18);
	contentPane.add(label_3);

	label_4 = new JLabel("秒");
	label_4.setFont(new Font("SimSun", Font.BOLD, 12));
	label_4.setBounds(292, 70, 72, 18);
	contentPane.add(label_4);

	label_5 = new JLabel("定 时 设 置：");
	label_5.setFont(new Font("SimSun", Font.BOLD, 12));
	label_5.setBounds(20, 70, 110, 18);
	contentPane.add(label_5);

	label_6 = new JLabel("日志：");
	label_6.setFont(new Font("SimSun", Font.BOLD, 12));
	label_6.setBounds(8, 234, 110, 18);
	contentPane.add(label_6);

	label_7 = new JLabel("领取频率：");
	label_7.setFont(new Font("SimSun", Font.BOLD, 12));
	label_7.setBounds(321, 70, 87, 18);
	contentPane.add(label_7);

	label_8 = new JLabel("用户未登录");
	label_8.setFont(new Font("SimSun", Font.BOLD, 12));
	label_8.setBounds(405, 23, 92, 18);
	contentPane.add(label_8);

	spinner_4 = new JSpinner();
	spinner_4.setBounds(471, 66, 38, 24);
	contentPane.add(spinner_4);

	label_9 = new JLabel("秒后停止");
	label_9.setFont(new Font("SimSun", Font.BOLD, 12));
	label_9.setBounds(520, 70, 87, 18);
	contentPane.add(label_9);

	spinner_5 = new JSpinner();
	spinner_5.setBounds(630, 118, 38, 24);
	contentPane.add(spinner_5);
	spinner_5.setValue(2);

	label_10 = new JLabel("线程");
	label_10.setFont(new Font("SimSun", Font.BOLD, 12));
	label_10.setBounds(680, 122, 72, 18);
	contentPane.add(label_10);
	
	textPane = new JTextPane();
	textPane.setFont(new Font("SimSun", Font.BOLD, 12));
	jsp=new JScrollPane(textPane);
	jsp.setBounds(8, 262, 855, 260);
	textPane.setEditable(false);
	contentPane.add(jsp);
	
	button = new JButton("清空日志");
	button.setFont(new Font("SimSun", Font.BOLD, 12));
	button.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		    textPane.setText("");
		}
	});
	button.setBounds(750, 230, 113, 27);
	contentPane.add(button);
	
	label_11 = new JLabel("时  间：");
	label_11.setFont(new Font("SimSun", Font.BOLD, 15));
	label_11.setBounds(391, 200, 67, 18);
	contentPane.add(label_11);
	
	label_12 = new JLabel("");
	label_12.setFont(new Font("SimSun", Font.BOLD, 15));
	label_12.setBounds(455, 200, 302, 18);
	contentPane.add(label_12);
	
	JLabel lblDaojishi = new JLabel("倒计时：");
	lblDaojishi.setFont(new Font("SimSun", Font.BOLD, 15));
	lblDaojishi.setBounds(391, 236, 67, 18);
	contentPane.add(lblDaojishi);
	
	label_13 = new JLabel("未定时");
	label_13.setFont(new Font("SimSun", Font.BOLD, 15));
	label_13.setBounds(455, 236, 302, 18);
	contentPane.add(label_13);
	
	btnNewButton_3 = new JButton("清空优惠券信息");
	btnNewButton_3.setFont(new Font("SimSun", Font.BOLD, 12));
	btnNewButton_3.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		    textField.setText("");
		    textField_1.setText("");
		}
	});
	btnNewButton_3.setBounds(630, 151, 127, 24);
	contentPane.add(btnNewButton_3);

	
	timers.localTimer.start();
	
    }
}
