package com.testcompany.app.customerdata.application.persistence.dao;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import com.sun.jersey.api.NotFoundException;
import com.testcompany.app.customerdata.application.persistence.entity.Customer;

/**
 * A fake customer DAO to hard code some dummy customer records here
 *
 * Real implementation will use some ORM e.g. hibernate for database layer access
 *
 */
@Component
public class CustomerDao {

    private final Map<Long, Customer> customerMap = new HashMap<>();

    /**
     * pre create some customer data here
     */
    @PostConstruct
    public void init() {
        Customer customer = new Customer();
        customer.setId(1L);
        customer.setName("customer 1");
        customer.setAddress("1 Lake Rd");
        customer.setTelephone("09-123456");
        customerMap.put(customer.getId(), customer);

        customer = new Customer();
        customer.setId(2L);
        customer.setName("customer 2");
        customer.setAddress("2 Sea view Rd");
        customer.setTelephone("09-7896552");
        customerMap.put(customer.getId(), customer);

        customer = new Customer();
        customer.setId(3L);
        customer.setName("customer 3");
        customer.setAddress("3 Montain view Rd");
        customer.setTelephone("07-5556677");
        customerMap.put(customer.getId(), customer);
    }

    public Customer getCustomerById(final Long id) {
        final Customer customer = customerMap.get(id);
        if (customer == null) {
            throw new NotFoundException(String.format("Customer %s not found", id));
        }

        return customer;

    }

    /**
     * simulate to insert an new customer and return new id
     *
     * @param customer
     * @return
     */
    public Long add(final Customer customer) {
        final Random random = new Random();
        final Long id = random.nextLong();
        customer.setId(id);
        customerMap.put(id, customer);
        return id;
    }

    public void update(final Long customerId, final Customer newCustomer) {
        final Customer customer = getCustomerById(customerId);
        customer.setName(newCustomer.getName());
        customer.setAddress(newCustomer.getAddress());
        customer.setTelephone(newCustomer.getTelephone());
        customerMap.put(customerId, customer);
    }

    public void delete(final Long customerId) {
        final Customer customer = getCustomerById(customerId);
        customerMap.remove(customerId);
    }
}
