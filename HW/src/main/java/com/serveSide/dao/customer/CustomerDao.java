package com.serveSide.dao.customer;

import com.serveSide.dao.BaseDao;
import com.serveSide.pojo.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDao {
    public List<Customer> getCustomers() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql = "select customerID, name, phoneNum, address from customers";
        Object[] params = {};
        List<Customer> customers = new ArrayList<Customer>();

        try {
            connection = BaseDao.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            resultSet = BaseDao.executeQuery(preparedStatement, params);
            while(resultSet.next()){
                Customer customer = new Customer();
                customer.setAddress(resultSet.getString("address"));
                customer.setID(resultSet.getInt("customerID"));
                customer.setName(resultSet.getString("name"));
                customer.setPhoneNum(resultSet.getString("phoneNum"));
                customers.add(customer);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            BaseDao.closeResources(connection, preparedStatement, resultSet);
        }

        return customers;
    }
}
