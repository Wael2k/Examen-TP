package examen_TP.demo.dao.dto.userDto;


import examen_TP.demo.dao.dto.roleDto.RoleResponseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class UserResponseDto {
    private Integer id ;
    private String userName ;
    private List<RoleResponseDto> role ;
}
