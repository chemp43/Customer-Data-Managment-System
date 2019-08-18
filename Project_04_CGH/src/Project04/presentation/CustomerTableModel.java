/*
 * Written By: Curtis Hempstead
 * Project 04
 * Date Written: 7/24/2017
 * 
 * Purpose: Provides JTable controls for the UI, defines header row, and
 * getter for customer data based on row index.
 * 
 */

package Project04.presentation;

import Project04.database.DBException;
import Project04.database.CustomerDB;
import Project04.business.Customer;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class CustomerTableModel extends AbstractTableModel{
    private List<Customer> customers;
    private final String[] COLUMN_NAMES = { "Email", "First Name", "Last Name" };
    
    public CustomerTableModel() {
        try {
            customers = CustomerDB.getAll();
        } catch (DBException e) {
            System.err.println(e);
        }
    }
    
    @Override
    public int getRowCount(){
        return customers.size();
    }
    
    @Override
    public int getColumnCount(){
        return COLUMN_NAMES.length;
    }
    
    @Override
    public String getColumnName(int columnIndex){
        return COLUMN_NAMES[columnIndex];
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex){ //defines which data goes in each column
        switch (columnIndex) {
            case 0:
                return customers.get(rowIndex).getCustomerEmail();
            case 1:
                return customers.get(rowIndex).getCustomerFirst();
            case 2:
                return customers.get(rowIndex).getCustomerLast();
            default:
                return null;
        }
    }
    
    Customer getCustomer(int rowIndex) {
        return customers.get(rowIndex);
    }
    
    void databaseUpdated(){
        try {
            customers = CustomerDB.getAll();
            fireTableDataChanged();
        } catch (DBException e) {
            System.err.println(e);
        }
    }
}
