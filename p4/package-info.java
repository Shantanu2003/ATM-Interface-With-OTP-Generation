package p4;
import java.util.*;
import java.util.ArrayList;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
public class User {
private String FirstName;
private String LastName;
private String uvid;
private byte pinHash[];
private ArrayList<Account> accounts;
public User(String FirstName,String LastName,String pin,Bank theBank)
{
	this.FirstName = FirstName;
	this.LastName = LastName;
	try {
	MessageDigest md=MessageDigest.getInstance("MD5");
	this.pinHash =md.digest(pin.getBytes());
	
	}
	catch(NoSuchAlgorithmException e) {
		System.out.println("error,Caught NoSuch AlgorithmException");
		e.printStackTrace();
		System.exit(1);
	}
	this.uvid= theBank.getNewUserUVID();	
	this.accounts = new ArrayList<Account>();
	System.out.printf("New user Id created.\n",LastName,FirstName , this.uvid);
}
public void addAccount(Account anAcct) {
	this.accounts.add(anAcct);
}
public String getUVID() {
	return this.uvid;
}
	public boolean validatePin(String aPin) {
		try {
		MessageDigest md = MessageDigest.getInstance("MDS");
		return MessageDigest.isEqual(md.digest(aPin.getBytes()), this.pinHash);
		}
		catch(NoSuchAlgorithmException e){
			System.out.println("error , caught NoSuchAlgoritmException");
			e.printStackTrace();
			System.exit(1);
			}
		return false;

}
	public String getFirstName() {
		return this.FirstName;
		
	}
	public void printAccountsSummary()
	{
		System.out.printf("\n\n,%s's Accounts Summary\n" ,this.FirstName);
		for(int a = 0;a< this.accounts.size();a++) {
			System.out.printf("%d) %s\n",a+1 , this.accounts.get(a).getSummaryLine());
		}
		
		System.out.println();
		
	}
	public int numAccounts() {
		return this.accounts.size();
	}
	public void printAcctTransactionHistory(int acctIdx) {
		this.accounts.get(acctIdx).printTransactionHistory();
	}
	public double getAcctBalance(int acctIdx) {
		return this.accounts.get(acctIdx).getBalance();
		
	}
	public String getAcctUVID(int acctIdx) {
	return this.accounts.get(acctIdx).getUVID();
	}
	public void addAcctTransaction(int acctIdx, double amount,String memo){
		this.accounts.get(acctIdx).addTransaction(amount,memo);
	}
		
	}

