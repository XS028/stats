package stats;

import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

public class system{
	
	public static void sleep(int sec){
    	try {
			TimeUnit.SECONDS.sleep(sec);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    }
	
    public static String rv7dstr(double in){// для массива значений
    	BigDecimal bigDecimal = new BigDecimal(in);
    	bigDecimal = bigDecimal.setScale(7, BigDecimal.ROUND_HALF_UP);
    	return bigDecimal.toPlainString();
    }

}