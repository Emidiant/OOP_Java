package dal.dalSql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
    private final static String databaseName = "ProductShop";
    private final static String userName = "emidiant";
    private final static String pass = "tata1969";
    private final static String connectionUrl = "jdbc:sqlserver://localhost:1433;databaseName=%1$s;user=%2$s;password=%3$s;";
    private final static String connectionString = String.format(connectionUrl, /*instanceName,*/ databaseName, userName, pass);

    private static Connection connection;

    public static java.sql.Connection getConnection(){
        if (connection == null){
            try {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                connection = DriverManager.getConnection(connectionString);

                //
                // System.out.println("Connection OK");
            } catch (SQLException ex){
                ex.printStackTrace();
                System.out.println("Connection Error");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                System.out.println("Connection Error");
            }
        }
        return connection;
    }
}
