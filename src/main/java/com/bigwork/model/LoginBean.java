package com.bigwork.model;

/**
 * Created by asus on 2016/5/4.
 */
public class LoginBean {
    String name = "";
    String password = "";

    public LoginBean(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean checkUser(String name, String password){
        boolean valid = false;
        if(name.equals("admin")&&password.equals("admin")){
            valid = true;
        }
        return valid;
    }
}
