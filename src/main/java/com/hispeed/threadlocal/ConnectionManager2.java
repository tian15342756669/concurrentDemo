package com.hispeed.threadlocal;


import org.springframework.boot.lang.UsesUnsafeJava;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by dengtg on 2018-9-5.
 * 线程安全
 * 但是这样会有一个致命的影响：导致服务器压力非常大，并且严重影响程序执行性能。
 * 由于在方法中需要频繁地开启和关闭数据库连接，这样不尽严重影响程序执行效率，还可能导致服务器压力巨大
 */

@UsesUnsafeJava
public class ConnectionManager2 {

    private Connection connect = null;

    public Connection openConnection() throws SQLException {
        if(connect == null){
            connect = DriverManager.getConnection("");
        }
        return connect;
    }

    public void closeConnection() throws SQLException {
        if(connect!=null) {
            connect.close();
        }
    }

    class Dao {
        public void insert() throws SQLException{

            ConnectionManager2 manager2 = new ConnectionManager2();

            Connection connection = manager2.openConnection();

            //使用connection进行操作

            manager2.closeConnection();
        }
    }
}
