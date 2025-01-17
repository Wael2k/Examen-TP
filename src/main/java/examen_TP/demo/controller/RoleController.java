package examen_TP.demo.controller;


import examen_TP.demo.dao.dto.roleDto.RoleRequestDto;
import examen_TP.demo.dao.dto.roleDto.RoleRequestUpdateDto;
import examen_TP.demo.dao.dto.roleDto.RoleResponseDto;
import examen_TP.demo.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/role/")
public class RoleController {
    @Autowired
    private RoleService roleService;
    @PostMapping("create")
    public ResponseEntity<RoleResponseDto> createRole(@RequestBody RoleRequestDto roleRequestDto){

        return ResponseEntity.ok(roleService.addRole(roleRequestDto));
    }
    @PutMapping("update")
    public ResponseEntity<RoleResponseDto> updateRole(@RequestBody RoleRequestUpdateDto roleRequestDto){

        return ResponseEntity.ok(roleService.updateRole(roleRequestDto));
    }
    @DeleteMapping("delete/{id}")
    public ResponseEntity<RoleResponseDto> deleteRole(@PathVariable("id") Integer id){
        roleService.deleteRole(id);
        return ResponseEntity.ok().build();
    }
    @GetMapping("getAll")
    public ResponseEntity<List<RoleResponseDto>> getAll(){
        return ResponseEntity.ok(roleService.getAllRoles());
    }

}
