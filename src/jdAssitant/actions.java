package jdAssitant;

import java.awt.event.ActionEvent;
import java.util.Date;
import java.util.concurrent.CountDownLatch;

import javax.swing.AbstractAction;
import javax.swing.SpinnerDateModel;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.SimpleAttributeSet;

public class actions {
    public static class lingQuYouHuiQuan extends AbstractAction {
	public lingQuYouHuiQuan() {
	    putValue(NAME, "测试优惠券");
	}

	public void actionPerformed(ActionEvent e) {
	    if (window.textField_1.getText().length() == 0 || window.textField_1.getText().equals("https://")) {
		Document docs = window.textPane.getDocument();// 获得文本对象
		try {
		    docs.insertString(docs.getLength(), new Date().toLocaleString() + ":优惠券链接1为空\n",
			    new SimpleAttributeSet());
		} catch (BadLocationException e1) {
		    e1.printStackTrace();
		}
		System.out.println("优惠券链接1为空");
	    } else {
		qiangquan.qiangquan(window.textField_1.getText(),1);
	    }
	    if (window.textField.getText().length() == 0 || window.textField.getText().equals("https://")) {
		Document docs = window.textPane.getDocument();// 获得文本对象
		try {
		    docs.insertString(docs.getLength(), new Date().toLocaleString() + ":优惠券链接2为空\n",
			    new SimpleAttributeSet());
		} catch (BadLocationException e2) {
		    e2.printStackTrace();
		}
		System.out.println("优惠券链接2为空");
	    } else {
		qiangquan.qiangquan(window.textField.getText(),2);
	    }
	}
    }

    public static class dingShiQiangQuan extends AbstractAction {

	public static boolean flag = false;
	public static boolean interrupt = false;

	public dingShiQiangQuan() {
	    putValue(NAME, "定时抢券");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	    
	    if (!flag) { // 未启动
		interrupt = false;
		window.btnNewButton_1.setText("取消抢券");
		flag = true;
		int threadNum = (int) window.spinner_5.getValue();
		
		variables.Interval = (int) window.spinner_3.getValue();
		variables.countDownLatch = new CountDownLatch(1);
		variables.flag = true;
		for (int i = 0; i < threadNum; i++) {
		    variables.pool.execute(new qiangquan());
		}
		Date date = new Date(System.currentTimeMillis());
		date.setHours((int) window.spinner.getValue());
		date.setMinutes((int) window.spinner_1.getValue());
		date.setSeconds((int) window.spinner_2.getValue());
		// 本地时间
		// long delay = (date.getTime()-System.currentTimeMillis());
		// 网络时间
		variables.date = date;
		long delay = (date.getTime() - timeUtil.getNetworkTime());
		System.out.println(delay);
		if (delay <= 0) {
		    return;
		}
		timers.qiangquandingshi.setInitialDelay((int) delay);
		timers.qiangquandingshi.start();
		timers.daojishi.start();
		
	    } else {
		variables.flag = false;
		timers.daojishi.stop();
		window.label_13.setText("未定时");
		variables.date = null;
		interrupt = true;
		window.btnNewButton_1.setText("定时抢券");
		variables.countDownLatch.countDown();
		flag = false;
	    }
	}

    }
}
