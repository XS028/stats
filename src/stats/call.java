package stats;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class call {
	public static JSONObject ticker = new JSONObject();
	public static int errcount = 0;
	
	public static void callapi(String urladdr) {
		int cangonext = 0;
		HttpURLConnection connection = null;
		try{
			URL url = new URL(urladdr);
			connection = (HttpURLConnection)url.openConnection();
			connection.setRequestMethod("POST");
			cangonext = 1;
		} catch (Exception e) {
		e.printStackTrace();
		errcount++;
		connection.disconnect();
		system.sleep(errcount);
		if(errcount<=10){callapi(urladdr);errcount = 0;}else{io.t();}
		}
		if (cangonext==1){
			connection.setRequestProperty("Content-Language","en-US"); 
			connection.setRequestProperty("User-Agent","Mozilla/5.0 (Windows NT 5.1) AppleWebKit/535.11 (KHTML, like Gecko) Chrome/17.0.963.56 Safari/535.11");
			connection.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
			connection.setConnectTimeout(60000);
			connection.setReadTimeout(60000);
			connection.setUseCaches(false);
			connection.setDoInput(true);
			connection.setDoOutput(false);
		}
		JSONObject js = new JSONObject();
		try{
			InputStream is = connection.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			String line = br.readLine();
			JSONParser parser = new JSONParser();
			Object obj = new Object();
			try {
				obj = parser.parse(line);
			} catch (ParseException e) {
				e.printStackTrace();
				errcount++;
				connection.disconnect();
				system.sleep(errcount);
				if(errcount<=10){callapi(urladdr);errcount = 0;}else{io.t();}
			}
			js = (JSONObject) obj;
			cangonext = 2;
		} catch (Exception e) {
			e.printStackTrace();
			errcount++;
			connection.disconnect();
			system.sleep(errcount);
			if(errcount<=10){callapi(urladdr);errcount = 0;}else{io.t();}
		}
		if(cangonext==2){include(js,urladdr);}
	}

	/// json include
	public static void include(JSONObject info, String urladdr){
		try{
			ticker = (JSONObject) info;
		}catch(Exception e){
			e.printStackTrace();
			errcount++;
			system.sleep(errcount);
			if(errcount<=10){callapi(urladdr);errcount = 0;}else{io.t();}
		}
	}
}//
