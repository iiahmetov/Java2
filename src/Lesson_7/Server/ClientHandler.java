package Lesson_7.Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientHandler {

    private Server server;
    private Socket socket;
    DataOutputStream out;
    DataInputStream in;
    String nick;

    public ClientHandler(Server server, Socket socket) {
        try {
            this.server = server;
            this.socket = socket;
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());


            new Thread(() -> {
                try {
                    // цикл авторизации.
                    String login;
                    while (true) {
                        String str = in.readUTF();
                        if(str.startsWith("/auth")){
                            String[] token = str.split(" ");
                            login = token[1];           //сохраняем логин для корретного переключения статуса "онлайн"
                            String newNick = AuthService.getNickByLoginAndPass(token[1],token[2]);
                            if(newNick != null){
                                if (newNick.equals("/alreadyOnline")) {     //если получено данное сообщение
                                    sendMsg("Учётная запись уже активна");  //то информируем пользователя
                                } else {
                                    sendMsg("/authok");
                                    nick = newNick;
                                    server.subscribe(this);
                                    break;
                                }
                            } else {
                                sendMsg("Неверный логин / пароль");
                            }
                        }
                    }
                    //Цикл для работы
                    while (true) {
                        String str = in.readUTF();
                        if (str.equals("/end")) {
                            out.writeUTF("/end");
                            System.out.println("Клиент отключился");
                            AuthService.SwitchOffline(login);     //в случае выхода переключаем статус "онлайн" на ложь
                            break;
                        }
                        if (str.startsWith("/w ")){             //в случае ввода префикса приватного сообщения
                            String[] prv = str.split(" ", 3); //создаю строчный массив из 3 элементов
                            server.sendPrvMsg(prv[2], getNick() ,prv[1]); //отправляю на сервер приватное сообщение
                            System.out.println("Private message from [ " + getNick() + " ] to [ " + prv[1] + " ]: " + prv[2]);
                        } else {
                            System.out.println(getNick() + ": " + str);
                            server.broadcastMsg(str, getNick());
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        in.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    try {
                        out.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    try {
                        socket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    server.unsubscribe(this);
                }
            }).start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMsg(String str){
        try {
            out.writeUTF(str);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getNick() {return nick;}
}
