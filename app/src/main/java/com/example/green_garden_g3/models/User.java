package com.example.green_garden_g3.models;

public class User {

    private final String idUser;
    private String name, lastName, email, password;

    public User(String idUser, String name, String lastName, String email, String password) {
        this.idUser = idUser;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }
    public String getIdUser() {
        return idUser;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
