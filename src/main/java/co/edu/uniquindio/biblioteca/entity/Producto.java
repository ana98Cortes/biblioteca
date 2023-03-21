package co.edu.uniquindio.biblioteca.entity;

import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;

@Entity
@NoArgsConstructor
@Data
@Builder
@AllArgsConstructor
public class Producto implements Serializable {

    @Id
    private String id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private int unidades;

    @Column(nullable = false)
    private String descripcion;

    @Column(nullable = false)
    private double precio;

    @Column(nullable = false)
    private Categoria categoria;

    @Column(nullable = false)
    private String imagen;

    @Column(nullable = false)
    private int calificacion;

}
