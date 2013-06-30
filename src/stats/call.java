package stats;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Hex;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class call {
	public static JSONObject ticker = new JSONObject();
	
	public static String _key = "";
	public static String _secret = "";
	static long nonce = 0;
	public static int errcount = 0;
	
	public static void callapi(String urladdr, String method) {
		
			int cangonext = 0;
			
			HttpURLConnection connection = null;
	    	
	    	try{
	    	URL url = new URL(urladdr);
	     	connection = (HttpURLConnection)url.openConnection();
	    	connection.setRequestMethod("POST");
	    	cangonext = 1;
	    	} catch (IOException e) {
				e.printStackTrace();
				errcount++;
				connection.disconnect();
				system.sleep(errcount);
				if(errcount<=10){callapi(urladdr,method);errcount = 0;}else{io.t();}
			} catch (Exception e) {
				e.printStackTrace();
				errcount++;
				connection.disconnect();
				system.sleep(errcount);
				if(errcount<=10){callapi(urladdr,method);errcount = 0;}else{io.t();}
			}
	    	if (cangonext==1){
	    	
	    	connection.setRequestProperty("Content-Language","en-US"); 
		    connection.setRequestProperty("User-Agent","Mozilla/5.0 (Windows NT 5.1) AppleWebKit/535.11 (KHTML, like Gecko) Chrome/17.0.963.56 Safari/535.11");
	    	connection.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
	        connection.setConnectTimeout(60000);
	    	connection.setReadTimeout(60000);
   
	        if (method.equals("")){
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
				}
				
	        	js = (JSONObject) obj;
	        	cangonext = 2;
	        	}catch (IOException e) {
	        		e.printStackTrace();
	        		errcount++;
					connection.disconnect();
					system.sleep(errcount);
					if(errcount<=10){callapi(urladdr,method);errcount = 0;}else{io.t();}
	        	} catch (Exception e) {
	        		e.printStackTrace();
	        		errcount++;
					connection.disconnect();
					system.sleep(errcount);
					if(errcount<=10){callapi(urladdr,method);errcount = 0;}else{io.t();}
				}
	        if(cangonext==2){
	        	include(js,urladdr,method);
	   }}}
	
	/// json include
	public static void include(JSONObject info, String urladdr, String method){
		int invalidnonce = 0;
		
		if (info.containsKey("error")){
			if (info.get("error").equals("invalid nonce parameter")){
				invalidnonce = 1;
				errcount++;
				system.sleep(errcount);
				if(errcount<=10){callapi(urladdr,method);errcount = 0;}else{io.t();}
			}
		}
		
		if (invalidnonce==0){
			ticker = (JSONObject) info;
		}
	}

	   ///// HMAC sha512
	   public static String gethash(String method){
	    	String nonces = new Long(nonce).toString();
	    	String datas = "nonce="+nonces+"&method="+method;
	        Mac mac;
	        String result = "";
	        try
	        {
				SecretKeySpec secretKey = new SecretKeySpec(_secret.getBytes("UTF-8"),"HmacSHA512"); 
	            mac = Mac.getInstance("HmacSHA512");
	            mac.init(secretKey);
	            final byte[] macData = mac.doFinal(datas.getBytes());
	            byte[] hex = new Hex().encode(macData);
	            result = new String(hex, "ISO-8859-1");
	        }
	        catch (final Exception e)
	        {
	        	e.printStackTrace();
	        }
	        return result;
	    }

	    /// get keys
	    public static void getkeys() {
	    	Scanner sc;
			try {
				sc = new Scanner(new File("C:/stats/keys"));
		        	 _key = sc.next();
		        	 sc.nextLine();
		        	 _secret = sc.next();
		        	 sc.close();
			} catch (FileNotFoundException e) {
				File file = new File("C:/stats/keys");
				try {
					e.printStackTrace();
					file.createNewFile();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
	    }
}//