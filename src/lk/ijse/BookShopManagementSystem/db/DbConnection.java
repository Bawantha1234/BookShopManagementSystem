package lk.ijse.BookShopManagementSystem.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
    private Connection connection;
    private static DbConnection dbconnection;

    private DbConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/BookShopManagement",
                "root", "1234");

    }
    public static DbConnection getInstance() throws SQLException, ClassNotFoundException {
        if(dbconnection == null){
            dbconnection = new DbConnection();
        }
        return dbconnection;
    }
    public Connection getConnection(){
        return connection;
    }
}


