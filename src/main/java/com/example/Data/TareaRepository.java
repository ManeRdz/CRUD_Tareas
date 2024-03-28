package com.example.Data;

import com.example.Models.CrearTareaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class TareaRepository implements ITareaRepository{
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Override
    public void CrearTarea(CrearTareaDTO request) {
        String sql = "EXEC pa_i_CrearTarea ?, ?";
        jdbcTemplate.update(sql, new Object[]{request.Descripcion, request.IdEstatus});
    }
}
