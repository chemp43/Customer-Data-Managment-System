/*
 * Written By: Curtis Hempstead
 * Project 04
 * Date Written: 7/24/2017
 * 
 * Purpose: Main method calls on CustomerManagerFrame method to start the UI
 * 
 * Description: Project was to create a program that interfaces with a MySQL
 * database to contain the customer data (First name, last name, email, and
 * customer ID #).  The program would allow a user to add, edit, and delete the
 * customer data within the database.  Program would be interacted with by
 * using a UI containing buttons for the actions, and a table view of the
 * customer data.  The data in the table is the data that is represented in the
 * MySQL database.
 */
package Project04.presentation;

public class Main {

    public static void main(String[] args) {
       CustomerManagerFrame customerManagerFrame = new CustomerManagerFrame();
    }    
}
