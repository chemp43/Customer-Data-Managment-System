/*
 * Written By: Curtis Hempstead
 * Project 04
 * Date Written: 7/24/2017
 * 
 * Code Originated from: ch20_ex4_ProductManager
 * By: Murach
 *
 * Purpose: Provides wrapper class to throw a common exception for
 * the UI to catch without coupling the UI to the database layer.
 * 
 */

package Project04.database;

public class DBException extends Exception {
    DBException() {}
    
    DBException(Exception e) {
        super(e);
    }
}