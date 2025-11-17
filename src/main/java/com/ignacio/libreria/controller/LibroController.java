package com.ignacio.libreria.controller;

import com.ignacio.libreria.model.Libro;
import com.ignacio.libreria.service.LibroService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/libros")
public class LibroController {
    private final LibroService libroService;

    public LibroController(LibroService libroService) {
        this.libroService = libroService;
    }

    // GET: Obtener todos los libros
    @GetMapping
    public ResponseEntity<List<Libro>> getAllLibros() {
        List<Libro> libros = libroService.obtenerTodosLosLibros();
        return ResponseEntity.ok(libros);
    }

    // GET: Obtener libro por ID
    @GetMapping("/{id}")
    public ResponseEntity<Libro> getLibroById(@PathVariable Long id) {
        return libroService.obtenerLibroPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // POST: Agregar nuevo libro
    @PostMapping
    public ResponseEntity<Libro> createLibro(@RequestBody Libro libro) {
        try {
            Libro nuevoLibro = libroService.crearLibro(libro);
            return ResponseEntity.ok(nuevoLibro);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // PUT: Actualizar libro existente
    @PutMapping("/{id}")
    public ResponseEntity<Libro> updateLibro(@PathVariable Long id, @RequestBody Libro libroDetalles) {
        try {
            return libroService.actualizarLibro(id, libroDetalles)
                    .map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // DELETE: Eliminar libro por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLibro(@PathVariable Long id) {
        if (libroService.eliminarLibro(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
