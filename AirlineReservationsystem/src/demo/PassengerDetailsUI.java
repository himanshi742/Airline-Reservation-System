package demo;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class PassengerDetailsUI extends JFrame {
	private JTextField cid;
	private JTextField fid;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PassengerDetailsUI frame = new PassengerDetailsUI();
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
	public PassengerDetailsUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1354,764);
		getContentPane().setLayout(null);
		
		JLabel jLabel2 = new JLabel();
		jLabel2.setText("Enter Passenger ID");
		jLabel2.setHorizontalAlignment(SwingConstants.CENTER);
		jLabel2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		jLabel2.setBounds(201, 181, 258, 53);
		getContentPane().add(jLabel2);
		
		JLabel jLabel3 = new JLabel();
		jLabel3.setText("Enter Flight ID");
		jLabel3.setHorizontalAlignment(SwingConstants.CENTER);
		jLabel3.setFont(new Font("Tahoma", Font.PLAIN, 16));
		jLabel3.setBounds(201, 240, 258, 54);
		getContentPane().add(jLabel3);
		
		JLabel jLabel18 = new JLabel();
		jLabel18.setText("Amount :");
		jLabel18.setBounds(162, 445, 91, 25);
		getContentPane().add(jLabel18);
		
		JLabel jLabel5 = new JLabel();
		jLabel5.setText("PHONE NO. :");
		jLabel5.setBounds(162, 399, 91, 28);
		getContentPane().add(jLabel5);
		
		JLabel jT = new JLabel();
		jT.setText("NAME :");
		jT.setBounds(162, 353, 91, 28);
		getContentPane().add(jT);
		
		JLabel lName = new JLabel();
		lName.setBounds(259, 353, 109, 28);
		getContentPane().add(lName);
		
		JLabel lPhone = new JLabel();
		lPhone.setBounds(259, 399, 109, 28);
		getContentPane().add(lPhone);
		
		JLabel lAmt = new JLabel();
		lAmt.setBounds(259, 445, 109, 25);
		getContentPane().add(lAmt);
		
		JLabel jLabel12 = new JLabel();
		jLabel12.setText("ARRIVAL :");
		jLabel12.setBounds(350, 493, 106, 29);
		getContentPane().add(jLabel12);
		
		JLabel jLabel11 = new JLabel();
		jLabel11.setText("DEPARTURE :");
		jLabel11.setBounds(350, 458, 106, 29);
		getContentPane().add(jLabel11);
		
		JLabel jLabel10 = new JLabel();
		jLabel10.setText("DATE :");
		jLabel10.setBounds(350, 423, 106, 29);
		getContentPane().add(jLabel10);
		
		JLabel jLabel8 = new JLabel();
		jLabel8.setText("FLIGHT FROM :");
		jLabel8.setBounds(350, 353, 106, 29);
		getContentPane().add(jLabel8);
		
		JLabel jLabel9 = new JLabel();
		jLabel9.setText("FLIGHT TO :");
		jLabel9.setBounds(350, 388, 106, 29);
		getContentPane().add(jLabel9);
		
		JLabel lFrom = new JLabel();
		lFrom.setBounds(462, 353, 129, 29);
		getContentPane().add(lFrom);
		
		JLabel lTo = new JLabel();
		lTo.setBounds(462, 388, 129, 29);
		getContentPane().add(lTo);
		
		JLabel lDate = new JLabel();
		lDate.setBounds(462, 423, 129, 29);
		getContentPane().add(lDate);
		
		JLabel dTime = new JLabel();
		dTime.setBounds(462, 458, 129, 29);
		getContentPane().add(dTime);
		
		JLabel aTime = new JLabel();
		aTime.setBounds(462, 493, 129, 29);
		getContentPane().add(aTime);
		
		JLabel lStatus2 = new JLabel();
		lStatus2.setVerticalAlignment(SwingConstants.TOP);
		lStatus2.setBounds(515, 596, 299, 25);
		getContentPane().add(lStatus2);
		
		cid = new JTextField();
		cid.setFont(new Font("Tahoma", Font.PLAIN, 16));
		cid.setBounds(515, 186, 173, 42);
		getContentPane().add(cid);
		
		fid = new JTextField();
		fid.setFont(new Font("Tahoma", Font.PLAIN, 16));
		fid.setBounds(515, 246, 173, 42);
		getContentPane().add(fid);
		
		JLabel jLabel1 = new JLabel();
		jLabel1.setText("CHECK THE DETAILS OF YOUR BOOKED FLIGHT HERE");
		jLabel1.setHorizontalAlignment(SwingConstants.CENTER);
		jLabel1.setFont(new Font("Tahoma", Font.BOLD, 24));
		jLabel1.setBounds(180, 44, 940, 81);
		getContentPane().add(jLabel1);
		
		JLabel lStatus1 = new JLabel();
		lStatus1.setVerticalAlignment(SwingConstants.BOTTOM);
		lStatus1.setToolTipText("");
		lStatus1.setBounds(515, 558, 299, 25);
		getContentPane().add(lStatus1);
		
		JButton jButton2 = new JButton();
		jButton2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		jButton2.setText("close");
		jButton2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		jButton2.setBackground(Color.RED);
		jButton2.setBounds(170, 564, 100, 40);
		getContentPane().add(jButton2);
		
		JButton jButton1 = new JButton();
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
		jButton1.setBounds(950, 182, 179, 65);
		getContentPane().add(jButton1);
		
		JButton jButton3 = new JButton();
		jButton3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

		        try {
		            String flid = fid.getText();
		            int clid = Integer.parseInt(cid.getText());
		            Class.forName("com.mysql.cj.jdbc.Driver");
		            java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://localhost/flightdb","root","");
		            Statement stmt = (Statement) con.createStatement();
		            Statement stmt2 = (Statement) con.createStatement();
		            Statement stmt3 = (Statement) con.createStatement();
		            String query="delete from customer where cusid="+clid+" and bookfid='"+flid+"';";
		            String query3 = "select * from customer where cusid="+clid+" and bookfid='"+flid+"';";
		            ResultSet rs = stmt3.executeQuery(query3);
		            int flag = 0;
		            //String query2;
		            while(rs.next())
		            {
		                int seat = Integer.parseInt(rs.getString("tickets"));
		                String query2 = "update airline set seats = seats+"+seat+" where fid='"+flid+"';";
		                stmt2.executeUpdate(query2);
		                stmt.executeUpdate(query);
		                JOptionPane.showMessageDialog(null,"Your flight has been cancelled");
		                flag = 1;
		                setVisible(false);
		            }
		            
		            if(flag == 0)
		            while(!rs.next())
		            {
		                JOptionPane.showMessageDialog(null,"Please check that you have entered flight Id and customer Id correctly.");
		                break;
		            }

		            stmt.close();
		            con.close();
		            stmt2.close();
		            stmt3.close();
		            
		            
		        } catch (Exception e1) {
		            JOptionPane.showMessageDialog(null,"Please check that you have entered flight Id and customer Id correctly.");
		        }

			}
		});
		jButton3.setText("CANCEL FLIGHT");
		jButton3.setFont(new Font("Tahoma", Font.PLAIN, 18));
		jButton3.setBackground(new Color(255, 153, 0));
		jButton3.setBounds(950, 387, 179, 61);
		getContentPane().add(jButton3);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(PassengerDetailsUI.class.getResource("/images/img5.jpg")));
		lblNewLabel.setBounds(0, 0, 1283, 727);
		getContentPane().add(lblNewLabel);
	}
}
