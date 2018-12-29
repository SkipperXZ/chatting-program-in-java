import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.*;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Server {

    static Map<String, DataOutputStream> clients;
    Server() {
        clients = Collections.synchronizedMap
                (new HashMap<String, DataOutputStream>());
    }
    public   boolean seachsql(String Username,String Password)throws Exception
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
    }

    void startServer()
    {

        ServerSocket serverSocket=null;
        Socket socket=null;
        DataInputStream dataIn=null;
        DataOutputStream dataOut=null;
        try {
            serverSocket = new ServerSocket(11179);
            while (true) {
                socket = serverSocket.accept();
                dataIn=new DataInputStream(socket.getInputStream());
                dataOut = new DataOutputStream(socket.getOutputStream());
                String User=dataIn.readUTF();
                String Pass = dataIn.readUTF();
                if (User.equals("jopark123")&&Pass.equals("15394772")) {
                  dataOut.writeBoolean(true);
                    System.out.println(socket.getInetAddress() + " " + "Connected");
                    dataIn.close();
                    ServerReceiver serverReceiver = new ServerReceiver(socket);
                    ServerReceiver thread = serverReceiver;
                    thread.start();
                }
                else
                {
                    dataOut.writeBoolean(false);
                }

            }
        }
        catch (Exception e)
        {
            System.out.println("Connection Failed");
            System.out.println(e);
        }
        finally {
            try {
                dataIn.close();
                dataOut.close();
            }catch (Exception e)
            {
                System.out.println("close");
            }

        }
    }

    public static void main(String[] args) {
        new Server().startServer();
    }
    static public void ChatModel(String message) {
        Iterator<String> it = clients.keySet().iterator();

        while(it.hasNext()) {
            try {
                String name = it.next();
                DataOutputStream out = clients.get(name);
                out.writeUTF(message);
            } catch(IOException e) {
                System.out.println("Map Failed");
            }
        }
    }


}
