package com.serveSide.dao.packages;

import com.serveSide.dao.BaseDao;
import com.serveSide.pojo.Package;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PackageDaoIml implements PackageDao {
    @Override
    public Package getPackage() {
        return null;
    }

    @Override
    public boolean addPackage(int courier, int customer) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String sql = "insert into packages(customerID, courierID, time) VALUES(?, ?, date(now()));";
        Object[] params = {customer, courier};
        boolean flag = false;

        try {
            connection = BaseDao.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            if (BaseDao.executeUpdate(preparedStatement, params) > 0){
                flag = true;
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            BaseDao.closeResources(connection, preparedStatement, null);
        }
        return flag;
    }

    @Override
    public boolean delPackage(int courier, int customer) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String sql = "delete from packages where customerID = ? and courierID = ?;";
        Object[] params = {customer, courier};
        boolean flag = false;

        try {
            connection = BaseDao.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            if (BaseDao.executeUpdate(preparedStatement, params) > 0){
                flag = true;
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            BaseDao.closeResources(connection, preparedStatement, null);
        }
        return flag;
    }

    @Override
    public List<Package> getPackagesList(String cusName) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        StringBuilder sql = new StringBuilder("select OrderNo as OrderNo, time as time, y.courierID as courierID, y.name as courierName, y.phoneNum as courierPhoneNum," +
                " z.name as customerName, z.phoneNum as customerPhoneNum, address as customerAddress\n" +
                "from packages x, couriers y, customers z\n" +
                "where x.courierID = y.courierID and x.customerID = z.customerID\n" +
                "order by OrderNo");

        List<Object> params = new ArrayList<Object>();

        if (cusName != null){
            sql.append(" and z.name=?");
            params.add(cusName);
        }

        List<Package> packageList = new ArrayList<Package>();
        try {
            connection = BaseDao.getConnection();
            preparedStatement = connection.prepareStatement(sql.toString());
            resultSet = BaseDao.executeQuery(preparedStatement, params.toArray());
            while (resultSet.next()) {
                int OrderNo = resultSet.getInt("OrderNo");
                Date time = resultSet.getDate("time");
                int courierID = resultSet.getInt("courierID");
                String courierName = resultSet.getString("courierName");

                String courierPhoneNum = resultSet.getString("courierPhoneNum");
                String customerName = resultSet.getString("customerName");
                String customerPhoneNum = resultSet.getString("customerPhoneNum");
                String customerAddress = resultSet.getString("customerAddress");
                Package aPackage = new Package(OrderNo, time, courierID, courierName, courierPhoneNum, customerName, customerPhoneNum, customerAddress);
                packageList.add(aPackage);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            BaseDao.closeResources(connection, preparedStatement, resultSet);
        }
        return packageList;
    }

    @Override
    public boolean createView(List<String> options) {
        boolean flag = false;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        int length = options.size();
        StringBuilder sql = new StringBuilder("create or replace view t_packages_couriers as select\n");
        for (int i = 0; i < length - 1; i++) {
            sql.append(options.get(i)).append(", ");
        }
        sql.append(options.get(length - 1)).append("\n");
        sql.append("from packages, couriers, customers\n" +
                "where packages.courierID = couriers.courierID and packages.customerID = customers.customerID;");

        Object[] params = {};
        try {
            connection = BaseDao.getConnection();
            preparedStatement = connection.prepareStatement(sql.toString());
            if (BaseDao.executeUpdate(preparedStatement, params) == 0) {
                flag = true;
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            BaseDao.closeResources(connection, preparedStatement, null);
        }

        return flag;
    }

    @Override
    public List<String> getQrCodeInfo() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql = "select * from t_packages_couriers;";
        Object[] params = {};
        List<String> res = new ArrayList<String>();
        try {
            connection = BaseDao.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            resultSet = BaseDao.executeQuery(preparedStatement, params);
            while (resultSet.next()) {
                // 每次都存一个冗余的 courierID 在字符串的最前面 方便获取
                StringBuilder item = new StringBuilder(resultSet.getInt("courierID") + "&");
                int i;
                for (i = 0; i < resultSet.getMetaData().getColumnCount() - 1; i++) {
                    item.append(resultSet.getObject(i + 1).toString()).append("&");
                }
                item.append(resultSet.getObject(i + 1).toString());
                res.add(item.toString());
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            BaseDao.closeResources(connection, preparedStatement, resultSet);
        }
        return res;
    }

    @Override
    public List<String> getColumnsOfView() {

        List<String> columnsOfView = new ArrayList<String>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String sql = "select column_name as id from information_schema.columns\n" +
                "where table_schema='hw' and table_name='t_packages_couriers'\n" +
                "order by field(id, 'OrderNo','time','courierID','courierName','courierPhoneNum','customerName','customerPhoneNum','address');";
        Object[] params = {};
        ResultSet resultSet = null;
        try {
            connection = BaseDao.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            resultSet = BaseDao.executeQuery(preparedStatement, params);
            while (resultSet.next()) {
                    columnsOfView.add(resultSet.getString(1));
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            BaseDao.closeResources(connection, preparedStatement, resultSet);
        }

        return columnsOfView;

    }
}
