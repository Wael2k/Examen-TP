package examen_TP.demo.service;

import examen_TP.demo.dao.dto.LoginRequestDto;
import examen_TP.demo.dao.dto.LoginResponseDto;
import examen_TP.demo.dao.dto.RegisterResponseDto;
import examen_TP.demo.dao.dto.RegisterRequestDto;
import examen_TP.demo.dao.dto.userDto.UserCreateRequestDto;
import examen_TP.demo.dao.dto.userDto.UserResponseDto;
import examen_TP.demo.dao.dto.userDto.UserUpdateRequestDto;


import java.util.List;

public interface AuthService  {
    RegisterResponseDto register (RegisterRequestDto registerRequest);

    LoginResponseDto login(LoginRequestDto loginRequestDto);
    UserResponseDto add(UserCreateRequestDto userCreateRequestDto);
    UserResponseDto update(UserUpdateRequestDto userUpdateRequestDto);
    void delete(Integer id);
    List<UserResponseDto> getAll();

}
