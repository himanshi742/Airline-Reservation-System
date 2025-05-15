package demo;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;

public class WelcomeUI extends JFrame {

	private JPanel contentPane;

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WelcomeUI frame = new WelcomeUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public WelcomeUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		//setSize(1600,1200);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
    	setVisible(true);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("BOOK MY TRIP");
		lblNewLabel.setFont(new Font("Century Gothic", Font.BOLD | Font.ITALIC, 82));
		lblNewLabel.setBounds(368, 27, 611, 82);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("WELCOME TO AIRLINE RESERVATION SYSTEM !");
		lblNewLabel_1.setFont(new Font("Century Gothic", Font.PLAIN, 45));
		lblNewLabel_1.setBounds(156, 120, 980, 69);
		contentPane.add(lblNewLabel_1);
		
		JButton btnNewButton_2 = new JButton("ADMIN LOGIN");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdminLoginUI admin = new AdminLoginUI();
		        admin.setVisible(true);
			}
		});
		btnNewButton_2.setFont(new Font("Century Gothic", Font.BOLD | Font.ITALIC, 24));
		btnNewButton_2.setBounds(79, 608, 223, 59);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("EXIT");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnNewButton_3.setFont(new Font("Century Gothic", Font.BOLD, 24));
		btnNewButton_3.setBounds(1012, 611, 198, 52);
		contentPane.add(btnNewButton_3);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(79, 264, 1134, 130);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton btnNewButton = new JButton("FLIGHT SCHEDULE");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 BookingUI flight = new BookingUI();
			        flight.setVisible(true);
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 24));
		btnNewButton.setBounds(806, 35, 322, 68);
		panel.add(btnNewButton);
		
		JLabel lblNewLabel_2 = new JLabel("You can check details of flight here and then book your desired flight");
		lblNewLabel_2.setBounds(10, 36, 786, 69);
		panel.add(lblNewLabel_2);
		lblNewLabel_2.setFont(new Font("Century Gothic", Font.BOLD | Font.ITALIC, 23));
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(79, 439, 1134, 130);
		contentPane.add(panel_1);
		
		JLabel lblNewLabel_2_1 = new JLabel("You can check the status of your already booked tickets here");
		lblNewLabel_2_1.setBounds(10, 21, 784, 69);
		panel_1.add(lblNewLabel_2_1);
		lblNewLabel_2_1.setFont(new Font("Century Gothic", Font.BOLD | Font.ITALIC, 23));
		
		JButton btnPassengerDetails = new JButton("PASSENGER DETAILS");
		btnPassengerDetails.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PassengerDetailsUI pass = new PassengerDetailsUI();
		        pass.setVisible(true);
			}
		});
		btnPassengerDetails.setFont(new Font("Tahoma", Font.BOLD, 24));
		btnPassengerDetails.setBounds(799, 20, 325, 68);
		panel_1.add(btnPassengerDetails);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(255, 0, 0));
		panel_2.setBounds(79, 218, 1134, 10);
		contentPane.add(panel_2);
		
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setIcon(new ImageIcon(WelcomeUI.class.getResource("/images/img2.jpg")));
		lblNewLabel_3.setBounds(10, 11, 1263, 758);
		contentPane.add(lblNewLabel_3);
	}
}
