package application;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class account {

	private int AccountId;
	private String AccountType;
	private double Balance;
	int lid = 1;
	private List<Loan> loans;
	
	
	public Loan makeLoan(double amount, int months,
			LocalDate sDate, String type) {
		if(loans== null) {
			loans = new ArrayList<>();
		}
		Loan loan = new Loan(this.lid, amount, months, sDate, type);
		lid+=1;
		loans.add(loan);
		Balance+= amount;
		return loan;
	}
	
	public Loan searchLoanByID(int loanID) {
		if(loans != null) {
			for (Loan loan : loans) {
	            if (loan.getLoanId() == loanID) {
	                return loan;
	            }
	        }
		}
		else {
			return null;
		}
        return null;
	}
	
	public account(int AID, String AccType) {
		AccountId = AID;
		AccountType = AccType;
		this.Balance = 0;
	}
	
	public int getAccountId() {
		return AccountId;
	}
	
	public String getAccountType() {
		return AccountType;
	}
	
	public double getBalance() {
		return Balance;
	}
	
	public void setBalance(double balance) {
		this.Balance = balance;
	}
	
	
	
    public boolean deposit(double amount) {
        if (amount > 0 && amount < 500000000) {
            Balance += amount;
            //addTransaction("Deposit", amount);
            return true; // Deposit successful
        } else {
            System.out.println("Invalid deposit amount.");
            return false; // Deposit failed
        }
    }

    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= Balance) {
            Balance -= amount;
            //addTransaction("Withdraw", amount);
            return true; // Withdrawal successful
        } else {
            System.out.println("Invalid withdrawal amount.");
            return false;
        }
    }

	public boolean TransferFunds(account senderAccount, account receiverAccount, double amount) {
        if (amount <= 0) {
            System.out.println("Invalid transfer amount.");
            return false;
        }
        
        if (senderAccount == receiverAccount) {
            System.out.println("Cannot transfer funds to the same account.");
            return false;
        }
        
        if (amount > senderAccount.getBalance()) {
            System.out.println("Insufficient funds in sender account.");
            return false;
        }
        
        senderAccount.setBalance(senderAccount.getBalance() - amount);
        receiverAccount.setBalance(receiverAccount.getBalance() + amount);
        System.out.println("Funds transferred successfully.");
        return true;
    }

	public int getLid() {
		return lid;
	}
	
	public String toString() {
        return "Account{" +
                "AccountId=" + AccountId +
                ", AccountType='" + AccountType + '\'' +
                ", balance= " + Balance +
                ", loans = " + loans+
                '}';
    }
	
}
