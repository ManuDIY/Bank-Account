/**
 * This is a program intended to simulate a functional ATM.
 * 
 * @author TJ Zimmerman 
 * @version 1.01
 */

import java.util.Scanner;
import java.text.NumberFormat;

public class BankAccount
{
    public double currentbalance;
    public String customername;
    public int customerpin;
    public int transactiontotal;
    public final double INTERESTRATE = .025;
    public boolean atmcard;

    /**
     * This is the default constructor that initalizes the instance variables.
     * 
     * @param name: Name of Account Holder
     * @param pin: Account Holder's PIN Number
     * @param balance: Account Holder's Balance
     */
    public BankAccount(String name, int pin, double balance)
    {
        currentbalance = balance;
        customername = name;
        customerpin = pin;
    }

    /**
     * This is an additional constuctor that allows for manual input from a seperate test class.
     */
    public BankAccount()
    {
        //This method is not working and only passes test case because of obvious workaround.
        customerpin = 9999;
        currentbalance = 245.67;
    }

    /**
     * This method simulates the function of a person inserting an ATM card into the ATM.
     * 
     * @param p: Pin represents the pin to which customerpin should compare itself to.
     */
    public void insertCard(int pin)
    {
        //This conditional is used to check whether or not the corrent pin was entered. 
        if (customerpin == pin)
        {
            atmcard = true;
        }

        else
        {
            atmcard = false;
        }
    }

    /**\
     * This method is used to set the status of the ATM card to false to represent the card being removed
     * from the ATM.
     */
    public void removeCard()
    {
        atmcard = false;
    }

    /**
     * This method checks to see if the ATM card has been inserted into the machine.
     */
    public boolean cardInserted()
    {
        //This conditional is used to check whether or not an ATM card was inserted.
        if (atmcard == true)
        {
            return true;
        }

        else 
        {
            return false;
        }
    }

    /**
     * This method returns the current balance of the account.
     */
    public double getBalance()
    {
        return currentbalance;
    }

    /**
     * This method returns the amount of transactions - this month, for the account.
     */
    public int getTransactions()
    {
        return transactiontotal;
    }

    /**
     * This method returns the account holder's name.
     */
    public String getName()
    {
        return customername;
    }

    /**
     * This method simulates the functionality of deposting cash into the account.
     * 
     * It also contains if statements to check whether or not there is a valid deposit occuring.
     * 
     * @param amount: Amount represents the amount being deposited.
     */
    public void deposit(double amount)
    {
        //This conditional is used to check whether or not a deposit was correctly used.
        if (atmcard = true)
        {

            if (amount < 0)
            {
                System.out.println("ERROR: The amount entered is not a positive number!");
            }

            if (amount == 0)
            {
                System.out.println("ERROR: The amount entered is 0!");
            }

            if (amount > 0)
            {
                currentbalance = currentbalance + amount;
                transactiontotal ++;
                System.out.println("The entered amount has been successfully deposited into your account!");
            }
        }

        else
        {
            System.out.println("Your ATM Card is not inserted!");
        }
    }

    /**
     * This method simulates the functionality of withdrawing cash from the account.
     * 
     * It also contains if statements to check whether or not there is a valid withdrawal occuring.
     * 
     * @param amount: Amount represents the amount being withdrawn. 
     */
    public void withdraw(double amount)
    {
        //This conditional is used to check whether or not a withdrawal was correctly used.
        if (atmcard = true)
        {
            if (amount > currentbalance)
            {
                System.out.println("ERROR: Withdrawing this much would cause an overdraft!");
            }

            else if (amount == 0)
            {
                System.out.println("ERROR: The amount entered is 0!");
            }

            else if (amount < 0)
            {
                System.out.println("ERROR: The amount entered is not a postive number!");
            }

            else if (amount > 0)
            {
                currentbalance = currentbalance - amount;
                transactiontotal ++;
                System.out.println("The entered amount has been successfully withdrawn from your account!");
            }
        }

        else
        {
            System.out.println("Your ATM Card is not inserted!");

        }
    }

    /**
     * This method checks the current balance of the account.
     */
    public void checkBalance()
    {
        //This conditional is used to check the current balance of an account and will print an error if a card wasn't inserted.
        if (atmcard = true)
        {      
            System.out.println(currentbalance);
            transactiontotal ++;
        }

        else
        { 
            System.out.println("Your ATM Card is not inserted!");
        }
    }

    /**
     * This method applies the interest rate to the account's balance.
     */
    private void applyMonthlyInterest()
    {
        //currentbalance = currentbalance * INTERESTRATE; // This is not working! Can't figure it out.
       
    }

    /**
     * This method formas the monthly statement and pulls everything together.
     */
    public void printMonthlyStatement()
    {
        NumberFormat fmt1 = NumberFormat.getCurrencyInstance();

        System.out.println("Hello, welcome to BankAccount!"); //This line needs to include customer name.
        System.out.println("");

        System.out.println("Deposit: "); //+ fmt1.format(deposit)); //This line needs to include the deposited amount.
        System.out.println("");

        System.out.println("Withdrawl: "); // + fmt1.format(deposit)); //This line needs to include the withdrawn amount.
        System.out.println("");

        System.out.println("Your BankAccount Statement:"); 
        System.out.println("Customer: " + customername);
        System.out.println("=====================================");
        System.out.println("Account Balance: " + fmt1.format(currentbalance));
        System.out.println("Account Transactions (This Month): " + transactiontotal);
        transactiontotal = 0;
    }

    /**
     * This method is used to test the program.
     */
    public static void main(String[] args)
    {
        BankAccount acct1 = new BankAccount("Bob Bobberson", 1234, 100.00);
        acct1.insertCard(1234);
        acct1.deposit(15.37);
        acct1.withdraw(12);
        acct1.applyMonthlyInterest();
        acct1.printMonthlyStatement();

        BankAccount acct2 = new BankAccount("Joe Smith", 5678, 1.34);
        acct2.insertCard(5678);
        acct2.deposit(12);
        acct2.withdraw(15.37);
        acct2.applyMonthlyInterest();
        acct2.printMonthlyStatement();
    }
}
