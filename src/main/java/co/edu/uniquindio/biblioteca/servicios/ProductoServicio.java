package co.edu.uniquindio.biblioteca.servicios;

import co.edu.uniquindio.biblioteca.dto.ProductoDTO;
import co.edu.uniquindio.biblioteca.entity.Producto;
import co.edu.uniquindio.biblioteca.repo.ProductoRepo;
import co.edu.uniquindio.biblioteca.servicios.excepciones.ProductoNoEncontradoException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductoServicio {

    private final ProductoRepo productoRepo;

    public Producto save(ProductoDTO producto){

        Optional<Producto> guardado = productoRepo.findById(producto.id());

        if(guardado.isPresent()){
            throw new RuntimeException("El producto con el codigo "+producto.id()+" ya existe");
        }

        return productoRepo.save( convertir(producto) );
    }

    public Producto findById(String id){
        return productoRepo.findById(id).orElseThrow(()-> new ProductoNoEncontradoException("El producto no existe"));
    }

    public List<Producto> findAll(){

        return productoRepo.findAll();
    }

    public Producto update(ProductoDTO producto){

        return productoRepo.save( convertir(producto) );

    }

    public String delete (String id){
        findById(id);
        productoRepo.deleteById(id);
        return "Producto eliminado";
    }

    private Producto convertir(ProductoDTO producto){

        Producto nuevo = Producto.builder()
                .id(producto.id())
                .nombre(producto.nombre())
                .precio(producto.precio())
                .unidades(producto.unidades())
                .descripcion(producto.descripcion())
                .precio(producto.precio())
                .categoria(producto.categoria())
                .imagen(producto.imagen())
                .calificacion(producto.calificacion())
                .build();

        return nuevo;
    }

}
