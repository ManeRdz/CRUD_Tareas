package com.example.Service;

import com.example.Data.IUserRepository;
import com.example.JWTHandler.JwtGenerator;
import com.example.Models.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
@RequiredArgsConstructor
public class UserService implements  IUserService{
    @Autowired
    private IUserRepository iUserRepository;
    @Autowired
    private JwtGenerator jwtGenerator;

    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    @Override
    public Optional<User> FindByUserName(String UserName) {
        Optional<User> user = Optional.of(new User());
        try{
            user = iUserRepository.FindByUserName(UserName);
            return user;
        }catch (Exception ex){
            throw ex;
        }
    }

    @Override
    public AuthResponseDTO RegisterUser(RegisterRequestDTO Request) {
            try{
                User user = User.builder()
                        .UserName(Request.getUserName())
                        .Password(passwordEncoder.encode(Request.getPassword()))
                        .role(Role.USER)
                        .build();
                iUserRepository.RegisterUser(user);
                AuthResponseDTO authRes = AuthResponseDTO.builder()
                        .Token(jwtGenerator.GetToken(user))
                        .build();
                return authRes;
            }catch (Exception ex){
                throw ex;
            }
    }

    @Override
    public AuthResponseDTO LoginUser(LoginRequestDTO Request) {
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken( Request.getUserName(),Request.getPassword() ));
            UserDetails user = iUserRepository.FindByUserName(Request.getUserName()).orElseThrow();
            String Token = jwtGenerator.GetToken(user);
            return AuthResponseDTO.builder().Token(Token).build();
        }catch(Exception ex){
            throw ex;
        }
    }
}
