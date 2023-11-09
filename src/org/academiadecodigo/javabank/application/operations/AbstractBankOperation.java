package org.academiadecodigo.javabank.application.operations;

import org.academiadecodigo.javabank.application.BankApplication;
import org.academiadecodigo.javabank.domain.Customer;

/**
 * A generic bank operation to be used as a base for concrete types of bank operations
 * @see Operation
 */
public abstract class AbstractBankOperation implements Operation {

    protected BankApplication bankApplication;
    protected Customer customer;

    /**
     * Initializes a new {@code AbstractBankOperation} given a bank application
     *
     * @param bankApplication the bank application
     */
    public AbstractBankOperation(BankApplication bankApplication) {
        this.bankApplication = bankApplication;
        customer = bankApplication.getBank().getCustomer(bankApplication.getAccessingCustomerId());
    }
}
