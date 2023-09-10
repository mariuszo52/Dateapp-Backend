package com.dateapp.dateapp.user;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
class PasswordChangeDto {
    @NotNull
    @Size(min = 1)
    private String oldPassword;
    @NotNull
    @Size(min = 1)
    private String newPassword;

}
