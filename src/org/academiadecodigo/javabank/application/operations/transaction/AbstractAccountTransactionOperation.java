package org.academiadecodigo.javabank.application.operations.transaction;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.integer.IntegerSetInputScanner;
import org.academiadecodigo.bootcamp.scanners.precisiondouble.DoubleInputScanner;
import org.academiadecodigo.javabank.application.BankApplication;
import org.academiadecodigo.javabank.application.Messages;
import org.academiadecodigo.javabank.application.operations.AbstractBankOperation;
import org.academiadecodigo.javabank.managers.AccountManager;

/**
 * A generic account transaction to be used as a base for concrete transaction implementations
 */
public abstract class AbstractAccountTransactionOperation extends AbstractBankOperation {

    protected AccountManager accountManager;
    private Prompt prompt;

    /**
     * Initializes a new {@code AbstractAccountTransactionOperation} given a bank application
     *
     * @see AbstractBankOperation#AbstractBankOperation(BankApplication)
     */
    public AbstractAccountTransactionOperation(BankApplication bankApplication) {
        super(bankApplication);
        prompt = bankApplication.getPrompt();
        accountManager = bankApplication.getBank().getAccountManager();
    }

    /**
     * Performs the transaction operation
     *
     * @see AbstractBankOperation#execute()
     * @see AbstractAccountTransactionOperation#hasAccounts()
     */
    @Override
    public void execute() {

        if (!hasAccounts()) {
            System.out.println("\n" + Messages.ERROR_NO_ACCOUNT);
            return;
        }

        System.out.println("\n" + Messages.OPEN_ACCOUNTS + buildAccountList());
    }

    /**
     * Checks if customer has accounts
     *
     * @return {@code true} if the customer has accounts
     */
    protected boolean hasAccounts() {
        return customer.getAccountIds().size() > 0;
    }

    /**
     * Shows all the customer accounts
     *
     * @return the customer accounts
     */
    private String buildAccountList() {

        StringBuilder builder = new StringBuilder();

        for (Integer id : customer.getAccountIds()) {
            builder.append(id);
            builder.append(" ");
        }

        return builder.toString();
    }

    /**
     * Prompts the user for an account number
     *
     * @return the account id
     */
    protected int scanAccount() {
        IntegerSetInputScanner scanner = new IntegerSetInputScanner(customer.getAccountIds());
        scanner.setMessage(Messages.CHOOSE_ACCOUNT);
        scanner.setError(Messages.ERROR_INVALID_ACCOUNT);

        return prompt.getUserInput(scanner);
    }

    /**
     * Prompts the user for a transaction amount
     *
     * @return the amount to be transactioned
     */
    protected double scanAmount() {
        DoubleInputScanner scanner = new DoubleInputScanner();
        scanner.setMessage(Messages.CHOOSE_AMOUNT);
        scanner.setError(Messages.ERROR_INVALID_AMOUNT);

        return prompt.getUserInput(scanner);
    }
}
