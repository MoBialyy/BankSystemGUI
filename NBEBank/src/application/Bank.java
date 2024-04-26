package application;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.time.LocalDate;

public class Bank {
    private String bankName;
    private String bankAddress;
    private List<Customer> customers;
	private static Bank instance;
	static int CID = 1;
	
    public Bank() {
        this.customers = new ArrayList<>();
        //this.loans = new ArrayList<>();
        //this.transactions = new ArrayList<>();
    }
    
    public static Bank getInstance() {
        if (instance == null) {
            instance = new Bank();
        }
        return instance;
    }

    public void setBankName(String bankName) {
    	this.bankName = bankName;
    }
    
    public String getBankName() {
    	return bankName;
    }

    public void setBankAddress(String bankAddress) {
    	this.bankAddress = bankAddress;
    }
    
    public String getBankAddress() {
    	return bankAddress;
    }
    
    public List<Customer> getAllCustomers() {
        return customers;
    }
    
    public void addCustomer(String name, String address, String NID, String PhoneNo, String emaiAddress,String Password, String acctype) {
    	Customer customer = new Customer (name, address,
    			NID, PhoneNo, emaiAddress,Password,acctype,CID );
    	CID+=1;
        customers.add(customer); 
    }

    public void removeCustomer(Customer customer) {
        customers.remove(customer);
    }
    
    public boolean existAccount(int cid) {
        for (Customer customer : customers) {
            if (customer.getCid() == cid) {
                return true;
            }
        }
        return false;
    }

    public Customer searchCustomerById(int cid) {
        for (Customer customer : customers) {
            if (customer.getCid() == cid) {
                return customer;
            }
        }
        return null;
    }
    
    
 
    public void closeAccount(Customer customer, account account) {
        customer.removeAccount(account);
    }

    public void transfer(account senderAccount, account receiverAccount, double amount) {
       senderAccount.TransferFunds(senderAccount, receiverAccount, amount);
    }

    public double getAccountBalance(Customer customer, int AID) {
        account acc =  customer.searchAccountById(AID);
        return acc.getBalance();
    }
    
    public int getCustCID() {
    	return Bank.CID;
    }

}