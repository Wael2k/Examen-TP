package examen_TP.demo.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("api/test/")
public class TestController {
    @PostMapping("test")
    public ResponseEntity<Principal> registerUser(Principal user) {
        return ResponseEntity.ok(user);
    }
}
