package com.enmivida.rest.controller;

import com.enmivida.rest.data.model.User;
import com.enmivida.rest.repository.UserRepository;
import com.enmivida.rest.security.AccountCredentialsVO;
import com.enmivida.rest.security.jwt.JwtUtil;
import com.enmivida.rest.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
// OJO al static
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UserRepository repository;
    private final UserService service;

    @PostMapping("/signin")
    public ResponseEntity signIn(@RequestBody AccountCredentialsVO credentials) {
        try {
            String username = credentials.getUsername();
            String password = credentials.getPassword();

            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            User user = repository.findByUserName(username).
                    orElseThrow(() -> new UsernameNotFoundException("Username "+ username+" not found"));
            String token = jwtUtil.generateToken(username, user.getRoles());
            Map<Object, Object> model = new HashMap<>();
            model.put("username", username);
            model.put("token", token);
            return ok(model);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username/password supplied");
        }
    }
}
