import static org.junit.Assert.*;
import org.junit.*;
/**********************************************************
 * The jUnit test class BankAcount.
 *
 * @author Scott Grissom
 * @version January 12, 2013
 *********************************************************/
public class BankTest{

    private BankAccount account;
    /** allow for round off error */
    private double TOLERANCE = 0.01; 
    /** monthly interest rate */
    private double RATE = 1 + 0.025/12;
    
    public BankTest(){
        // no action necessary in this constructor
    }

    /******************************************************
     * Sets up the test fixture.
     *
     * Called before every test case method.
     *****************************************************/
    @Before
    public void setUp()
    {
        account = new BankAccount("Name",123,500); 
        account.insertCard(123);     
    }

    /******************************************************
     * Test initial values of the constructor
     *****************************************************/
    @Test 
    public void testConstructor()
    {
        int pin = 9999;
        double balance = 200.01;
        BankAccount acct = new BankAccount("Your Name", pin, balance); 
        acct.insertCard(pin);        
        Assert.assertTrue("BankAccount(): PIN not initialized", 
                acct.cardInserted()); 
        acct.removeCard();        
        Assert.assertEquals("BankAccount(): balance not properly initialized", 
                acct.getBalance(), balance, TOLERANCE);                  
    } 

    /******************************************************
     * Test initial values of the default constructor
     *****************************************************/    
    @Test 
    public void testDefaultConstructor()
    {
        BankAccount acct = new BankAccount(); 
        acct.insertCard(9999);        
        Assert.assertTrue("BankAccount(): PIN not initialized to 9999", 
                acct.cardInserted()); 
        acct.removeCard();        
        Assert.assertEquals("BankAccount(): balance not properly initialized", 
                acct.getBalance(), 245.67, TOLERANCE);                  
    } 

    /******************************************************
     * Test customer PIN and card insertion
     *****************************************************/    
    @Test 
    public void testValidation()
    {
        BankAccount acct = new BankAccount("Name",123,500); 
        Assert.assertTrue("BankAccount(): validation should start as false", 
                !acct.cardInserted()); 
        acct.insertCard(12);        
        Assert.assertTrue("Login(): did not properly test the PIN", 
                !acct.cardInserted()); 
        acct.insertCard(123);        
        Assert.assertTrue("Login(): did not properly test the PIN", 
                acct.cardInserted());  
        acct.removeCard();        
        Assert.assertTrue("Logout(): did not properly logout", 
                !acct.cardInserted());                  
    }  
    
    /******************************************************
     * Test for negative deposit
     *****************************************************/    
    @Test
    public void testNegativeDeposit()
    {
        double beforeBalance = account.getBalance();
        int beforeTransactions = account.getTransactions();
        account.deposit(-100);
        Assert.assertEquals("deposit(): balance should not change with negative deposit", 
                beforeBalance, account.getBalance(), TOLERANCE); 
        Assert.assertEquals("deposit(): transactions should not change with negative deposit", 
                beforeTransactions, account.getTransactions(), TOLERANCE); 
    }

    /******************************************************
     * Test for negative withdrawal
     *****************************************************/    
    @Test
    public void testNegativeWithdrawal()
    {
        double beforeBalance = account.getBalance();
        int beforeTransactions = account.getTransactions();
        account.withdraw(-100);
        Assert.assertEquals("withdraw(): balance should not change with negative deposit", 
                beforeBalance, account.getBalance(), TOLERANCE); 
        Assert.assertEquals("withdraw(): transactions should not change with negative deposit", 
                beforeTransactions, account.getTransactions(), TOLERANCE); 
    } 

    /******************************************************
     * Test positive withdrawal
     *****************************************************/    
    @Test
    public void testPositiveWithdrawal()
    {
        double beforeBalance = account.getBalance();
        double amount = 100;
        int beforeTransactions = account.getTransactions();
        account.withdraw(amount);
        Assert.assertEquals("withdraw(): balance change with withdrawal", 
                beforeBalance-amount, account.getBalance(), TOLERANCE); 
        Assert.assertEquals("withdraw(): transactions should increase with a wthdrawal", 
                beforeTransactions+1, account.getTransactions(), TOLERANCE); 
    } 
    
    /******************************************************
     * Test for overdrawn
     *****************************************************/    
    @Test
    public void testOverdrawnWithdrawal()
    {
        double beforeBalance = account.getBalance();
        double amount = 10000;
        int beforeTransactions = account.getTransactions();
        account.withdraw(amount);
        Assert.assertEquals("withdraw(): balance should not change with a potential overdraw", 
                beforeBalance, account.getBalance(), TOLERANCE); 
        Assert.assertEquals("withdraw(): transactions should not change with a potential overdraw", 
                beforeTransactions, account.getTransactions(), TOLERANCE); 
    }    
    
    /******************************************************
     * Test for positive deposit
     *****************************************************/    
    @Test
    public void testPositiveDeposit()
    {
        double amount = 100;
        double beforeBalance = account.getBalance();
        int beforeTransactions = account.getTransactions();
        account.deposit(amount);
        Assert.assertEquals("deposit(): balance should increase with deposit", 
                beforeBalance+amount, account.getBalance(), TOLERANCE); 
        Assert.assertEquals("deposit(): transactions should increase by one with deposit", 
                beforeTransactions+1, account.getTransactions(), TOLERANCE); 
    }
    
    /******************************************************
     * Test balance and transactions after monthly statement
     *****************************************************/    
    @Test
    public void testMonthlyStatement()
    {
        double amount = 100;
        double beforeBalance = account.getBalance();
        beforeBalance = beforeBalance*RATE;
        int beforeTransactions = account.getTransactions();
        account.printMonthlyStatement();
        Assert.assertEquals("printMonthlyStatement(): balance should increase with interest", 
                beforeBalance, account.getBalance(), TOLERANCE); 
        Assert.assertEquals("printMonthlyStatement(): transactions should be set to zero at start of month", 
                0, account.getTransactions(), TOLERANCE); 
    }    
}
