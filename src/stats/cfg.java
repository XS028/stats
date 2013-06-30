package stats;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class cfg{
	
	public static void readcfg(){
		Scanner sc;
		try {
			sc = new Scanner(new File("C:/stats/config.ini"));
			sc.nextLine();
			stats.slp_time=Integer.parseInt(sc.next());
			sc.close();
		}catch(FileNotFoundException e){e.printStackTrace();} 
		catch(Exception e){e.printStackTrace();}
	}

}//