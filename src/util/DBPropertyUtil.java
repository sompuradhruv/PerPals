package util;

public class DBPropertyUtil {

    public static String getConnectionString() {
        return DBProperties.URL + ";" + DBProperties.USERNAME + ";" + DBProperties.PASSWORD; 
    }
}
