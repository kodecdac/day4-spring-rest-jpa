package in.cdac.controller;

import in.cdac.model.AuthRequest;
import in.cdac.model.AuthResponse;
import in.cdac.security.JwtUtil;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Validated
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    AuthenticationManager authenticationManager;

    @PostMapping("/authenticate")
    public AuthResponse authenticate(@RequestBody AuthRequest authRequest) {

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));

        UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.getUsername());
        String jwt = jwtUtil.generateToken(userDetails);

        AuthResponse authResponse = new AuthResponse();
        authResponse.setJwt(jwt);

        return authResponse;
    }



    @GetMapping("/jwt")
    public String generateToken() {
        return Jwts.builder()
                .claim("userid", "1")
                .signWith(SignatureAlgorithm.HS256, "secret")
                .compact();
    }
}
