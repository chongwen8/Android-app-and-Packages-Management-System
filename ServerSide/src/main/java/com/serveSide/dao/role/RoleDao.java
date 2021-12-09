package com.serveSide.dao.role;

import com.serveSide.dao.BaseDao;
import com.serveSide.pojo.Role;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoleDao {
    public List<Role> getRoleList() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql = "select * from roles";
        List<Role> roleList = new ArrayList<Role>();
        Object[] params = {};
        try {
            connection = BaseDao.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            resultSet = BaseDao.executeQuery(preparedStatement, params);
            while(resultSet.next()){
                Role role = new Role();
                role.setPower(resultSet.getInt("power"));
                role.setRoleName(resultSet.getString("roleName"));
                roleList.add(role);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            BaseDao.closeResources(connection, preparedStatement, resultSet);
        }
        return roleList;
    }
}
