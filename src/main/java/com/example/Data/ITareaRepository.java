package com.example.Data;

import com.example.Models.*;

import java.util.List;

public interface ITareaRepository {
    public void CrearTarea(CrearTareaDTO request);

    public List<Tarea> ObtenerTareas();

    public List<Tarea> ObtenerTareaPorId(ObtenerTareaPorIdDTO request);
    public void EditarTarea(EditarTareaDTO request);
    public void EliminarTarea(EliminarTareaDTO request);

}
