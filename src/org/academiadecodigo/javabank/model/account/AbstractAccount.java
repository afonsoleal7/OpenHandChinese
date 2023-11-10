package org.academiadecodigo.javabank.model.account;

/**
 * A generic account domain entity to be used as a base for concrete types of accounts
 * @see Account
 */
public abstract class AbstractAccount implements Account {

    private int id;
    private double balance = 0;

    /**
     * Initializes a new {@code Account} instance with a given id
     *
     * @param id the account id
     */
    public AbstractAccount(int id) {
        this.id = id;
    }

    /**
     * @see Account#getId()
     */
    public int getId() {
        return id;
    }

    /**
     * @see Account#getBalance()
     */
    public double getBalance() {
        return balance;
    }

    /**
     * @see Account#getAccountType()
     */
    public abstract AccountType getAccountType();

    /**
     * Credits account if possible
     *
     * @param amount the amount to credit
     * @see Account#credit(double)
     */
    public void credit(double amount) {
        if (canCredit(amount)) {
            balance += amount;
        }
    }

    /**
     * Debits the account if possible
     *
     * @param amount the amount to debit
     * @see Account#canDebit(double)
     */
    public void debit(double amount) {
        if (canDebit(amount)) {
            balance -= amount;
        }
    }

    /**
     * @see Account#canCredit(double)
     */
    public boolean canCredit(double amount) {
        return amount > 0;
    }

    /**
     * @see Account#canDebit(double)
     */
    public boolean canDebit(double amount) {
        return amount > 0 && amount <= balance;
    }

    /**
     * @see Account#canWithdraw()
     */
    public boolean canWithdraw() {
        return true;
    }
}
