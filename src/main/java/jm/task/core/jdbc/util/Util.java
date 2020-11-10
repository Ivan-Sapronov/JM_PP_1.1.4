package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    //private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/jmpreproject?useUnicode=true&useSSL=true&useJDBCCompliantTimezoneShift=true"
            + "&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private static final String LOGIN = "root";
    private static final String PASSWORD = "msqlroot";

    public Connection getConnection(){
        Connection connection = null;
        try {
            //Это уже не нужно, т.к. все драйверы JDBC 4.0, обнаруженные в пути к классу, загружаются автоматически
            //Class.forName(DRIVER);
            connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
            System.out.println("Соединение с Б.Д. установлено!");
        } catch (SQLException e) {
            System.out.println("Ошибка соединения с Б.Д.!");
            e.printStackTrace();
        }
        return connection;
    }

}
