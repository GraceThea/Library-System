package db;

import config.Config;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author bagon
 */
public class Database {
    
    public static Connection connection;
    public static Statement stm;
    
    public static void openConnection() throws ClassNotFoundException, SQLException{
        Class.forName("com.mysql.jdbc.Driver");
        Database.connection = DriverManager
                .getConnection("jdbc:mysql://" + Config.ENV.get("DB_HOST") + 
                        "/" + Config.ENV.get("DB_NAME") + "?useSSL=false",
                        Config.ENV.get("DB_USER"), Config.ENV.get("DB_PASS"));
        Database.stm = Database.connection.createStatement();
    }
    public static void closeConnection() throws SQLException{
        Database.connection.close();
    }
    
    public static ResultSet get(String tableName,String condition) throws SQLException{
        String query = "SELECT * FROM " + tableName + " " + condition;
        return Database.stm.executeQuery(query);
    }
}
