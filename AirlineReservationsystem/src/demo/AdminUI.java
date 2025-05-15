package demo;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.ImageIcon;

public class AdminUI extends JFrame {
    private JTable flightable;

    public static void main(String[] args) {
        new AdminUI();
    }

    public AdminUI() {
        getContentPane().setBackground(new Color(204, 229, 255));
        setSize(1600,1200);
        //setExtendedState(JFrame.MAXIMIZED_BOTH);

        setVisible(true);
    
        getContentPane().setLayout(null);

        JButton jButton2 = new JButton("Close");
        jButton2.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		//this.dispose();
        	}
        });
        jButton2.setFont(new Font("Century Gothic", Font.BOLD | Font.ITALIC, 18));
        jButton2.setBounds(22, 630, 140, 37);
        getContentPane().add(jButton2);

        flightable = new JTable();
        flightable.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "Flight No.", "Reg. No.", "Airline", "From", "To", "Date", "Departue", "Arrival", "Fare", "Seats Left"
                }
        ));
        flightable.setBounds(44, 157, 1229, 412);
        getContentPane().add(flightable);

        JScrollPane jScrollPane1 = new JScrollPane(flightable);
        jScrollPane1.setViewportBorder(new LineBorder(new Color(0, 0, 0)));
        jScrollPane1.setBounds(22, 114, 1251, 412);
        getContentPane().add(jScrollPane1);
        
        JLabel lblNewLabel = new JLabel("ADMIN PORTAL");
        lblNewLabel.setFont(new Font("Century Gothic", Font.ITALIC, 56));
        lblNewLabel.setBounds(48, 21, 514, 82);
        getContentPane().add(lblNewLabel);
        
        JButton jButton1 = new JButton("LOAD FLIGHT DETAILS");
        jButton1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		DefaultTableModel model= (DefaultTableModel)flightable.getModel();

                model.setRowCount(0);
                try 
                {
                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost/flightdb","root","");
                    Statement stmt = con.createStatement();
                    String query="select * from flight, airline where flight.flightid = airline.fid order by date;";
                    ResultSet myRs =stmt.executeQuery(query);
                    while(myRs.next()) {
                        String s1= myRs.getString("flightid");
                        String s2= myRs.getString("regno");
                        String s3= myRs.getString("airline");
                        String s4= myRs.getString("fromcity");
                        String s5= myRs.getString("tocity");
                        String s6= myRs.getString("date");
                        String s7= myRs.getString("deptime");
                        String s8= myRs.getString("arrtime");
                        String s9= myRs.getString("fare");
                        String s10= myRs.getString("seats");
                        
                        model.addRow(new Object[] {s1,s2,s3,s4,s5,s6, s7, s8, s9, s10});
                    }
                    myRs.close();
                    stmt.close();
                    con.close();     
                }
                catch (Exception e1) 
                {
                    JOptionPane.showMessageDialog(null,"Error");
                } 
        	}
        });
        jButton1.setFont(new Font("Century Gothic", Font.BOLD, 36));
        jButton1.setBounds(752, 28, 471, 75);
        getContentPane().add(jButton1);
        
        JButton jButton3 = new JButton("REMOVE SELECTED FLIGHT FROM DATABASE\r\n");
        jButton3.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {

                int a = flightable.getSelectedRow();
                if(a < 0)
                {
                    JOptionPane.showMessageDialog(null, "Please click on desired flight");
                }
                else
                {
                    Object obj1 = flightable.getModel().getValueAt(a, 0);
                    
                    String flight = obj1.toString();
                    try 
                    {
                    	Class.forName("com.mysql.cj.jdbc.Driver");
                        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/flightdb","root","");
                        Statement stmt = con.createStatement();
                        //int flag = 0;
                        
                        String query1 = "delete from flight where flightid = '" + flight + "';";
                        String query2 = "delete from customer where bookfid = '" + flight + "';";
                        String query3 = "delete from airline where fid = '" + flight + "';";
                        stmt.executeUpdate(query1);
                        stmt.executeUpdate(query2);
                        stmt.executeUpdate(query3);

                        JOptionPane.showMessageDialog(null,"Selected flight has been cancelled and removed from database");

                        stmt.close();
                        con.close();
                    
                    

                    }
                    catch (Exception e1)
                    {
                        JOptionPane.showMessageDialog(null, "Please click on desired flight");
                    }
                }  
        	}
        });
        jButton3.setFont(new Font("Century Gothic", Font.ITALIC, 26));
        jButton3.setBounds(22, 537, 595, 82);
        getContentPane().add(jButton3);
        
        JButton jButton4 = new JButton("ADD A NEW FLIGHT TO DATABASE");
        jButton4.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		AdminAddFlight f = new AdminAddFlight();
                f.setVisible(true);
        	}
        });
        jButton4.setFont(new Font("Century Gothic", Font.ITALIC, 33));
        jButton4.setBounds(639, 537, 595, 82);
        getContentPane().add(jButton4);
        
        JLabel lblNewLabel_1 = new JLabel("");
        lblNewLabel_1.setIcon(new ImageIcon(AdminUI.class.getResource("/images/img4.jpg")));
        lblNewLabel_1.setBounds(0, 0, 1273, 780);
        getContentPane().add(lblNewLabel_1);

        setVisible(true);
    }
}
