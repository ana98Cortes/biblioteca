package co.edu.uniquindio.biblioteca.controller;

import co.edu.uniquindio.biblioteca.dto.*;
import co.edu.uniquindio.biblioteca.entity.Prestamo;
import co.edu.uniquindio.biblioteca.servicios.PrestamoServicio;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/prestamo")
@AllArgsConstructor
public class PrestamoController {

    private final PrestamoServicio prestamoServicio;
    @PostMapping
    public ResponseEntity<Respuesta<Prestamo>> save(@RequestBody PrestamoDTOPost prestamo){
        return ResponseEntity.status(HttpStatus.CREATED).body( new Respuesta<>("Prestamo creado correctamente", prestamoServicio.save(prestamo)) );
    }

    @GetMapping
    public ResponseEntity<Respuesta<List<PrestamoDTOGet>>> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body( new Respuesta<>("", prestamoServicio.findAll()));
    }

    @GetMapping("/listaPrestamoIdCliente")
    public ResponseEntity<Respuesta<Optional<List<Prestamo>>>> findAllidCliente(@RequestParam Long idCliente){
        return ResponseEntity.status(HttpStatus.OK).body( new Respuesta<>("", prestamoServicio.listaPrestamoPorIdCliente(idCliente)));
    }

    @GetMapping("/listaPrestamoFecha/{fechaPrestamo}")
    public ResponseEntity<Respuesta<Optional<List<Prestamo>>>> findByFechaPrestamo(@PathVariable String fechaPrestamo) {

        LocalDate dateTime = LocalDate.parse(fechaPrestamo);
        return ResponseEntity.status(HttpStatus.OK).body(new Respuesta<>("", prestamoServicio.findByFechaPrestamo(dateTime)));
    }

    @GetMapping("/listaPrestamoIsbn/{isbn}")
    public ResponseEntity<Respuesta<Optional<List<Prestamo>>>> findPrestamoByIsbn(@PathVariable String isbn){
        return ResponseEntity.status(HttpStatus.OK).body( new Respuesta<>("", prestamoServicio.findPrestamoByIsbn(isbn)));
    }

//    @GetMapping("/libro/{isbn}/contar")
//    public ResponseEntity<Respuesta<Optional<List<Prestamo>>>> findById(@PathVariable String isbn) {
//        return ResponseEntity.status(HttpStatus.OK).body( new Respuesta<>("OK", prestamoServicio.lendingCount(isbn)));
//    }
}