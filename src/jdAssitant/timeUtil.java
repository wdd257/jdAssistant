package jdAssitant;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;

public class timeUtil {
    public static long getNetworkTime() {
	try {
	    String webUrl = "http://www.jd.com";
	    URL url = new URL(webUrl);
	    URLConnection conn = url.openConnection();
	    conn.connect();
	    long dateL = conn.getDate();
	    Date date = new Date(dateL);
	    SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
	    return date.getTime();
	} catch (MalformedURLException e) {
	    e.printStackTrace();
	} catch (IOException e) {
	    e.printStackTrace();
	}
	return 0;
    }

}
