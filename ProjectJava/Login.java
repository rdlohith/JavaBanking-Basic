import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class Login extends JFrame implements ActionListener
{
    JLabel l1, l2, l3;
    JTextField tf1;
    JPasswordField pf1;
    JButton btn1, btn2;
    JPanel panel;
    GridLayout layout;
   public Login()
    {
        setVisible(true);
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setTitle("Login");
        l1 = new JLabel("Login Details");
        l2 = new JLabel("Account Number");
        l3 = new JLabel("PIN");
        tf1 = new JTextField(10);
        pf1 = new JPasswordField(10);
        btn1 = new JButton("Login");
        btn2 = new JButton("Register");
        btn1.addActionListener(this);
        btn2.addActionListener(this);
        layout = new GridLayout();
        layout.setColumns(2);
        layout.setRows(3);
        layout.setHgap(5);
        layout.setVgap(15);
        panel = new JPanel(layout);
        panel.setBorder(BorderFactory.createEmptyBorder(15,50,60,50));
        l1.setBorder(BorderFactory.createEmptyBorder(20,120,15,120));
        add(l1, BorderLayout.NORTH);
        panel.add(l2);
        panel.add(tf1);
        panel.add(l3);
        panel.add(pf1);
        panel.add(btn1);
        panel.add(btn2);
        add(panel, BorderLayout.CENTER);          
    }
    public static void main(String args[])
    {
        new Login();
    }
    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == btn1)
        {
           MyConnection con = new MyConnection();
          if (!"".equals(tf1.getText()) && !"".equals(pf1.getText()))
          {
           try
           {
             con.Login(Integer.parseInt(tf1.getText()),Integer.parseInt(pf1.getText()));
           }
           catch(Exception ex)
           {
              System.out.print(ex.getMessage());
            }
           if(con.getState1() == true)
           {
               Account a = new Account();
               a.setVisible(true);
               this.setVisible(false);
           }
           else
           {
               JOptionPane.showMessageDialog(this, "Invalid Credentials");
           }
         }
          else
          {
              JOptionPane.showMessageDialog(this, "All Fields Required");
          }
    }
        if(e.getSource() == btn2)
        {
            Registration a = new Registration();
            a.setVisible(true);
            this.setVisible(false);
        }
}
}