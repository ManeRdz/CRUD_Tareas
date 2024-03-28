package com.example.Models;

import lombok.Data;

@Data
public class RegisterRequestDTO {
    public String UserName;
    public String Password;
    public Role role;
}
