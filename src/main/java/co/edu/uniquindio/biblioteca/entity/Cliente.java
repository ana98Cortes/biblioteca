package co.edu.uniquindio.biblioteca.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.util.List;

@Entity
@NoArgsConstructor
@Data
public class Cliente implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long codigo;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String telefono;

    private String password;

    @OneToMany(mappedBy = "cliente")
    @JsonIgnore
    private List<Prestamo> prestamos;

    @Column(nullable = false)
    private boolean estado;

    @Builder
    public Cliente(String nombre, String email, String telefono, String password, boolean estado) {
        this.nombre = nombre;
        this.email = email;
        this.telefono = telefono;
        this.password = password;
        this.estado = estado;
    }
}
