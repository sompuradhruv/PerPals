package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnUtil {

    public static Connection getConnection() {
        String connectionString = DBPropertyUtil.getConnectionString();
        String[] dbDetails = connectionString.split(";"); 
        
        try {
            return DriverManager.getConnection(dbDetails[0], dbDetails[1], dbDetails[2]); 
        } catch (SQLException e) {
            throw new RuntimeException("Error establishing DB connection: " + e.getMessage(), e);
        }
    }
}
