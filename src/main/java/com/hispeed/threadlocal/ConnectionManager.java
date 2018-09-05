package com.hispeed.threadlocal;


import org.springframework.boot.lang.UsesUnsafeJava;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static java.sql.DriverManager.getConnection;

/**
 * Created by dengtg on 2018-9-5.
 * 非线程安全
 */

@UsesUnsafeJava
public class ConnectionManager {

    private static Connection connect = null;

    public static Connection openConnection() throws SQLException {
        if(connect == null){
            connect = DriverManager.getConnection("");
        }
        return connect;
    }

    public static void closeConnection() throws SQLException {
        if(connect!=null) {
            connect.close();
        }
    }
}
