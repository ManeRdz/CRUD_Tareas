package com.example.Service;

import com.example.Data.ITareaRepository;
import com.example.Models.CrearTareaDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
