/*
 * Written By: Curtis Hempstead
 * Project 04
 * Date Written: 7/24/2017
 *
 * Code Originated from: ch20_ex4_ProductManager
 * By: Murach
 * 
 * Purpose: Provides connection data and properties to connect to the MySQL 
 * database.
 * 
 */

package Project04.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
    
    private static Connection connection;
    
    private DBUtil() {}

    public static synchronized Connection getConnection() throws DBException {
        if (connection != null) {
            return connection;
        }
        else {
            try {
                // set the db url, username, and password
                String url = "jdbc:mysql://localhost:3306/mma";
                String username = "mma_user";
                String password = "sesame";

                // get and return connection
                connection = DriverManager.getConnection(
                        url, username, password);
                return connection;
            } catch (SQLException e) {
                throw new DBException(e);
            }            
        }
    }
    
    public static synchronized void closeConnection() throws DBException {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new DBException(e);
            } finally {
                connection = null;                
            }
        }
    }
}