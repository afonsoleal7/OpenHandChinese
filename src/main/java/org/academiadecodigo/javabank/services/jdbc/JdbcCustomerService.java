package org.academiadecodigo.javabank.services.jdbc;

import org.academiadecodigo.javabank.model.Customer;
import org.academiadecodigo.javabank.model.account.Account;
import org.academiadecodigo.javabank.persistence.ConnectionManager;
import org.academiadecodigo.javabank.services.AccountService;
import org.academiadecodigo.javabank.services.CustomerService;

import java.sql.*;
import java.util.*;

public class JdbcCustomerService implements CustomerService {

    private AccountService accountService;
    private ConnectionManager connectionManager;

    public JdbcCustomerService(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    public void setAccountService(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public Customer get(Integer id) {

        Customer customer = null;

        try {
            String query = "SELECT customer.id AS cid, first_name, last_name, phone, email, account.id AS aid " +
                "FROM customer " +
                "LEFT JOIN account " +
                "ON customer.id = account.customer_id " +
                "WHERE customer.id = ?";

            PreparedStatement statement = connectionManager.getConnection().prepareStatement(query);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {

                if (customer == null) {
                    customer = buildCustomer(resultSet);
                }

                int accountId = resultSet.getInt("aid");
                Account account = accountService.get(accountId);

                if (account == null) {
                    break;
                }

                customer.addAccount(account);
            }

            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return customer;
    }

    @Override
    public List<Customer> list() {

        Map<Integer, Customer> customers = new HashMap<>();

        try {
            String query = "SELECT customer.id AS cid, first_name, last_name, phone, email, account.id AS aid " +
                "FROM customer " +
                "LEFT JOIN account " +
                "ON customer.id = account.customer_id";

            PreparedStatement statement = connectionManager.getConnection().prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                if (!customers.containsKey(resultSet.getInt("cid"))) {
                    Customer customer = buildCustomer(resultSet);
                    customers.put(customer.getId(), customer);
                }

                Account account = accountService.get(resultSet.getInt("aid"));
                if (account != null) {
                    customers.get(resultSet.getInt("cid")).addAccount(account);
                }
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return new LinkedList<>(customers.values());
    }

    @Override
    public Set<Integer> listCustomerAccountIds(Integer id) {

        Customer customer = get(id);

        if (customer == null) {
            throw new IllegalArgumentException("Customer does not exist");
        }

        List<Account> accounts = customer.getAccounts();

        if (accounts.size() == 0) {
            return Collections.emptySet();
        }

        Set<Integer> customerAccountIds = new HashSet<>();

        for (Account account : accounts) {
            customerAccountIds.add(account.getId());
        }

        return customerAccountIds;
    }

    @Override
    public double getBalance(int id) {

        Customer customer = get(id);

        if (customer == null) {
            throw new IllegalArgumentException("Customer does not exist");
        }

        List<Account> accounts = customer.getAccounts();

        double balance = 0;
        for (Account account : accounts) {
            balance += account.getBalance();
        }

        return balance;
    }

    @Override
    public void add(Customer customer) {

        if (customer.getId() != null && get(customer.getId()) != null) {
            return;
        }

        try {

            String query = "INSERT INTO customer(first_name, last_name, email, phone) " +
                "VALUES(?, ?, ?, ?)";

            PreparedStatement statement = connectionManager.getConnection().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, customer.getFirstName());
            statement.setString(2, customer.getLastName());
            statement.setString(3, customer.getEmail());
            statement.setString(4, customer.getPhone());

            statement.executeUpdate();

            ResultSet generatedKeys = statement.getGeneratedKeys();

            if (generatedKeys.next()) {
                customer.setId(generatedKeys.getInt(1));
            }

            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Customer buildCustomer(ResultSet resultSet) throws SQLException {

        Customer customer = new Customer();

        customer.setId(resultSet.getInt("cid"));
        customer.setFirstName(resultSet.getString("first_name"));
        customer.setLastName(resultSet.getString("last_name"));
        customer.setPhone(resultSet.getString("phone"));
        customer.setEmail(resultSet.getString("email"));

        return customer;
    }
}
