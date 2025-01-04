package examen_TP.demo.config.security;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class JwtConfig {
    @Value("${signingKey}")
    private String signingKey;


    @Value("${access-key:access_id}")
    private String accessId;
    @Value("${role:role}")
    private String role;
    @Value("${jwt.expiration}")
    private String expiration;
}
