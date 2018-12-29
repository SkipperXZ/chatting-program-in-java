package sample;

import java.sql.*;

/**
 * Created by duckk on 7/3/2560.
 */
public class LoginModel {

  /*  public   boolean seachsql(String Username,String Password)throws Exception
    {
        Class.forName("org.sqlite.JDBC");
        Connection  conn = DriverManager.getConnection("jdbc:sqlite:userpassword.sqlite");
        Statement statement = conn.createStatement();
        ResultSet resultSet=null;
        PreparedStatement pstmt=null;
        String sql= "SELECT * FROM product WHERE Username=? AND Password=?";
        statement.executeUpdate(sql);
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,Username);
            pstmt.setString(2,Password);
            resultSet= pstmt.executeQuery();
            if (resultSet.next()) {
                return true;
            }
            else{
                return  false;
            }
        }
        catch (Exception ex)
        {
            return false;
        }
        finally {
            pstmt.close();
            resultSet.close();
        }
    }*/
    public  boolean registersql(String Username,String Password,String comfirmPassword)throws Exception
    {
        Class.forName("org.sqlite.JDBC");
        Connection  conn = DriverManager.getConnection("jdbc:sqlite:userpassword.sqlite");
        Statement statement = conn.createStatement();
        if (Password.equals(comfirmPassword)) {
            String sql = "INSERT INTO product(id,Username,Password) VALUES(?,?,?)";
            statement.executeUpdate(sql);
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(2, Username);
            pstmt.setString(3, Password);
            pstmt.executeUpdate();
            pstmt.close();
            return  true;
        }
        else {
            return false;
        }
    }
}
