package com.mrrice.movieflix.Controller;

import com.mrrice.movieflix.Model.User;
import com.mrrice.movieflix.Repository.UserRepository;
import com.mrrice.movieflix.Security.JwtUtil;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final BCryptPasswordEncoder passwordEncoder;

    public AuthController(UserRepository userRepository,
                          JwtUtil jwtUtil,
                          BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body("Username is already taken!!");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return ResponseEntity.ok("User registered successfully!");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User loginUser) {
        return userRepository.findByUsername(loginUser.getUsername())
            .map(user -> {
                if (passwordEncoder.matches(loginUser.getPassword(), user.getPassword())) {
                    String token = jwtUtil.generateToken(user.getUsername());
                    return ResponseEntity.ok(Map.of(
                        "token", token,
                        "message", "Login successful!"
                    ));
                } else {
                    return ResponseEntity
                        .status(HttpStatus.UNAUTHORIZED)
                        .body("Invalid password");
                }
            })
            .orElse(ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body("User not found"));
    }
}
