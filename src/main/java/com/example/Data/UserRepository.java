package com.example.Data;

import com.example.Models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public class UserRepository implements  IUserRepository{
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Optional<User> FindByUserName(String UserName) {
        String sql = "pa_s_ObtenerUsuarioPorUsername ?";
        List<User> users = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(User.class), UserName);
        return users.isEmpty() ? Optional.empty() : Optional.of(users.get(0));
    }

    @Override
    public void RegisterUser(User Request) {
        String sql = "EXEC pa_i_RegistrarUsuario ?, ?, ?";
        jdbcTemplate.update(sql, new Object[]{Request.UserName, Request.Password, Request.role.toString()});
    }
}
