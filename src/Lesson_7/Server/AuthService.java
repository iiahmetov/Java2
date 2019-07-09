package Lesson_7.Server;

import java.sql.*;

public class AuthService {
    private static Connection connection;
    private static Statement stmt;

    public static void connect() throws SQLException {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:src/Lesson_7/clients.db");
            stmt = connection.createStatement();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void resetAllClients(){       //метод для сброса параметра "онлайн" для всех клиентовв базе данных
        String sqlSwitchAllOffline = String.format("UPDATE base\n" +
                "SET online = 0");
        try {
            stmt.execute(sqlSwitchAllOffline);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void disconnect(){
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static String getNickByLoginAndPass(String login, String pass){
        String sqlLogPass = String.format("SELECT nickname FROM base\n" +   //создаём запрос ника по паре логин/пароль
                "WHERE login = '%s'\n" +
                "AND password = '%s'", login, pass);
        String sqlOnline = String.format("SELECT online FROM base\n" +      //создаём запрос статуса "онлайн" по логину
                "WHERE login = '%s'\n", login);                             //т.к. логины уникальны, а ники нет
        try {
            ResultSet rsLogPas = stmt.executeQuery(sqlLogPass);             //выполняем запрос ника
            if (rsLogPas.next()){                                           //если ответ релевантный то
                String rsNick = rsLogPas.getString("nickname"); //сохраняем ник
                ResultSet rsOnline = stmt.executeQuery(sqlOnline);          //выполняем запрос статуса "онлайн"
                if (rsOnline.getBoolean(1)){                    //если статус "онлайн" истина
                    return ("/alreadyOnline");                              //возвращаем команду "уже онлайн"
                } else {                                                    //если статус "онлайн" ложь
                    SwitchOnline(login);                                    //переключаем статус "онлайн" на истину
                    return rsNick;                                          //передаём ник
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void SwitchOnline (String login){       //метод для переключения статуса "онлайн" на истину
        String sqlSwitchOnline = String.format("UPDATE base\n" +
                "SET online = 1\n" +
                "WHERE login = '%s'", login);
        try {
            stmt.execute(sqlSwitchOnline);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void SwitchOffline (String login){        //метод переключения статуса "онлайн" на ложь
        String sqlSwitchOnline = String.format("UPDATE base\n" +
                "SET online = 0\n" +
                "WHERE login = '%s'", login);
        try {
            stmt.execute(sqlSwitchOnline);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
