package examen_TP.demo.service.imp;

import examen_TP.demo.config.security.JwtConfig;
import examen_TP.demo.config.security.JwtUtils;
import examen_TP.demo.config.security.ObjectsValidatorUtils;
import examen_TP.demo.config.security.exceptions.BadRequestException;
import examen_TP.demo.config.security.exceptions.DataNotFoundException;
import examen_TP.demo.dao.AuthRepository;
import examen_TP.demo.dao.RoleRepository;
import examen_TP.demo.dao.UserRepository;
import examen_TP.demo.dao.dto.LoginRequestDto;
import examen_TP.demo.dao.dto.LoginResponseDto;
import examen_TP.demo.dao.dto.RegisterResponseDto;
import examen_TP.demo.dao.dto.RegisterRequestDto;
import examen_TP.demo.dao.dto.roleDto.RoleResponseDto;
import examen_TP.demo.dao.dto.userDto.UserCreateRequestDto;
import examen_TP.demo.dao.dto.userDto.UserResponseDto;
import examen_TP.demo.dao.dto.userDto.UserUpdateRequestDto;
import examen_TP.demo.dao.model.Role;
import examen_TP.demo.dao.model.User;
import examen_TP.demo.enumaration.RoleEnum;
import examen_TP.demo.service.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class AuthServiceImp implements AuthService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    private ObjectsValidatorUtils objectsValidatorUtil;

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
        List<Role> setRoles = roleRepository.saveAll(roles);
        user.setRole(setRoles);
        user = authRepository.save(user);
        log.info("User created with id  {}",user.getId());
        return RegisterResponseDto.builder()
                .userName(user.getUsername()).build();
    }
    @Override
    public LoginResponseDto login(LoginRequestDto loginRequestDto) {
        List<String> errors  = objectsValidatorUtil.validate(loginRequestDto);
        if(!errors.isEmpty()){
            throw new BadRequestException("BAD_REQUEST","Bad request" ,errors);
        }
        User userInfo = userRepository.findByUserName(loginRequestDto.getUsername()).orElseThrow(() -> new DataNotFoundException("NOT_FOUND","User not found"));

        if(!this.encoder.matches(loginRequestDto.getPassword(), userInfo.getPassword())){
            throw new RuntimeException("Incorrect password");
        }
        var jwtToken = JwtUtils.generateToken(userInfo,jwtConfig);
        log.trace("jwt token generated for user {}",userInfo.getId());
        return LoginResponseDto.builder().accessToken(jwtToken).build();
    }

    @Override
    public UserResponseDto add(UserCreateRequestDto userCreateRequestDto) {
        User user = new User();
        user.setPassword(encoder.encode(userCreateRequestDto.getPassword()));
        user.setUserName(userCreateRequestDto.getUsername());
        user = authRepository.save(user);
        List<Role> roles = new ArrayList<>();
        User finalUser = user;
        userCreateRequestDto.getRolesId().forEach(roleId -> {
            Role role = roleRepository.findById(roleId).orElseThrow(() -> new DataNotFoundException("NOT_FOUND","Role not found"));
            List<User> users = role.getUsers();
            users.add(finalUser);
            role.setUsers(users);
            roleRepository.save(role);
            roles.add(role);
        });
        user.setRole(roles);
        user = userRepository.save(user);

        List<RoleResponseDto> roleResponseDtos = new ArrayList<>();
        user.getRole().forEach(role -> {
            roleResponseDtos.add(RoleResponseDto.builder().name(role.getName())
                    .id(role.getId()).build());
        });


        UserResponseDto userResponseDto = UserResponseDto.builder().id(user.getId()).role(roleResponseDtos).userName(user.getUsername()).build();
      return userResponseDto;
    }

    @Override
    public UserResponseDto update(UserUpdateRequestDto userUpdateRequestDto) {
        User user = userRepository.findById(userUpdateRequestDto.getId()).orElseThrow(() -> new DataNotFoundException("NOT_FOUND","User not found"));
        user.setPassword(encoder.encode(userUpdateRequestDto.getPassword()));
        user.setUserName(userUpdateRequestDto.getUsername());
        user = userRepository.save(user);
        List<RoleResponseDto> roleResponseDtos = new ArrayList<>();

        user.getRole().forEach(role -> {
            roleResponseDtos.add(RoleResponseDto.builder().name(role.getName())
                    .id(role.getId()).build());
        });
        UserResponseDto userResponseDto = UserResponseDto.builder().id(user.getId()).role(roleResponseDtos).userName(user.getUsername()).build();
        return userResponseDto;
    }

    @Override
    public void delete(Integer id) {
        User user = userRepository.findById(id).orElseThrow(() -> new DataNotFoundException("NOT_FOUND","User not found"));
        userRepository.delete(user);
    }

    @Override
    public List<UserResponseDto> getAll() {
        List<User> users = userRepository.findAll();
        List<UserResponseDto> userResponseDtos = new ArrayList<>();
        users.forEach(user -> {
            List<RoleResponseDto> roleResponseDtos = new ArrayList<>();

            user.getRole().forEach(role -> {
                roleResponseDtos.add(RoleResponseDto.builder().name(role.getName())
                        .id(role.getId()).build());
            });
            UserResponseDto userResponseDto = UserResponseDto.builder().id(user.getId()).role(roleResponseDtos).userName(user.getUsername()).build();
            userResponseDtos.add(userResponseDto);
        });
        return userResponseDtos;
    }
}
