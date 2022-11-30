import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class Balance extends JFrame implements ActionListener
{
    JLabel l1, l2;
    JTextField tf1;
    JButton btn1, btn2;
    JPanel panel;
    GridLayout layout;
    public Balance()
    {
        setVisible(true);
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setTitle("Balance");
        l1 = new JLabel("BALANCE");
        l2 = new JLabel("Amount");
        tf1 = new JTextField(10);
        btn1 = new JButton("Back");
        btn2 = new JButton("Logout");
        btn1.addActionListener(this);
        btn2.addActionListener(this);
        layout = new GridLayout();
        layout.setColumns(2);
        layout.setRows(2);
        layout.setVgap(10);
        layout.setHgap(5);
        panel = new JPanel(layout);
        panel.setBorder(BorderFactory.createEmptyBorder(10,120,80,120));            
        l1.setBorder(BorderFactory.createEmptyBorder(50,150,10,150));
        add(l1, BorderLayout.NORTH);
        panel.add(l2);
        panel.add(tf1);
        panel.add(btn1);
        panel.add(btn2);
        add(panel, BorderLayout.CENTER);
        tf1.setEditable(false);
        MyConnection con = new MyConnection();
        con.myBal();
        tf1.setText(Double.toString(con.getBal()));
    }
    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == btn1)
        {
            Account a = new Account();
            a.setVisible(true);
            this.setVisible(false);
        }
        if(e.getSource() == btn2)
        {
            Login a = new Login();
            a.setVisible(true);
            this.setVisible(false);
        }
    } 
}