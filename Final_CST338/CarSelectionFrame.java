package project2;
/**
 * Title: CancelSelectionFrame.java
 * Abstract: Selecting Type of Car
 * Author: Arturo Lopez
 * ID:2412
 * Date: December, 12 2014
 */
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

class CarSelectionFrame extends JFrame{
	
	public CarSelectionFrame()
	{
		setTitle("Car Selection");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		JPanel panel = new CarSelectionPanel();
		this.add(panel);
		this.setResizable(false);
		this.setSize(350, 200);
	}
}
class CarSelectionPanel extends JPanel implements ActionListener{

	private JLabel vehicleSelection, minivanCharge, sedanCharge,truckCharge;
	private JButton minivan,sedan,truck,cancel;
	
	private int hourly = 0;
	
	public CarSelectionPanel() {
		this.setLayout(new GridBagLayout());
		
		vehicleSelection = new JLabel("Choose vehicle");
		minivanCharge 	 = new JLabel("$50.00 per day");
		sedanCharge 	 = new JLabel("$25.00 per day");
		truckCharge 	 = new JLabel("$35.00 per day");
		
		minivan = new JButton("Minivan");
		sedan 	= new JButton("Sedan");
		truck   = new JButton("Truck");
		cancel  = new JButton("Cancel");
		
		minivan.setVisible(MakeReservationFrame.hide_minivan);
		minivanCharge.setVisible(MakeReservationFrame.hide_minivan);
		
		sedan.setVisible(MakeReservationFrame.hide_sedan);
		sedanCharge.setVisible(MakeReservationFrame.hide_sedan);
		
		truck.setVisible(MakeReservationFrame.hide_truck);
		truckCharge.setVisible(MakeReservationFrame.hide_truck);
		
		if(MakeReservationFrame.hide_minivan == false && 
				MakeReservationFrame.hide_sedan == false && 
				MakeReservationFrame.hide_truck == false)
		{
			vehicleSelection.setText("No vehicles are available for this time");
		}
		
		minivan.addActionListener(this);
		sedan.addActionListener(this);
		truck.addActionListener(this);
		cancel.addActionListener(this);
		
		this.add(vehicleSelection,getConstraints(1, 0, 1, 1, GridBagConstraints.WEST));
		this.add(minivan,		  getConstraints(0, 1, 1, 1, GridBagConstraints.WEST));
		this.add(sedan, 		  getConstraints(1, 1, 1, 1, GridBagConstraints.WEST));
		this.add(truck,			  getConstraints(2, 1, 1, 1, GridBagConstraints.WEST));
		this.add(minivanCharge,   getConstraints(0, 3, 1, 1, GridBagConstraints.WEST));
		this.add(sedanCharge,	  getConstraints(1, 3, 1, 1, GridBagConstraints.WEST));
		this.add(truckCharge,	  getConstraints(2, 3, 1, 1, GridBagConstraints.WEST));
		this.add(cancel,		  getConstraints(1, 4, 1, 1, GridBagConstraints.WEST));
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if(source == cancel)
			backToDateInfo();
			if(source == minivan )
			{
				hourly = 50;
				InfoSingleton.getConfirm().setTotal(totalAmt(hourly));
				InfoSingleton.getConfirm().setCarType("Minivan");
				login();
			}
			if( source == sedan)
			{
				hourly = 25;
				InfoSingleton.getConfirm().setTotal(totalAmt(hourly));
				InfoSingleton.getConfirm().setCarType("Sedan");
				login();
			}
			if(source == truck)
			{
				hourly = 35;
				InfoSingleton.getConfirm().setTotal(totalAmt(hourly));
				InfoSingleton.getConfirm().setCarType("Truck");
				login();
			}
	}
	public void backToDateInfo(){
		(SwingUtilities.getWindowAncestor(this)).dispose(); 
	} 
	
	public void login(){
		JFrame frame = new LogInFrame();
		frame.setVisible(true);
		backToDateInfo();
	}
	public double totalAmt(int hourly)
	{
		int days = InfoSingleton.getConfirm().getReturnIndex() - InfoSingleton.getConfirm().getPickIndex();
		if(days == 0)
			return hourly;
		else
			return days * hourly;
	}
	
	private GridBagConstraints getConstraints(int gridx, int gridy, int gridwidth, int gridheight, int anchor)
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
