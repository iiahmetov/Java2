package Lesson_6;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        final String IP_adress = "localhost";
        final int port = 8188;

        Socket socket;

        try {
            socket = new Socket(IP_adress, port);
            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            Scanner sc = new Scanner(System.in);
            boolean flag = true;

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        while (true){
                            String str = in.readUTF();
                            if (str.equals("/end")){
                                out.writeUTF("/end");
                                break;
                            }
                            System.out.println("Сервер: " + str);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            socket.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();

            while (true){
                String msg = sc.nextLine();
                if (msg.equals("/end")){
                    out.writeUTF(msg);
                    break;
                }
                out.writeUTF(msg);
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
