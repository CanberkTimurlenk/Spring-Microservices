package com.microservices.mobilemarket.auth.userdetail;

import com.microservices.mobilemarket.auth.grpc.UserGrpcClient;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailService implements UserDetailsService {

    private final UserGrpcClient userGrpcClient;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

//        get user info from the user service with gRPC
        var user = userGrpcClient.findUserByEmail(email);

        return User.builder()
                .username(user.getEmail())
                .password(user.getPassword())
                .roles(user.getRole())
                .build();
    }
}
