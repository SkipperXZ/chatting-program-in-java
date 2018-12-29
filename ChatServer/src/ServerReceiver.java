import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

/**
 * Created by duckk on 15/3/2560.
 */
public class ServerReceiver extends Thread {
    Socket socket;
    DataInputStream dataIn;
    DataOutputStream dataOut;

    ServerReceiver(Socket socket)
    {
        this.socket=socket;
        try {
             dataIn = new DataInputStream(socket.getInputStream());
             dataOut = new DataOutputStream(socket.getOutputStream());
        }catch (Exception e) {
            System.out.println("dsadasdasd");
            System.out.println(e);
        }

    }
    @Override
    public void run() {
        String name = "noName";
        try{
            name=dataIn.readUTF();
            System.out.println(name);
            Server.ChatModel(name+" : "+"Connected");
            Server.clients.put(name,dataOut);
            while(dataIn!=null) {
                Server.ChatModel(name + " : " + dataIn.readUTF());
            }
        }catch (Exception e)
        {
            System.out.println(e);
            System.out.println("read Error");
        }
        finally {
            Server.ChatModel(name + " : Disconnected");
            Server.clients.remove(name);
        }
    }
}

