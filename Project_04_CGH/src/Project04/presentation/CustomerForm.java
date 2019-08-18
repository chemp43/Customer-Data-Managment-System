/*
 * Written By: Curtis Hempstead
 * Project 04
 * Date Written: 7/24/2017
 * 
 * Purpose: Provides form data to be used when adding/editing a customer data
 * in the database.  Provides panel, button, and form data for UI. Communicates
 * and calls on database methods to send the data input to the SQL database.
 * 
 */

package Project04.presentation;

import Project04.database.CustomerDB;
import Project04.business.Customer;
import Project04.database.DBException;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;


public class CustomerForm extends JDialog{
    private JTextField firstNameField;
    private JTextField lastNameField;
    private JTextField emailField;
    private JButton confirmButton;
    private JButton cancelButton;
    
    private Customer customer = new Customer();
    
    public CustomerForm(java.awt.Frame parent, String title, boolean modal) {
        super (parent, title, modal);
        initComponents();
        
    }
    
    public CustomerForm(java.awt.Frame parent, String title, boolean modal, Customer customer){
        this(parent, title, modal);
        this.customer = customer;
        confirmButton.setText("Save");
        firstNameField.setText(customer.getCustomerFirst());
        lastNameField.setText(customer.getCustomerLast());
        emailField.setText(customer.getCustomerEmail());
    }
    
    private void initComponents(){
        firstNameField = new JTextField();
        lastNameField = new JTextField();
        emailField = new JTextField();
        cancelButton = new JButton();
        confirmButton = new JButton();
        
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        Dimension longField = new Dimension(300, 20);
        firstNameField.setPreferredSize(longField);
        firstNameField.setMinimumSize(longField);
        lastNameField.setPreferredSize(longField);
        lastNameField.setMinimumSize(longField);
        emailField.setPreferredSize(longField);
        emailField.setMinimumSize(longField);
        
        cancelButton.setText("Cancel");
        cancelButton.addActionListener((ActionEvent) -> {
            cancelButtonActionPerformed();
        });
        cancelButton.setToolTipText("Cancel");
        
        confirmButton.setText("Add");
        confirmButton.addActionListener((ActionEvent) -> {
            confirmButtonActionPerformed();
        });
        confirmButton.setToolTipText("Add/Save customer data");
        
        //JLabel and JTextField panel
        JPanel customerPanel = new JPanel();
        customerPanel.setLayout(new GridBagLayout());
        customerPanel.add(new JLabel("First Name:"), getConstraints(0,0, GridBagConstraints.LINE_END));
        customerPanel.add(firstNameField, getConstraints(1,0, GridBagConstraints.LINE_START));
        customerPanel.add(new JLabel("Last Name:"), getConstraints(0,1, GridBagConstraints.LINE_END));
        customerPanel.add(lastNameField, getConstraints(1,1, GridBagConstraints.LINE_START));
        customerPanel.add(new JLabel("Email:"), getConstraints(0,2, GridBagConstraints.LINE_END));
        customerPanel.add(emailField, getConstraints(1,2, GridBagConstraints.LINE_START));
        
        //JButton Panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(confirmButton);
        buttonPanel.add(cancelButton);
        
        //add panels to main panel
        setLayout(new BorderLayout());
        add(customerPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        pack();   
    }
    
    private GridBagConstraints getConstraints(int x, int y, int anchor){
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(5,5,0,5);
        c.gridx = x; 
        c.gridy = y; 
        c.anchor = anchor;
        return c;
    }
    
    private void cancelButtonActionPerformed(){ //cancel button action, deletes data if pressed
        dispose();
    }
    
    private void confirmButtonActionPerformed(){ //confirm button action, changes action depending on button text
        if (validateData()) {
            setData();
            if (confirmButton.getText().equals("Add")){
                doAdd();
            } else {
                doEdit();
            }
        }
    }
    
    private boolean validateData() { //checks that all fields have data
        String customerFirst = firstNameField.getText();
        String customerLast = lastNameField.getText();
        String customerEmail = emailField.getText();
        if (customerFirst==null || customerLast==null || customerEmail==null){
            JOptionPane.showMessageDialog(this, "Please fill in all fields.", "Missing Fields", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }
    return true;
    }
    
    private void setData() { //setData method
        String customerFirst = firstNameField.getText();
        String customerLast = lastNameField.getText();
        String customerEmail = emailField.getText();
        customer.setCustomerFirst(customerFirst);
        customer.setCustomerLast(customerLast);
        customer.setCustomerEmail(customerEmail);
    }
    
    private void doEdit() { //edit method, calls update method in CustomerDB
        try {
            CustomerDB.update(customer);
            dispose();
            fireDatabaseUpdateEvent();
        } catch (DBException e) {
            System.out.println(e);
        }
    }
    
    private void doAdd() { //add method, calls add method in CustomerDB
        try {
            CustomerDB.add(customer);
            dispose();
            fireDatabaseUpdateEvent();
        } catch (DBException e) {
            System.out.println(e);
        } 
    }
    
    private void fireDatabaseUpdateEvent() { 
        CustomerManagerFrame mainWindow = (CustomerManagerFrame) getOwner();
        mainWindow.fireDatabaseUpdateEvent();
    }
}
