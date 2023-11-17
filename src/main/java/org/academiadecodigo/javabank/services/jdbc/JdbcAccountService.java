package org.academiadecodigo.javabank.services.jdbc;

import org.academiadecodigo.javabank.factories.AccountFactory;
import org.academiadecodigo.javabank.model.account.Account;
import org.academiadecodigo.javabank.model.account.AccountType;
import org.academiadecodigo.javabank.persistence.ConnectionManager;
import org.academiadecodigo.javabank.services.AccountService;

import java.sql.*;

public class JdbcAccountService implements AccountService {

    private ConnectionManager connectionManager;
    private AccountFactory accountFactory;

    public JdbcAccountService(ConnectionManager connectionManager, AccountFactory accountFactory) {
        this.connectionManager = connectionManager;
        this.accountFactory = accountFactory;
    }

    @Override
    public Account get(Integer id) {

        Account account = null;

        try {

            String query = "SELECT id, account_type, customer_id, balance FROM account WHERE id=?";
            PreparedStatement statement = connectionManager.getConnection().prepareStatement(query);

            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {

                AccountType accountType = AccountType.valueOf(resultSet.getString("account_type"));

                account = accountFactory.createAccount(accountType);
                account.setId(resultSet.getInt("id"));
                account.setCustomerId(resultSet.getInt("customer_id"));
                account.credit(resultSet.getInt("balance"));
            }

            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return account;
    }

    @Override
    public void add(Account account) {


        try {

            String query = "INSERT INTO account(account_type, balance, customer_id) " +
                    "VALUES (?, ?, ?)";

            PreparedStatement statement = connectionManager.getConnection().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, account.getAccountType().name());
            statement.setDouble(2, account.getBalance());
            statement.setInt(3, account.getCustomerId());

            statement.executeUpdate();

            ResultSet generatedKeys = statement.getGeneratedKeys();

            if(generatedKeys.next()) {
                account.setId(generatedKeys.getInt(1));
            }

            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deposit(int id, double amount) {

        Account account = get(id);

        if (account == null) {
            throw new IllegalArgumentException("invalid account id");
        }

        try {

            account.credit(amount);
            updateBalance(account.getId(), account.getBalance());

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void withdraw(int id, double amount) {

        Account account = get(id);

        if (account == null) {
            throw new IllegalArgumentException("invalid account id");
        }

        try {

            account.debit(amount);
            updateBalance(account.getId(), account.getBalance());

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void transfer(int srcId, int dstId, double amount) {

        Account srcAccount = get(srcId);
        Account dstAccount = get(dstId);

        if (srcAccount == null || dstAccount == null) {
            throw new IllegalArgumentException("invalid account id");
        }

        try {
            if(srcAccount. canDebit(amount)){
                srcAccount.debit(amount);
                dstAccount.credit(amount);

                updateBalance(srcAccount.getId(), srcAccount.getBalance());
                updateBalance(dstAccount.getId(), dstAccount.getBalance());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void updateBalance(int id, double totalBalance) throws SQLException {

        String query = "UPDATE account SET balance = ? WHERE id = ?";

        PreparedStatement statement = connectionManager.getConnection().prepareStatement(query);

        statement.setDouble(1, totalBalance);
        statement.setInt(2, id);

        statement.executeUpdate();
        statement.close();
    }
}
