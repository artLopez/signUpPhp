package project2;

/**
 * Title: ReservationSystem.java
 * Abstract: CSUMB Reservation system 
 * for vehicles.
 * Author: Arturo Lopez
 * ID:2412
 * Date: December, 12 2014
 */
import javax.swing.JFrame;

public class ReservationSystem {
	public static int ctr = 1;
	public static boolean is_cancel = true;
	public static int resNumber = 1;
	
	public static void main(String[] args) {
		JFrame frame = new CarSystem();
		frame.setVisible(true);
	}	
}




