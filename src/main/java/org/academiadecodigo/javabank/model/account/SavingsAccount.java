package org.academiadecodigo.javabank.model.account;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * A savings account model entity which requires a minimum balance
 * and can only be used for transferring money, not for debiting
 * @see Account
 * @see AccountType#SAVINGS
 */

@Entity(name = "saving_single_table")
@DiscriminatorValue("saving")
public class SavingsAccount extends AbstractAccount {

    public static final double MIN_BALANCE = 100;

    /**
     * @see Account#getAccountType()
     */
    @Override
    public AccountType getAccountType() {
        return AccountType.SAVINGS;
    }

    /**
     * @see Account#canDebit(double)
     */
    @Override
    public boolean canDebit(double amount) {
        return super.canDebit(amount) && (getBalance() - amount) >= MIN_BALANCE;
    }

    /**
     * @see Account#canWithdraw()
     */
    @Override
    public boolean canWithdraw() {
        return false;
    }
}
