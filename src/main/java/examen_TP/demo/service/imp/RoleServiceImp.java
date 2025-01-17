package examen_TP.demo.service.imp;

import examen_TP.demo.config.security.exceptions.DataNotFoundException;
import examen_TP.demo.dao.RoleRepository;
import examen_TP.demo.dao.dto.roleDto.RoleRequestDto;
import examen_TP.demo.dao.dto.roleDto.RoleRequestUpdateDto;
import examen_TP.demo.dao.dto.roleDto.RoleResponseDto;
import examen_TP.demo.dao.model.Role;
import examen_TP.demo.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImp implements RoleService {
    @Autowired
    RoleRepository roleRepository;

    @Override
    public RoleResponseDto addRole(RoleRequestDto roleRequestDto) {
        Role role = new Role();
        role.setName(roleRequestDto.getName());
        role = roleRepository.save(role);
        return RoleResponseDto.builder().id(role.getId()).name(role.getName()).build();
    }

    @Override
    public RoleResponseDto updateRole(RoleRequestUpdateDto roleRequestUpdateDto) {
        Optional<Role>role = roleRepository.findById(roleRequestUpdateDto.getId());
        if (!role.isPresent()) {
            throw new DataNotFoundException("NOT_FOUND","Role not found");
        }
        role.get().setName(roleRequestUpdateDto.getName());
        roleRepository.save(role.get());
        return RoleResponseDto.builder().id(roleRequestUpdateDto.getId()).name(roleRequestUpdateDto.getName()).build();

    }

    @Override
    public void deleteRole(Integer id) {
        Optional<Role>role = roleRepository.findById(id);
        if (!role.isPresent()) {
           throw new DataNotFoundException("NOT_FOUND","Role not found");
        }
       roleRepository.delete(role.get());
    }

    @Override
    public List<RoleResponseDto> getAllRoles() {
        List<Role> roles = roleRepository.findAll();
        List<RoleResponseDto> roleResponseDtos = new ArrayList<>();
        roles.forEach(role -> {
            roleResponseDtos.add(RoleResponseDto.builder().id(role.getId()).name(role.getName()).build());
        });
        return roleResponseDtos;
    }
}
