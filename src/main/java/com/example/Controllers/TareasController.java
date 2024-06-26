package com.example.Controllers;

import com.example.Models.*;
import com.example.Service.ITareaService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
                    .Message("Tarea creada correctamente.")
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
    @GetMapping(value="/obtener-tareas")
    public ResponseEntity<HttpResponseHandler> ObtenerTareas(){
        try{
            var tareas = iTareaService.ObtenerTareas();
            HttpResponseHandler response = HttpResponseHandler.builder()
                    .Success(true)
                    .Error("")
                    .Message("Tareas obtenidas correctamente.")
                    .Data(tareas)
                    .build();
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception ex){
            HttpResponseHandler response = HttpResponseHandler.builder()
                    .Success(false)
                    .Error(ex.getMessage())
                    .Message("Ocurrio un error al recuperar las tareas.")
                    .Data(null)
                    .build();
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping(value="/obtener-tarea-por-id")
    public ResponseEntity<HttpResponseHandler> ObtenerTareaPorId(@RequestBody ObtenerTareaPorIdDTO request){
        try{
            var tareas = iTareaService.ObtenerTareaPorId(request);
            if(tareas.isEmpty()){
                HttpResponseHandler response = HttpResponseHandler.builder()
                        .Success(false)
                        .Error("")
                        .Message("La tarea no fue encontrada.")
                        .Data(tareas)
                        .build();
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }else{
                HttpResponseHandler response = HttpResponseHandler.builder()
                        .Success(true)
                        .Error("")
                        .Message("Tarea obtenida satisfactoriamente.")
                        .Data(tareas)
                        .build();
                return new ResponseEntity<>(response, HttpStatus.OK);
            }

        }catch (Exception ex){
            HttpResponseHandler response = HttpResponseHandler.builder()
                    .Success(false)
                    .Error(ex.getMessage())
                    .Message("Ocurrio un error al obtener la tarea.")
                    .Data(null)
                    .build();
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }
    @PutMapping(value="/editar-tarea")
    public ResponseEntity<HttpResponseHandler> EditarTarea(@RequestBody EditarTareaDTO request){
        try{
            ObtenerTareaPorIdDTO obtenerTarea = new ObtenerTareaPorIdDTO();
            obtenerTarea.setIdTarea(request.IdTarea);
            var tarea = iTareaService.ObtenerTareaPorId(obtenerTarea);
            if(tarea.isEmpty()){
                HttpResponseHandler response = HttpResponseHandler.builder()
                        .Success(false)
                        .Error("")
                        .Message("La tarea no fue encontrada.")
                        .Data(null)
                        .build();
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }else {
                iTareaService.EditarTarea(request);
                HttpResponseHandler response = HttpResponseHandler.builder()
                        .Success(true)
                        .Error("")
                        .Message("Tarea editada correctamente")
                        .Data(null)
                        .build();
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
        }catch (Exception ex){
            HttpResponseHandler response = HttpResponseHandler.builder()
                    .Success(false)
                    .Error(ex.getMessage())
                    .Message("Ocurrio un error al editar la tarea.")
                    .Data(null)
                    .build();
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }
    @DeleteMapping(value="/eliminar-tarea")
    public ResponseEntity<HttpResponseHandler> EliminarTarea(@RequestBody EliminarTareaDTO request){
        try{
            ObtenerTareaPorIdDTO obtenerTarea = new ObtenerTareaPorIdDTO();
            obtenerTarea.setIdTarea(request.IdTarea);
            var tarea = iTareaService.ObtenerTareaPorId(obtenerTarea);
            if(tarea.isEmpty()){
                HttpResponseHandler response = HttpResponseHandler.builder()
                        .Success(false)
                        .Error("")
                        .Message("La tarea no fue encontrada.")
                        .Data(null)
                        .build();
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }else {
                iTareaService.EliminarTarea(request);
                HttpResponseHandler response = HttpResponseHandler.builder()
                        .Success(true)
                        .Error("")
                        .Message("Tarea eliminada correctamente")
                        .Data(null)
                        .build();
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
        }catch (Exception ex){
            HttpResponseHandler response = HttpResponseHandler.builder()
                    .Success(false)
                    .Error(ex.getMessage())
                    .Message("Ocurrio un error al eliminar la tarea.")
                    .Data(null)
                    .build();
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

}
