package org.academiadecodigo.javabank.test;

public class Test {

    public static void main(String[] args) {

        CheckingAccountTest checkingAccountTest = new CheckingAccountTest();
        SavingsAccountTest savingsAccountTest = new SavingsAccountTest();
        AccountManagerTest accountManagerTest = new AccountManagerTest();
        CustomerTest customerTest = new CustomerTest();
        BankTest bankTest = new BankTest();

        System.out.println("CheckingAccount: " + (checkingAccountTest.test() ? "OK" : "FAIL"));
        System.out.println("SavingsAccount: " + (savingsAccountTest.test() ? "OK" : "FAIL"));
        System.out.println("AccountManager: " + (accountManagerTest.test() ? "OK" : "FAIL"));
        System.out.println("Customer: " + (customerTest.test() ? "OK" : "FAIL"));
        System.out.println("Bank: " + (bankTest.test() ? "OK" : "FAIL"));

    }

}
