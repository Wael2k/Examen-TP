package examen_TP.demo.controller;

import examen_TP.demo.dao.dto.LoginRequestDto;
import examen_TP.demo.dao.dto.LoginResponseDto;
import examen_TP.demo.dao.dto.RegisterRequestDto;
import examen_TP.demo.dao.dto.RegisterResponseDto;
import examen_TP.demo.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("api/v1/auth/")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("register")
    public ResponseEntity<RegisterResponseDto> registerUser(@RequestBody RegisterRequestDto registerRequestDto){

        return ResponseEntity.ok(authService.register(registerRequestDto));
    }
    @PostMapping("login")
    public ResponseEntity<LoginResponseDto> login (@RequestBody LoginRequestDto loginRequestDto){
        return ResponseEntity.ok(authService.login(loginRequestDto));
    }

}
