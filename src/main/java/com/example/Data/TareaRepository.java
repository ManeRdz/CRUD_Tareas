package com.example.Data;

import com.example.Models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class TareaRepository implements ITareaRepository{
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Override
    public void CrearTarea(CrearTareaDTO request) {
        String sql = "EXEC pa_i_CrearTarea ?, ?";
        jdbcTemplate.update(sql, new Object[]{request.Descripcion, request.IdEstatus});
    }

    @Override
    public List<Tarea> ObtenerTareas() {
        String sql = "EXEC pa_s_ObtenerTareas";
        var tareas = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Tarea.class));
        return tareas;
    }
    @Override
    public List<Tarea> ObtenerTareaPorId(ObtenerTareaPorIdDTO request) {
        String sql = "EXEC pa_s_ObtenerTareaPorId ?";
        List<Tarea> tareas = jdbcTemplate.query(sql,new Object[]{request.IdTarea}, BeanPropertyRowMapper.newInstance(Tarea.class));
        return tareas;
    }


}
