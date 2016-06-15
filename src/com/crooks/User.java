package com.crooks;

/**
 * Created by johncrooks on 6/15/16.
 */
public class User {
    Integer userID;
    String username;
    String address;
    String email;

    public User() {
    }

    public User(Integer userID, String username, String address, String email) {

        this.userID = userID;
        this.username = username;
        this.address = address;
        this.email = email;
    }

    public Integer getId() {
        return userID;
    }

    public void setId(Integer id) {
        this.userID = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "userID=" + userID +
                ", username='" + username + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
