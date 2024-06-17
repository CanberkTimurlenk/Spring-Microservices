package com.microservices.userservice.grpc;

import com.google.protobuf.Empty;
import com.microservices.userservice.dto.UserCredentials;
import com.microservices.userservice.exceptionHandling.UserAlreadyExistsException;
import com.microservices.userservice.grpc.generated.*;
import com.microservices.userservice.service.UserService;
import com.microservices.userservice.service.mapper.UserMapper;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
@RequiredArgsConstructor
public class UserGrpcService extends UserServiceGrpc.UserServiceImplBase {

    private final UserService userService;
    private final UserMapper userMapper;

    @Override
    public void findUserByEmail(EmailGrpcRequestDto request, StreamObserver<UserGrpcResponseDto> responseObserver) {

        userService
                .findUserByEmail(request.getEmail())
                .ifPresentOrElse(u -> {
                            UserGrpcResponseDto response = UserGrpcResponseDto.newBuilder()
                                    .setId(u.id())
                                    .setFirstname(u.firstname())
                                    .setLastname(u.lastname())
                                    .setEmail(u.email())
                                    .setPassword(u.password())
                                    .setPhoneNumber(u.phoneNumber())
                                    .setAddress(u.address())
                                    .setRole(u.role())
                                    .setPremium(u.premium())
                                    .build();
                            responseObserver.onNext(response);
                            responseObserver.onCompleted();
                        },
                        responseObserver::onCompleted);
    }

    @Override
    public void findUserByCredentials(UserCredentialsGrpcRequestDto request, StreamObserver<UserGrpcResponseDto> responseObserver) {
        userService
                .findByCredentials(new UserCredentials(request.getEmail(), request.getPassword()))
                .ifPresentOrElse(u -> {
                            UserGrpcResponseDto response = UserGrpcResponseDto.newBuilder()
                                    .setId(u.id())
                                    .setFirstname(u.firstname())
                                    .setLastname(u.lastname())
                                    .setEmail(u.email())
                                    .setPassword(u.password())
                                    .setPhoneNumber(u.phoneNumber())
                                    .setAddress(u.address())
                                    .setRole(u.role())
                                    .setPremium(u.premium())
                                    .build();
                            responseObserver.onNext(response);
                            responseObserver.onCompleted();
                        },
                        responseObserver::onCompleted);
    }

    @Override
    public void saveUser(UserGrpcRequestDto request, StreamObserver<Empty> responseObserver) {

        try {
            userService.save(userMapper.toUserRequestDto(request));
            responseObserver.onNext(Empty.newBuilder().build());
            responseObserver.onCompleted();
        }
        catch (UserAlreadyExistsException e) {
            // Convert custom exception to gRPC status
            responseObserver.onError(
                    Status.ALREADY_EXISTS
                            .withDescription(e.getMessage())
                            .asRuntimeException()
            );
        }

    }
}
