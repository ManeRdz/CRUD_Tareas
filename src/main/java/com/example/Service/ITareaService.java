package com.example.Service;

import com.example.Models.*;

import java.util.List;

public interface ITareaService {
    public void CrearTarea(CrearTareaDTO request);
    public List<Tarea> ObtenerTareas();


}
