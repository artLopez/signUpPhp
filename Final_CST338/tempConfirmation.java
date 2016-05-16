package project2;
/**
 * Title: tempConfirmation.java
 * Abstract: Temporary class for 
 * making a reservation.
 * Author: Arturo Lopez
 * ID:2412
 * Date: December, 12 2014
 */


public class tempConfirmation {
	private int pickIndex   = 0;
	private int returnIndex = 0;
	private String pickUpDateTime = null;
	private String returnDateTime = null;
	private String carType 		  = null;
	private double total = 0;
	
	public tempConfirmation() {
		// TODO Auto-generated constructor stub
		pickIndex   = 0;
		returnIndex = 0;
		pickUpDateTime = null;
		returnDateTime = null;
		carType 	   = null;
		total = 0;		
	}

	public int getPickIndex() {
		return pickIndex;
	}
	public void setPickIndex(int pickIndex) {
		this.pickIndex = pickIndex;
	}
	public int getReturnIndex() {
		return returnIndex;
	}
	public void setReturnIndex(int returnIndex) {
		this.returnIndex = returnIndex;
	}
	public String getPickUpDateTime() {
		return pickUpDateTime;
	}
	public void setPickUpDateTime(String pickUpDateTime) {
		this.pickUpDateTime = pickUpDateTime;
	}
	public String getReturnDateTime() {
		return returnDateTime;
	}
	public void setReturnDateTime(String returnDateTime) {
		this.returnDateTime = returnDateTime;
	}
	public String getCarType() {
		return carType;
	}
	public void setCarType(String carType) {
		this.carType = carType;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
}
