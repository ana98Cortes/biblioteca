package co.edu.uniquindio.biblioteca.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@NoArgsConstructor
@Data
public class Prestamo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long codigo;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Cliente cliente;

    @Column(nullable = false)
    @JsonFormat(pattern="yyyy-MM-dd", timezone="America/Bogota")
    private LocalDate fechaPrestamo;

    @Column(nullable = false)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="America/Bogota")
    private LocalDateTime fechaDevolucion;

    @ManyToMany
    private List<Libro> libros;

    @Column(nullable = false)
    private Boolean estado;

    @Builder
    public Prestamo( Cliente cliente, LocalDateTime fechaDevolucion, List<Libro> libros, boolean estado) {
        this.cliente = cliente;
        this.fechaDevolucion = fechaDevolucion;
        this.libros = libros;
        this.estado = estado;
    }
}
