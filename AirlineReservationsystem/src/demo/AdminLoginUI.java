package demo;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;

public class AdminLoginUI extends JFrame {

    private JPanel contentPane;
    private JTextField user1;
    private JPasswordField pass1;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    AdminLoginUI frame = new AdminLoginUI(); // Corrected class name here
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public AdminLoginUI() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
       
      // setExtendedState(JFrame.MAXIMIZED_BOTH);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(204, 229, 255));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JLabel lblNewLabel = new JLabel("Authentication Required\r\n");
        lblNewLabel.setForeground(Color.WHITE);
        lblNewLabel.setFont(new Font("Century Gothic", Font.BOLD | Font.ITALIC, 30));
        lblNewLabel.setBounds(466, 93, 414, 58);
        contentPane.add(lblNewLabel);
        
        JLabel jLabel2 = new JLabel("Username\r\n:");
        jLabel2.setForeground(Color.WHITE);
        jLabel2.setFont(new Font("Century Gothic", Font.BOLD, 27));
        jLabel2.setBounds(377, 271, 168, 44);
        contentPane.add(jLabel2);
        
        user1 = new JTextField();
        user1.setBounds(593, 271, 240, 44);
        contentPane.add(user1);
        user1.setColumns(10);
        
        JLabel jLabel3 = new JLabel("Password:");
        jLabel3.setForeground(Color.WHITE);
        jLabel3.setFont(new Font("Century Gothic", Font.BOLD, 27));
        jLabel3.setBounds(377, 366, 168, 44);
        contentPane.add(jLabel3);
        
        pass1 = new JPasswordField();
        pass1.setBounds(593, 366, 240, 39);
        contentPane.add(pass1);
        pass1.setColumns(10);
        
        JButton jButton1 = new JButton("Login");
        jButton1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                 String s1 = user1.getText();
                    char[] s2 = pass1.getPassword();
                    char[] actualpass = {'a', 'd', 'm', 'i', 'n'};
                    if("admin".equals(s1) && Arrays.equals(actualpass, s2))
                    {
                        JOptionPane.showMessageDialog(null,"Successful Login. Welcome ADMIN");
                        AdminUI aframe = new AdminUI();
                        aframe.setVisible(true); 
                        
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null,"Incorrect Username or Password");
                    }
            }
        });
        jButton1.setFont(new Font("Century Gothic", Font.BOLD, 17));
        jButton1.setBounds(732, 457, 101, 31);
        contentPane.add(jButton1);
        
        JLabel lblNewLabel_1 = new JLabel("");
        lblNewLabel_1.setIcon(new ImageIcon(AdminLoginUI.class.getResource("/images/img3.jpg")));
        lblNewLabel_1.setBounds(10, 11, 1263, 758);
        contentPane.add(lblNewLabel_1);
    }
}
