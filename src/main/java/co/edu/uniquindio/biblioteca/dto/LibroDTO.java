package co.edu.uniquindio.biblioteca.dto;

import co.edu.uniquindio.biblioteca.entity.Genero;
import lombok.Builder;

import java.time.LocalDate;
import java.util.List;
@Builder
public record LibroDTO(String isbn, String nombre, Genero genero, int unidades, LocalDate fechaPublicacion, List<Long> idAutores){

}
