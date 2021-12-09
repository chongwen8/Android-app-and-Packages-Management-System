package com.serveSide.dao.manager;

import com.serveSide.dao.BaseDao;
import com.serveSide.pojo.Manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ManagerDao {
    public Manager getManager(String account, String password, String phoneNum) {
        Manager manager = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Object> params = new ArrayList<Object>();

        StringBuilder sql = new StringBuilder("select account, password, phoneNum, lastLoginTime, creationDate, power\n" +
                "from managers\n");
        if (account != null) {
            sql.append("where account = ? and password = ?;");
            params.add(account);
            params.add(password);
        } else if (phoneNum != null) {
            sql.append("where phoneNum=?;");
            params.add(phoneNum);
        }

        try {
            connection = BaseDao.getConnection();
            preparedStatement = connection.prepareStatement(sql.toString());
            resultSet = BaseDao.executeQuery(preparedStatement, params.toArray());
            if (resultSet.next()) {
                manager = new Manager();
                manager.setAccount(resultSet.getString("account"));
                manager.setPassword(resultSet.getString("password"));
                manager.setPhoneNum(resultSet.getString("phoneNum"));
                manager.setLastLoginTime(resultSet.getDate("lastLoginTime"));
                manager.setCreationDate(resultSet.getDate("creationDate"));
                manager.setPower(resultSet.getInt("power"));
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            BaseDao.closeResources(connection, preparedStatement, resultSet);
        }

        return manager;
    }

    public boolean addManager(Manager manager) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String sql = "insert into managers (account, password, phoneNum, creationDate, power)\n" +
                "values (?, ?, ?, curdate(), ?);";
        Object[] params = {manager.getAccount(), manager.getPassword(), manager.getPhoneNum(), manager.getPower()};
        boolean flag = false;
        try {
            connection = BaseDao.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            if (BaseDao.executeUpdate(preparedStatement, params) > 0) {
                flag = true;
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            BaseDao.closeResources(connection, preparedStatement, null);
        }
        return flag;
    }

    public boolean delManager(String account) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String sql = "delete from managers where account = ?";
        Object[] params = {account};
        boolean flag = false;
        try {
            connection = BaseDao.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            if (BaseDao.executeUpdate(preparedStatement, params) > 0) {
                flag = true;
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            BaseDao.closeResources(connection, preparedStatement, null);
        }
        return flag;
    }

    public boolean updatePwd(String account, String phoneNum, String newPassword) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        StringBuilder sql = new StringBuilder("update managers set password = ?\n");
        List<Object> params = new ArrayList<Object>();
        params.add(newPassword);
        if (account != null) {
            sql.append("where account = ?;");
            params.add(account);
        } else if (phoneNum != null) {
            sql.append("where phoneNum = ?;");
            params.add(phoneNum);
        }
        boolean flag = false;

        try {
            connection = BaseDao.getConnection();
            preparedStatement = connection.prepareStatement(sql.toString());
            if (BaseDao.executeUpdate(preparedStatement, params.toArray()) > 0) {
                flag = true;
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            BaseDao.closeResources(connection, preparedStatement, null);
        }

        return flag;
    }

    public List<Manager> getManagerList(int power) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        StringBuilder sql = new StringBuilder("select account, phoneNum, lastLoginTime, creationDate, power\n" +
                "from managers\n");
        ResultSet resultSet = null;
        List<Object> params = new ArrayList<Object>();
        if (power > 0) {
            sql.append("where power=?\n");
            params.add(power);
        }
        sql.append("order by creationDate;");
        List<Manager> managerList = new ArrayList<Manager>();
        try {
            connection = BaseDao.getConnection();
            preparedStatement = connection.prepareStatement(sql.toString());
            resultSet = BaseDao.executeQuery(preparedStatement, params.toArray());
            while (resultSet.next()) {
                Manager manager = new Manager();
                manager.setAccount(resultSet.getString("account"));
                manager.setPhoneNum(resultSet.getString("phoneNum"));
                manager.setPower(resultSet.getInt("power"));
                manager.setLastLoginTime(resultSet.getDate("lastLoginTime"));
                manager.setCreationDate(resultSet.getDate("creationDate"));
                managerList.add(manager);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            BaseDao.closeResources(connection, preparedStatement, resultSet);
        }
        return managerList;
    }

}
