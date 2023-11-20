package org.academiadecodigo.javabank.services;

import org.academiadecodigo.javabank.model.account.Account;
import org.academiadecodigo.javabank.persistence.TransactionException;
import org.academiadecodigo.javabank.persistence.TransactionManager;
import org.academiadecodigo.javabank.persistence.daos.AccountDao;


public class AccountServiceImpl implements AccountService {

    private AccountDao accountDao;
    private TransactionManager tm;

    public void setTm(TransactionManager tm) {
        this.tm = tm;
    }

    public void setAccountDAO(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    @Override
    public Account get(Integer id) {

        try {
            tm.beginRead();
            return accountDao.findById(id);
        } finally {
            tm.commit();
        }

    }

    @Override
    public void add(Account account) {

        try {
            tm.beginWrite();
            accountDao.saveOrUpdate(account);

            tm.commit();
        } catch (TransactionException e) {
            tm.rollback();
        }

    }

    @Override
    public void deposit(int id, double amount) {

        try {
            tm.beginWrite();

            Account account = get(id);

            if (account == null) {
                throw new IllegalArgumentException("invalid account id");
            }

            account.credit(amount);
            accountDao.saveOrUpdate(account);
            tm.commit();
        } catch (TransactionException e) {
            tm.rollback();
        }
    }

    @Override
    public void withdraw(int id, double amount) {

        try {
            tm.beginWrite();

            Account account = get(id);

            if (account == null) {
                throw new IllegalArgumentException("invalid account id");
            }

            account.debit(amount);
            accountDao.saveOrUpdate(account);
            tm.commit();

        } catch (TransactionException e) {
            tm.rollback();
        }
    }

    @Override
    public void transfer(int srcId, int dstId, double amount) {

        try {
            tm.beginWrite();
            Account srcAccount = get(srcId);
            Account dstAccount = get(dstId);

            if (srcAccount == null || dstAccount == null) {
                throw new IllegalArgumentException("invalid account id");
            }

            if (srcAccount.canDebit(amount)) {
                srcAccount.debit(amount);
                dstAccount.credit(amount);

                accountDao.saveOrUpdate(srcAccount);
                accountDao.saveOrUpdate(dstAccount);
            }
            tm.commit();
        } catch (TransactionException e) {
            tm.rollback();
        }
    }


}
