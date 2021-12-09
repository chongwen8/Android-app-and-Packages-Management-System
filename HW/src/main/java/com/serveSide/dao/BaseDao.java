package com.serveSide.dao;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class BaseDao {
    private static final String DRIVER;
    private static final String URL;
    private static final String USER;
    private static final String PASSWORD;

    static {
        Properties properties = new Properties();
        // 读取资源文件
        InputStream inputStream = BaseDao.class.getClassLoader().getResourceAsStream("db.properties");

        try {
            properties.load(inputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }

        DRIVER = properties.getProperty("DRIVER");
        URL = properties.getProperty("URL");
        USER = properties.getProperty("USER");
        PASSWORD = properties.getProperty("PASSWORD");
    }

    // 连接数据库
    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        Connection connection = null;
        Class.forName(DRIVER);
        connection = DriverManager.getConnection(URL, USER, PASSWORD);
        return connection;
    }


    /**
     * @return 查询结果
     * @throws SQLException
     *  查询静态方法
     */
    public static ResultSet executeQuery(PreparedStatement preparedStatement, Object[] params) throws SQLException {
        for (int i = 0; i < params.length; i++) {
            preparedStatement.setObject(i+1, params[i]);
        }
        return preparedStatement.executeQuery();
    }

    // 增删改公共方法

    /**
     * @return 增删删改 int
     * @throws SQLException
     * 增删改静态方法
     */
    public static int executeUpdate(PreparedStatement preparedStatement, Object[] params) throws SQLException {
        for (int i = 0; i < params.length; i++) {
            preparedStatement.setObject(i + 1, params[i]);
        }
        return preparedStatement.executeUpdate();
    }

    // 关闭连接
    public static void closeResources(Connection connection, PreparedStatement preparedStatement, ResultSet resultSet) {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
