package project2;
/**
 * Title: LogTransaction.java
 * Abstract: log transaction
 * for making new account, reservation
 *  or cancel reservation
 * Author: Arturo Lopez
 * ID:2412
 * Date: December, 12 2014
 */

public class LogTransaction {
	
	//Login Transaction
	private int    uniqueTransactionNum;
	private String trasType 	   = null;
	private String userName 	   = null;
	private String currentDateTime = null;
	
	//Reservation Transaction
	private String pickUpInfo = null;
	private String returnInfo = null;
	private String carType 	  = null;
	private int reservationNum = 0;
	private double totalAmount = 0;
	
	public LogTransaction(int uniqueNum, String userName, String currentDateTime, String trasType)
	{
		this.setUniqueTransactionNum(uniqueNum);
		this.setUserName(userName);
		this.setCurrentDateTime(currentDateTime);
		this.setTrasType(trasType);
	}
	
	public LogTransaction(int uniqueNum, String userName, String currentDateTime, String trasType,
			String pickUp,String returnInfo,String carType,int reserveNum,double totalAmt)
	{
		this.setUniqueTransactionNum(uniqueNum);
		this.setUserName(userName);
		this.setCurrentDateTime(currentDateTime);
		this.setTrasType(trasType);
		
		this.setPickUpInfo(pickUp);
		this.setReturnInfo(returnInfo);
		this.setCarType(carType);
		this.setReservationNum(reserveNum);
		this.setTotalAmount(totalAmt);
	}
	
	public LogTransaction(String userName, String carType, String pickUpTime, String returnTime, String transType,String currentTime)
	{
		this.setUserName(userName);
		this.setCarType(carType);
		this.setPickUpInfo(pickUpTime);
		this.setReturnInfo(returnTime);
		this.setTrasType(transType);
		this.setCurrentDateTime(currentTime);	
	}
	
	
	public int getUniqueTransactionNum() {
		return uniqueTransactionNum;
	}

	public void setUniqueTransactionNum(int uniqueTransactionNum) {
		this.uniqueTransactionNum = uniqueTransactionNum;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getCurrentDateTime() {
		return currentDateTime;
	}

	public void setCurrentDateTime(String currentDateTime) {
		this.currentDateTime = currentDateTime;
	}

	public String gettrasType() {
		return trasType;
	}

	public void setTrasType(String trasType) {
		this.trasType = trasType;
	}
	public String toString()
	{
		if(trasType.equals("New Account"))
		{
			
			return ("New Account- Name: " + userName + " Transaction Number : " + uniqueTransactionNum  + " Time created: " + currentDateTime); 
		}
		else if(trasType.equals("Reservation"))
		{
			return  (trasType + " Transaction number: " + uniqueTransactionNum + " Car Type " + carType + " Pick up Info " + pickUpInfo + " " + "Return Info " + returnInfo + 
					" Customer's Name: " + userName + " created " + currentDateTime);
		}
		else if(trasType.equals("cancellation"))
		{
			return("Cancellation - Name: " + userName + " Car type: " + carType + " Pickup Date/Time: " + pickUpInfo + " Return Date/Time: " + returnInfo + " Date Cancelled: " + currentDateTime);
		}
		return null;
	}

	public String getPickUpInfo() {
		return pickUpInfo;
	}

	public void setPickUpInfo(String pickUpInfo) {
		this.pickUpInfo = pickUpInfo;
	}

	public String getReturnInfo() {
		return returnInfo;
	}

	public void setReturnInfo(String returnInfo) {
		this.returnInfo = returnInfo;
	}

	public String getCarType() {
		return carType;
	}

	public void setCarType(String carType) {
		this.carType = carType;
	}

	public int getReservationNum() {
		return reservationNum;
	}

	public void setReservationNum(int reservationNum) {
		this.reservationNum = reservationNum;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}
}
