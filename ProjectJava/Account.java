import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class Account extends JFrame implements ActionListener
{
    JLabel l1;
    JButton btn1, btn2, btn3, btn4, btn5;
    JPanel panel;
    GridLayout layout;
    Dimension dims;
    public Account()
    {
        setVisible(true);
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setTitle("Account");
        l1 = new JLabel("Welcome");
        btn1 = new JButton("Withdraw");
        btn2 = new JButton("Deposit");
        btn3 = new JButton("Balance");
        btn4 = new JButton("Transfer");
        btn5 = new JButton("Logout");
        btn1.addActionListener(this);
        btn2.addActionListener(this);
        btn3.addActionListener(this);
        btn4.addActionListener(this);
        btn5.addActionListener(this);
        layout = new GridLayout();
        layout.setColumns(1);
        layout.setRows(5);
        layout.setVgap(20);
        panel = new JPanel(layout);
        panel.setBorder(BorderFactory.createEmptyBorder(25,150,100,150));
        l1.setBorder(BorderFactory.createEmptyBorder(50,120,25,120));
        add(l1, BorderLayout.NORTH);
        panel.add(btn1);
        panel.add(btn2);
        panel.add(btn3);
        panel.add(btn4);
        panel.add(btn5);
        add(panel, BorderLayout.CENTER);
    }
    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == btn1)
        {
            Withdraw a = new Withdraw();
            a.setVisible(true);
            this.setVisible(false);
        }
        if(e.getSource() == btn2)
        {
            Deposit a = new Deposit();
            a.setVisible(true);
            this.setVisible(false);
        }
        if(e.getSource() == btn3)
        {
            Balance a = new Balance();
            a.setVisible(true);
            this.setVisible(false);
        }
        if(e.getSource() == btn4)
        {
            Transfer a = new Transfer();
            a.setVisible(true);
            this.setVisible(false);
        }
        if(e.getSource() == btn5)
        {
            Login a = new Login();
            a.setVisible(true);
            this.setVisible(false);
        }
    }
}