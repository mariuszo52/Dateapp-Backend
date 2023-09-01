package com.dateapp.dateapp.login;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

class UserLoginDto {
    @NotNull
    @Size(min = 1)
    private String email;
    @NotNull
    @Size(min = 1)
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

