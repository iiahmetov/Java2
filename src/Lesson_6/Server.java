package Lesson_6;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {

    public static void main(String[] args) {

        ServerSocket server = null;
        Socket socket = null;

        try {
            server = new ServerSocket(8188);
            System.out.println("Сервер запущен.");
            socket = server.accept();
            System.out.println("Клиент подключился.");

            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            Scanner sc = new Scanner(System.in);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        while (true){

                            String str = in.readUTF();
                            if (str.equals("/end")){
                                out.writeUTF("/end");
                                System.out.println("Клиент отключился.");
                                break;
                            }
                            System.out.println("Клиент: " + str);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
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
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                server.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
