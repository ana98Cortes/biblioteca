package co.edu.uniquindio.biblioteca.dto;

import co.edu.uniquindio.biblioteca.entity.Categoria;
import lombok.Builder;

import java.time.LocalDate;
import java.util.List;
@Builder
public record ProductoDTO(String id, String nombre, int unidades, String descripcion, double precio, Categoria categoria, String imagen, int calificacion){

}
