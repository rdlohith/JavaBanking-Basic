import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class Transfer extends JFrame implements ActionListener
{
    JLabel l1, l2, l3;
    JTextField tf1, tf2;
    JButton btn1, btn2, btn3, btn4;
    JPanel panel1, panel2;
    FlowLayout layout2;
    GridLayout layout1;
    public Transfer()
    {
        setVisible(true);
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setTitle("Transfer");
        l1 = new JLabel("TRANSFER");
        l2 = new JLabel("Account");
        l3 = new JLabel("Amount");
        tf1 = new JTextField(10);
        tf2 = new JTextField(10);
        btn1 = new JButton("Confirm");
        btn2 = new JButton("Go");
        btn3 = new JButton("Back");
        btn4 = new JButton("Logout");
        btn1.addActionListener(this);
        btn2.addActionListener(this);
        btn3.addActionListener(this);
        btn4.addActionListener(this);
        layout1 = new GridLayout();
        layout1.setColumns(3);
        layout1.setRows(2);
        layout1.setHgap(5);
        layout1.setVgap(10);
        panel1 = new JPanel();
        panel1.setLayout(layout1);
        panel1.setBorder(BorderFactory.createEmptyBorder(15,50,25,50));
        layout2 = new FlowLayout();
        layout2.setHgap(10);      
        panel2 = new JPanel();
        panel2.setLayout(layout2);
        panel2.setBorder(BorderFactory.createEmptyBorder(5,50,15,50));        
        l1.setBorder(BorderFactory.createEmptyBorder(50,150,10,150));
        add(l1, BorderLayout.NORTH);
        panel1.add(l2);
        panel1.add(tf1);
        panel1.add(btn1);
        panel1.add(l3);
        panel1.add(tf2);
        panel1.add(btn2);
        add(panel1, BorderLayout.CENTER);
        panel2.add(btn3);
        panel2.add(btn4);
        add(panel2, BorderLayout.SOUTH);
    }
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == btn1)
        {
           if(!"".equals(tf1.getText()) && !"".equals(tf2.getText()))
           {
               JOptionPane.showMessageDialog(this, "Confirm Details\n\nAccount:      " + tf1.getText() + "\n\nAmount:      " + tf2.getText());
           }
           else
           {
               JOptionPane.showMessageDialog(this, "All fields required");
           }
        }
        if(e.getSource() == btn2)
        {
           MyConnection con = new MyConnection();
           if(!"".equals(tf1.getText()) && !"".equals(tf2.getText()))
           {
               try
               {
                   con.transfer(Integer.parseInt(tf1.getText()), Double.parseDouble(tf2.getText()));
                   if(con.getState3() == true)
                   {
                       if(con.getState1() == false)
                   {
                       JOptionPane.showMessageDialog(this, "Account not Found");
                   }
                   if(con.getState2() == true)
                   {
                       JOptionPane.showMessageDialog(this, "Transfer Successful\n\nAccount:      " + tf1.getText() + "\n\nAccount Name:   " + con.getName() + "\n\nAmount:      " + tf2.getText() + "\n\nBalance:      " + con.getBal());
                   }
                   else
                   {
                       JOptionPane.showMessageDialog(this, "Transfer Unsuccessful\n\nBalance:      " + con.getBal());
                   }
                   }
                   else
                   {
                       JOptionPane.showMessageDialog(this, "You cannot tranfer to your account");
                   }
               }
               catch(Exception ex)
               {
                   System.out.print(ex.getMessage());
                   JOptionPane.showMessageDialog(this, "Invalid Account Number or Amount");
               }
           }
           else
           {
               JOptionPane.showMessageDialog(this, "All fields required");
           }
        }
        if(e.getSource() == btn3)
        {
            Account a = new Account();
            a.setVisible(true);
            this.setVisible(false);
        }
        if(e.getSource() == btn4)
        {
            Login a = new Login();
            a.setVisible(true);
            this.setVisible(false);
        }
    }
}