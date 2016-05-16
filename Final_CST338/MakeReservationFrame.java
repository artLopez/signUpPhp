package project2;
/**
 * Title: MakeReservationFrame.java
 * Abstract: Making reservation for vehicle
 * Author: Arturo Lopez
 * ID:2412
 * Date: December, 12 2014
 */
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;

class MakeReservationFrame extends JFrame
{
	public static boolean hide_minivan = true;
	public static boolean hide_sedan = true;
	public static boolean hide_truck = true;
	
	public MakeReservationFrame()
	{
		setTitle("Make Reservation");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		JPanel panel = new MakeReservationPanel();
		this.add(panel);
		this.setResizable(false);
		this.setSize(550, 200);
	}	
}

class MakeReservationPanel extends JPanel implements ActionListener
{
	private String[] description ={"December 01, 2014", "December 02, 2014", "December 03, 2014",
		      "December 04, 2014", "December 05, 2014", "December 06, 2014", "December 07, 2014", "December 08, 2014",
		      "December 09, 2014", "December 10, 2014", "December 11, 2014", "December 12, 2014", "December 13, 2014", 
		      "December 14, 2014", "December 15, 2014", "December 16, 2014", "December 17, 2014", "December 18, 2014", 
		      "December 19, 2014", "December 20, 2014", "December 21, 2014", "December 22, 2014", "December 23, 2014",
		      "December 24, 2014", "December 25, 2014", "December 26, 2014", "December 27, 2014", "December 28, 2014", 
		      "December 29, 2014", "December 30, 2014", "December 31, 2014"};
	
	private String[] time = {"AM","PM"};
	private String pickUpPT   = null;
	private String returnPT   = null;
	private String pickUpDate = null;
	private String returnDate = null;
	private String pickUpMin  = null;
	private String returnMin  = null;
	
	private int pickedIndex = 0;
	private int returnedIndex  = 0;
	
	private JLabel 			 pickUpLabel,
				   			 returnVecLabel,
				   			 pickUpTimeLabel,
				   			 returnTimeLabel;
	
	private JSpinner 		 pickUpHour,
							 pickUpMinutes,
				 	 		 returnHour,
				 	 		 returnMinutes;
	
	private JButton 		 submit,cancel;

