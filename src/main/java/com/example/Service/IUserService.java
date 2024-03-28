package com.example.Service;

import com.example.Models.AuthResponseDTO;
import com.example.Models.LoginRequestDTO;
import com.example.Models.RegisterRequestDTO;
import com.example.Models.User;

import java.util.Optional;

public interface IUserService {
    public Optional<User> FindByUserName(String UserName);

    public AuthResponseDTO RegisterUser(RegisterRequestDTO Request);

    public AuthResponseDTO LoginUser(LoginRequestDTO Request);
}
