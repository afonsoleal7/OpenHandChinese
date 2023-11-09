package org.academiadecodigo.javabank.application.operations.transaction;

import org.academiadecodigo.javabank.application.BankApplication;
import org.academiadecodigo.javabank.application.UserOptions;

/**
 * An account transaction used to deposit an amount
 * @see AbstractAccountTransactionOperation
 * @see UserOptions#DEPOSIT
 */
public class DepositOperation extends AbstractAccountTransactionOperation {

    /**
     * Initializes a new {@code DepositOperation}
     *
     * @see AbstractAccountTransactionOperation
     */
    public DepositOperation(BankApplication bankApplication) {
        super(bankApplication);
    }

    /**
     * Deposit an amount into an account
     *
     * @see AbstractAccountTransactionOperation#execute()
     */
    @Override
    public void execute() {

        super.execute();

        if (!hasAccounts()) {
            return;
        }

        Integer accountId = scanAccount();
        Double amount = scanAmount();

        if (customer.getAccountIds().contains(accountId)) {
            accountManager.deposit(accountId, amount);
        }
    }
}
