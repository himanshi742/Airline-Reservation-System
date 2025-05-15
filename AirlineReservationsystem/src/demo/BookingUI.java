package demo;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;

public class BookingUI extends JFrame {

    private JPanel contentPane;
    private JTextField From;
    private JTextField to;
    private JTextField date;
    private JTable FlightTable;
    private JTextField tName;
    private JTextField tAge;
    private JTextField tNo;
    private JTextField tMail;
    private JTextField tAddress;
    private JLabel farebox;
    private JLabel gstbox;
    private JLabel labelFrom;
    private JLabel labelTo;
    private JLabel tSelectedFlight;
    private JLabel net;
    
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                BookingUI frame = new BookingUI();
                frame.setVisible(true);
            }
        });
    }

    public BookingUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1600,1200);
        //setExtendedState(JFrame.MAXIMIZED_BOTH);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(205, 229, 255));
        setSize(1600, 1200);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("BOOK A FLIGHT");
        lblNewLabel.setForeground(Color.BLACK);
        lblNewLabel.setFont(new Font("Century Gothic", Font.BOLD | Font.ITALIC, 43));
        lblNewLabel.setBounds(77, 38, 429, 39);
        contentPane.add(lblNewLabel);

        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        tabbedPane.setFont(new Font("Century Gothic", Font.BOLD | Font.ITALIC, 18));
        tabbedPane.setBounds(77, 99, 1179, 566);
        contentPane.add(tabbedPane);

        JPanel panel = new JPanel();
        panel.setForeground(Color.RED);
        tabbedPane.addTab("FLIGHT SELECTION", null, panel, null);
        panel.setLayout(null);

        JLabel lblNewLabel_1 = new JLabel("Location:\r\n");
        lblNewLabel_1.setBounds(59, 39, 74, 29);
        lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 15));
        panel.add(lblNewLabel_1);

        JLabel lblNewLabel_1_1 = new JLabel("From:");
        lblNewLabel_1_1.setBounds(143, 39, 68, 29);
        lblNewLabel_1_1.setFont(new Font("Times New Roman", Font.BOLD, 15));
        panel.add(lblNewLabel_1_1);

        From = new JTextField();
        From.setBounds(195, 44, 172, 20);
        From.setToolTipText("Enter Location");
        panel.add(From);
        From.setColumns(10);

        JLabel lblNewLabel_1_1_1 = new JLabel("To:");
        lblNewLabel_1_1_1.setBounds(401, 39, 68, 29);
        lblNewLabel_1_1_1.setFont(new Font("Times New Roman", Font.BOLD, 15));
        panel.add(lblNewLabel_1_1_1);

        to = new JTextField();
        to.setBounds(448, 44, 172, 20);
        to.setToolTipText("Enter Location");
        to.setColumns(10);
        panel.add(to);

        JLabel lblNewLabel_1_2 = new JLabel("Filter By Date:");
        lblNewLabel_1_2.setBounds(665, 39, 105, 29);
        lblNewLabel_1_2.setFont(new Font("Times New Roman", Font.BOLD, 15));
        panel.add(lblNewLabel_1_2);

        date = new JTextField();
        date.setBounds(780, 44, 172, 20);
        date.setToolTipText("yyyy-mm-dd");
        date.setColumns(10);
        panel.add(date);

        JButton btnNewButton = new JButton("SEARCH");
        btnNewButton.setBounds(991, 39, 122, 29);
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                DefaultTableModel model = (DefaultTableModel) FlightTable.getModel();
                model.setRowCount(0);
                try {
                    try {
                        Class.forName("com.mysql.cj.jdbc.Driver");
                    } catch (ClassNotFoundException e1) {
                        e1.printStackTrace();
                    }
                    java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://localhost/flightdb", "root", "");
                    System.out.println("connected");
                    java.sql.Statement stmt = con.createStatement();
                    String query = "select flightid, fromcity, tocity, date, deptime, arrtime, fare, seats from flight where fromcity='" + From.getText().toString() + "' and tocity='" + to.getText().toString() + "' and date='" + date.getText().toString() + "';";
                    System.out.println(query);
                    ResultSet myRs = stmt.executeQuery(query);
                    System.out.println(myRs);
                    while (myRs.next()) {
                        String s1 = myRs.getString("flightid");
                        String s2 = myRs.getString("fromcity");
                        String s3 = myRs.getString("tocity");
                        String s4 = myRs.getString("date");
                        String s5 = myRs.getString("deptime");
                        String s6 = myRs.getString("arrtime");
                        String s7 = myRs.getString("fare");
                        String s8 = myRs.getString("seats");
                        model.addRow(new Object[]{s1, s2, s3, s4, s5, s6, s7, s8});
                    }
                    myRs.close();
                    stmt.close();
                    con.close();

                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, ex);
                }
            }
        });
        btnNewButton.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 18));
        panel.add(btnNewButton);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(59, 146, 1091, 272);
        panel.add(scrollPane);

        FlightTable = new JTable();
        FlightTable.setModel(new DefaultTableModel(
                new Object[][]{},
                new String[]{"Flight ID", "From ", "To", "Date", "Departure Time", "Arrival Time", "Fare", "Seats"}
        ));
        scrollPane.setViewportView(FlightTable);

        JButton btnNewButton_1 = new JButton("SELECT THIS FLIGHT");
        btnNewButton_1.setBounds(915, 445, 235, 49);
        btnNewButton_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		int a = FlightTable.getSelectedRow();
                if(a < 0)
                {
                    JOptionPane.showMessageDialog(null, "Please click on desired flight");
                }
                else
                {
                    Object obj1 = FlightTable.getModel().getValueAt(a, 0);
                    System.out.println(obj1);
                    Object obj2 = FlightTable.getModel().getValueAt(a, 1);
                    System.out.println(obj2);
                    Object obj3 = FlightTable.getModel().getValueAt(a, 2);
                    System.out.println(obj3);
                    double fareValue = Double.parseDouble((String) FlightTable.getModel().getValueAt(a, 6));
                    int obj4 = (int) fareValue;
                    System.out.println(obj4);

                    
                    
                    farebox.setText("" + obj4);
                    int gst = (int) (0.05*obj4);
                    gstbox.setText(""+ gst);
                    JOptionPane.showMessageDialog(null, "You have selected flight from " + obj2 + " to " + obj3 + " whose FLIGHT ID  is : " + obj1);
                    labelFrom.setText(""+obj2);
                    labelTo.setText(""+obj3);
                    tSelectedFlight.setText(""+obj1);
                }
        	}
        });
        btnNewButton_1.setFont(new Font("Times New Roman", Font.BOLD, 18));
        panel.add(btnNewButton_1);
        
        JButton btnNewButton_2 = new JButton("Back");
        btnNewButton_2.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		WelcomeUI w = new WelcomeUI();
        				w.setVisible(true);
        	}
        });
        btnNewButton_2.setForeground(new Color(255, 0, 0));
        btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 22));
        btnNewButton_2.setBounds(82, 445, 142, 38);
        panel.add(btnNewButton_2);
        
        JButton jButton3 = new JButton();
        jButton3.setBounds(240, 445, 97, 34);
        panel.add(jButton3);
        jButton3.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		dispose();
        	}
        });
        jButton3.setText("CLOSE");
        jButton3.setBackground(Color.RED);

        JPanel panel_1 = new JPanel();
        tabbedPane.addTab("CUSTOMER DETAILS", null, panel_1, null);
        panel_1.setLayout(null);
        
        JLabel jLabel6 = new JLabel();
        jLabel6.setText("NAME");
        jLabel6.setHorizontalAlignment(SwingConstants.CENTER);
        jLabel6.setBounds(179, 60, 157, 32);
        panel_1.add(jLabel6);
        
        tName = new JTextField();
        tName.setBounds(354, 60, 240, 32);
        panel_1.add(tName);
        
        tAge = new JTextField();
        tAge.setBounds(354, 110, 240, 30);
        panel_1.add(tAge);
        
        JLabel jLabel8 = new JLabel();
        jLabel8.setText("AGE");
        jLabel8.setHorizontalAlignment(SwingConstants.CENTER);
        jLabel8.setBounds(179, 110, 157, 30);
        panel_1.add(jLabel8);
        
        JLabel jLabel13 = new JLabel();
        jLabel13.setText("GENDER");
        jLabel13.setHorizontalAlignment(SwingConstants.CENTER);
        jLabel13.setBounds(179, 158, 157, 31);
        panel_1.add(jLabel13);
        
        JComboBox<String> tGender = new JComboBox<String>();
        tGender.setModel(new DefaultComboBoxModel<String>(new String[] {"MALE", "FEMALE"}));
        tGender.setBounds(354, 158, 131, 31);
        panel_1.add(tGender);
        
        JLabel jLabel10 = new JLabel();
        jLabel10.setText("CONTACT NO.");
        jLabel10.setHorizontalAlignment(SwingConstants.CENTER);
        jLabel10.setBounds(179, 207, 157, 30);
        panel_1.add(jLabel10);
        
        tNo = new JTextField();
        tNo.setBounds(354, 207, 240, 30);
        panel_1.add(tNo);
        
        JLabel jLabel14 = new JLabel();
        jLabel14.setText("EMAIL");
        jLabel14.setHorizontalAlignment(SwingConstants.CENTER);
        jLabel14.setBounds(179, 255, 157, 30);
        panel_1.add(jLabel14);
        
        tMail = new JTextField();
        tMail.setBounds(354, 255, 240, 30);
        panel_1.add(tMail);
        
        JLabel jLabel15 = new JLabel();
        jLabel15.setText("ADDRESS");
        jLabel15.setHorizontalAlignment(SwingConstants.CENTER);
        jLabel15.setBounds(179, 303, 157, 30);
        panel_1.add(jLabel15);
        
        tAddress = new JTextField();
        tAddress.setBounds(354, 303, 240, 109);
        panel_1.add(tAddress);

        JPanel panel_2 = new JPanel();
        tabbedPane.addTab("PAYMENT DETAILS", null, panel_2, null);
        panel_2.setLayout(null);
        
        JLabel jLabel21 = new JLabel();
        jLabel21.setText("NUMBER OF TICKETS");
        jLabel21.setBounds(189, 91, 133, 45);
        panel_2.add(jLabel21);
        
        JLabel jLabel16 = new JLabel();
        jLabel16.setText("BASE FARE ");
        jLabel16.setBounds(189, 171, 133, 51);
        panel_2.add(jLabel16);
        
        JLabel jLabel18 = new JLabel();
        jLabel18.setText("GST(5%)");
        jLabel18.setBounds(189, 255, 133, 54);
        panel_2.add(jLabel18);
        
        JLabel jLabel20 = new JLabel();
        jLabel20.setText("FOOD");
        jLabel20.setBounds(189, 347, 122, 54);
        panel_2.add(jLabel20);
        
        JComboBox<String> noticket = new JComboBox<String>();
        noticket.setModel(new DefaultComboBoxModel<String>(new String[] {"1", "2", "3", "4", "5", "6", "7", "8"}));
        noticket.setBounds(363, 91, 145, 45);
        panel_2.add(noticket);
        
        //JLabel farebox = new JLabel();
        farebox = new JLabel();
        farebox.setToolTipText("");
        farebox.setText("(fare)");
        farebox.setBounds(363, 171, 145, 50);
        panel_2.add(farebox);
        
       // JLabel gstbox = new JLabel();
        gstbox = new JLabel();
        gstbox.setText("(gst)");
        gstbox.setBounds(363, 255, 145, 54);
        panel_2.add(gstbox);
        
        JComboBox<String> food = new JComboBox<String>();
        food.setModel(new DefaultComboBoxModel<String>(new String[] {"NONE", "VEG(200/-)", "NONVEG(300/-)"}));
        food.setBounds(363, 347, 145, 54);
        panel_2.add(food);
        
        JButton jButton4 = new JButton();
        jButton4.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		int x = Integer.parseInt(farebox.getText());
                x = (int) (1.05*x);
                
                if("VEG(200/-)".equals(food.getSelectedItem().toString()))
                {
                    x = x + 200;
                }
                else if("NONVEG(300/-)".equals(food.getSelectedItem().toString()))
                {
                    x = x + 300;
                }
                
                x = x*(Integer.parseInt(noticket.getSelectedItem().toString()));
                
                net.setText(""+x);
                
                JOptionPane.showMessageDialog(null, "Net Amount is " + x);
        	}
        });
        jButton4.setText("CALCULATE NET AMOUNT");
        jButton4.setBounds(173, 457, 405, 41);
        panel_2.add(jButton4);
        
        JButton jButton5 = new JButton();
        jButton5.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		 String cName = tName.getText();
        	        int cAge = Integer.parseInt(tAge.getText());
        	        String cNo = tNo.getText();
        	        String cMail = tMail.getText();
        	        String cAddress = tAddress.getText();
        	        
        	        String cGender = "MALE";
        	        if("MALE".equals(tGender.getSelectedItem().toString()))
        	        {
        	            cGender = "MALE";
        	        }
        	        else if("FEMALE".equals(tGender.getSelectedItem().toString()))
        	        {
        	            cGender = "FEMALE";
        	        }
        	        int ne = Integer.parseInt(net.getText());
        	        Statement stmt = null;
        	        Statement stmt2 = null;
        	       // Statement stmt3 = null;
        	        try
        	        {
        	        	try {
        					Class.forName("com.mysql.cj.jdbc.Driver");
        				} catch (ClassNotFoundException e1) {
        					e1.printStackTrace();
        				}
        	            java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://localhost/flightdb","root","");
        	            stmt = (Statement) con.createStatement();
        	            stmt2 = (Statement) con.createStatement();
        	           // stmt3 = (Statement) con.createStatement();
        	            
        	            String query = "insert into customer(cusname, cusage, cusgender, cuscontact, cusmail, cusaddress, bookfid, tickets, flamount)values('"+cName+"',"+cAge+",'"+cGender+"','"+cNo+"','"+cMail+"','"+cAddress+"','"+tSelectedFlight.getText()+"',"+Integer.parseInt(noticket.getSelectedItem().toString())+","+ne+");";
        	            stmt.executeUpdate(query);
        	            String query3 = "update airline set seats = seats-"+Integer.parseInt(noticket.getSelectedItem().toString())+" where fid = '"+tSelectedFlight.getText()+"';";
        	            String query4 = "update flight set seats = seats-"+Integer.parseInt(noticket.getSelectedItem().toString())+" where flightid = '"+tSelectedFlight.getText()+"';";
        	            stmt2.executeUpdate(query3);
        	            stmt2.executeUpdate(query4);
        	            JOptionPane.showMessageDialog(null,"Processing your request.");
        	            
        	            try 
        	            {
        	            	Class.forName("com.mysql.cj.jdbc.Driver");
        	                Connection con1 = DriverManager.getConnection("jdbc:mysql://localhost/flightdb","root","");
        	                Statement stmt1 = con1.createStatement();
        	                String query2="select cusid from customer order by cusid desc limit 1;";
        	                ResultSet rs4 =stmt1.executeQuery(query2);
        	                while(rs4.next()) {
        	                    String sno= rs4.getString("cusid");
        	                    JOptionPane.showMessageDialog(null,"Booking Successful, Please note down following data for reference:\nCustomer ID: "+sno+"\nFlight ID: "+tSelectedFlight.getText()+"");
        	                    
        	                    //this.setVisible(false);  
        	            }
        	                rs4.close();
        	                stmt1.close();
        	                con1.close();
        	            }
        	            catch(Exception e1)
        	            {
        	                JOptionPane.showMessageDialog(null,"Processing failed. Please try again later");  
        	            }
        	            
        	            
        	        }
        	        catch(SQLException e1)
        	        {
        	            JOptionPane.showMessageDialog(null,"One or more fields not filled correctly. \nPlease make sure you have filled all the details properly.");
        	        }
        	}
        });
        jButton5.setText("BOOK FLIGHT");
        jButton5.setBackground(new Color(0, 255, 51));
        jButton5.setBounds(688, 457, 240, 41);
        panel_2.add(jButton5);
        
        JLabel jLabel5 = new JLabel();
        jLabel5.setText("Cuurent Selection :    From");
        jLabel5.setHorizontalAlignment(SwingConstants.CENTER);
        jLabel5.setFont(new Font("Tahoma", Font.BOLD, 13));
        jLabel5.setBounds(212, 690, 181, 34);
        contentPane.add(jLabel5);
        
       // JLabel labelFrom = new JLabel();
        labelFrom = new JLabel();
        labelFrom.setText("(location)");
        labelFrom.setHorizontalAlignment(SwingConstants.CENTER);
        labelFrom.setBounds(403, 691, 103, 34);
        contentPane.add(labelFrom);
        
        JLabel jLabel7 = new JLabel();
        jLabel7.setText("To");
        jLabel7.setHorizontalAlignment(SwingConstants.CENTER);
        jLabel7.setFont(new Font("Tahoma", Font.BOLD, 13));
        jLabel7.setBounds(512, 690, 58, 34);
        contentPane.add(jLabel7);
        
        //JLabel labelTo = new JLabel();
        labelTo = new JLabel();
        labelTo.setText("(location)");
        labelTo.setHorizontalAlignment(SwingConstants.CENTER);
        labelTo.setBounds(580, 691, 103, 34);
        contentPane.add(labelTo);
        
        JLabel jLabel9 = new JLabel();
        jLabel9.setText("ID of selected flight :");
        jLabel9.setHorizontalAlignment(SwingConstants.CENTER);
        jLabel9.setFont(new Font("Tahoma", Font.BOLD, 13));
        jLabel9.setBounds(693, 691, 145, 34);
        contentPane.add(jLabel9);
        
        //JLabel tSelectedFlight = new JLabel();
        tSelectedFlight = new JLabel();
        tSelectedFlight.setText("(ID)");
        tSelectedFlight.setHorizontalAlignment(SwingConstants.CENTER);
        tSelectedFlight.setBounds(835, 691, 82, 34);
        contentPane.add(tSelectedFlight);
        
        JLabel jLabel11 = new JLabel();
        jLabel11.setText("Net Amount :");
        jLabel11.setHorizontalAlignment(SwingConstants.CENTER);
        jLabel11.setFont(new Font("Tahoma", Font.BOLD, 13));
        jLabel11.setBounds(936, 690, 114, 34);
        contentPane.add(jLabel11);
        
        //JLabel net = new JLabel();
        net = new JLabel();
        net.setText("(Amount)");
        net.setHorizontalAlignment(SwingConstants.CENTER);
        net.setBounds(1060, 691, 82, 34);
        contentPane.add(net);
        
        JLabel lblNewLabel_2 = new JLabel("");
        lblNewLabel_2.setIcon(new ImageIcon(BookingUI.class.getResource("/images/img4.jpg")));
        lblNewLabel_2.setBounds(0, 11, 1273, 758);
        contentPane.add(lblNewLabel_2);
    }
}
