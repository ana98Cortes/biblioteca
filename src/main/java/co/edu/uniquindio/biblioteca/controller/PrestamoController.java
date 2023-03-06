package co.edu.uniquindio.biblioteca.controller;

import co.edu.uniquindio.biblioteca.dto.ClienteGet;
import co.edu.uniquindio.biblioteca.dto.ClientePost;
import co.edu.uniquindio.biblioteca.dto.PrestamoDTOGet;
import co.edu.uniquindio.biblioteca.dto.Respuesta;
import co.edu.uniquindio.biblioteca.servicios.ClienteServicio;
import co.edu.uniquindio.biblioteca.servicios.PrestamoServicio;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/prestamo")
@AllArgsConstructor
public class PrestamoController {

    private final PrestamoServicio prestamoServicio;
    @PostMapping
    public ResponseEntity<Respuesta<PrestamoDTOGet>> save(@RequestBody PrestamoDTOGet prestamo){
        return ResponseEntity.status(HttpStatus.CREATED).body( new Respuesta<>("Cliente creado correctamente", prestamoServicio.save(prestamo)) );
    }
}
