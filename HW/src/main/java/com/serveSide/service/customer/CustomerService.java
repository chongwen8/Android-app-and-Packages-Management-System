package com.serveSide.service.customer;

import com.serveSide.dao.customer.CustomerDao;
import com.serveSide.pojo.Customer;

import java.util.List;

public class CustomerService {
    private CustomerDao customerDao;

    public CustomerService(){
        customerDao = new CustomerDao();
    }

    public boolean register() {
        return false;
    }

    public Customer login() {
        return null;
    }

    public List<Customer> getCustomers() {
        return customerDao.getCustomers();
    }
}
