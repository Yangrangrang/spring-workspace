package org.example;

import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {

    public static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
    public static final String DB_URL = "jdbc:mysql://localhost:3306/test";
    public static final int MAX_POOL_SIZE = 40;
    private static final DataSource ds;

    static {
        HikariDataSource hikariDataSource = new HikariDataSource();
        hikariDataSource.setDriverClassName(DB_DRIVER);
        hikariDataSource.setJdbcUrl(DB_URL);
        hikariDataSource.setUsername("root");
        hikariDataSource.setPassword("1234");
        hikariDataSource.setMaximumPoolSize(MAX_POOL_SIZE);
        hikariDataSource.setMinimumIdle(MAX_POOL_SIZE);

        ds = hikariDataSource;
    }

    public static Connection getConnection() {
//        String url = "jdbc:h2:mem://localhost/~/jdbc-practice;MODE=MySQL;DB_CLOSE_DELAY=-1";
//        String url = "jdbc:mysql://localhost:3306/test";
//        String id = "root";
//        String pw = "1234";
//
//        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            return DriverManager.getConnection(url, id, pw);
//        } catch (Exception e) {
//            return null;
//        }
        try {
            return ds.getConnection();
        } catch (SQLException e) {
            throw new IllegalStateException();
        }
    }

    public static DataSource getDataSource() {
//        HikariDataSource hikariDataSource = new HikariDataSource();
//        hikariDataSource.setDriverClassName(DB_DRIVER);
//        hikariDataSource.setJdbcUrl(DB_URL);
//        hikariDataSource.setUsername("root");
//        hikariDataSource.setPassword("1234");
//        hikariDataSource.setMaximumPoolSize(MAX_POOL_SIZE);
//        hikariDataSource.setMinimumIdle(MAX_POOL_SIZE);
//
//        return hikariDataSource;
        return ds;
    }
}
