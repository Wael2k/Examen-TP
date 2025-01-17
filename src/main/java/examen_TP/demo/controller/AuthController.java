package examen_TP.demo.controller;

import examen_TP.demo.dao.dto.LoginRequestDto;
import examen_TP.demo.dao.dto.LoginResponseDto;
import examen_TP.demo.dao.dto.RegisterRequestDto;
import examen_TP.demo.dao.dto.RegisterResponseDto;
import examen_TP.demo.dao.dto.userDto.UserCreateRequestDto;
import examen_TP.demo.dao.dto.userDto.UserResponseDto;
import examen_TP.demo.dao.dto.userDto.UserUpdateRequestDto;
import examen_TP.demo.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


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

    @PostMapping("create")
    public ResponseEntity<UserResponseDto> createRole(@RequestBody UserCreateRequestDto userCreateRequestDto){

        return ResponseEntity.ok(authService.add(userCreateRequestDto));
    }
    @PutMapping("update")
    public ResponseEntity<UserResponseDto> updateRole(@RequestBody UserUpdateRequestDto userUpdateRequestDto){

        return ResponseEntity.ok(authService.update(userUpdateRequestDto));
    }
    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteRole(@PathVariable("id") Integer id){
        authService.delete(id);
        return ResponseEntity.ok().body("deleted");
    }
    @GetMapping("getAll")
    public ResponseEntity<List<UserResponseDto>> getAll(){
        return ResponseEntity.ok(authService.getAll());
    }

}
