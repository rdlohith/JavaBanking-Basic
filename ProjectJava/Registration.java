import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class Registration extends JFrame implements ActionListener
{
    JLabel l1, l2, l3, l4, l5, l6, l7, l8, l9;
    JTextField tf1, tf2, tf3, tf4, tf5, tf6;
    JPasswordField pf1, pf2;
    JButton btn1, btn2;
    JPanel components;
    GridLayout layout;
    public Registration()
    {
        setVisible(true);
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setTitle("New Account");
        l1 = new JLabel("Register New Account");
        l2 = new JLabel("First Name  *");
        l3 = new JLabel("Last Name  *");
        l4 = new JLabel("Email");
        l5 = new JLabel("ID Number  *");
        l6 = new JLabel("Phone Number  *");
        l7 = new JLabel("MPIN Pin  *");
        l8 = new JLabel("PIN  *");
        l9 = new JLabel("Confirm PIN  *");
        tf1 = new JTextField(10);
        tf2 = new JTextField(10);
        tf3 = new JTextField(30);
        tf4 = new JTextField(10);
        tf5 = new JTextField(10);
        tf6 = new JTextField(10);
        pf1 = new JPasswordField(10);
        pf2 = new JPasswordField(10);
        btn1 = new JButton("Register");
        btn2 = new JButton("Back");
        btn1.addActionListener(this);
        btn2.addActionListener(this);
        layout = new GridLayout();
        layout.setColumns(2);
        layout.setRows(9);
        layout.setHgap(8);
        layout.setVgap(5);
        components = new JPanel();
        components.setLayout(layout);
        components.setBorder(BorderFactory.createEmptyBorder(5,50,50,50));
        l1.setBorder(BorderFactory.createEmptyBorder(5,100,5,100));
        add(l1, BorderLayout.NORTH);
        components.add(l2);
        components.add(tf1);
        components.add(l3);
        components.add(tf2);
        components.add(l4);
        components.add(tf3);
        components.add(l5);
        components.add(tf4);
        components.add(l6);
        components.add(tf5);
        components.add(l7);
        components.add(tf6);
        components.add(l8);
        components.add(pf1);
        components.add(l9);
        components.add(pf2);       
        components.add(btn1);
        components.add(btn2);
        add(components, BorderLayout.CENTER);
    }
    @Override
    public void actionPerformed(ActionEvent e)
    {
        String fname = "", lname = "", email = "", MPIN = "", pin = "";
        int id = 0, phoneNo = 0;
        if(e.getSource() == btn1)
        {
            MyConnection con = new MyConnection();  
            if (!"".equals(tf1.getText()) && !"".equals(tf2.getText()) && !"".equals(tf4.getText()) && !"".equals(tf5.getText()) && !"".equals(tf6.getText()) && !"".equals(pf1.getText()) && !"".equals(pf2.getText()))
            {
                fname = tf1.getText();
                lname = tf2.getText();
                email = tf3.getText();
                MPIN = tf6.getText();
               try
               {
                 id = Integer.parseInt(tf4.getText());
               }
               catch(Exception ex)
               {
                   ex.getMessage();
                   JOptionPane.showMessageDialog(this, "Invalid ID Number\nID has 10 digits only");
               }
               try
               {
                  phoneNo = Integer.parseInt(tf5.getText());
               }
               catch(Exception ex)
               {
                   ex.getMessage();
                   JOptionPane.showMessageDialog(this, "Invalid Phone Number\nPhone Number has 10 digits only");
               }
               if(pf1.getText().equals(pf2.getText()))
               {
                   try
                   {
                      if(pf1.getText().length() != 4)
                      {
                          JOptionPane.showMessageDialog(this, "PIN is supposed to be 4 digits long");
                      }
                       else
                      {
                          try
                          {
                              pin = pf1.getText();
                          }
                          catch(Exception ex)
                          {
                              ex.getMessage();
                              JOptionPane.showMessageDialog(this, "No letters allowed for pin");
                          }
                      }
                   }
                   catch(Exception ex)
                   {
                       System.out.print(ex.getMessage());
                   }
               }
               else
               {
                   JOptionPane.showMessageDialog(this, "PINs do not match");
               }
               if(!"".equals(fname) && !"".equals(lname) && id != 0 && phoneNo != 0 && !"".equals(MPIN) && !"".equals(pin))
               {
                   con.newAccount(fname, lname, email, id, phoneNo, MPIN, pin);
                   if (con.getAccNo() == 0)
            {
                JOptionPane.showMessageDialog(this, "Registration Unsuccessful");
            }
            else
            {
                JOptionPane.showMessageDialog(this, "Registration Successful\n\n\tAccountNo:      " + con.getAccNo());
            }
               }               
            }    
            else
            {
                JOptionPane.showMessageDialog(this, "Fields with * are mandatory");
            }
        }
        if(e.getSource() == btn2)
        {
            Login b = new Login();
            b.setVisible(true);
            this.setVisible(false);
        }
    }   
}