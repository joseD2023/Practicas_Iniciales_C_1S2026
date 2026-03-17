package org.example.practicaweb.Controller;


import org.example.practicaweb.Model.Comentario;
import org.example.practicaweb.Service.ComentarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")

@RestController
@RequestMapping("/api/comentarios")
public class ControllerComentario {

    private final ComentarioService comentarioService;

    public ControllerComentario(ComentarioService comentarioService) {
        this.comentarioService = comentarioService;
    }

    @GetMapping()
    public List<Comentario> obtenerTodosComentarios(){
        return comentarioService.getAllComentarios();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Comentario> comentarioById(@PathVariable Long id){
        Comentario existe = comentarioService.getComentarioById(id);
        if(existe==null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(existe);
    }


    @PostMapping()
    public ResponseEntity<Comentario> crearComentario(@RequestBody Comentario nuevoComentario){
        Comentario nuevo = comentarioService.createComentario(nuevoComentario);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
    }



}
