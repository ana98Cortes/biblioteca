package co.edu.uniquindio.biblioteca.controller;

import co.edu.uniquindio.biblioteca.dto.*;
import co.edu.uniquindio.biblioteca.entity.Prestamo;
import co.edu.uniquindio.biblioteca.servicios.PrestamoServicio;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

}
