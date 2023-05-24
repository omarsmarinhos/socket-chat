package prueba;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class alo {

    private static final String URL = "jdbc:sqlite:identifier.sqlite";

    private static Connection connection;

    public static void main(String[] args) throws SQLException {
        if (connection == null) {
            connection = DriverManager.getConnection(URL);
            System.out.println("conectar");
        }

    }



}
