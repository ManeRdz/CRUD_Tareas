package com.example.Data;

import com.example.Models.User;

import java.util.Optional;

public interface IUserRepository {
    public Optional<User> FindByUserName(String UserName);

    public void RegisterUser(User Request);
}
