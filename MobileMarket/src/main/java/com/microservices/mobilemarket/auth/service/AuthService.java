package com.microservices.mobilemarket.auth.service;

import com.microservices.mobilemarket.auth.dto.LoginRequest;
import com.microservices.mobilemarket.auth.dto.RegisterRequest;
import com.microservices.mobilemarket.auth.grpc.UserGrpcClient;
import com.microservices.mobilemarket.auth.jwt.JwtService;

import com.microservices.mobilemarket.auth.jwt.token.TokenContent;
import com.microservices.mobilemarket.util.PasswordUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.auth.InvalidCredentialsException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthService {

    private final UserGrpcClient userGrpcClient;
    private final JwtService jwtService;

    public String login(LoginRequest loginRequest)
            throws InvalidCredentialsException {

        // generate and return token, if credentials are correct

        // retrieve user role if credentials are correct
        var userInfo = userGrpcClient.findUserByEmail(loginRequest.email());

        // Check userInfo exists
        if (userInfo == null)
            throw new InvalidCredentialsException();

        // Check if the provided password is correct
        if (!PasswordUtil.matches(loginRequest.password(), userInfo.getPassword())) {
            log.warn("Wrong password attempt for user: {}", userInfo.getEmail());
            throw new InvalidCredentialsException();
        }

        return jwtService.generateToken(new TokenContent(userInfo.getRole()), loginRequest.email());
    }

    public void register(RegisterRequest registerRequest) {

        userGrpcClient.register(
                registerRequest.copyWithNewEmail(
                        PasswordUtil.hashPassword(registerRequest.password())));

    }
}
