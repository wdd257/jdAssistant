package jdAssitant;

import java.util.concurrent.TimeUnit;

public class CountDown extends Thread {
    @Override
    public void run() {
	int limitSec = 7;// (int) window.spinner_4.getValue();
	System.out.println("Count from " + limitSec);

	try {
	    while (limitSec > 0) {
		System.out.println("remians " + --limitSec + " s");
		if (actions.dingShiQiangQuan.interrupt) {
		    System.out.println("线程已经结束，我要退出");
		    throw new InterruptedException();
		}
		TimeUnit.SECONDS.sleep(1);
		System.out.println(limitSec);
	    }
	} catch (InterruptedException e) {
	    e.printStackTrace();
	}
	variables.flag = false;
	window.btnNewButton_1.setText("抢券结束");
	System.out.println("Time is out");

    }
}