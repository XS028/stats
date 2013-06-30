package stats;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import org.json.simple.JSONObject;

public class stats{
	
	public static Dimension sSize = Toolkit.getDefaultToolkit().getScreenSize();
	public static int ww = sSize.width;;
	public static int wh = sSize.height;;
	public static ui gui = new ui();	//draw GUI
	
	public static String publicapiurl = "https://btc-e.com/api/2/";
	public static String publicapiv3url = "https://btc-e.com/api/3/";
	public static String privateapiurl = "https://btc-e.com/tapi";
	public static String add = "ticker/btc_usd-btc_rur-btc_eur-ltc_btc-ltc_usd-ltc_rur-nmc_btc-nvc_btc-usd_rur-eur_usd-trc_btc-ppc_btc-ftc_btc";
	
	public static int slp_time = 60*1; // sec
	
	public static String[] st = new String[500];
	public static String[] before = new String[10];//9
	public static String[] after = new String[14];//13

	public static Timestamp timest = new Timestamp(System.currentTimeMillis());
	public static String filename = "C:/stats/ready/"+timest.toString().replace(' ', '.').replace(":", "-")+".html";
	
	public static int wannaexit = 0;
	public static int sleeping = 0;
	
	public static int totalsteps = 0;
	
	public static void main(String[] args){
		
		cfg.readcfg();
		init.initba();
		
		int i=1;
		JSONObject tmp = new JSONObject();
		
		call.callapi(publicapiv3url+add, "");
		
		tmp=(JSONObject) call.ticker.get("btc_usd");
		double d1 = (1/Double.parseDouble(tmp.get("sell").toString()));
		tmp=(JSONObject) call.ticker.get("btc_rur");
		double d2 = (1/Double.parseDouble(tmp.get("sell").toString()));
		tmp=(JSONObject) call.ticker.get("btc_eur");
		double d3 = (1/Double.parseDouble(tmp.get("sell").toString()));
		tmp=(JSONObject) call.ticker.get("ltc_btc");
		double d4 = (1/Double.parseDouble(tmp.get("sell").toString()));
		tmp=(JSONObject) call.ticker.get("ltc_usd");
		double d5 = (1/Double.parseDouble(tmp.get("sell").toString()));
		tmp=(JSONObject) call.ticker.get("ltc_rur");
		double d6 = (1/Double.parseDouble(tmp.get("sell").toString()));
		tmp=(JSONObject) call.ticker.get("nmc_btc");
		double d7 = (1/Double.parseDouble(tmp.get("sell").toString()));
		tmp=(JSONObject) call.ticker.get("nvc_btc");
		double d8 = (1/Double.parseDouble(tmp.get("sell").toString()));
		tmp=(JSONObject) call.ticker.get("usd_rur");
		double d9 = (1/Double.parseDouble(tmp.get("sell").toString()));
		tmp=(JSONObject) call.ticker.get("eur_usd");
		double d10 = (1/Double.parseDouble(tmp.get("sell").toString()));
		tmp=(JSONObject) call.ticker.get("trc_btc");
		double d11 = (1/Double.parseDouble(tmp.get("sell").toString()));
		tmp=(JSONObject) call.ticker.get("ppc_btc");
		double d12 = (1/Double.parseDouble(tmp.get("sell").toString()));
		tmp=(JSONObject) call.ticker.get("ftc_btc");
		double d13 = (1/Double.parseDouble(tmp.get("sell").toString()));

		while (true){
			if (wannaexit==0){

			if(i>1){if (wannaexit==0){st[i-1] = st[i-1]+",";}call.callapi(publicapiv3url+add, "");}
			
			tmp=(JSONObject) call.ticker.get("btc_usd");
			st[i] = "['"+i+"', "+system.rv7dstr(Double.parseDouble(tmp.get("sell").toString())*d1);
			tmp=(JSONObject) call.ticker.get("btc_rur");
			st[i] = st[i]+", "+system.rv7dstr(Double.parseDouble(tmp.get("sell").toString())*d2);
			tmp=(JSONObject) call.ticker.get("btc_eur");
			st[i] = st[i]+", "+system.rv7dstr(Double.parseDouble(tmp.get("sell").toString())*d3);
			tmp=(JSONObject) call.ticker.get("ltc_btc");
			st[i] = st[i]+", "+system.rv7dstr(Double.parseDouble(tmp.get("sell").toString())*d4);
			tmp=(JSONObject) call.ticker.get("ltc_usd");
			st[i] = st[i]+", "+system.rv7dstr(Double.parseDouble(tmp.get("sell").toString())*d5);
			tmp=(JSONObject) call.ticker.get("ltc_rur");
			st[i] = st[i]+", "+system.rv7dstr(Double.parseDouble(tmp.get("sell").toString())*d6);;
			tmp=(JSONObject) call.ticker.get("nmc_btc");
			st[i] = st[i]+", "+system.rv7dstr(Double.parseDouble(tmp.get("sell").toString())*d7);
			tmp=(JSONObject) call.ticker.get("nvc_btc");
			st[i] = st[i]+", "+system.rv7dstr(Double.parseDouble(tmp.get("sell").toString())*d8);
			tmp=(JSONObject) call.ticker.get("usd_rur");
			st[i] = st[i]+", "+system.rv7dstr(Double.parseDouble(tmp.get("sell").toString())*d9);
			tmp=(JSONObject) call.ticker.get("eur_usd");
			st[i] = st[i]+", "+system.rv7dstr(Double.parseDouble(tmp.get("sell").toString())*d10);
			tmp=(JSONObject) call.ticker.get("trc_btc");
			st[i] = st[i]+", "+system.rv7dstr(Double.parseDouble(tmp.get("sell").toString())*d11);
			tmp=(JSONObject) call.ticker.get("ppc_btc");
			st[i] = st[i]+", "+system.rv7dstr(Double.parseDouble(tmp.get("sell").toString())*d12);
			tmp=(JSONObject) call.ticker.get("ftc_btc");
			st[i] = st[i]+", "+system.rv7dstr(Double.parseDouble(tmp.get("sell").toString())*d13)+"]";

			io.p(st[i]);
			ui.text.setText("");
			ui.text.append("Collected now: "+i+"\n");
			ui.text.append("Time period (sec): "+slp_time);
			totalsteps = i;
			i++;
			}
			
			if(wannaexit==1){exit();io.t();}
			
			sleeping = 1;
			system.sleep(slp_time);
			sleeping = 0;

		}// while

	}
	
	public static void exit(){
	    	PrintWriter out;
			try {
				File file = new File(filename);
				if (file.exists()){file.delete(); file.createNewFile();}
				out = new PrintWriter(filename);
				for (int i=1; i<before.length; i++){out.println(before[i]);}
				out.println();
				for (int i=1; i<=totalsteps; i++){out.println(st[i]);}
				out.println();
				for (int i=1; i<after.length; i++){out.println(after[i]);}
		    	out.close();
		    	
		    	String[] cmd = {"cmd", "/C", filename};
		    	Runtime runtime = Runtime.getRuntime();
		    	runtime.exec(cmd);
		    	
			}  catch (IOException e1) {
					e1.printStackTrace();
			}
	}
	
}//