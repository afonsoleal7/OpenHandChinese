package org.academiadecodigo.javabank.test;

public class Test {

    public static void main(String[] args) {

        Test test = new Test();
        test.testAccount();
        test.testAuthService();
        test.testCustomerService();
        test.testAccountService();

    }

    private static String getResult(boolean result) {
        return result ? "OK" : "FAIL";
    }

    private void testAccount() {

        CheckingAccountTest checkingAccountTest = new CheckingAccountTest();
        SavingsAccountTest savingsAccountTest = new SavingsAccountTest();
        System.out.println("Checking Account: " + Test.getResult(checkingAccountTest.test()));
        System.out.println("Savings Account: " + Test.getResult(savingsAccountTest.test()));

    }

    private void testAuthService() {
        AuthServiceTest authServiceTest = new AuthServiceTest();
        System.out.println("AuthService: " + Test.getResult(authServiceTest.test()));
    }

    private void testCustomerService() {
        CustomerServiceTest customerServiceTest = new CustomerServiceTest();
        System.out.println("Customer: " + Test.getResult(customerServiceTest.test()));
    }

    private void testAccountService() {
        AccountServiceTest accountServiceTest = new AccountServiceTest();
        System.out.println("AccountService: " + Test.getResult(accountServiceTest.test()));
    }
}
