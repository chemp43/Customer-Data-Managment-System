/*
 * Written By: Curtis Hempstead
 * Project 04
 * Date Written: 7/24/2017
 * 
 * Purpose: Provides frame constructor for UI.  Provide location data for each
 * UI object.  Calls on methods to provide data depending on actions performed
 * within the UI.
 * 
 */

package Project04.presentation;

import Project04.database.DBException;
import Project04.database.CustomerDB;
import Project04.business.Customer;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class CustomerManagerFrame extends JFrame {
    private CustomerTableModel customerTableModel = new CustomerTableModel();
    private JTable customerTable = new JTable(customerTableModel);
    
    
    public CustomerManagerFrame() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            System.out.println(e);
        }
        setTitle("Customer Manager");
        setSize(900, 200);
        setLocationByPlatform(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(buttonPanel(), BorderLayout.SOUTH);
        add(new JScrollPane(customerTable), BorderLayout.CENTER);
        setVisible(true);
        
    }
    
    private JPanel buttonPanel() { //button panel method containting 
        JPanel panel = new JPanel();
        JButton addButton = new JButton("Add");
        addButton.setToolTipText("Add Customer Data");
        JButton editButton = new JButton("Edit");
        editButton.setToolTipText("Edit selected Customer");
        JButton deleteButton = new JButton ("Delete");
        deleteButton.setToolTipText("Delete selected Customer");
        
        addButton.addActionListener((ActionEvent) -> {
            doAddButton();
        });
        editButton.addActionListener((ActionEvent) -> {
            doEditButton();
        });
        deleteButton.addActionListener((ActionEvent) -> {
            doDeleteButton();
        });
        
        panel.add(addButton);
        panel.add(editButton);
        panel.add(deleteButton);
        
        return panel;
    }
    
    private void doAddButton() { //provides action for add button
        CustomerForm customerForm = new CustomerForm(this, "Add Customer", true);
        customerForm.setLocationRelativeTo(this);
        customerForm.setVisible(true);
    }
    
    private void doEditButton() { //provides action for edit button
        int selectedRow = customerTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "No customer is currently selected.", "No customer selected", JOptionPane.ERROR_MESSAGE);
        } else {
            Customer customer = customerTableModel.getCustomer(selectedRow);
            int ask = JOptionPane.showConfirmDialog(this, "Are you sure you want to edit " +customer.getCustomerFirst()+" "+customer.getCustomerLast()+"?","Confirm edit",JOptionPane.YES_NO_OPTION);
        if (ask == JOptionPane.YES_OPTION) {
            CustomerForm customerForm = new CustomerForm(this, "Edit Customer", true, customer);
            customerForm.setLocationRelativeTo(this);
            customerForm.setVisible(true);
        }
        }
    }
        
    
    private void doDeleteButton() { //provides action for delete button
        int selectedRow = customerTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "No customer is currently selected.", "No customer selected", JOptionPane.ERROR_MESSAGE);
        } else {
            Customer customer = customerTableModel.getCustomer(selectedRow);
            int ask = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete " +customer.getCustomerFirst()+" "+customer.getCustomerLast()+" from the database?","Confirm delete",JOptionPane.YES_NO_OPTION);
        if (ask == JOptionPane.YES_OPTION) {
            try {
                CustomerDB.delete(customer);
                fireDatabaseUpdateEvent();
            } catch (DBException e){
                System.out.println(e);
            }
        }
        }
    }
    
    public void fireDatabaseUpdateEvent(){
        customerTableModel.databaseUpdated();
    }
    
    /*private JTable buildCustomerTable(){
        int rowIndex = customerTable.getSelectedRow();
        customerTableModel = new CustomerTableModel();
        JTable table = new JTable(customerTableModel);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setBorder(null);
        if (rowIndex !=-1){
            Customer customer = customerTableModel.getCustomer(rowIndex);
        }
        return table;
    }*/
        

}
