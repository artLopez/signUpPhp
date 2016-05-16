package project2;
/**
 * Title: AdminFrame.java
 * Abstract: Viewing all logs by
 * admin login
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
import java.util.ArrayList;
import java.util.List;

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

public class AdminFrame extends JFrame {
	public static JScrollPane scrollView;
	
	public AdminFrame()
	{
		setTitle("Admin");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		scrollView = new JScrollPane();
		scrollView.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollView.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		JPanel panel = new AdminPanel();
		this.add(scrollView);
		this.setSize(400, 400);
	}
}

class AdminPanel extends JPanel implements ActionListener{
	private List<JLabel> listOfLabels = new ArrayList<JLabel>();
	private JButton logOff;
	private boolean no_account = true;
	private boolean no_reservation = true;
	private boolean no_cancellation = true;
	
	
	int x = 0;
	
	public AdminPanel() {
		this.setLayout(new GridBagLayout());
		
		AdminFrame.scrollView.add(this);
		
		Border loweredBorder = BorderFactory.createBevelBorder(BevelBorder.LOWERED);
		
		
		this.setBorder(BorderFactory.createTitledBorder(loweredBorder, "Logs"));
		this.setBackground(new Color(255, 255, 255));
		
		logOff = new JButton("Log off");
		logOff.addActionListener(this);
		
		
		listOfLabels.add(new JLabel("Accounts:"));
		this.add(listOfLabels.get(x),getConstraints(0, x, 1, 1, GridBagConstraints.WEST));
		x++;
	
		for (LogTransaction s : InfoSingleton.getLogs())
		{
			if(s.gettrasType().equals("New Account"))
			{
				no_account = false;
				listOfLabels.add(new JLabel(s.toString()));
				this.add(listOfLabels.get(x),getConstraints(0, x, 1, 1, GridBagConstraints.WEST));
				x++;
			}
		}
		
		if(no_account == true)
		{
			listOfLabels.add(new JLabel("\t\t\t\tNo current accounts"));
			this.add(listOfLabels.get(x),getConstraints(0, x, 1, 1, GridBagConstraints.WEST));
			x++;
			
		}
		
		listOfLabels.add(new JLabel("Reservations:"));
		this.add(listOfLabels.get(x),getConstraints(0, x, 1, 1, GridBagConstraints.WEST));
		x++;
		
		for (LogTransaction s : InfoSingleton.getLogs())
		{
			if(s.gettrasType().equals("Reservation"))
			{
				no_reservation = false;
				listOfLabels.add(new JLabel(s.toString()));
				this.add(listOfLabels.get(x),getConstraints(0, x, 1, 1, GridBagConstraints.WEST));
				x++;
			}
		}
		if(no_reservation)
		{
			listOfLabels.add(new JLabel("\t\t\t\tNo current reservations"));
			this.add(listOfLabels.get(x),getConstraints(0, x, 1, 1, GridBagConstraints.WEST));
			x++;
		}
		
		listOfLabels.add(new JLabel("Cancellations: "));
		this.add(listOfLabels.get(x),getConstraints(0, x, 1, 1, GridBagConstraints.WEST));
		x++;
		
		for (LogTransaction s : InfoSingleton.getLogs())
		{
			if(s.gettrasType().equals("cancellation"))
			{
				no_cancellation = false;
				listOfLabels.add(new JLabel(s.toString()));
				this.add(listOfLabels.get(x),getConstraints(0, x, 1, 1, GridBagConstraints.WEST));
				x++;
			}
		}
		
		if(no_cancellation)
		{
			listOfLabels.add(new JLabel("\t\t\t\tNo current cancellations"));
			this.add(listOfLabels.get(x),getConstraints(0, x, 1, 1, GridBagConstraints.WEST));
			x++;
			
		}
		
		
		this.add(logOff,getConstraints(0, x, 1, 1, GridBagConstraints.WEST));
	
		AdminFrame.scrollView.getViewport().add(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object source = e.getSource();
		
		if(source == logOff)
		{
			int dialogButton = JOptionPane.YES_NO_OPTION;
			int dialogResult = JOptionPane.showConfirmDialog(this, "Are you sure you want to log off?", "Loggin Out",dialogButton);
			if(dialogResult == 0)
				(SwingUtilities.getWindowAncestor(this)).dispose();
			
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
