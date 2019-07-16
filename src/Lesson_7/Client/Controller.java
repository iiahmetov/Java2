package Lesson_7.Client;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Controller {

    @FXML
    public HBox upperPanel;
    @FXML
    public TextField loginField;
    @FXML
    public PasswordField passwordField;
    @FXML
    public Button authButton;
    @FXML
    public TextArea textArea;
    @FXML
    public HBox bottomPanel;
    @FXML
    public TextField textField;
    @FXML
    public Button sendButton;

    private boolean isAuthorized;

    Socket socket;
    DataOutputStream out;
    DataInputStream in;

    final String IP_ADRESS = "localhost";
    final int PORT = 8189;

    public void setAuthorized(boolean isAuthorized){
        this.isAuthorized = isAuthorized;
        if(isAuthorized){
            upperPanel.setVisible(false);
            upperPanel.setManaged(false);
            bottomPanel.setVisible(true);
            bottomPanel.setManaged(true);
        } else {
            upperPanel.setVisible(true);
            upperPanel.setManaged(true);
            bottomPanel.setVisible(false);
            bottomPanel.setManaged(false);
        }
    }

    public void connect() {
        try {
            socket = new Socket(IP_ADRESS, PORT);
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        //цикл авторизации
                        while(true){
                            String str = in.readUTF();
                            if (str.equals("/authok")) {
                                setAuthorized(true);
                                break;
                            }
                            textArea.appendText(str +"\n");
                        }
                        //цикл работы
                        while(true){
                            String str = in.readUTF();

                            if (str.equals("/end")) {
                                System.out.println("Клиент отключился");
                                break;
                            }
                            textArea.appendText(str +"\n");
                        }
                    }catch (IOException e) {
                        e.printStackTrace();
                    }finally {
                        try {
                            socket.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        setAuthorized(false);
                    }
                }
            }).start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMSG(ActionEvent actionEvent) {
        try {
            if (!(textField.getText().equals(""))) {                  //условие для избежания ввода пустой строки
                out.writeUTF(textField.getText());
                textField.clear();
                textField.requestFocus();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onMouseReleasedSendButton(MouseEvent mouseEvent) { //хотелось сделать именно клик по кнопке, чтобы избежать случайной отправки сообщения вслучае нажатия Tab-пробел
        ActionEvent actionEvent = new ActionEvent();
        sendMSG(actionEvent);
    }

    public void tryToAuth(ActionEvent actionEvent) {
        if(socket == null || socket.isClosed()){
            connect();
        }
        try {
            out.writeUTF("/auth "+loginField.getText()+" "
                    + passwordField.getText());
            loginField.clear();
            passwordField.clear();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onMouseReleasedTryToAuth(MouseEvent mouseEvent) {
        ActionEvent actionEvent = new ActionEvent();
        tryToAuth(actionEvent);
    }
}
