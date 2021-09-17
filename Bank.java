import java.util.*;
import java.util.ArrayList;
import java.util.Random;
public class Bank {
private String name;
private ArrayList<User> users;
private ArrayList<Account> accounts;
public Bank(String name) {
	this.name = name;
	this.users = new ArrayList<User>();
	this.accounts = new ArrayList<Account>();
}
public String getNewUserUVID() {
	String uvid;
	Random rng= new Random();
	int len=6;
	boolean nonUnique;
	do {
		uvid ="";
		for(int c=0;c<len;c++)
		{uvid+= (Integer)rng.nextInt(10);
		}
		nonUnique = false;
		for(User  u : this.users) {
			if(uvidcompareTo(u.getUVID()) == 0)
					{
				nonUnique = true;
				break;
					}
		}
		}
	while(nonUnique);
	return uvid;
}
public String getNewAccountUVID()
{
	String uvid;
	Random rng= new Random();
	int len=10;
	boolean nonUnique;
	do {
		uvid ="";
		for(int c=0;c<len;c++)
		{uvid += (Integer)rng.nextInt(10);
		}
		nonUnique = false;
		for(Account a : this.accounts) {
			if(uvidcompareTo(a.getUVID()) == 0)
					{
				nonUnique = true;
				break;
					}
		}
		}
	while(nonUnique);
	return uvid;
}
private int uvidcompareTo(String uvid) {
	// TODO Auto-generated method stub
	return 0;
}
public void addAccount(Account anAcct) {
	this.accounts.add(anAcct);
}
public User addUser(String FirstName, String LastName, String pin ) {
	User newUser = new User(FirstName ,LastName, pin,this);
	this.users.add(newUser);
	Account newAccount = new Account("Savings", newUser, this);
	newUser.addAccount(newAccount);
	this.addAccount(newAccount);
	return newUser;
}
public User userLogin(String userID , String pin) {
	for(User u: this.users) {
		if(u.getUVID().compareTo(userID) == 0 && u.validatePin(pin)) {
			return u;
		}
	}
	return null;
}
public String getName() {
	return this.name;
}
}
