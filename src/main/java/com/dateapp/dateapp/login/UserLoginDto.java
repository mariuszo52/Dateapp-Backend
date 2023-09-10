package com.dateapp.dateapp.login;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
class UserLoginDto {
    @NotNull
    @Size(min = 1)
    private String email;
    @NotNull
    @Size(min = 1)
    private String password;
}

