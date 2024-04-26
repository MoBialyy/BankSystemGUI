package application;

import java.util.ArrayList;
import java.util.List;

public class Customer {
	private String Name;
	private String Address;
	private String NationalId;
	private int cid;
	private String EmailAddress;
	private String PhoneNumber;
	private String Password;
	private String acctype;
	private List<account> Accounts;
	int accid=1;
	
	public Customer(String name, String address, 
			String NID, String PhoneNo, String emaiAddress,
			String Password, String acctype,int cid){
		Name = name;
		Address = address;
		NationalId = NID;
		PhoneNumber = PhoneNo;
		EmailAddress = emaiAddress;
		this.Password = Password;
		this.acctype = acctype;
		Accounts = new ArrayList<>();
		account acc = new account(accid,acctype);
		Accounts.add(acc);
		accid+=1;
		this.cid = cid;
	}
	
	public String getName() {
		return Name;
	}
	
	public void setName(String name) {
		if (name.isEmpty()) {
			System.out.println("Invalid (Empty Input)");
		}
		else {
			this.Name = name;
		}
	}
	
	public int getAccID() {
		return this.accid;
	}
	
	public String getAccType() {
		return acctype;
	}
	
	public void setAccType(String name) {
		this.acctype = name;
	}
	
	public String getAddress() {
		return Address;
	}
	
	public void setAddress(String address) {
		if (address.isEmpty()) {
			System.out.println("Invalid (Empty Input)");
		}
		else {
			this.Address = address;
		}
	}
	
	public String getNationalId() {
		return NationalId;
	}
	
	public void setNationalId(String nationalId) {
	    if (nationalId.isEmpty()) {
	    	System.out.println("Invalid (Empty Input)");
	    }
	    else {
	    	this.NationalId = nationalId;
	    }
	}
	
	public String getPhoneNumber() {
		return PhoneNumber;
	}
	
	public void setPhoneNumber(String phoneNumber) {
		if (phoneNumber.isEmpty()) {
			System.out.println("Invalid (Empty Input)");
		}
		else {
			if (phoneNumber.length() < 11) {
		        System.out.println("Error... Phone number must be at least 11 characters long.");
		    }
		    else {
		        this.PhoneNumber = phoneNumber.substring(0, 11);
		    }
		}
	}
	
	public String getEmailAddress() {
		return EmailAddress;
	}
	
	public void setEmailAddress(String email) {
	    if (email.isEmpty()) {
	    	System.out.println("Invalid (Empty Input)");
	    }
	    else {
	    	if (!email.contains("@")) {
		        System.out.println("Invalid Email Address (No @ symbol)");
		    } 
		    else {
		        this.EmailAddress = email;
		    }
	    }
	}
	
 	public String getPassword() {
		return Password;
	}
	
	public void setPassword(String password) {
		this.Password = password;
	}
	
	public void addAccount(String acctype) {
		account acc = new account(accid,acctype);
		Accounts.add(acc);
		accid+=1;
	}
	
	public void removeAccount(account account) {
        Accounts.remove(account);
    }
	
	public double getTotalBalance() {
        double totalBalance = 0.0;
        for (account account : Accounts) {
        	totalBalance += account.getBalance();
        }
        return totalBalance;
    }
	
	public account searchAccountById(int AID) {
        for (account account : Accounts) {
            if (account.getAccountId() == AID) {
                return account;
            }
        }
        return null;
    }
	
	public List<account> getAccounts() {
        return Accounts;
    }
	
    public String toString() {
        return "Customer{" +
                "Name='" + Name + '\'' +
                ", Address='" + Address + '\'' +
                ", NationalId='" + NationalId + '\'' +
                ", CustomerId='" + cid + '\'' +
                ", EmailAddress=" + EmailAddress +
                ", PhoneNumber='" + PhoneNumber + '\'' +
                ", Accounts='" + Accounts + '\'' +
                ", Password='" + Password + '\'' +
                '}';
    }

	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}
	
	public boolean existAccountID(int aid) {
        for (account acc : Accounts) {
            if (acc.getAccountId() == aid) {
                return true;
            }
        }
        return false;
    }
}