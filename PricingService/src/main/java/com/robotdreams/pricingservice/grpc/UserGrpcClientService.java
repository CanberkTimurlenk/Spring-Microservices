package com.robotdreams.pricingservice.grpc;

import com.robotdreams.pricingservice.dto.user.UserResponseDto;
import com.robotdreams.usergrpcservice.UserRequest;
import com.robotdreams.usergrpcservice.UserResponse;
import com.robotdreams.usergrpcservice.UserServiceGrpc;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserGrpcClientService {

    @GrpcClient("cloud-grpc-server")
    private UserServiceGrpc.UserServiceBlockingStub userServiceBlockingStub;

    public UserResponse getUserResponseDto(long userId) {
        return userServiceBlockingStub.getUserById(
                UserRequest.newBuilder().setUserId(userId).build());
    }
}