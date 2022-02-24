package in.cdac.controller;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Validated
@RestController
@RequestMapping("/auth")
public class AuthController {

    @GetMapping("/jwt")
    public String generateToken() {
        return Jwts.builder()
                .claim("userid", "1")
                .signWith(SignatureAlgorithm.HS256, "secret")
                .compact();
    }
}
