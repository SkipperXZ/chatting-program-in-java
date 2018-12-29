package sample;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
public class ChatApp {
    @FXML
    public JFXTextArea messageArea;
    @FXML
    public JFXTextField messageBox;
    @FXML
    public JFXButton sendButton;;
    String messageInput;
    DataOutputStream dataOut;
    DataInputStream dataIn;
    @FXML
    public void initialize()
    {
        try{
            Socket socket = new Socket("127.0.0.1",11179);
            dataIn = new DataInputStream(socket.getInputStream());
            dataOut = new DataOutputStream(socket.getOutputStream());
            dataOut.writeUTF(data.name);
            receiveClient();
        }catch (Exception e)
        {
            System.out.println("Can't connect server");
            System.out.println(e);
        }
    }
    @FXML
    public void makeSend(ActionEvent event){
        try {
            dataOut.writeUTF(messageBox.getText());
            messageBox.setText("");
            messageBox.requestFocus();
        } catch(IOException e) {
            System.out.println("Can't send message");
        }
    }
    public void receiveClient() {
        Thread th = new Thread() {

            @Override
            public void run() {
                while(true) {
                    try {
                        messageInput = dataIn.readUTF();
                        messageArea.appendText(messageInput + "\n");
                    } catch(IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        th.start();
    }
}
