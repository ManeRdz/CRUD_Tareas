package com.example.Models;

import lombok.Data;
import org.springframework.data.relational.core.mapping.Column;

@Data
public class Tarea {
    private int IdTarea;
    private String DescripcionTarea;
    private String Descripcion_Estatus;
    private int IdEstatus;
}
