import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class Withdraw extends JFrame implements ActionListener
{
    JLabel l1, l2;
    JTextField tf1;
    JButton btn1, btn2, btn3;
    JPanel panel1, panel2;
    FlowLayout layout;
    public Withdraw()
    {
        setVisible(true);
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setTitle("Withdraw");
        l1 = new JLabel("WITHDRAW");
        l2 = new JLabel("Amount");
        tf1 = new JTextField(10);
        btn1 = new JButton("Go");
        btn2 = new JButton("Back");
        btn3 = new JButton("Logout");
        btn1.addActionListener(this);
        btn2.addActionListener(this);
        btn3.addActionListener(this);
        layout = new FlowLayout();
        layout.setHgap(10);      
        panel1 = new JPanel();
        panel1.setLayout(layout);
        panel1.setBorder(BorderFactory.createEmptyBorder(10,50,15,50));
        panel2 = new JPanel();
        panel2.setLayout(layout);
        panel2.setBorder(BorderFactory.createEmptyBorder(15,50,70,50));
        l1.setBorder(BorderFactory.createEmptyBorder(50,150,10,150));
        add(l1, BorderLayout.NORTH);
        panel1.add(l2);
        panel1.add(tf1);
        panel1.add(btn1);
        add(panel1, BorderLayout.CENTER);
        panel2.add(btn2);
        panel2.add(btn3);
        add(panel2, BorderLayout.SOUTH);
    }
    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == btn1)
        {
           MyConnection con = new MyConnection();
           if(!"".equals(tf1.getText()))
             {
               try
                {
                  con.withdraw(Double.parseDouble(tf1.getText()));
                  if(con.getState2() == true)
           {
               JOptionPane.showMessageDialog(this, "Withdrawal Successful" + "\n\n\tBalance:     " + con.getBal());
           }
           else
           {
               JOptionPane.showMessageDialog(this, "You have insuffient funds" + "\n\n\tBalance:      " + con.getBal());
           }
                }
               catch(Exception ex)
                {
                  ex.getMessage();
                  JOptionPane.showMessageDialog(this, "Invalid Amount");               
                }  
               }
           else
           {
               JOptionPane.showMessageDialog(this, "Field is Empty");
           }
           
        }
        if(e.getSource() == btn2)
        {
            Account a = new Account();
            a.setVisible(true);
            this.setVisible(false);
        }
        if(e.getSource() == btn3)
        {
            Login a = new Login();
            a.setVisible(true);
            this.setVisible(false);
        }
    }
}