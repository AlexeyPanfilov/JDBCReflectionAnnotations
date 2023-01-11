package org.example.db;

import org.example.annotations.Table;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class DataBase {
    protected static Connection connection;
    protected static Statement statement;

    public void connect(String dbName) {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:" + dbName);
            statement = connection.createStatement();
            System.out.println("Connected to " + dbName);
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Unable to connect database");
        }
    }

    public void disconnect() {
        try {
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            connection.close();
            System.out.println("Disconnected");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createTable(String tableName, List<String[]> columns, Class classForCreate) {
        if (!classForCreate.isAnnotationPresent(Table.class)) {
            System.out.println("Class " + classForCreate + " can't be added to table");
            return;
        }
        StringBuilder reqText = new StringBuilder("CREATE TABLE ");
        reqText.append(tableName).append(" (id INTEGER PRIMARY KEY AUTOINCREMENT, ");
        for (String[] s : columns) {
            reqText.append(s[0]).append(" ").append(s[1]).append(", ");
        }
        reqText.setLength(reqText.length() - 2);
        reqText.append(");");
        request(reqText.toString());
        System.out.println("table created");
    }

    public void request(String request) {
        try {
            statement.executeUpdate(request);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteID(String table, String id) {
        try {
            statement.executeUpdate("DELETE FROM " + table + " WHERE id = " + id + ";");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteTable(String tableName) throws SQLException {
        statement.executeUpdate("DROP TABLE " + tableName + ";");
    }
}
