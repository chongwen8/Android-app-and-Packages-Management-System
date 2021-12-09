package com.serveSide.dao.courier;

import com.serveSide.dao.BaseDao;
import com.serveSide.pojo.Courier;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CourierDao {
    public Courier getCourier(String username, String password) {
        Connection connection = null;
        Courier courier = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        StringBuilder sql = new StringBuilder("select * from couriers where userName=?");
        List<Object> params = new ArrayList<Object>();
        params.add(username);
        if (password!=null) {
            params.add(password);
            sql.append(" and password=?;");
        }
        try {
            connection = BaseDao.getConnection();
            preparedStatement = connection.prepareStatement(sql.toString());
            resultSet = BaseDao.executeQuery(preparedStatement, params.toArray());
            if (resultSet.next()){
                courier = new Courier();
                courier.setID(resultSet.getInt("courierID"));
                courier.setName(resultSet.getString("name"));
                courier.setUserName(resultSet.getString("userName"));
                courier.setPassword(resultSet.getString("password"));
                courier.setPhoneNum(resultSet.getString("phoneNum"));
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            BaseDao.closeResources(connection, preparedStatement, resultSet);
        }

        return courier;
    }

    public boolean addCourier(Courier courier) {
        boolean flag = false;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String sql = "insert into couriers(name, userName, password, phoneNum) VALUES (?,?,?,?);";

        Object[] params = {courier.getName(), courier.getUserName(), courier.getPassword(), courier.getPhoneNum()};

        try {
            connection = BaseDao.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            if (BaseDao.executeUpdate(preparedStatement, params)>0){
                flag = true;
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            BaseDao.closeResources(connection, preparedStatement, null);
        }


        return flag;
    }

    public List<Courier> getCourierList() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql = "select courierID, name, userName, phoneNum from couriers;";
        Object[] params = {};
        List<Courier> couriers = new ArrayList<Courier>();
        try {
            connection = BaseDao.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            resultSet = BaseDao.executeQuery(preparedStatement, params);
            while (resultSet.next()){
                Courier courier = new Courier();
                courier.setID(resultSet.getInt("courierID"));
                courier.setName(resultSet.getString("name"));
                courier.setUserName(resultSet.getString("userName"));
                courier.setPhoneNum(resultSet.getString("phoneNum"));
                couriers.add(courier);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            BaseDao.closeResources(connection, preparedStatement, resultSet);
        }
        return couriers;
    }
}
