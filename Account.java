import java.util.*;
import java.util.ArrayList;
public class Account {
	private String name;
	private double balance;
	private String uvid;
	private String accountid;
	private User holder;
	private ArrayList<Transaction> transactions;
	public Account(String name,User Holder,Bank theBank) {
		this.name= name;
		this.holder = holder;
		this.uvid = theBank.getNewAccountUVID();
		this.transactions = new ArrayList<Transaction>();
	
		}
	public String getUVID() {
		return this.uvid;
	}
	public String getSummaryLine() {
		double balance = this.getBalance();
		if(balance>=0) {
			return String.format("%s : $%.02f  : %s", this.uvid , balance, this.name);
		}
		else {
			return String.format("%s : $(%.02f): %s" , this.uvid , balance, this.name);
			
		}
	}
	public  double getBalance() {
		double balance = 0;
		for(Transaction t: this.transactions) { 
			balance += t.getAmount();
			
		}
		return balance;
		
	}
	public void printTransactionHistory() {
		System.out.printf("\nTransaction History for account %s\n" ,  this.uvid);
		for(int t=this.transactions.size()-1; t>=0;t--) {
		System.out.println(this.transactions.get(t).getSummaryLine());
		
		}
		System.out.println();;
	}
	public void addTransaction(double amount, String memo) {
		Transaction newTra =new Transaction(amount,memo,this);
this.transactions.add(newTra);
}
}
