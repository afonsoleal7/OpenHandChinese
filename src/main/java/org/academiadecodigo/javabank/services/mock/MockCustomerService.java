package org.academiadecodigo.javabank.services.mock;

import org.academiadecodigo.javabank.model.Customer;
import org.academiadecodigo.javabank.model.account.Account;
import org.academiadecodigo.javabank.services.CustomerService;

import java.util.*;

/**
 * An {@link CustomerService} implementation
 */
public class MockCustomerService implements CustomerService {

    private Map<Integer, Customer> customerMap = new HashMap<>();

    /**
     * Gets the next account id
     *
     * @return the next id
     */
    private Integer getNextId() {
        return customerMap.isEmpty() ? 1 : Collections.max(customerMap.keySet()) + 1;
    }

    /**
     * @see CustomerService#get(Integer)
     */
    @Override
    public Customer get(Integer id) {
        return customerMap.get(id);
    }

    /**
     * @see CustomerService#list()
     */
    @Override
    public List<Customer> list() {
        return new ArrayList<>(customerMap.values());
    }

    /**
     * @see CustomerService#listCustomerAccountIds(Integer)
     */
    @Override
    public Set<Integer> listCustomerAccountIds(Integer id) {

        Set<Integer> accountIds = new HashSet<>();
        List<Account> accountList = customerMap.get(id).getAccounts();

        for (Account account : accountList) {
            accountIds.add(account.getId());
        }

        return accountIds;
    }

    /**
     * @see CustomerService#getBalance(int)
     */
    @Override
    public double getBalance(int id) {

        List<Account> accounts = customerMap.get(id).getAccounts();

        double balance = 0;
        for (Account account : accounts) {
            balance += account.getBalance();
        }

        return balance;
    }

    /**
     * @see CustomerService#add(Customer)
     */
    @Override
    public void add(Customer customer) {

        if (customer.getId() == null) {
            customer.setId(getNextId());
        }

        customerMap.put(customer.getId(), customer);
    }
}
