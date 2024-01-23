package com.ahmad.healthbodyandmind.signup;

public class signupCustomModel {
    String name;
    String phone;
    String email;
    String Password;

    public signupCustomModel(String name, String phone, String email, String password) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        Password = password;
    }
    public signupCustomModel() {
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}
