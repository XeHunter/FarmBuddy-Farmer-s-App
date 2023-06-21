package com.example.farmbuddy;

public class farmermodel {
    private String name,number,email,age,state,password;

    public farmermodel() {
    }

    public farmermodel(String name, String number, String email, String age, String state, String password) {
        this.name = name;
        this.number = number;
        this.email = email;
        this.age = age;
        this.state = state;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
