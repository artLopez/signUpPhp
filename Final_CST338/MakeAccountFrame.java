package project2;
/**
 * Title: MakeAccountFrame.java
 * Abstract: Make new account 
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
import java.util.Calendar;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

class MakeAccountFrame extends JFrame
{
	public static boolean already_tried = false;
	public MakeAccountFrame()
	{
		setTitle("Make Account");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		JPanel panel = new MakeAccountPanel();
		this.add(panel);
		this.setResizable(false);
		this.setSize(300, 200);
	}
}

class MakeAccountPanel extends JPanel implements ActionListener
{
	private JButton submitButton,
					cancelButton;
	
	private JTextField userName;
	
	private JPasswordField password;
	
	private JLabel userNameLabel,
				   passwordLabel;

	
	private String userNameString,
				   passwordString;

	

	public MakeAccountPanel()
	{
		this.setLayout(new GridBagLayout());
		
		submitButton = new JButton("Submit");
	    cancelButton = new JButton("Cancel");
	    userName = new JTextField(10);
	    password = new JPasswordField(10);
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
			 backToMain();
		if(source == submitButton)
		{
			userNameString = userName.getText();
			passwordString = password.getText();
			
			if(userNameString.equals("admin2"))
			{
				if(MakeAccountFrame.already_tried )
				{
					JOptionPane.showMessageDialog(null, "Did not meet criteria. You will be taken back to the main menu.", 
							"Error", JOptionPane.ERROR_MESSAGE);
					MakeAccountFrame.already_tried = false;
					backToMain();
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Cannot used admin2 for username.");
					MakeAccountFrame.already_tried = true;
					userName.setText("");
					password.setText("");
				}
			}
			else if (userNameString.length() < 5 || passwordString.length() < 5)
			{
				if(MakeAccountFrame.already_tried)
				{
					JOptionPane.showMessageDialog(null, "Did not meet criteria. You will be taken back to the main menu.", 
							"Error", JOptionPane.ERROR_MESSAGE);
					MakeAccountFrame.already_tried = false;
					backToMain();
				}
				else
				{
					JOptionPane.showMessageDialog(null, "The username and/or password are not in the correct format.\n"
							+ "Needs to atleast five characters");
					MakeAccountFrame.already_tried = true;
					userName.setText("");
					password.setText("");
				}
			}
			else if(!is_correct(userNameString, passwordString))
			{
				if(MakeAccountFrame.already_tried)
				{
					JOptionPane.showMessageDialog(null, "Did not meet criteria. You will be taken back to the main menu.", 
							"Error", JOptionPane.ERROR_MESSAGE);
					MakeAccountFrame.already_tried = false;
					backToMain();
				}
				else
				{
					JOptionPane.showMessageDialog(null, "The username and/or password are not in the correct format.\n"
							+ "Only letters and digits.");
					MakeAccountFrame.already_tried = true;
					userName.setText("");
					password.setText("");
				}
				
			}
			else if(InfoSingleton.getAccounts().containsKey(userNameString))
			{
				if(MakeAccountFrame.already_tried)
				{
					JOptionPane.showMessageDialog(null, "Username already exists.You will be taken back to the main menu.", "Error", JOptionPane.ERROR_MESSAGE);
					MakeAccountFrame.already_tried = false;
					backToMain();
				}
				else
				{
					MakeAccountFrame.already_tried = true;
					JOptionPane.showMessageDialog(null, "That username already exists. Try again.");
					userName.setText("");
					password.setText("");
				}
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Your account was successfully created. Thank you!");
				InfoSingleton.getAccounts().put(userNameString, passwordString);
					
				DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm a");
				Calendar cal = Calendar.getInstance();
				
				InfoSingleton.getLogs().add(new LogTransaction(ReservationSystem.ctr,userNameString,dateFormat.format(cal.getTime()) , "New Account"));				
				ReservationSystem.ctr++;
	
				backToMain();
			}
		}
	}
	// Goes back to the main menu frame
	public void backToMain(){
		(SwingUtilities.getWindowAncestor(this)).dispose(); 
	} 
	
	public boolean is_correct(String userName, String password){
				
		for(int x = 0; x < userName.length();x++)
		{
			if(!(Character.isDigit(userName.charAt(x)) || Character.isLetter(userName.charAt(x))))
				return false;
			
		}
		
		for(int y = 0; y < password.length();y++)
		{
			if(!(Character.isDigit(password.charAt(y)) || Character.isLetter(password.charAt(y))))
				return false;
		}
		
		return true;
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