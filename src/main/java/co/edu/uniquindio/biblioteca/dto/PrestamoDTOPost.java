package co.edu.uniquindio.biblioteca.dto;

import java.time.LocalDateTime;
import java.util.List;

public record PrestamoDTOPost(long clienteID, List<String> isbnLibros, LocalDateTime fechaDevolucion) {
}
