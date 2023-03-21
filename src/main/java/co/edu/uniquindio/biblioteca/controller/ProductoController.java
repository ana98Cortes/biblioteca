package co.edu.uniquindio.biblioteca.controller;

import co.edu.uniquindio.biblioteca.dto.ProductoDTO;
import co.edu.uniquindio.biblioteca.dto.Respuesta;
import co.edu.uniquindio.biblioteca.entity.Producto;
import co.edu.uniquindio.biblioteca.servicios.ProductoServicio;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/producto")
@AllArgsConstructor
public class ProductoController {

    private final ProductoServicio productoServicio;

    @PostMapping
    public ResponseEntity<Respuesta<Producto>> save(@RequestBody ProductoDTO productoDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body( new Respuesta<>("Producto creado correctamente", productoServicio.save(productoDTO)) );
    }

    @GetMapping
    public ResponseEntity<Respuesta<List<Producto>>> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body( new Respuesta<>("", productoServicio.findAll()) );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Respuesta<Producto>> findAll(@PathVariable String id){
        return ResponseEntity.status(HttpStatus.OK).body( new Respuesta<>("", productoServicio.findById(id)) );
    }

    @PutMapping("/{id}")
    public ResponseEntity<Respuesta<Producto>> actualizar(@RequestBody ProductoDTO productoDTO){
        return ResponseEntity.status(HttpStatus.OK).body( new Respuesta<>("Producto creado correctamente", productoServicio.save(productoDTO)) );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Respuesta<String>> eliminarProducto(@PathVariable String id){
        return  ResponseEntity.status(HttpStatus.OK).body(new Respuesta<>("Producto eliminado correctamente",productoServicio.delete(id)));
    }
}
