package examen_TP.demo.service.imp;

import examen_TP.demo.config.security.JwtConfig;
import examen_TP.demo.config.security.JwtUtils;
import examen_TP.demo.dao.AuthRepository;
import examen_TP.demo.dao.RoleRepository;
import examen_TP.demo.dao.UserRepository;
import examen_TP.demo.dao.dto.LoginRequestDto;
import examen_TP.demo.dao.dto.LoginResponseDto;
import examen_TP.demo.dao.dto.RegisterResponseDto;
import examen_TP.demo.dao.dto.RegisterRequestDto;
import examen_TP.demo.dao.model.Role;
import examen_TP.demo.dao.model.User;
import examen_TP.demo.enumaration.RoleEnum;
import examen_TP.demo.service.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Slf4j
public class AuthServiceImp implements AuthService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthRepository authRepository;

    @Autowired
    RoleRepository roleRepository;



    @Autowired
    private JwtConfig jwtConfig;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    @Override
    public RegisterResponseDto register(RegisterRequestDto registerRequest) {
        if(userRepository.findByUserName(registerRequest.getUserName()).isPresent()){
            throw new RuntimeException("User already exists");
        }

        User user = new User();
        user.setPassword(encoder.encode(registerRequest.getPassword()));
        user.setUserName(registerRequest.getUserName());
        List<Role> roles = new ArrayList<>();
        Role roleAdmin = new Role();
        roleAdmin.setName(RoleEnum.ADMIN);
        roles.add(roleAdmin);
//        Role roleUser = new Role();
//        roleUser.setName(RoleEnum.USER);
//        roles.add(roleUser);
        Set<Role> setRoles = new HashSet<>(roleRepository.saveAll(roles));
        user.setRole(setRoles);
        user = authRepository.save(user);
        log.info("User created with id  {}",user.getId());
        return RegisterResponseDto.builder()
                .userName(user.getUsername()).build();
    }
    @Override
    public LoginResponseDto login(LoginRequestDto loginRequestDto) {
        User userInfo = userRepository.findByUserName(loginRequestDto.getUsername()).orElseThrow(() -> new RuntimeException("User not found"));

        if(!this.encoder.matches(loginRequestDto.getPassword(), userInfo.getPassword())){
            throw new RuntimeException("Incorrect password");
        }
        var jwtToken = JwtUtils.generateToken(userInfo,jwtConfig);
        log.trace("jwt token generated for user {}",userInfo.getId());
        return LoginResponseDto.builder().accessToken(jwtToken).build();
    }
}
