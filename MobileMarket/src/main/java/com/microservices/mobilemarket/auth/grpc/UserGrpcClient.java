package com.microservices.mobilemarket.auth.grpc;

import com.microservices.mobilemarket.auth.dto.RegisterRequest;
import com.microservices.mobilemarket.grpc.generated.*;
import com.microservices.mobilemarket.user.dto.UserCredentials;
import io.grpc.Status;
import io.grpc.StatusRuntimeException;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserGrpcClient {

    @GrpcClient("UserService")
    private UserServiceGrpc.UserServiceBlockingStub userServiceBlockingStub;

    public UserGrpcResponseDto findUserByCredentials(UserCredentials credentials) {

        UserCredentialsGrpcRequestDto request = UserCredentialsGrpcRequestDto.newBuilder()
                .setEmail(credentials.email())
                .setPassword(credentials.password())
                .build();
        return userServiceBlockingStub.findUserByCredentials(request);
    }

    public UserGrpcResponseDto findUserByEmail(String email) {

        EmailGrpcRequestDto request = EmailGrpcRequestDto.newBuilder()
                .setEmail(email)
                .build();

        return userServiceBlockingStub.findUserByEmail(request);
    }

    public void register(RegisterRequest dto) {

        UserGrpcRequestDto request = UserGrpcRequestDto.newBuilder()
                .setFirstname(dto.firstname())
                .setLastname(dto.lastname())
                .setEmail(dto.email())
                .setPassword(dto.password())
                .setPhoneNumber(dto.phoneNumber())
                .setAddress(dto.address())
                .setRole(dto.role())
                .setPremium(dto.premium())
                .build();

        try {
            userServiceBlockingStub.saveUser(request);
        }
        catch (StatusRuntimeException e) {

            if (Objects.requireNonNull(e.getStatus().getCode()) == Status.Code.ALREADY_EXISTS)
                throw new IllegalArgumentException("A user already exists with email");

            else
                throw new RuntimeException("An unexpected error occurred : " + e.getStatus().getDescription());
        }
    }

}