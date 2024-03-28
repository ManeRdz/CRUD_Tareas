package com.example.Controllers;

import com.example.Models.CrearTareaDTO;
import com.example.Models.HttpResponseHandler;
import com.example.Service.ITareaService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tareas")
@RequiredArgsConstructor
public class TareasController {
    @Autowired
    private ITareaService iTareaService;
    @PostMapping(value="/crear")
    public ResponseEntity<HttpResponseHandler> CrearTarea(@RequestBody CrearTareaDTO request){
        try{
            iTareaService.CrearTarea(request);
            HttpResponseHandler response = HttpResponseHandler.builder()
                    .Success(true)
                    .Error("")
                    .Message("Tarea creada correctamente")
                    .Data(null)
                    .build();
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception ex){
            HttpResponseHandler response = HttpResponseHandler.builder()
                    .Success(false)
                    .Error(ex.getMessage())
                    .Message("Ocurrio un error al crear la tarea.")
                    .Data(null)
                    .build();
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }
}
