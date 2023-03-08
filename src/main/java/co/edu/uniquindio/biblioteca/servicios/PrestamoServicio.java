package co.edu.uniquindio.biblioteca.servicios;

import co.edu.uniquindio.biblioteca.controller.excepciones.ManejoExcepciones;
import co.edu.uniquindio.biblioteca.dto.*;
import co.edu.uniquindio.biblioteca.entity.Cliente;
import co.edu.uniquindio.biblioteca.entity.Libro;
import co.edu.uniquindio.biblioteca.entity.Prestamo;
import co.edu.uniquindio.biblioteca.repo.ClienteRepo;
import co.edu.uniquindio.biblioteca.repo.LibroRepo;
import co.edu.uniquindio.biblioteca.repo.PrestamoRepo;
import co.edu.uniquindio.biblioteca.servicios.excepciones.ClienteNoEncontradoException;
import co.edu.uniquindio.biblioteca.servicios.excepciones.LibroNoEncontradoException;
import co.edu.uniquindio.biblioteca.servicios.excepciones.PrestamoNoEncontrado;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PrestamoServicio {

    private final PrestamoRepo prestamoRepo;
    private final ClienteRepo clienteRepo;
    private final LibroRepo libroRepo;

    public Prestamo save(PrestamoDTOPost prestamoDTO){

        Prestamo prestamoDTOGet = convertir(prestamoDTO);
        prestamoDTOGet.setFechaPrestamo(LocalDateTime.now());
        return prestamoRepo.save(prestamoDTOGet);

    }
    public List<PrestamoDTOGet> findAllByCliente(long clienteId) {
        Cliente cliente = obtenerCliente(clienteId);
        return convertirList(obtenerPrestamoPorCliente(cliente));
    }

    private List<Prestamo> obtenerPrestamoPorCliente(Cliente cliente) {
        return prestamoRepo.findAllByCliente(cliente)
                .orElseThrow(() -> new PrestamoNoEncontrado("El prestamo no existe"));
    }

    private List<PrestamoDTOGet> convertirList(List<Prestamo> prestamos) {
        return prestamos.stream()
                .filter(p -> p.getEstado())
                .map(p -> convertir(p))
                .collect(Collectors.toList());
    }

    public List<PrestamoDTOGet> findAll(){
        return prestamoRepo.findAll()
                .stream()
                .map(c -> convertir(c))
                .collect(Collectors.toList());
    }

    //TODO Completar
    public List<PrestamoDTOPost> findByCodigoCliente(long codigoCliente){

        return null;
    }

    //TODO usar DTO y la exepción propia de préstamo
    public Prestamo findById(long codigoPrestamo){
        return prestamoRepo.findById(codigoPrestamo).orElseThrow(()-> new RuntimeException("No existe"));
    }

    private ClienteGet convertirCliente(Cliente cliente) {
        return new ClienteGet(cliente.getCodigo(),cliente.getNombre(),cliente.getEmail(),cliente.getTelefono(),cliente.isEstado());
    }

/*
    private PrestamoDTOGet convertir(Prestamo prestamo) {
        return PrestamoDTOGet.builder()
                .cliente(prestamo.getCliente())
                .fechaPrestamo(prestamo.getFechaPrestamo())
                .fechaDevolucion(prestamo.getFechaDevolucion())
                .isbnLibros(prestamo.getLibros())
                .build();
    }
*/
    private Cliente obtenerCliente(long idCodigo){
        return clienteRepo.findById(idCodigo).orElseThrow(() -> new ClienteNoEncontradoException("El cliente no existe"));
    }

    private List<Libro> obtenerLibros(List<String> isbnLibros) {
        List<Libro> libros = libroRepo.findAllById(isbnLibros);

        if (libros.size() != isbnLibros.size()) {
            String noEncontrados = isbnLibros
                    .stream()
                    .filter(isbn -> !libros.stream()
                            .map(Libro::getIsbn).toList()
                            .contains(isbn))
                    .map(Object::toString)
                    .collect(Collectors.joining(","));
            throw new LibroNoEncontradoException("Los libros " + noEncontrados + " no existen");
        }
        return libros;
        }

    private Prestamo convertir(PrestamoDTOPost prestamoDTO) {
        return Prestamo.builder()
                .cliente(obtenerCliente(prestamoDTO.clienteID()))
                .fechaDevolucion(prestamoDTO.fechaDevolucion())
                .libros(obtenerLibros(prestamoDTO.isbnLibros()))
                .estado(prestamoDTO.estado())
                .build();
    }

    private PrestamoDTOGet convertir(Prestamo prestamo) {
        return PrestamoDTOGet.builder()
                .cliente(prestamo.getCliente())
                .fechaDevolucion(prestamo.getFechaDevolucion())
                .isbnLibros(prestamo.getLibros())
                .estado(prestamo.getEstado())
                .build();
    }
//
//    private PrestamoDTOGet convertir(Prestamo prestamo) {
//        return PrestamoDTOGet.builder()
//                .cliente(prestamo.getCliente())
//                .fechaPrestamo(prestamo.getFechaPrestamo())
//                .fechaDevolucion(prestamo.getFechaDevolucion())
//                .isbnLibros(convertirLibro(prestamo.getLibros()))
//                .build();
//    }
//    public List<LibroDTO> convertirLibros(List<Libro> libros) {
//        return libros.stream()
//                .map(item -> convertirLibro(item))
//                .collect(Collectors.toList());
//    }
//
//    private LibroDTO convertirLibro(Libro libro) {
//        return LibroDTO.builder()
//                .isbn(libro.getIsbn())
//                .nombre(libro.getNombre())
//                .genero(libro.getGenero())
//                .unidades(libro.getUnidades())
//                .autores(libro.getNombre())
//                .fechaPublicacion(libro.getFechaPublicacion())
//                .build();
//    }
}
