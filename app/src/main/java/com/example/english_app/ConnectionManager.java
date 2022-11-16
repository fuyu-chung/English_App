package com.example.english_app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.Executor;

public class ConnectionManager implements Executor {
    private Connection connection;
    public ConnectionManager() throws SQLException {
    }
    public void close() throws SQLException {
        connection.close();
        connection = null;
    }
    public ResultSet executeQuery(String query) throws SQLException {
        Statement statement = connection.createStatement();
        return statement.executeQuery(query);
    }
    public ResultSet executeQuery(String query, String[] arr) throws SQLException { // arr 是 替換的字 (user_name)
        PreparedStatement statement = connection.prepareStatement(query);
        for (int i = 0; i < arr.length; i++) {
           statement.setString(i + 1, arr[i]); // 把query問號替換成arr的第i個
        }
        return statement.executeQuery(query);
    }

    @Override
    public void execute(Runnable runnable) {
        try {
            connection = DriverManager.getConnection("jdbc:jtds:sqlserver://myenglishserver.database.windows.net:1433/englishapp_db;user=englishapp@myenglishserver;password=English1234@@;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;ssl=request;");
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
