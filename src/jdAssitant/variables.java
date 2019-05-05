package jdAssitant;

import java.io.File;
import java.util.Date;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.http.HttpEntity;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class variables {

    //线程池
    static ExecutorService pool = Executors.newFixedThreadPool(10);
    
    // Cookies
    static CookieStore cookieStore = new BasicCookieStore();
    static File cookieFile = null;
    /////////////////////////////////////
    // 获取连接客户端工具
    static CloseableHttpClient httpClient = HttpClients.custom().setDefaultCookieStore(cookieStore).build();
    static CloseableHttpResponse response = null;
    static HttpGet httpGet = null;
    // 响应实体
    static HttpEntity entity = null;
    
    //是否继续抢券标志
    static boolean flag = true;
    
    //
    static CountDownLatch countDownLatch = null;
    
    static CountDown cd = null;
    
    //抢券间隔
    static int Interval = 50;
    //抢券持续时间
    static int duration = 0;
    static int remainSec = 0;
    
    
    //cookie path
    static String cookiePath = null;
    //抢券次数
    
    //抢券开始时间
    static Date date = null;

}
