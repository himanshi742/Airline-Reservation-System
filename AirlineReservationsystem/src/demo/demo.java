package demo;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class demo extends JFrame {

	private JPanel contentPane;
	private JTextField cid;
	private JTextField fid;
	private JLabel jT;
	private JLabel lName;
	private JLabel jLabel5;
	private JLabel lPhone;
	private JLabel jLabel18;
	private JLabel lAmt;
	private JPanel panel;
	private JPanel panel_1;
	private JLabel jLabel8;
	private JLabel lFrom;
	private JLabel jLabel9;
	private JLabel lTo;
	private JLabel jLabel10;
	private JLabel lDate;
	private JLabel jLabel11;
	private JLabel dTime;
	private JLabel jLabel12;
	private JLabel aTime;
	private JButton jButton1;
	private JButton jButton3;
	private JButton jButton2;
	private JLabel lStatus1;
	private JLabel lStatus2;
	private JLabel lblNewLabel;

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					demo frame = new demo();
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
	public demo() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1600,1200);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(205, 229, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel jLabel1 = new JLabel();
		jLabel1.setText("CHECK THE DETAILS OF YOUR BOOKED FLIGHT HERE");
		jLabel1.setHorizontalAlignment(SwingConstants.CENTER);
		jLabel1.setFont(new Font("Tahoma", Font.BOLD, 24));
		jLabel1.setBounds(195, 35, 940, 81);
		contentPane.add(jLabel1);
		
		JLabel jLabel2 = new JLabel();
		jLabel2.setText("Enter Passenger ID");
		jLabel2.setHorizontalAlignment(SwingConstants.CENTER);
		jLabel2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		jLabel2.setBounds(285, 191, 173, 53);
		contentPane.add(jLabel2);
		
		JLabel jLabel3 = new JLabel();
		jLabel3.setText("Enter Flight ID");
		jLabel3.setHorizontalAlignment(SwingConstants.CENTER);
		jLabel3.setFont(new Font("Tahoma", Font.PLAIN, 16));
		jLabel3.setBounds(295, 250, 131, 42);
		contentPane.add(jLabel3);
		
		cid = new JTextField();
		cid.setFont(new Font("Tahoma", Font.PLAIN, 16));
		cid.setBounds(490, 202, 490, 31);
		contentPane.add(cid);
		
		fid = new JTextField();
		fid.setFont(new Font("Tahoma", Font.PLAIN, 16));
		fid.setBounds(490, 256, 490, 31);
		contentPane.add(fid);
		
		panel = new JPanel();
		panel.setBounds(166, 348, 437, 211);
		contentPane.add(panel);
		panel.setLayout(null);
		
		jT = new JLabel();
		jT.setBounds(99, 43, 91, 28);
		panel.add(jT);
		jT.setText("NAME :");
		
		lName = new JLabel();
		lName.setBounds(196, 43, 109, 28);
		panel.add(lName);
		
		lPhone = new JLabel();
		lPhone.setBounds(196, 89, 109, 28);
		panel.add(lPhone);
		
		jLabel5 = new JLabel();
		jLabel5.setBounds(99, 89, 91, 28);
		panel.add(jLabel5);
		jLabel5.setText("PHONE NO. :");
		
		jLabel18 = new JLabel();
		jLabel18.setBounds(99, 135, 91, 25);
		panel.add(jLabel18);
		jLabel18.setText("Amount :");
		
		lAmt = new JLabel();
		lAmt.setBounds(196, 135, 109, 25);
		panel.add(lAmt);
		
		panel_1 = new JPanel();
		panel_1.setBounds(650, 348, 437, 211);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		jLabel8 = new JLabel();
		jLabel8.setText("FLIGHT FROM :");
		jLabel8.setBounds(52, 11, 106, 29);
		panel_1.add(jLabel8);
		
		lFrom = new JLabel();
		lFrom.setBounds(164, 11, 129, 29);
		panel_1.add(lFrom);
		
		jLabel9 = new JLabel();
		jLabel9.setText("FLIGHT TO :");
		jLabel9.setBounds(52, 46, 106, 29);
		panel_1.add(jLabel9);
		
		lTo = new JLabel();
		lTo.setBounds(164, 46, 129, 29);
		panel_1.add(lTo);
		
		jLabel10 = new JLabel();
		jLabel10.setText("DATE :");
		jLabel10.setBounds(52, 81, 106, 29);
		panel_1.add(jLabel10);
		
		lDate = new JLabel();
		lDate.setBounds(164, 81, 129, 29);
		panel_1.add(lDate);
		
		jLabel11 = new JLabel();
		jLabel11.setText("DEPARTURE :");
		jLabel11.setBounds(52, 116, 106, 29);
		panel_1.add(jLabel11);
		
		dTime = new JLabel();
		dTime.setBounds(164, 116, 129, 29);
		panel_1.add(dTime);
		
		jLabel12 = new JLabel();
		jLabel12.setText("ARRIVAL :");
		jLabel12.setBounds(52, 151, 106, 29);
		panel_1.add(jLabel12);
		
		aTime = new JLabel();
		aTime.setBounds(164, 151, 129, 29);
		panel_1.add(aTime);
		
		jButton1 = new JButton();
		jButton1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 try 
			        {
			        	Class.forName("com.mysql.cj.jdbc.Driver");
			            java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://localhost/flightdb","root","");
			            Statement stmt = (Statement) con.createStatement();
			            String flid = fid.getText();
			            int clid = Integer.parseInt(cid.getText());
			            String query="select * from customer, flight where customer.bookfid = flight.flightid and customer.cusid = "+clid+" and flight.flightid='"+flid+"';";
			            
			            ResultSet rs3 =stmt.executeQuery(query);
			            int flag = 0;
			            
			            while(rs3.next()) {
			                String sName= rs3.getString("cusname");
			                String sPhone= rs3.getString("cuscontact");
			                String sFrom= rs3.getString("fromcity");
			                String sTo= rs3.getString("tocity");
			                String sDate= rs3.getString("date");
			                String sDept= rs3.getString("deptime");
			                String sArrival= rs3.getString("arrtime");
			                String amt = rs3.getString("flamount");
			                lName.setText(sName);
			                lPhone.setText(sPhone);
			                lAmt.setText(amt);
			                lTo.setText(sTo);
			                lFrom.setText(sFrom);
			                lDate.setText(sDate);
			                aTime.setText(sArrival);
			                dTime.setText(sDept);
			                lStatus1.setText("FLIGHT BOOKED, ADDITIONAL DETAILS");
			                lStatus2.setText("HAVE BEEN SENT TO YOUR E-MAIL");
			                flag = 1;
			            }
			            if(flag == 0)
			            while(!rs3.next())
			            {
			                JOptionPane.showMessageDialog(null, "Customer ID "+ cid.getText() +" has no flight booking for Flight ID "+fid.getText());
			                break;
			            }
			            rs3.close();
			            stmt.close();
			            con.close();

			        } 
			        catch(Exception e1) 
			        {
			            JOptionPane.showMessageDialog(null, "Customer ID "+ cid.getText() +" has no flight booking for Flight ID "+fid.getText());
			        }
			}
		});
		jButton1.setText("GET DETAILS");
		jButton1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		jButton1.setBounds(1034, 205, 179, 65);
		contentPane.add(jButton1);
		
		jButton3 = new JButton();
		jButton3.setText("CANCEL FLIGHT");
		jButton3.setFont(new Font("Tahoma", Font.PLAIN, 18));
		jButton3.setBackground(new Color(255, 153, 0));
		jButton3.setBounds(691, 623, 179, 61);
		contentPane.add(jButton3);
		
		jButton2 = new JButton();
		jButton2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		jButton2.setText("close");
		jButton2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		jButton2.setBackground(Color.RED);
		jButton2.setBounds(166, 634, 100, 40);
		contentPane.add(jButton2);
		
		lStatus1 = new JLabel();
		lStatus1.setVerticalAlignment(SwingConstants.BOTTOM);
		lStatus1.setToolTipText("");
		lStatus1.setBounds(510, 634, 299, 25);
		contentPane.add(lStatus1);
		
		lStatus2 = new JLabel();
		lStatus2.setVerticalAlignment(SwingConstants.TOP);
		lStatus2.setBounds(510, 689, 299, 25);
		contentPane.add(lStatus2);
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(demo.class.getResource("/images/img4.jpg")));
		lblNewLabel.setBounds(0, 0, 1283, 780);
		contentPane.add(lblNewLabel);
	}

}
