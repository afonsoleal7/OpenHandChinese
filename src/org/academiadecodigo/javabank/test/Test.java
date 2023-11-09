package org.academiadecodigo.javabank.test;

public class Test {

    public static void main(String[] args) {

        Test test = new Test();
        test.testAccount();
        test.testCustomer();
        test.testAccountManager();
        test.testBank();

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

    private void testCustomer() {
        CustomerTest customerTest = new CustomerTest();
        System.out.println("Customer: " + Test.getResult(customerTest.test()));
    }

    private void testAccountManager() {
        AccountManagerTest accountManagerTest = new AccountManagerTest();
        System.out.println("AccountManager: " + Test.getResult(accountManagerTest.test()));
    }

    private void testBank() {
        BankTest bankTest = new BankTest();
        System.out.println("Bank: " + Test.getResult(bankTest.test()));
    }
}
