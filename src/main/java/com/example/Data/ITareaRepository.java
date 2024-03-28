package com.example.Data;

import com.example.Models.*;

import java.util.List;

public interface ITareaRepository {
    public void CrearTarea(CrearTareaDTO request);

    public List<Tarea> ObtenerTareas();


}
