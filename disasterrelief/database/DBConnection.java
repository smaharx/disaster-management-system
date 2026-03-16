package disasterrelief.database;

import disasterrelief.models.*;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
	
    public static Connection getConnection() {
        try {
            // Load MySQL driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Connect to your database
            return DriverManager.getConnection(
                "jdbc:mysql://127.0.0.1:3306/disasterrelief", //   DB name
                "root",                                       //   MySQL username
                "dbms123" 
                                              //  MySQL password
            );
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
