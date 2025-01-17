package examen_TP.demo.dao.dto.userDto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserUpdateRequestDto {
    private Integer id;
    private String username;
    private String password;

}
