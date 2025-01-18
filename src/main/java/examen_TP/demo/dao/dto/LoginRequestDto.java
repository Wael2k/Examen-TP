package examen_TP.demo.dao.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class LoginRequestDto {
    @NotEmpty(message = "User name should not be empty")
    @NotNull(message = "User name should not be null")
    private String username;
    @NotEmpty(message = "Password should not be empty")
    @NotNull(message = "Password should not be null")
    private String password;
}
