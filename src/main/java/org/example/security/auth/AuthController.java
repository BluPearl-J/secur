package org.example.security.auth;

import lombok.RequiredArgsConstructor;
import org.example.data.models.User;
import org.example.data.repositories.UserRepository;
import org.example.dtos.requests.LoginRequest;
import org.example.dtos.responses.TokenResponse;
import org.example.dtos.requests.UserRequest;
import org.example.dtos.responses.UserResponse;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public UserResponse register(@RequestBody UserRequest req) {
        User user = new User();
        user.setName(req.getName());
        user.setEmail(req.getEmail());
        user.setPassword(passwordEncoder.encode(req.getPassword()));
        user.setRole(req.getRole());

        User saved = userRepository.save(user);
        return new UserResponse(saved.getId(), saved.getName(), saved.getEmail(), saved.getRole());
    }

    @PostMapping("/login")
    public TokenResponse login(@RequestBody LoginRequest req) {
        var user = userRepository.findByEmail(req.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid credentials"));

        if (!passwordEncoder.matches(req.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        return new TokenResponse("OK", user.getRole().name());
    }
}
