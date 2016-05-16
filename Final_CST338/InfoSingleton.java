package project2;
/**
 * Title: InfoSingleton.java
 * Abstract: Singleton for data structures
 * Author: Arturo Lopez
 * ID:2412
 * Date: December, 12 2014
 */
import java.util.ArrayList;
import java.util.HashMap;

public class InfoSingleton {
	 private static HashMap<String,String> accounts;
	 private static ArrayList<LogTransaction> logs;
	 private static HashMap<String, ArrayList<LogTransaction>> reservation;
	 private static tempConfirmation confirm;
	 
	 public boolean isEmpty() {
	        return true;
	 }

	 public static HashMap<String, String> getAccounts()
	 {
	    	if(accounts == null)
	    		accounts = new HashMap<String,String>();
	   
	    	return accounts;
	 }
	 
	 public static HashMap<String,ArrayList<LogTransaction>> getUserTrans()
	 {
		 if(reservation == null)
			 reservation = new HashMap<String, ArrayList<LogTransaction>>();
		 
		 return reservation;
	}
	 
	 public static ArrayList<LogTransaction> getLogs()
	 {
	    	if(logs == null)
	    		logs = new ArrayList<LogTransaction>();

	    	return logs;
	 }
	 
	 public static tempConfirmation getConfirm()
	 {
		 if(confirm == null)
		 {
			 confirm = new tempConfirmation();
		 }
		 return confirm;
	 }
	 
}
