package project2;
/**
 * Title: LoginFrame.java
 * Abstract: login information
 * for making reservation or cancel reservation
 * Author: Arturo Lopez
 * ID:2412
 * Date: December, 12 2014
 */
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

class LogInFrame extends JFrame{
	public LogInFrame() {
		setTitle("Login to System");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		JPanel panel = new LoginPanel();
		this.add(panel);
		this.setResizable(false);
		this.setSize(350, 200);
	}
}

class LoginPanel extends JPanel implements ActionListener{
	private JButton submitButton, cancelButton;
	private JTextField userName;
	private JPasswordField password;
	private JLabel userNameLabel, passwordLabel;
	private String userNameString, passwordString;
	private String value;
	private boolean did_try = false;

	public LoginPanel()
	{
		this.setLayout(new GridBagLayout());
		
		submitButton  = new JButton("Submit");
		cancelButton  = new JButton("Cancel");
		userName 	  = new JTextField(10);
		password	  = new JPasswordField(10);
		userNameLabel = new JLabel("Username: ");
		passwordLabel = new JLabel("Password: ");
		
		submitButton.addActionListener(this);
		cancelButton.addActionListener(this);
		
		this.add(userNameLabel,getConstraints(0, 0, 1, 1, GridBagConstraints.WEST));
		this.add(userName,	   getConstraints(1, 0, 1, 1, GridBagConstraints.WEST));
		this.add(passwordLabel,getConstraints(0, 1, 1, 1, GridBagConstraints.WEST));
		this.add(password,	   getConstraints(1, 1, 1, 1, GridBagConstraints.WEST));
		this.add(submitButton, getConstraints(0, 2, 1, 1, GridBagConstraints.WEST));
		this.add(cancelButton, getConstraints(1, 2, 1, 1, GridBagConstraints.WEST));
	}		
	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if(source == cancelButton)
		{
			(SwingUtilities.getWindowAncestor(this)).dispose();
		}
		if(source == submitButton)
		{
			userNameString = userName.getText();
			passwordString = password.getText();
			value = InfoSingleton.getAccounts().get(userNameString);
			
			if(ReservationSystem.is_cancel && userNameString.equals("admin2"))
			{
				if(passwordString.equals("admin2"))
				{
					JOptionPane.showMessageDialog(null, "Manage system");
					JFrame frames = new AdminFrame();
					frames.setVisible(true);
					(SwingUtilities.getWindowAncestor(this)).dispose();
				}
				else
				{
					if(did_try)
					{
						JOptionPane.showMessageDialog(null, "Invalid cancel user name or password.", "Error", JOptionPane.ERROR_MESSAGE);
						(SwingUtilities.getWindowAncestor(this)).dispose();
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Operator: Invalid information entered!");
						did_try = true;
						userName.setText("");
						password.setText("");
					}
				}
			}
			else if(ReservationSystem.is_cancel)
			{
				if(value != null && value.equals(passwordString))
				{
					JOptionPane.showMessageDialog(null, "valid cancel user name and password!");
					 CancelReservationFrame.setUserName(userNameString);
					 
					 (SwingUtilities.getWindowAncestor(this)).dispose();
					 JFrame frame = new CancelReservationFrame(userNameString);
			         frame.setVisible(true);
				}
				else
				{
					if(did_try)
					{
						JOptionPane.showMessageDialog(null, "Invalid cancel user name or password.", "Error", JOptionPane.ERROR_MESSAGE);
						(SwingUtilities.getWindowAncestor(this)).dispose();
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Invalid cancel user name or password!");
						did_try = true;
						userName.setText("");
						password.setText("");
					}				
				}	
			}
			else
			{
				if(userNameString.equals("admin2"))
				{
					JOptionPane.showMessageDialog(null, "Invalid user name or password");
					did_try = true;
					userName.setText("");
					password.setText("");
				}
				else
				{
					if(value != null && value.equals(passwordString))
					{
						(SwingUtilities.getWindowAncestor(this)).dispose();
						JOptionPane.showMessageDialog(null, "valid user name and password!");
						
						DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm a");
						Calendar cal = Calendar.getInstance();
						String time = dateFormat.format(cal.getTime());
						//strings for confirm object data
						String pickUpDateTime = InfoSingleton.getConfirm().getPickUpDateTime();
						String returnDateTime = InfoSingleton.getConfirm().getReturnDateTime();
						String carType = InfoSingleton.getConfirm().getCarType();
						double total =  InfoSingleton.getConfirm().getTotal();
								
						String printOut = " Reservation- Name: " + userNameString + " Transaction number: " + ReservationSystem.ctr  + " Reservation Created" + time +  "\n Pick up date: " + pickUpDateTime + " "
								+ " Return date: " + returnDateTime + " Vehicle: " + carType  + " Reservation number: " + ReservationSystem.resNumber + " Total: $" + total;
						
						System.out.println(total);
						
						JOptionPane.showMessageDialog(null, printOut);
						
						InfoSingleton.getLogs().add(new LogTransaction(ReservationSystem.ctr, userNameString, time, "Reservation", pickUpDateTime 
								, returnDateTime, carType, ReservationSystem.resNumber,total));
						
						if(InfoSingleton.getUserTrans().containsKey(userNameString))
						{
							InfoSingleton.getUserTrans().get(userNameString).add(new LogTransaction(ReservationSystem.ctr, userNameString, time, "Reservation", pickUpDateTime 
									, returnDateTime, carType, ReservationSystem.resNumber,total));
						}
						else
						{
							InfoSingleton.getUserTrans().put(userNameString, new ArrayList<LogTransaction>());
							InfoSingleton.getUserTrans().get(userNameString).add(new LogTransaction(ReservationSystem.ctr, userNameString, time, "Reservation", pickUpDateTime 
									, returnDateTime, carType, ReservationSystem.resNumber,total));	
						}
						
						ReservationSystem.ctr++;
						ReservationSystem.resNumber++;
					}
					else 
					{
						if(did_try)
						{
							JOptionPane.showMessageDialog(null, "Invalid user name or password.You will be taken back to the main menu.", "Error", JOptionPane.ERROR_MESSAGE);
							(SwingUtilities.getWindowAncestor(this)).dispose();
						}
						else
						{
							JOptionPane.showMessageDialog(null, "Invalid user name or password");
							did_try = true;
							userName.setText("");
							password.setText("");
						}				
					 }	
				  }
			  }
		 }
	}	
		   // a  method for setting grid bag constraints
	private GridBagConstraints getConstraints(int gridx, int gridy,
	                                              int gridwidth, int gridheight, 
	                                              int anchor)
	    {
	        GridBagConstraints c = new GridBagConstraints();
	        c.insets = new Insets(5, 5, 5, 5);
	        c.ipadx = 0;
	        c.ipady = 0;
	        c.gridx = gridx;
	        c.gridy = gridy;
	        c.gridwidth = gridwidth;
	        c.gridheight = gridheight;
	        c.anchor = anchor;
	        return c;
	    }
}