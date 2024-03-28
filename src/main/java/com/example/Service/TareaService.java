package com.example.Service;

import com.example.Data.ITareaRepository;
import com.example.Models.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TareaService implements ITareaService {
    @Autowired
    private ITareaRepository iTareaRepository;

    @Override
    public void CrearTarea(CrearTareaDTO request) {
        try{
            iTareaRepository.CrearTarea(request);
        }catch (Exception ex){
            throw ex;
        }
    }

    @Override
    public List<Tarea> ObtenerTareas() {
        try{
            var tareas = iTareaRepository.ObtenerTareas();
            return tareas;
        }catch (Exception ex){
            throw ex;
        }
    }
    @Override
    public List<Tarea> ObtenerTareaPorId(ObtenerTareaPorIdDTO request) {
        try{
            var tarea = iTareaRepository.ObtenerTareaPorId(request);
            return tarea;
        }catch (Exception ex){
            throw ex;
        }
    }
    @Override
    public void EditarTarea(EditarTareaDTO request) {
        try{
            iTareaRepository.EditarTarea(request);
        }catch (Exception ex){
            throw ex;
        }
    }


}
