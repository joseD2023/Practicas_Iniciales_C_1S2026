package org.example.practicaweb.Controller;


import org.example.practicaweb.Model.Comentario;
import org.example.practicaweb.Model.Publicacion;
import org.example.practicaweb.Service.PublicacionesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
@RestController
@RequestMapping("/api/publicaciones")
public class ControllerPublicacion {

    private final PublicacionesService publicacionesService;

    public ControllerPublicacion(PublicacionesService publicacionesService) {
        this.publicacionesService = publicacionesService;
    }


    @GetMapping()
    public List<Publicacion> obtenerPublicaciones(){
        return publicacionesService.obtenerTodasPublicacion();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Publicacion> obtenerById(@PathVariable Long id){
        Publicacion encontrar = publicacionesService.getPublicacionById(id);
        if(encontrar==null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(encontrar);

    }

    @PostMapping()
    public ResponseEntity<Publicacion> crearPublicacion(@RequestBody Publicacion nuevaPublicacion){
        Publicacion publicacion = publicacionesService.createPublicacion(nuevaPublicacion);
        return ResponseEntity.status(HttpStatus.CREATED).body(publicacion);
    }

    @GetMapping("/{id}/comentarios")
    public List<Comentario> obtenerMisComentarios(@PathVariable Long id){
        return publicacionesService.obtenerComentarioPublicacion(id);
    }

}
