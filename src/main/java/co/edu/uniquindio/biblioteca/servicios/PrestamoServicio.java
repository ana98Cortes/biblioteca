package co.edu.uniquindio.biblioteca.servicios;

import co.edu.uniquindio.biblioteca.dto.ClienteGet;
import co.edu.uniquindio.biblioteca.dto.ClientePost;
import co.edu.uniquindio.biblioteca.dto.PrestamoDTOGet;
import co.edu.uniquindio.biblioteca.dto.PrestamoDTOPost;
import co.edu.uniquindio.biblioteca.entity.Cliente;
import co.edu.uniquindio.biblioteca.entity.Libro;
import co.edu.uniquindio.biblioteca.entity.Prestamo;
import co.edu.uniquindio.biblioteca.repo.ClienteRepo;
import co.edu.uniquindio.biblioteca.repo.PrestamoRepo;
import co.edu.uniquindio.biblioteca.servicios.excepciones.ClienteNoEncontradoException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PrestamoServicio {

    private final PrestamoRepo prestamoRepo;
    private final ClienteRepo clienteRepo;

    public Prestamo save(PrestamoDTOPost prestamoDTO){

        long codigoCliente = prestamoDTO.clienteID();
        Optional<Cliente> consulta = clienteRepo.findById(codigoCliente);

        if(consulta.isEmpty()){
            throw new ClienteNoEncontradoException("No existe");
        }

        Prestamo prestamo = new Prestamo();
        prestamo.setCliente(consulta.get());
        prestamo.setFechaPrestamo(LocalDateTime.now());

        List<String> codigosLibros = prestamoDTO.isbnLibros();
        List<Libro> libros = new ArrayList<>();

        /*Optional<Libro> buscado libroRepo.findById(codigosLibros[0]);

        if(buscado.isEmpty()){
            throw new LibroNoExiste("El libro no existe");
        }

        libros.add( buscado );*/

        //TODO Completar la parte de los libros
        prestamo.setLibros(libros);
        prestamo.setFechaDevolucion(prestamoDTO.fechaDevolucion());

        return prestamoRepo.save(prestamo);
    }


    //TODO Completar
    public List<PrestamoDTOPost> findByCodigoCliente(long codigoCliente){

        return null;
    }

    //TODO usar DTO y la exepción propia de préstamo
    public Prestamo findById(long codigoPrestamo){
        return prestamoRepo.findById(codigoPrestamo).orElseThrow(()-> new RuntimeException("No existe"));
    }

    public PrestamoDTOGet save(PrestamoDTOPost prestamo){

        return convertir( prestamoRepo.save( convertir(prestamo) ) );
    }

    private PrestamoDTOGet convertir(Prestamo prestamo){
        return new PrestamoDTOGet(prestamo.getCodigo(),prestamo.getFechaDevolucion(),prestamo.getFechaPrestamo(),prestamo.getCliente().getCodigo(),prestamo.getLibros());
    }
    /*
    private Prestamo convertir(PrestamoDTOPost prestamo){
        return Prestamo.builder()
                .cliente(prestamo.clienteID())
                .fechaDevolucion(prestamo.fechaDevolucion())
                .libros(prestamo.isbnLibros()).build();
    }
    
     */
}
