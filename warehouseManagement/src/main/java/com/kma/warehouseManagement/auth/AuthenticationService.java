package com.kma.warehouseManagement.auth;

import com.kma.warehouseManagement.config.JwtService;
import com.kma.warehouseManagement.entity.User;
import com.kma.warehouseManagement.enumCustom.RoleUser;
import com.kma.warehouseManagement.exception.EmailAlreadyExistException;
import com.kma.warehouseManagement.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {
        if (userRepository.countByEmail(request.getEmail()) > 0 ||
                userRepository.countByUsername(request.getUsername()) >0) {
            throw new EmailAlreadyExistException("Email or username already exists");
        }
        // xac dionh role
        RoleUser role;
        if (Objects.equals(request.getRole(), "SALER")) role = RoleUser.SALER;
        else role = RoleUser.STOCKER;

        var user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(role)
                .phone(request.getPhone())
                .storageId(request.getStorageId())
                .marketId(request.getMarketId())
                .build();
        userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(      //this authentication manager take an object of type username and password authentication token
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );// if the username and password is not correct the exception is thrown
        var user = userRepository.findByUsername(request.getUsername())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}


