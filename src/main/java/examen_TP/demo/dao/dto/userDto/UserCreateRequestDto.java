package examen_TP.demo.dao.dto.userDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserCreateRequestDto {
    private String username;
    private String password;
    private List<Integer> rolesId;

}
