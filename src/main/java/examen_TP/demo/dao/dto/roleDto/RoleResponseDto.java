package examen_TP.demo.dao.dto.roleDto;

import examen_TP.demo.enumaration.RoleEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class RoleResponseDto {

    private Integer id;
    private RoleEnum name;
}
