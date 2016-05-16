package project2;
/**
 * Title: CarSystem.java
 * Abstract: Select make account, reservation or
 * cancel reservation
 *
 * Author: Arturo Lopez
 * ID:2412
 * Date: December, 12 2014
 */
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

class CarSystem extends JFrame
{
	public CarSystem()
	{
		setTitle("CSUMB Car Reservation System");
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JPanel panel = new CarOptions();
        this.add(panel);
        this.setSize(300, 200); 
	}
}
class CarOptions extends JPanel implements ActionListener{
	
	JButton makeReservation,
			cancelReservation,
			createAccount;
		
	public CarOptions()
	{
		//create panel for the labels and textfields
		this.setLayout(new GridBagLayout());
		
		InfoSingleton.getAccounts().put("admin2", "admin2");
		
        createAccount = new JButton("    Make Account    ");
        makeReservation = new JButton(" Make Reservation ");
        cancelReservation = new JButton("Cancel Reservation");
		
        createAccount.addActionListener(this);
        makeReservation.addActionListener(this);
        cancelReservation.addActionListener(this);
        
        GridBagConstraints gc = new GridBagConstraints();
        gc.gridx = 0;
        gc.gridy = 0;
        gc.anchor = GridBagConstraints.CENTER;
        this.add(createAccount,gc);
        gc.gridy = 1;
        this.add(makeReservation,gc);
        gc.gridy = 2;
        this.add(cancelReservation,gc);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		
		if(source == createAccount)
		{
		     JFrame frame = new MakeAccountFrame();
	         frame.setVisible(true);
		}
		if(source == makeReservation)
		{
			ReservationSystem.is_cancel = false;
			JFrame frame = new MakeReservationFrame();
	        frame.setVisible(true);
		}
		if(source == cancelReservation)
		{
			ReservationSystem.is_cancel = true;
			JFrame frame = new LogInFrame();
			frame.setVisible(true);
		}
	}
}