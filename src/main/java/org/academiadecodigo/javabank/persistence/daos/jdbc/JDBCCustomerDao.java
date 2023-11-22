package org.academiadecodigo.javabank.persistence.daos.jdbc;

import org.academiadecodigo.javabank.model.Customer;
import org.academiadecodigo.javabank.persistence.TransactionException;
import org.academiadecodigo.javabank.persistence.daos.CustomerDao;
import org.academiadecodigo.javabank.persistence.jdbc.JPASessionManager;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.sql.*;
import java.util.List;

public class JDBCCustomerDao implements CustomerDao {

    private JPASessionManager sm;
    private JDBCAccountDao accountDao;


    public void setAccountDAO(JDBCAccountDao JDBCAccountDao) {
        this.accountDao = JDBCAccountDao;
    }

    public void setConnectionManager(JPASessionManager JDBCSessionManager) {
        this.sm = JDBCSessionManager;
    }

    @Override
    public Customer findById(Integer id) {


        try {

            // 1 - get a CriteriaBuilder object from the EntityManager
            CriteriaBuilder builder = sm.getCurrentSession().getCriteriaBuilder();

            // 2 - create a new CriteriaQuery instance for the Customer entity
            CriteriaQuery<Customer> criteriaQuery = builder.createQuery(Customer.class);

            // 3 - get the root of the query, from where all navigation starts
            Root<Customer> root = criteriaQuery.from(Customer.class);

            // 4 - specify the item that is to be returned in the query result
            criteriaQuery.select(root);

            // 5 - add query restrictions
            criteriaQuery.where(builder.equal(root.get("id"), id));

            // 6 - create and execute a query using the criteria
            return sm.getCurrentSession().createQuery(criteriaQuery).getSingleResult();

        } finally {
            if (sm != null) {
                sm.getCurrentSession().close();
            }
        }
    }


    @Override
    public List<Customer> findAll() {
        CriteriaBuilder builder = sm.getCurrentSession().getCriteriaBuilder();

        // 2 - create a new CriteriaQuery instance for the Customer entity
        CriteriaQuery<Customer> criteriaQuery = builder.createQuery(Customer.class);

        // 3 - get the root of the query, from where all navigation starts
        Root<Customer> root = criteriaQuery.from(Customer.class);

                // 4 - specify the item that is to be returned in the query result
        criteriaQuery.select(root);


        return sm.getCurrentSession().createQuery(criteriaQuery).getResultList();
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


        // 6 - create and execute a query using the criteria




        return 1;

}

        /*

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

         */

        //return customer.getId();



    private Integer insert(Customer customer) throws SQLException {
        /*

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

         */
        return customer.getId();


    }

    @Override
    public List<Integer> getCustomerIds() {
        CriteriaBuilder builder = sm.getCurrentSession().getCriteriaBuilder();


        CriteriaQuery<Integer> criteriaQuery = builder.createQuery(Integer.class);


        Root<Customer> root = criteriaQuery.from(Customer.class);


        criteriaQuery.select(root.get("id"));


        return sm.getCurrentSession().createQuery(criteriaQuery).getResultList();

        /*

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

         */
        return null;


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
        CriteriaBuilder builder = sm.getCurrentSession().getCriteriaBuilder();
        CriteriaDelete<Customer> criteriaQuery = builder.createCriteriaDelete(Customer.class);
        Root<Customer> root = criteriaQuery.from(Customer.class);
        criteriaQuery.where(builder.equal(root.get("id"), id));
        /*
        try {

            String query = "DELETE FROM customer WHERE id = ?";

            PreparedStatement statement = sm.getCurrentSession().prepareStatement(query);

            statement.setInt(1, id);

            statement.executeUpdate();
            statement.close();

        } catch (SQLException e) {
            throw new TransactionException();
        }
    } */
    }
}
