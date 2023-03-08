package co.edu.uniquindio.biblioteca.servicios.excepciones;

public class PrestamoNoEncontrado extends RuntimeException {

    public PrestamoNoEncontrado(String message) {
        super(message);
    }
}

