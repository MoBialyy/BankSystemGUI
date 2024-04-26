package application;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Loan {
	private int LoanId;
	private double Amount;
	private int months;
	private LocalDate startDate;
	private LocalDate endDate;
	private String LoanType;
    private double outstandingBalance;
    private LoanStatus loanStatus;
    
    public enum LoanStatus {
        ACTIVE, PAID_OFF, DEFAULT
    }
	
	public Loan(int LID,double amount,int months,
			LocalDate sDate, String Type) {
		this.LoanId = LID;
		this.Amount = amount;
		this.months = months;
		this.startDate = sDate;
		this.endDate = sDate.plusMonths(months);
		this.LoanType = Type;
		this.outstandingBalance = Math.ceil(amount + amount*0.005*months);
		this.loanStatus = LoanStatus.ACTIVE;
	}
	
	public int getLoanId() {
	    return LoanId;
	}

	public double getAmount() {
		return Amount;
	}

	public LocalDate getStartDate() {
	    return startDate;
	}

	public LocalDate getEndDate() {
	    return endDate;
	}

	public String getLoanType() {
	    return LoanType;
	}
	
	public double getOutstandingBalance() {
	    return outstandingBalance;
	}
	
	public void setOutstandingBalance(double amount) {
	    outstandingBalance = amount;
	}
	
	public void setLoanStatus(LoanStatus status) {
	    loanStatus = status;
	}
	
	public LoanStatus getLoanStatus() {
	    return loanStatus;
	}
	
	public boolean isValidLoan() {
	    if (this.Amount <= 0) {
	        return false;
	    }
	    else if (this.months <= 0) {
	        return false;
	    }
	    else if (this.startDate == null || endDate == null) {
	        return false;
	    }
	    else if (this.endDate.isBefore(startDate)) {
	        return false;
	    }
	    else if (this.LoanType == null || LoanType.isEmpty()) {
	        return false;
	    }
	    else if (this.outstandingBalance > 0) {
	        return false; // loan already has an outstanding balance
	    }
	    else if (this.outstandingBalance == Amount) {
	        return false; // loan has not been paid off yet
	    }
	    else {
	        return true;
	    }
	}
	
	
	public LoanStatus checkLoanStatus(LocalDate currentDate) {
	    if (outstandingBalance <= 0) {
	        return LoanStatus.PAID_OFF;
	    } else if (currentDate.isAfter(endDate)) {
	        return LoanStatus.DEFAULT;
	    } else {
	        return LoanStatus.ACTIVE;
	    }
	}
	
	public void makePayment(double payment) {
	    if (payment > outstandingBalance) {
	        System.out.println("Invalid Payment");
	    }
	    else {
	    	outstandingBalance -= payment;
	    	if (outstandingBalance == 0) {
	    		loanStatus = LoanStatus.PAID_OFF;
	    	}
	    }
	}

	public int getMonths() {
		return months;
	}

	public void setMonths(int months) {
		this.months = months;
	}
	
}
