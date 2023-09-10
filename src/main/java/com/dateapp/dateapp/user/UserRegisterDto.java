package com.dateapp.dateapp.user;

import com.dateapp.dateapp.userInfo.UserInfoDto;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserRegisterDto {
    private Long id;
    @Email
    @NotNull
    private String email;
    @Size(min = 1)
    @NotNull
    private String password;
    @Size(min = 1)
    @NotNull
    private String confirmPassword;
    private String userRole;
    @NotNull
    private UserInfoDto userInfo;

    public UserRegisterDto(Long id, String email, String password) {
        this.id = id;
        this.email = email;
        this.password = password;
    }

    public UserRegisterDto(String email, String password, String confirmPassword) {
        this.email = email;
        this.password = password;
        this.confirmPassword = confirmPassword;
    }
}
