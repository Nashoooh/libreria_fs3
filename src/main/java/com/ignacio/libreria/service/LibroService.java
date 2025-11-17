package com.ignacio.libreria.service;

import com.ignacio.libreria.model.Libro;
import com.ignacio.libreria.repository.LibroRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LibroService {
    
    private final LibroRepository libroRepository;

    public LibroService(LibroRepository libroRepository) {
        this.libroRepository = libroRepository;
    }

    // Obtener todos los libros
    public List<Libro> obtenerTodosLosLibros() {
        return libroRepository.findAll();
    }

    // Obtener libro por ID
    public Optional<Libro> obtenerLibroPorId(Long id) {
        return libroRepository.findById(id);
    }

    // Crear nuevo libro
    public Libro crearLibro(Libro libro) {
        // Aquí puedes agregar validaciones de negocio
        validarLibro(libro);
        return libroRepository.save(libro);
    }

    // Actualizar libro existente
    public Optional<Libro> actualizarLibro(Long id, Libro libroDetalles) {
        return libroRepository.findById(id)
                .map(libro -> {
                    validarLibro(libroDetalles);
                    libro.setTitulo(libroDetalles.getTitulo());
                    libro.setAutor(libroDetalles.getAutor());
                    libro.setAnioPublicacion(libroDetalles.getAnioPublicacion());
                    libro.setGenero(libroDetalles.getGenero());
                    return libroRepository.save(libro);
                });
    }

    // Eliminar libro por ID
    public boolean eliminarLibro(Long id) {
        if (libroRepository.existsById(id)) {
            libroRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // Validaciones de negocio (ejemplo de lógica en la capa de servicio)
    private void validarLibro(Libro libro) {
        if (libro.getTitulo() == null || libro.getTitulo().trim().isEmpty()) {
            throw new IllegalArgumentException("El título del libro no puede estar vacío");
        }
        if (libro.getAutor() == null || libro.getAutor().trim().isEmpty()) {
            throw new IllegalArgumentException("El autor del libro no puede estar vacío");
        }
        if (libro.getAnioPublicacion() == null || libro.getAnioPublicacion() < 1000 || libro.getAnioPublicacion() > 2100) {
            throw new IllegalArgumentException("El año de publicación debe ser válido");
        }
        if (libro.getGenero() == null || libro.getGenero().trim().isEmpty()) {
            throw new IllegalArgumentException("El género del libro no puede estar vacío");
        }
    }
}
