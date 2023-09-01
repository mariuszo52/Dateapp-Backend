package com.dateapp.dateapp.user;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

class PasswordChangeDto {
    @NotNull
    @Size(min = 1)
    private String oldPassword;
    @NotNull
    @Size(min = 1)
    private String newPassword;

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }
}
