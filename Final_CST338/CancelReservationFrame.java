package project2;

/**
 * Title: CancelReservationFrame.java
 * Abstract: Canceling reservation
 * Author: Arturo Lopez
 * ID:2412
 * Date: December, 12 2014
 */

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;

public class CancelReservationFrame extends JFrame {
	private static String userName = null;
	public static JScrollPane scrollView;
	
	public CancelReservationFrame(String userName)
	{
		setTitle("Cancel Reservation");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setUserName(userName);
		
		scrollView = new JScrollPane();
		scrollView.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollView.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		JPanel panel = new CancelReservationPanel();
		this.add(scrollView);
		this.setSize(350, 200);
	}

	public static String getUserName() {
		return userName;
	}

	public static void setUserName(String userName) {
		CancelReservationFrame.userName = userName;
	}
}

class CancelReservationPanel extends JPanel implements ActionListener{
	private boolean has_reser = false;
	private int ctrY = 0;
	
	private JLabel reservationStatus;
	private JButton submit, cancel;
	private String selectedText = null;
	
	
	public CancelReservationPanel() {
		this.setLayout(new GridBagLayout());
		
		CancelReservationFrame.scrollView.add(this);
		
		Border loweredBorder = BorderFactory.createBevelBorder(BevelBorder.LOWERED);
		
		this.setBorder(BorderFactory.createTitledBorder(loweredBorder, "Reservations"));
		this.setBackground(new Color(255, 255, 255));
		ButtonGroup buttonGroup1 = new ButtonGroup();
		cancel = new JButton("Cancel");
		cancel.addActionListener(this);
		submit = new JButton("Submit");
    	submit.addActionListener(this);
		
		if(InfoSingleton.getUserTrans().get(CancelReservationFrame.getUserName()) != null)
		{
			for (LogTransaction s : InfoSingleton.getUserTrans().get(CancelReservationFrame.getUserName()))
			{
				final JRadioButton button1 = new JRadioButton(s.toString());
	            this.add(button1,getConstraints(0, ctrY, 1, 1, GridBagConstraints.WEST));
	            buttonGroup1.add(button1);
	            
	            if(ctrY == 0)
	            {
	            	button1.setSelected(true);
	            	selectedText = button1.getText();
	            }
	            
	            ctrY++;
	          
	            button1.addActionListener(new ActionListener() {
	                @Override
	                public void actionPerformed(ActionEvent e) {  
	                    selectedText = button1.getText();
	                }
	            });  
				has_reser = true;	
			}
		}
	
		if(!has_reser)
		{
			reservationStatus = new JLabel("No reservations");
			this.add(reservationStatus,getConstraints(0, 0, 1, 1, GridBagConstraints.WEST));
			this.add(cancel,getConstraints(0, 1, 1, 1, GridBagConstraints.WEST));
		}
		else
		{
			this.add(submit,getConstraints(0, ctrY, 1, 1, GridBagConstraints.WEST));
			this.add(cancel,getConstraints(0, ctrY + 1, 1, 1, GridBagConstraints.WEST));
		}
		
		CancelReservationFrame.scrollView.getViewport().add(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		Object source = e.getSource();
		if(source == cancel)
			(SwingUtilities.getWindowAncestor(this)).dispose();
		if(source == submit)
		{	
			int dialogButton = JOptionPane.YES_NO_OPTION;
			int dialogResult = JOptionPane.showConfirmDialog(this, "Are you sure you want to cancel your reservation?", "Cancellation",dialogButton);
			if(dialogResult==0)
			{
				for (LogTransaction s: InfoSingleton.getUserTrans().get(CancelReservationFrame.getUserName()))
				{
					System.out.println(s.toString());
					
					DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm a");
					Calendar cal = Calendar.getInstance();
					String dates = dateFormat.format(cal.getTime());
					
					if(s.toString().equals(selectedText))
					{
						InfoSingleton.getLogs().add(new LogTransaction(s.getUserName(), s.getCarType(), s.getPickUpInfo(), s.getReturnInfo(), "cancellation", dates));
						InfoSingleton.getUserTrans().get(CancelReservationFrame.getUserName()).remove(s);
						
						(SwingUtilities.getWindowAncestor(this)).dispose();
						break;
					}
				}
			}			
		}
		
	}
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
