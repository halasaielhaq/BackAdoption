package com.test.demo.Security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import com.test.demo.models.User;
import com.test.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

@Service

public class JwtService {

    @Value("${secret.key}")
    private String secretkey;

    @Autowired
    private UserRepository userRepository;


    public String generateToken( User user, SimpleGrantedAuthority authorities){
        Algorithm algorithm=Algorithm.HMAC256(secretkey.getBytes());
        return JWT.create()
                .withSubject(user.getEmail())
                .withExpiresAt(new Date(System.currentTimeMillis()+50*60*1000))
                .withClaim("roles",authorities.getAuthority())
                .sign(algorithm);
    }






}