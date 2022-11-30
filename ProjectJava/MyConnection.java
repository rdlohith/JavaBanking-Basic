import java.sql.*;
import java.awt.Toolkit;
import javax.swing.*;

public class MyConnection
{
    Connection conn;
    PreparedStatement ps;
    ResultSet rs;
    Boolean state1 = false, state2 = false, state3 = false;
    
    private String name;
    private double balance;
    private int acc;
    
    public Boolean getState1()
    {
        return state1;
    }
    
    public void setState1(Boolean s)
    {
        state1 = s;
    }
    
    public Boolean getState2()
    {
        return state2;
    }
    
    public void setState2(Boolean s)
    {
        state2 = s;
    }
    
     public Boolean getState3()
    {
        return state3;
    }
    
    public void setState3(Boolean s)
    {
        state3 = s;
    }
    
     public int getAccNo()
    {
        return acc;
    }
    
    public void setAccNo(int acc)
    {
        this.acc = acc;
    }
    
    public String getName()
    {
        return name;
    }
    
    public void setName(String name)
    {
        this.name = name;
    }
    
    public double getBal()
    {
        return balance;
    }
    
    public void setBal(double bal)
    {
        balance = bal;
    }
        
    public void DoConnect()
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            String host = "jdbc:mysql://localhost/banking"; //Database URL
            String uName = "root";
            String uPass = "Chandan@123";
            
            conn = DriverManager.getConnection(host,uName,uPass);           
        }
        catch(SQLException | ClassNotFoundException e)
        {
            System.out.print(e.getMessage());
        }
    }
    
    public void Login(int account, int pin)
    {
        try
        {
        DoConnect();
        ps = conn.prepareStatement("Select Account_No, Pin from accounts where Account_No = ? and Pin = ?");
        ps.setInt(1, account);
        ps.setInt(2, pin);
        rs = ps.executeQuery();
        if(rs.next())
         {
            this.setState1(true);
            this.setAccNo(rs.getInt("Account_No"));
            ps = conn.prepareStatement("Insert into login (Account_No) values (?)");
            ps.setInt(1, this.getAccNo());
            ps.executeUpdate();
            
         }  
        }
        catch(SQLException e)
        {
            System.out.print(e.getMessage());
        }
    }
    
    public void withdraw(double amount)
    {
        try
        {
            DoConnect();
            ps = conn.prepareStatement("Select Account_No from login order by Login_ID Desc LIMIT 1");
            rs = ps.executeQuery();
            if (rs.next())
            {
             this.setAccNo(rs.getInt("Account_No"));
             System.out.print(this.getAccNo());
             ps = conn.prepareStatement("Select Balance from accounts where Account_No = ?");
             ps.setInt(1, this.getAccNo());
             rs = ps.executeQuery();
             rs.next();
             this.setBal(rs.getDouble("Balance"));
             
             if (balance != 0 && balance >= amount)
        {
            try
            {
            balance = balance - amount;
            ps = conn.prepareStatement("Update accounts set Balance = ? where Account_No = ?");
            ps.setDouble(1, this.getBal());
            ps.setInt(2, this.getAccNo());
            ps.executeUpdate();
            this.setState2(true);
            }
            catch(SQLException ex)
            {
                ex.getMessage();
            }
        }
            }
           
        }
        catch(Exception ex)
        {
            System.out.print(ex.getMessage());
        }                
    }
    
    public void deposit(double amount)
    {
       try
       {
           DoConnect();
           ps = conn.prepareStatement("Select Account_No from login order by Login_ID Desc LIMIT 1");
           rs = ps.executeQuery();
           if (rs.next())
           {
           this.setAccNo(rs.getInt("Account_No"));
           ps = conn.prepareStatement("Select Balance from accounts where Account_No = ?");
           ps.setInt(1, this.getAccNo());
           rs = ps.executeQuery();
           rs.next();
           this.setBal(rs.getDouble("Balance"));
           balance = balance + amount;
           ps = conn.prepareStatement("Update accounts set Balance = ? where Account_No = ?");
           ps.setDouble(1, this.getBal());
           ps.setInt(2, this.getAccNo());
           ps.executeUpdate();
           this.setState3(true);
           }
       }
       catch(SQLException ex)
       {
           System.out.print(ex.getMessage());
       }      
    }
    
    public void myBal()
    {        
        try
        {
           DoConnect();
           ps = conn.prepareStatement("Select Account_No from login order by Login_ID Desc LIMIT 1");
           rs = ps.executeQuery();
           if (rs.next())
           {
           this.setAccNo(rs.getInt("Account_No"));
           ps = conn.prepareStatement("Select Balance from accounts where Account_No = ?");
           ps.setInt(1, this.getAccNo());
           rs = ps.executeQuery();
           rs.next();
           this.setBal(rs.getDouble("Balance"));
           }
        }
        catch(SQLException ex)
        {
            System.out.print(ex.getMessage());
        }
                     
    }
    
    public void newAccount(String fname, String lname, String email, int id, int phoneNo, String MPIN, String pin)
    {
        //int acc = 0;        
        try
        {
            DoConnect();
            ps = conn.prepareStatement("Insert into accounts (FName, LName, Email, ID_Number, PhoneNo, MPIN_Pin, Pin) values (?,?,?,?,?,?,?)");
            ps.setString(1, fname);
            ps.setString(2, lname);
            ps.setString(3, email);
            ps.setInt(4, id);
            ps.setInt(5, phoneNo);
            ps.setString(6, MPIN);
            ps.setString(7, pin);
            ps.executeUpdate();
            ps = conn.prepareStatement("Select Account_No from accounts where ID_Number = ?");
            ps.setInt(1, id);
            System.out.print(id);
            System.out.print(phoneNo);
            rs = ps.executeQuery();
            if (rs.next())
            {
               this.setAccNo(rs.getInt("Account_No"));
            }
        }
        catch(SQLException ex)
        {
            System.out.print(ex.getMessage());
        }       
    }
    
    public void transfer(int account, double amount)
    {
        double acc2Bal = 0;
        try
        {
           DoConnect();
           ps = conn.prepareStatement("Select Account_No from login order by Login_ID Desc LIMIT 1");
           rs = ps.executeQuery();
           if (rs.next())
           {
              this.setAccNo(rs.getInt("Account_No"));
              
              if(acc != account)
              {
                  this.setState3(true);
              ps = conn.prepareStatement("Select Balance from accounts where Account_No = ?");
              ps.setInt(1, this.getAccNo());
              rs = ps.executeQuery();
              rs.next();
              this.setBal(rs.getDouble("Balance"));
              
              ps = conn.prepareStatement("Select FName, Balance from accounts where Account_No = ?");
              ps.setInt(1, account);
              rs = ps.executeQuery();
              if(rs.next())
              {
                  this.setState1(true);
                  acc2Bal = rs.getDouble("Balance");
                  this.setName(rs.getString("FName"));
                  if(balance != 0 && balance >= amount)
           {
               balance = balance - amount;
               ps = conn.prepareStatement("Update accounts set Balance = ? where Account_No = ?");
               ps.setDouble(1, this.getBal());
               ps.setInt(2, this.getAccNo());
               ps.executeUpdate();
               
               acc2Bal = acc2Bal + amount;
               ps = conn.prepareStatement("Update accounts set Balance = ? where Account_No = ?");
               ps.setDouble(1, acc2Bal);
               ps.setInt(2, account);
               ps.executeUpdate();
               this.setState2(true);
           }
              }          
              }
              
           }          
        }
        catch(SQLException ex)
        {
            ex.getMessage();
        }
    }
}
