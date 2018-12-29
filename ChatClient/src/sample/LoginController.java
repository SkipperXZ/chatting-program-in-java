package sample;

import com.jfoenix.controls.*;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;

import javafx.event.ActionEvent;
import javafx.stage.Stage;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class LoginController {
    LoginModel loginModel = new LoginModel();
    @FXML
    private Label lblMessage;
    @FXML
    private JFXButton loginButton;
    @FXML
    private JFXButton signinButton;
    @FXML
    private JFXTextField txtLoginUsername;
    @FXML
    private  JFXPasswordField txtLoginPassword;
    @FXML
    private Label lblWelcomeMessage;
    @FXML
    private JFXPasswordField registerComfirmPassword;
    @FXML
    private JFXButton registerButton;
    @FXML
    private JFXPasswordField registerPassword;
    @FXML
    private JFXTextField registerUsername;
    @FXML
    void makeLogin (ActionEvent event)throws Exception {
        Socket socket = new Socket("127.0.0.1",11179);
        DataInputStream dataIn = new DataInputStream(socket.getInputStream());
        DataOutputStream dataOut = new DataOutputStream(socket.getOutputStream());
        String Username = txtLoginUsername.getText();
        String Password = txtLoginPassword.getText();
        dataOut.writeUTF(Username);
        dataOut.writeUTF(Password);
                if (dataIn.readBoolean()) {
                    data.name=Username;
                    chatScene(event);
                }
                    lblMessage.setText("Wrong Password or Username");
    }
    @FXML
    void makeSignIn(ActionEvent event)throws  Exception {
      registerScene(event);
    }
    @FXML
    void makeRegister(ActionEvent event)throws  Exception {
       if (loginModel.registersql(registerUsername.getText(),registerPassword.getText(),registerComfirmPassword.getText()))
            loginScene(event);
       else
           registerScene(event);
    }
    @FXML
    void makeLogout(ActionEvent event)throws  Exception{
        loginScene(event);
    }
    void  chatScene(ActionEvent event)throws  Exception
    {
        Parent chatApp= FXMLLoader.load(getClass().getResource("chat.fxml"));
        Scene chatApp_scene = new Scene(chatApp);
        Stage chatApp_stage =(Stage)((Node)event.getSource()).getScene().getWindow();
        chatApp_stage.setScene(chatApp_scene);
        chatApp_stage.show();

}
    void  loginScene(ActionEvent event)throws  Exception
    {
        Parent login= FXMLLoader.load(getClass().getResource("FXML_Login.fxml"));
        Scene login_scene = new Scene(login);
        Stage login_stage =(Stage)((Node)event.getSource()).getScene().getWindow();
        login_stage.setScene(login_scene);
        login_stage.show();
    }
    void  registerScene (ActionEvent event)throws  Exception {
        FXMLLoader loader= new FXMLLoader(getClass().getResource("FXML_Register.fxml"));
        loader.setController(this);
        Parent register = loader.load();
        Scene register_scene = new Scene(register);
        Stage register_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        register_stage.setScene(register_scene);
        register_stage.show();
    }

}
