package examen_TP.demo.dao.dto.roleDto;

import examen_TP.demo.enumaration.RoleEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class RoleRequestDto {
    private RoleEnum name;
}
