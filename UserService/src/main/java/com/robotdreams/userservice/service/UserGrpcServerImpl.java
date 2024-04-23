package com.robotdreams.userservice.service;

import com.robotdreams.usergrpcservice.UserRequest;
import com.robotdreams.usergrpcservice.UserResponse;
import com.robotdreams.usergrpcservice.UserServiceGrpc;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@GrpcService
@RequiredArgsConstructor
public class UserGrpcServerImpl extends UserServiceGrpc.UserServiceImplBase {

    private static final Logger logger = LoggerFactory.getLogger(UserGrpcServerImpl.class);
    private final UserService userService;


    @Override
    public void getUserById(UserRequest request, StreamObserver<UserResponse> responseObserver) {
        logger.info("Grpc call received: " + request);
        var response = userService.findById(request.getUserId()).get();

        responseObserver.onNext(UserResponse.newBuilder()
                .setId(response.id())
                .setServerTime(response.serverTime())
                .setFirstname(response.firstname())
                .setLastname(response.lastname())
                .setEmail(response.email())
                .setPhoneNumber(response.phoneNumber())
                .setAddress(response.address())
                .setPremium(response.premium())
                .build());

        responseObserver.onCompleted();
    }
}
