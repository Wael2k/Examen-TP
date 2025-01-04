package examen_TP.demo.service;

import examen_TP.demo.dao.dto.LoginRequestDto;
import examen_TP.demo.dao.dto.LoginResponseDto;
import examen_TP.demo.dao.dto.RegisterResponseDto;
import examen_TP.demo.dao.dto.RegisterRequestDto;

public interface AuthService {
    RegisterResponseDto register (RegisterRequestDto registerRequest);

    LoginResponseDto login(LoginRequestDto loginRequestDto);
}
