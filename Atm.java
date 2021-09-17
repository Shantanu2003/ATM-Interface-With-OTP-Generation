import java.util.Scanner;
import java.util.Random;
public class Atm {
	    public static void generateOTP(int OTPLEN)
	    {
	        char OTPVAL[] =new char[OTPLEN];
	        String Digits = "0123456789";
	        Random randomObj =new Random();
	        for(int j=0;j<OTPLEN;j++) {
	            int randomNo =randomObj.nextInt(Digits.length());
	            OTPVAL[j] = Digits.charAt(randomNo);
	        }
	        System.out.println("YOURONE TIME PASSWORD IS "+ new String(OTPVAL));
	        }
	    public static void main(String[]args) {
	        generateOTP(12);
	Scanner sc=new Scanner(System.in);
	Bank theBank= new Bank("Bank of India");
	User aUser = theBank.addUser("SHANTANU","SINGH","9988");
	Account newAccount = new Account("Checking", aUser , theBank);
	aUser.addAccount(newAccount);
	theBank.addAccount(newAccount);
	User curUser;
  while(true) {
	  curUser = Atm.mainMenuPrompt(theBank ,sc);
	  Atm.printUserMenu(curUser ,sc);
	  
  }
}
public static User mainMenuPrompt(Bank theBank, Scanner sc) {
 String userID;
 String pin;
 User authUser;
 do {
	 System.out.printf("\n\nWelcome to %\n\n", theBank.getName());
	 System.out.print("Enter userID");
	 userID=sc.nextLine();
	 System.out.printf("Enter pin: ");
	 pin=sc.nextLine(); 
	 authUser = theBank.userLogin(userID, pin);
	 if(authUser == null) {
		 System.out.println("Incorrect user ID/pin combination. "+"Please try again");
	 }
 }while (authUser == null);
 return authUser;
}
public static void printUserMenu(User theUser ,Scanner sc) {
	theUser.printAccountsSummary();
	int choice;
	do {
System.out.printf("Welcome %s, what would you like to do ?");
theUser.getFirstName();
System.out.println("1) Show account transaction history");
System.out.println(" 2) Withdraw");
System.out.println(" 3) Deposit");
System.out.println(" 4)Tansfer");
System.out.println(" 5) Quit ");
System.out.println();
System.out.print("Enter choice: ");
choice = sc.nextInt();
if(choice<1 || choice>5) {
	System.out.println("Wrong choice Please choose betwenn1 to 5 ");
}
	}
	while(choice<1|| choice>5);
	switch(choice) {
	case 1:
		Atm.showTransactionHistory(theUser , sc);
		break;
	case 2:
		Atm.withdrawFunds(theUser , sc);
		break;
	case 3:
		Atm.depositFunds(theUser , sc);
		break;
	case 4:
		Atm.transferFunds(theUser , sc);
		break;
	case 5:
		sc.nextLine();
		break;
		}
	if(choice!=5) {
		Atm.printUserMenu(theUser , sc);
		
	}
}
	public static void showTransactionHistory(User theUser, Scanner sc) {
		int theAcct;
		do {
			System.out.printf("Enter the number (1-%d)of the account" +"whose transaction you want to see :",theUser.numAccounts());
			theAcct = sc.nextInt()-1;
			if(theAcct<0|| theAcct>= theUser.numAccounts()) {
				System.out.println("Invalid account . Please try again.");
			}
		}while(theAcct < 0|| theAcct>= theUser.numAccounts());
		theUser.printAcctTransactionHistory(theAcct);
		
	}
	public static void transferFunds(User theUser , Scanner sc) {
		int fromAcct;
		int toAcct;
		double amount;
		double acctBal ;
		do {
			System.out.printf("Enter the number(1-d%) of the account \n" +"to tansfer from: ",theUser.numAccounts());
			fromAcct = sc.nextInt()-1;
			if(fromAcct <0 || fromAcct >=theUser.numAccounts()) {
				System.out.println("Invalid Account .Please Try Again.");
			}
			}while(fromAcct < 0|| fromAcct>= theUser.numAccounts());
		acctBal =theUser.getAcctBalance(fromAcct);
		do {
			System.out.printf("Enter the number(1-d%) of the account \n" +"to tansfer from: ",theUser.numAccounts());
			toAcct = sc.nextInt()-1;
			if(toAcct <0 || toAcct >=theUser.numAccounts()) {
				System.out.println("Invalid Account .Please Try Again.");
			}
			}while(toAcct < 0|| toAcct>= theUser.numAccounts());
		do {
			System.out.printf("Enter, the Amount To Transfer(max $%.02f): $",acctBal);
			amount = sc.nextDouble();
			if(amount<0) {
				System.out.println("You have balance less than zero");
			}
			else if(amount> acctBal) {
				System.out.printf("Amount must not be greater than \n"+ "balance of $%.02f.\n", acctBal);
			}
		}while(amount <0 || amount>acctBal);
		theUser.addAcctTransaction(fromAcct, -1*amount,String.format("Transfer to account %s", theUser.getAcctUVID(toAcct)));
		theUser.addAcctTransaction(toAcct, -1*amount,String.format("Transfer to account %s", theUser.getAcctUVID(fromAcct)));
	}
	public static void withdrawFunds(User theUser,Scanner sc) {
		int fromAcct;
		double amount;
		double acctBal ;
		String memo;
		
		do {
			System.out.printf("Enter the number(1-d%) of the account \n" +"to withdraw from: ",theUser.numAccounts());
			fromAcct = sc.nextInt()-1;
			if(fromAcct <0 || fromAcct >=theUser.numAccounts()) {
				System.out.println("Invalid Account .Please Try Again.");
			}
			}while(fromAcct < 0|| fromAcct>= theUser.numAccounts());
		acctBal =theUser.getAcctBalance(fromAcct);
		do {
			System.out.printf("Enter, the Amount To Transfer(max $%.02f): $",acctBal);
			amount = sc.nextDouble();
			if(amount<0) {
				System.out.println("You have balance less than zero");
			}
			else if(amount> acctBal) {
				System.out.printf("Amount must not be greater than \n"+ "balance of $%.02f.\n", acctBal);
			}
		}while(amount <0 || amount>acctBal);
		sc.nextLine();
		System.out.println("Enter The Memo");
		memo=sc.nextLine();
		theUser.addAcctTransaction(fromAcct, -1*amount, memo);
	}
	public static void depositFunds(User theUser , Scanner sc) {
		int toAcct;
		double amount;
		double acctBal ;
		String memo;
		
		do {
			System.out.printf("Enter the number(1-d%) of the account \n" +"to deposit from: ", theUser.numAccounts());
			toAcct = sc.nextInt()-1;
			if(toAcct <0 ||toAcct >=theUser.numAccounts()) {
				System.out.println("Invalid Account .Please Try Again.");
			}
			}while(toAcct < 0|| toAcct>= theUser.numAccounts());
		acctBal =theUser.getAcctBalance(toAcct);
		do {
			System.out.printf("Enter, the Amount To Transfer(max $%.02f): $",acctBal);
			amount = sc.nextDouble();
			if(amount<0) {
				System.out.println("You have balance less than zero");
			}
		}while(amount <0 );
		sc.nextLine();
		System.out.println("Enter The Memo");
		memo=sc.nextLine();
		theUser.addAcctTransaction(toAcct, -1*amount, memo);
	}
}
	

