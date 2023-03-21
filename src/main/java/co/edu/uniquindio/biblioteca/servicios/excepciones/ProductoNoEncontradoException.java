package co.edu.uniquindio.biblioteca.servicios.excepciones;

public class ProductoNoEncontradoException extends RuntimeException {

    public ProductoNoEncontradoException(String message) {
        super(message);
    }
}
