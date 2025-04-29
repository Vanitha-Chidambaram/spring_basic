package com.vanitha.spring_basic.dao;

import lombok.Getter;
import lombok.Setter;

public class LoginUserDao {
        private String email;
        private String password;

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
