package jdAssitant;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.concurrent.CountDownLatch;

import javax.swing.JScrollBar;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.SimpleAttributeSet;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;

public class qiangquan implements Runnable {
    public static void qiangquan(String url, int cpuponNum) {
	HttpGet httpGet = new HttpGet(url);
	httpGet.addHeader("User-Agent",
		"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/66.0.3359.181 Safari/537.36");
	try {
	    // 执行请求
	    CloseableHttpResponse response = variables.httpClient.execute(httpGet);
	    // System.out.println(variables.cookieStore.toString());
	    // 获得响应的实体对象
	    HttpEntity entity = response.getEntity();
	    /*
	     * 获取到响应信息的流
	     */
	    InputStream is = entity.getContent();

	    InputStreamReader isr = new InputStreamReader(is, "utf-8");
	    BufferedReader br = new BufferedReader(isr);
	    StringBuffer buffer = new StringBuffer();
	    String line;
	    while ((line = br.readLine()) != null) {
		buffer.append(line);
	    }
	    Document docs = window.textPane.getDocument();// 获得文本对象
	    try {
		String resStr = buffer.toString();
		StringBuilder stringBuilder = new StringBuilder(new Date().toLocaleString());
		stringBuilder.append(Thread.currentThread().getName());
		stringBuilder.append(":优惠券链接").append(cpuponNum).append("结果：-----------");
		if (resStr.contains("您今天已经参加过此活动，别太贪心哟，明天再来~")) {
		    stringBuilder.append("您今天已经参加过此活动，别太贪心哟，明天再来~-----------\n");
		} else if (resStr.contains("恭喜您，抢到")) {
		    stringBuilder.append("恭喜您，抢到优惠券~----------\n");
		} else if (resStr.contains("您来早了，活动还没开始哟，请稍后再来~")) {
		    stringBuilder.append("您来早了，活动还没开始哟，请稍后再来~----------\n");
		} else {
		    stringBuilder.append("请登录----------\n");
		}
		docs.insertString(docs.getLength(), stringBuilder.toString(), new SimpleAttributeSet());
	    } catch (BadLocationException e) {
		e.printStackTrace();
	    }

	    // 关闭流
	    br.close();
	    isr.close();
	    is.close();
	} catch (ClientProtocolException e1) {
	    // TODO Auto-generated catch block
	    e1.printStackTrace();
	} catch (IOException e1) {
	    // TODO Auto-generated catch block
	    e1.printStackTrace();
	}
    }

    @Override
    public void run() {
	try {
	    String url1 = window.textField_1.getText(); // 优惠券链接1
	    String url2 = window.textField.getText(); // 优惠券链接2
	    boolean f1 = true, f2 = true;
	    if (url1.length() == 0) {
		f1 = false;
	    }
	    if (url2.length() == 0) {
		f2 = false;
	    }
	    
	    variables.countDownLatch.await();
	    if(timers.daojishi.isRunning()) {
		timers.daojishi.stop();
		window.label_13.setText("已开始");
		variables.date = null;
	    }
	    while (variables.flag) {
		Document docs = window.textPane.getDocument();// 获得文本对象
		try {

		    if (f1) {
			qiangquan(url1, 1);
		    } else {
			docs.insertString(docs.getLength(), new Date().toLocaleString() + ":优惠券链接1为空\n",
				new SimpleAttributeSet());
		    }
		    if (f2) {
			qiangquan(url2, 2);
		    } else {
			docs.insertString(docs.getLength(), new Date().toLocaleString() + ":优惠券链接2为空\n",
				new SimpleAttributeSet());
		    }
		} catch (BadLocationException e) {
		    e.printStackTrace();
		}
		JScrollBar jscrollBar = window.jsp.getVerticalScrollBar();
		jscrollBar.setValue(jscrollBar.getMaximum());
		Thread.sleep(variables.Interval);
	    }
	} catch (InterruptedException e) {
	    e.printStackTrace();
	}

    }
}
