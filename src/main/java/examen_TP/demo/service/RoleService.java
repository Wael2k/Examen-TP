package examen_TP.demo.service;

import examen_TP.demo.dao.dto.roleDto.RoleRequestDto;
import examen_TP.demo.dao.dto.roleDto.RoleRequestUpdateDto;
import examen_TP.demo.dao.dto.roleDto.RoleResponseDto;

import java.util.List;

public interface RoleService {

RoleResponseDto addRole(RoleRequestDto roleRequestDto);
RoleResponseDto updateRole(RoleRequestUpdateDto roleRequestUpdateDto);
void deleteRole(Integer id);
List<RoleResponseDto> getAllRoles();
}
