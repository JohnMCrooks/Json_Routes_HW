package com.crooks;

import jodd.json.JsonSerializer;
import org.h2.tools.Server;
import spark.Spark;

import java.sql.*;
import java.util.ArrayList;

public class Main {

    public static void createTable(Connection conn) throws SQLException {
        Statement stmt = conn.createStatement();
        stmt.execute("CREATE TABLE IF NOT EXISTS usersTable (id IDENTITY, username VARCHAR, address VARCHAR, email VARCHAR)");
    }

    public static void insertUser(Connection conn, User user) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO usersTable VALUES(null,?,?,?)");
        stmt.setString(1, user.username);
        stmt.setString(2, user.address);
        stmt.setString(3, user.email);
        stmt.execute();

    }
    public static ArrayList<User> selectUsers(Connection conn) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM usersTable");
        ResultSet results = stmt.executeQuery();
        ArrayList<User> userArrayList = new ArrayList<>();
        while(results.next()){
            int id = results.getInt("id");
            String username = results.getString("username");
            String address = results.getString("address");
            String email = results.getString("email");
            User u1 = new User(id, username,address,email);
            userArrayList.add(u1);
        }
        return userArrayList;
    }

    public static void updateUser(Connection conn, User user) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("UPDATE usersTable SET username = ?, address=?, email=?");
        stmt.setString(1, user.username);
        stmt.setString(2, user.address);
        stmt.setString(3, user.email);

        stmt.execute();
    }
    public static void deleteUser(Connection conn, int userID) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("DELETE FROM usersTable WHERE id=?");
        stmt.setInt(1, userID);
        stmt.execute();
    }




    public static void main(String[] args) throws SQLException {
        Server.createWebServer().start();
        Connection conn = DriverManager.getConnection("jdbc:h2:./main");

        Spark.externalStaticFileLocation("public");
        Spark.init();
        JsonSerializer serializer = new JsonSerializer();



    }
}
