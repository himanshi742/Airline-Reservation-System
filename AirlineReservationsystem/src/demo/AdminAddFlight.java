package demo;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class AdminAddFlight extends JFrame {

	private JPanel contentPane;
	private JTextField flightid;
	private JTextField date;
	private JTextField deptime;
	private JTextField arrtime;
	private JTextField fare;
	private JTextField seats;
	private JTextField regno;
	private JTextField airline;
	private JTextField fromcity;
	private JTextField tocity;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminAddFlight frame = new AdminAddFlight();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AdminAddFlight() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1080,542);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel jLabel1 = new JLabel();
		jLabel1.setText("PLEASE ENTER COMPLETE DETAILS TO ADD A NEW FLIGHT TO THE DATABASE");
		jLabel1.setHorizontalAlignment(SwingConstants.CENTER);
		jLabel1.setFont(new Font("Tahoma", Font.BOLD, 18));
		jLabel1.setBounds(134, 20, 835, 62);
		contentPane.add(jLabel1);
		
		JButton jButton1 = new JButton();
		jButton1.setText("CLOSE");
		jButton1.setBackground(Color.RED);
		jButton1.setBounds(167, 378, 78, 23);
		contentPane.add(jButton1);
		
		JButton jButton2 = new JButton();
		jButton2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try 
		        {
		            try {
						Class.forName("com.mysql.cj.jdbc.Driver");
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
		            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/flightdb","root","");
		            System.out.println("connceted");
		            Statement stmt = con.createStatement();
		            String fid = flightid.getText();
		            int reg = Integer.parseInt(regno.getText());
		            String air = airline.getText();
		            String from = fromcity.getText();
		            String to = tocity.getText();
		            
		            String myDate = date.getText();
		            
		            //Time time = new Time(date.getTime());
		            
		            String dtime = deptime.getText();
		            String atime = arrtime.getText();
		            
		            int f = Integer.parseInt(fare.getText());
		            int s = Integer.parseInt(seats.getText());
		            
		            String query = "INSERT INTO flight(flightid, regno, fromcity, tocity, date, deptime, arrtime, fare, seats) " +
		                    "VALUES('" + fid + "', '" + reg + "', '" + from + "','" + to + "','" + myDate + "','" + dtime + "', '" + atime + "', '" + f + "', '" + s + "' )";
		 
		           
		           
		            String query1 = "INSERT INTO airline(fid, airline,seats) VALUES ('" + fid + "', '" + air + "', '"+s+"')";

		                
		            JOptionPane.showMessageDialog(null,"New Flight Added !");
		           
		            stmt.executeUpdate(query);
		            stmt.executeUpdate(query1);
		            
		           
		            stmt.close();
		            con.close();
		            dispose();
		            
		            
		// TODO add your handling code here:
		        } 
		        catch (SQLException e1) 
		        {
		        	System.out.println("Failed to connect to the database: " + e1.getMessage());
		            JOptionPane.showMessageDialog(null, "Please check that you have entered all details correctly.");
		        }

			}
		});
		jButton2.setText("ADD FLIGHT");
		jButton2.setFont(new Font("Tahoma", Font.BOLD, 14));
		jButton2.setBackground(Color.GREEN);
		jButton2.setBounds(701, 384, 180, 28);
		contentPane.add(jButton2);
		
		
		
		JLabel jLabel2 = new JLabel();
		jLabel2.setBackground(new Color(0, 0, 0));
		jLabel2.setText("FLIGHT NO. :");
		jLabel2.setHorizontalAlignment(SwingConstants.CENTER);
		jLabel2.setBounds(179, 148, 139, 39);
		contentPane.add(jLabel2);
		
		flightid = new JTextField();
		flightid.setBounds(322, 148, 139, 39);
		contentPane.add(flightid);
		
		JLabel jLabel7 = new JLabel();
		jLabel7.setText("DATE :");
		jLabel7.setHorizontalAlignment(SwingConstants.CENTER);
		jLabel7.setBounds(586, 148, 139, 39);
		contentPane.add(jLabel7);
		
		date = new JTextField();
		date.setBounds(729, 148, 139, 39);
		contentPane.add(date);
		
		deptime = new JTextField();
		deptime.setBounds(729, 193, 139, 39);
		contentPane.add(deptime);
		
		arrtime = new JTextField();
		arrtime.setBounds(729, 238, 139, 39);
		contentPane.add(arrtime);
		
		fare = new JTextField();
		fare.setBounds(729, 283, 139, 39);
		contentPane.add(fare);
		
		seats = new JTextField();
		seats.setBounds(729, 328, 139, 39);
		contentPane.add(seats);
		
		JLabel jLabel11 = new JLabel();
		jLabel11.setText("SEATS :");
		jLabel11.setHorizontalAlignment(SwingConstants.CENTER);
		jLabel11.setBounds(586, 328, 139, 39);
		contentPane.add(jLabel11);
		
		JLabel jLabel10 = new JLabel();
		jLabel10.setText("FARE :");
		jLabel10.setHorizontalAlignment(SwingConstants.CENTER);
		jLabel10.setBounds(586, 283, 139, 39);
		contentPane.add(jLabel10);
		
		JLabel jLabel9 = new JLabel();
		jLabel9.setText("ARRIVAL :");
		jLabel9.setHorizontalAlignment(SwingConstants.CENTER);
		jLabel9.setBounds(586, 238, 139, 39);
		contentPane.add(jLabel9);
		
		JLabel jLabel8 = new JLabel();
		jLabel8.setText("DEPARTURE :");
		jLabel8.setHorizontalAlignment(SwingConstants.CENTER);
		jLabel8.setBounds(586, 193, 139, 39);
		contentPane.add(jLabel8);
		
		JLabel jLabel3 = new JLabel();
		jLabel3.setText("REG. NO. :");
		jLabel3.setHorizontalAlignment(SwingConstants.CENTER);
		jLabel3.setBounds(179, 193, 139, 39);
		contentPane.add(jLabel3);
		
		regno = new JTextField();
		regno.setBounds(322, 193, 139, 39);
		contentPane.add(regno);
		
		airline = new JTextField();
		airline.setBounds(322, 238, 139, 39);
		contentPane.add(airline);
		
		JLabel jLabel4 = new JLabel();
		jLabel4.setText("AIRLINE :");
		jLabel4.setHorizontalAlignment(SwingConstants.CENTER);
		jLabel4.setBounds(179, 238, 139, 39);
		contentPane.add(jLabel4);
		
		JLabel jLabel5 = new JLabel();
		jLabel5.setText("FROM :");
		jLabel5.setHorizontalAlignment(SwingConstants.CENTER);
		jLabel5.setBounds(179, 283, 139, 39);
		contentPane.add(jLabel5);
		
		fromcity = new JTextField();
		fromcity.setBounds(322, 283, 139, 39);
		contentPane.add(fromcity);
		
		tocity = new JTextField();
		tocity.setBounds(322, 328, 139, 39);
		contentPane.add(tocity);
		
		JLabel jLabel6 = new JLabel();
		jLabel6.setText("TO :");
		jLabel6.setHorizontalAlignment(SwingConstants.CENTER);
		jLabel6.setBounds(179, 328, 139, 39);
		contentPane.add(jLabel6);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(AdminAddFlight.class.getResource("/images/img1.jpg")));
		lblNewLabel.setBounds(10, 0, 1056, 505);
		contentPane.add(lblNewLabel);
		
	}
}
