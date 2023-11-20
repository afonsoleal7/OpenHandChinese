package org.academiadecodigo.javabank.persistence.daos.jdbc;

import org.academiadecodigo.javabank.model.Customer;
import org.academiadecodigo.javabank.model.account.Account;
import org.academiadecodigo.javabank.persistence.TransactionException;
import org.academiadecodigo.javabank.persistence.daos.CustomerDao;
import org.academiadecodigo.javabank.persistence.jdbc.JDBCSessionManager;

import java.sql.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class JDBCCustomerDao implements CustomerDao {

    private JDBCSessionManager sm;
    private JDBCAccountDao accountDao;


    public void setAccountDAO(JDBCAccountDao JDBCAccountDao) {
        this.accountDao = JDBCAccountDao;
    }

    public void setConnectionManager(JDBCSessionManager JDBCSessionManager) {
        this.sm = JDBCSessionManager;
    }

    @Override
    public Customer findById(Integer id) {

        Customer customer = null;

        try {
            String query = "SELECT customer.id AS cid, first_name, last_name, phone, email, account.id AS aid " +
                    "FROM customer " +
                    "LEFT JOIN account " +
                    "ON customer.id = account.customer_id " +
                    "WHERE customer.id = ?";


            PreparedStatement statement = sm.getCurrentSession().prepareStatement(query);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {

                if (customer == null) {
                    customer = buildCustomer(resultSet);
                }

                int accountId = resultSet.getInt("aid");
                Account account = accountDao.findById(accountId);

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
    public List<Customer> findAll() {

        Map<Integer, Customer> customers = new HashMap<>();

        try {
            String query = "SELECT customer.id AS cid, first_name, last_name, phone, email, account.id AS aid " +
                    "FROM customer " +
                    "LEFT JOIN account " +
                    "ON customer.id = account.customer_id";

            PreparedStatement statement = sm.getCurrentSession().prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                if (!customers.containsKey(resultSet.getInt("cid"))) {
                    Customer customer = buildCustomer(resultSet);
                    customers.put(customer.getId(), customer);
                }

                Account account = accountDao.findById(resultSet.getInt("aid"));
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


    private Customer buildCustomer(ResultSet resultSet) throws SQLException {

        Customer customer = new Customer();

        customer.setId(resultSet.getInt("cid"));
        customer.setFirstName(resultSet.getString("first_name"));
        customer.setLastName(resultSet.getString("last_name"));
        customer.setPhone(resultSet.getString("phone"));
        customer.setEmail(resultSet.getString("email"));

        return customer;
    }

    private Integer update(Customer customer) {

        try {

            String query = "UPDATE customer " +
                    "SET first_name = ?, last_name = ?, phone = ?, email = ?" +
                    "WHERE id = ?";

            PreparedStatement statement = sm.getCurrentSession().prepareStatement(query);

            statement.setString(1, customer.getFirstName());
            statement.setString(2, customer.getLastName());
            statement.setString(3, customer.getPhone());
            statement.setString(4, customer.getEmail());
            statement.setInt(5, customer.getId());

            statement.executeUpdate();
            statement.close();

        } catch (SQLException e) {
            throw new TransactionException();
        }

        return customer.getId();

    }

    private Integer insert(Customer customer) throws SQLException {

        String query = "INSERT INTO customer(first_name, last_name, email, phone) " +
                "VALUES(?, ?, ?, ?)";

        PreparedStatement statement = sm.getCurrentSession().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

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

        return customer.getId();


    }

    @Override
    public List<Integer> getCustomerIds() {

        List<Integer> customerIds = new LinkedList<>();

        try {

            String query = "SELECT id FROM customer";
            Statement statement = sm.getCurrentSession().createStatement();

            ResultSet result = statement.executeQuery(query);

            while (result.next()) {
                customerIds.add(result.getInt("id"));
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return customerIds;

    }


    @Override
    public Customer saveOrUpdate(Customer modelObject) {

        try {

            Integer id = null;

            if (findById(modelObject.getId()) != null) {
                id = update(modelObject);
            } else {
                id = insert(modelObject);
            }

            modelObject.setId(id);
            return modelObject;
        } catch (SQLException e) {
            throw new TransactionException();
        }
    }

    @Override
    public void delete(Integer id) {
        try {

            String query = "DELETE FROM customer WHERE id = ?";

            PreparedStatement statement = sm.getCurrentSession().prepareStatement(query);

            statement.setInt(1, id);

            statement.executeUpdate();
            statement.close();

        } catch (SQLException e) {
            throw new TransactionException();
        }
    }
}
