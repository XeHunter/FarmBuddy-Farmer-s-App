package com.example.farmbuddy;

public class UserModel {
    private String coname,number,eemail,state,pass;

    public UserModel() {
    }

    public UserModel(String coname, String number, String eemail, String state, String pass) {
        this.coname = coname;
        this.number = number;
        this.eemail = eemail;
        this.state = state;
        this.pass = pass;
    }

    public String getConame() {
        return coname;
    }

    public void setConame(String coname) {
        this.coname = coname;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getEemail() {
        return eemail;
    }

    public void setEemail(String eemail) {
        this.eemail = eemail;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
