package co.edu.uniquindio.biblioteca.repo;

import co.edu.uniquindio.biblioteca.entity.Cliente;
import co.edu.uniquindio.biblioteca.entity.Prestamo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface PrestamoRepo extends JpaRepository<Prestamo, Long> {

    Optional<List<Prestamo>> findAllByCliente(Cliente cliente);

    @Query("SELECT p FROM Prestamo p WHERE p.fechaPrestamo BETWEEN  :fechaInicio and :fechaFin")
    Optional<List<Prestamo>> findAllByFecha(@Param("fechaInicio") LocalDateTime fechaInicio,
                                            @Param("fechaFin") LocalDateTime fechaFin);

    @Query("SELECT COUNT(p) FROM Prestamo p JOIN p.libros l WHERE l.isbn = :isbn")
    int countByIsbn(@Param("isbn") String isbn);

    @Query("Select p from Prestamo p inner join Cliente c on c.codigo = p.cliente.codigo where c.codigo = ?1")
    Optional<List<Prestamo>> listaPrestamoPorIdCliente(Long idCliente);

    //select * from prestamo p where p.fecha_prestamo LIKE '2023-03-08%';
    @Query("select p from Prestamo p where p.fechaPrestamo =?1 ")
    Optional<List<Prestamo>> findByFechaPrestamos(LocalDate fechaPrestamo);
}
