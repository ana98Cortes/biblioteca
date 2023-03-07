package co.edu.uniquindio.biblioteca.dto;

import co.edu.uniquindio.biblioteca.entity.Cliente;
import co.edu.uniquindio.biblioteca.entity.Libro;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

@Builder
public record PrestamoDTOGet(Cliente cliente, LocalDateTime fechaDevolucion, LocalDateTime fechaPrestamo, long IdCliente, List<String> isbnLibros) {
}
