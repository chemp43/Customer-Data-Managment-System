/*
 * Written By: Curtis Hempstead
 * Project 04
 * Date Written: 7/24/2017
 * 
 * Purpose: Provides customer object.
 * 
 */
package Project04.business;

public class Customer {
    private int customerId;
    private String customerEmail;
    private String customerFirst;
    private String customerLast;
    
    public Customer(){ //customer constructor
        this.customerId = customerId;
        this.customerEmail = customerEmail;
        this.customerFirst = customerFirst;
        this.customerLast = customerLast;
    }

    public int getCustomerId() { //getters and setters
        return customerId;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public String getCustomerFirst() {
        return customerFirst;
    }

    public String getCustomerLast() {
        return customerLast;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public void setCustomerFirst(String customerFirst) {
        this.customerFirst = customerFirst;
    }

    public void setCustomerLast(String customerLast) {
        this.customerLast = customerLast;
    }
    
}
