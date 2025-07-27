package com.mrrice.movieflix.Controller;

import com.mrrice.movieflix.Model.User;
import com.mrrice.movieflix.Repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin
public class AuthController {
  private final UserRepository userRepository;
  private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

  public AuthController(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @PostMapping("/register")
  public String register(@RequestBody User user) {
    if(userRepository.findByUsername(user.getUsername()).isPresent()) {
      return "Username is already taken!!";
    }
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    userRepository.save(user);
    return "User registered successfully!";
  }
}
