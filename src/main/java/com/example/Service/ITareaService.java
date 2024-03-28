package com.example.Service;

import com.example.Models.*;

import java.util.List;

public interface ITareaService {
    public void CrearTarea(CrearTareaDTO request);
    public List<Tarea> ObtenerTareas();

    public List<Tarea> ObtenerTareaPorId(ObtenerTareaPorIdDTO request);

    public void EditarTarea(EditarTareaDTO request);

}