	public MakeReservationPanel()
	{
		this.setLayout(new GridBagLayout());
		
		final JComboBox<String> rentOut    		 = new JComboBox<String>(description);
		final JComboBox<String> returnVec  		 = new JComboBox<String>(description);
		final JComboBox<String> pickUpPeriodTime = new JComboBox<String>(time);
		final JComboBox<String> returnPeriodTime = new JComboBox<String>(time);
	
		SpinnerModel hour 		= new SpinnerNumberModel(12, 1, 12, 1);
		SpinnerModel minutes 	= new SpinnerNumberModel(00, 00, 59, 1);
		SpinnerModel returnHr 	= new SpinnerNumberModel(12, 1, 12, 1);
		SpinnerModel returnMin  = new SpinnerNumberModel(00, 00, 59, 1);
		
		pickUpDate = description[0];
		returnDate = description[0];
		pickUpPT   = time[0];
		returnPT   = time[0];
		 
		pickUpLabel     = new JLabel("Pick up:");
		returnVecLabel  = new JLabel("Return:");
		pickUpTimeLabel = new JLabel("Time: ");
		returnTimeLabel = new JLabel("Time: ");
		
		pickUpHour 	    = new JSpinner(hour);
		pickUpMinutes   = new JSpinner(minutes);
		returnHour      = new JSpinner(returnHr);
		returnMinutes   = new JSpinner(returnMin);
		submit 		    = new JButton("Submit");
		cancel 			= new JButton("Cancel");
		cancel.setPreferredSize(new Dimension(67,30));
		
		submit.addActionListener(this);
		cancel.addActionListener(this);
			
		rentOut.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				pickUpDate = (String) rentOut.getSelectedItem();
				pickedIndex = rentOut.getSelectedIndex();
				InfoSingleton.getConfirm().setPickIndex(rentOut.getSelectedIndex());
			}
		});
		
		returnVec.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				returnDate = (String) returnVec.getSelectedItem();
				returnedIndex = returnVec.getSelectedIndex();
				InfoSingleton.getConfirm().setReturnIndex(returnVec.getSelectedIndex());
			}
		});
		
		pickUpPeriodTime.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				pickUpPT = (String) pickUpPeriodTime.getSelectedItem();
			}
		});
		
		returnPeriodTime.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				returnPT = (String) returnPeriodTime.getSelectedItem();
			}
		});

		this.add(pickUpLabel,	  getConstraints(0, 0, 1, 1, GridBagConstraints.WEST));
		this.add(rentOut, 		  getConstraints(1, 0, 1, 1, GridBagConstraints.WEST));
		this.add(pickUpTimeLabel, getConstraints(2, 0, 1, 1, GridBagConstraints.WEST));
		this.add(pickUpHour, 	  getConstraints(3, 0, 1, 1, GridBagConstraints.WEST));
		this.add(pickUpMinutes,	  getConstraints(4, 0, 1, 1, GridBagConstraints.WEST));
		this.add(pickUpPeriodTime,getConstraints(5, 0, 1, 1, GridBagConstraints.WEST));
		
		this.add(returnVecLabel,  getConstraints(0, 1, 1, 1, GridBagConstraints.WEST));
		this.add(returnVec, 	  getConstraints(1, 1, 1, 1, GridBagConstraints.WEST));
		this.add(returnTimeLabel, getConstraints(2, 1, 1, 1, GridBagConstraints.WEST));
		this.add(returnHour,	  getConstraints(3, 1, 1, 1, GridBagConstraints.WEST));
		this.add(returnMinutes,	  getConstraints(4, 1, 1, 1, GridBagConstraints.WEST));
		this.add(returnPeriodTime,getConstraints(5, 1, 1, 1, GridBagConstraints.WEST));
		
		this.add(submit,getConstraints(5, 4, 1, 1, GridBagConstraints.WEST));	
		this.add(cancel,getConstraints(4, 4, 1, 1, GridBagConstraints.WEST));
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		Object source = e.getSource();
		
		if(source == submit)
		{
			if(pickedIndex > returnedIndex)
			{
				JOptionPane.showMessageDialog(null, "Invalid selection.");
			}
			else
			{
				backToMain();
				
				pickUpMin = pickUpMinutes.getValue().toString();
				returnMin = (String) returnMinutes.getValue().toString();
				
				if(pickUpMin.length() == 1)
					pickUpMin = "0" + pickUpMin;
				if(returnMin.length() == 1)
					returnMin = "0" + returnMin;
				
				JOptionPane.showMessageDialog(null, pickUpDate + " " + pickUpHour.getValue() + ":" + pickUpMin + "" + pickUpPT
						+ "\n " + returnDate + " " + returnHour.getValue() + ":" + returnMin + returnPT);
				
				
				InfoSingleton.getConfirm().setPickUpDateTime(pickUpDate + " " + pickUpHour.getValue() + ":" + pickUpMin + pickUpPT);
				InfoSingleton.getConfirm().setReturnDateTime(returnDate + " " + returnHour.getValue() + ":" + returnMin + returnPT);
				
				
				int currentPickUpDay = Integer.parseInt(pickUpDate.substring(9, 11));
				int currentReturnDay = Integer.parseInt(returnDate.substring(9, 11));
				
				
				
				for (LogTransaction p : InfoSingleton.getLogs())
				{
					if(p.gettrasType().equals("Reservation"))
					{
						System.out.println(p.getCarType() + " " + p.getPickUpInfo() + " " + p.getReturnInfo());
						
						int pickTime = Integer.parseInt(p.getPickUpInfo().substring(9, 11));
						int returnTime = Integer.parseInt(p.getReturnInfo().substring(9, 11));
						
						System.out.println(pickTime + " " + returnTime);
						
						
						
						if(!(pickTime <= currentPickUpDay && currentPickUpDay <= pickTime  && returnTime <  currentReturnDay))
						{
							if(p.getCarType().equals("Minivan"))
								MakeReservationFrame.hide_minivan = false;
							else if(p.getCarType().equals("Sedan"))
								MakeReservationFrame.hide_sedan = false;	
							else
								MakeReservationFrame.hide_truck = false;
						}
						
						if(!(currentPickUpDay < pickTime && returnTime < currentReturnDay))
						{
							if(p.getCarType().equals("Minivan"))
								MakeReservationFrame.hide_minivan = false;
							else if(p.getCarType().equals("Sedan"))
								MakeReservationFrame.hide_sedan = false;	
							else
								MakeReservationFrame.hide_truck = false;
							
						}
					}
				}
				JFrame frame = new CarSelectionFrame();
		        frame.setVisible(true);
			}
		}
		if(source == cancel)
			backToMain();
	}
	// Goes back to the main menu frame
	public void backToMain(){
		(SwingUtilities.getWindowAncestor(this)).dispose(); 
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