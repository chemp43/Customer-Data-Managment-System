/*
 * Written By: Curtis Hempstead
 * Project 04
 * Date Written: 7/24/2017
 * 
 * Purpose: Provides methods to work with the Customer object and constructor,
 * provides methods to communicate with the MySQL database data;  Provides
 * methods to add, edit (update), and delete data from the database. Provides 
 * Arraylist of customer objects to get customer with a specified ID.
 * 
 */

package Project04.database;

import Project04.business.Customer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.sql.SQLException;

public class CustomerDB {
    
    private static Customer getCustomerFromRow(ResultSet rs) throws SQLException{
        
        int customerID = rs.getInt(1);
        String customerFirst = rs.getString(2);
        String customerLast = rs.getString(3);
        String customerEmail = rs.getString(4);
        
        Customer c = new Customer();
        c.setCustomerId(customerID);
        c.setCustomerFirst(customerFirst);
        c.setCustomerLast(customerLast);
        c.setCustomerEmail(customerEmail);
        
        return c;
    }
    
    public static List<Customer> getAll() throws DBException{
        String sql = "SELECT * FROM Customer ORDER BY CustomerID";
        List<Customer> customers = new ArrayList<>();
        Connection connection = DBUtil.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()){
            while (rs.next()){
                Customer c = getCustomerFromRow(rs);
                customers.add(c);
            }
            rs.close();
            return customers;
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }
    
    public static Customer get(int customerId) throws DBException{
        String sql = "SELECT * FROM Customer WHERE CustomerID = ?";
        Connection connection = DBUtil.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, customerId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                Customer c = getCustomerFromRow(rs);
                rs.close();
                return c;
            } else {
                rs.close();
                return null;
            }
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }
    
    public static void add(Customer customer) throws DBException{
        String sql
                = "INSERT INTO Customer (FirstName, LastName, EmailAddress) "
                + "VALUES (?, ?, ?)";
        Connection connection = DBUtil.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, customer.getCustomerFirst());
            ps.setString(2, customer.getCustomerLast());
            ps.setString(3, customer.getCustomerEmail());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }
    
    public static void update(Customer customer) throws DBException {
        String sql = "UPDATE Customer SET "
                + "FirstName = ?,"
                + "LastName = ?,"
                + "EmailAddress = ?"
                + "WHERE CustomerID = ?";
        Connection connection = DBUtil.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, customer.getCustomerFirst());
            ps.setString(2, customer.getCustomerLast());
            ps.setString(3, customer.getCustomerEmail());
            ps.setInt(4, customer.getCustomerId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }
    
    public static void delete(Customer customer) throws DBException {
        String sql = "DELETE FROM Customer "
                + "WHERE CustomerID = ?";
        Connection connection = DBUtil.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, customer.getCustomerId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }
}
