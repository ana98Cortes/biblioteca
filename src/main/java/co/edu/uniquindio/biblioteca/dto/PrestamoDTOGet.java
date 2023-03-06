package co.edu.uniquindio.biblioteca.dto;

import co.edu.uniquindio.biblioteca.entity.Libro;

import java.time.LocalDateTime;
import java.util.List;

public record PrestamoDTOGet(long codigo, LocalDateTime fechaDevolucion, LocalDateTime fechaPrestamo, long IdCliente, List<Libro> isbnLibros) {
}
