package org.example.practicaweb.Controller;


import org.example.practicaweb.Model.Comentario;
import org.example.practicaweb.Model.Publicacion;
import org.example.practicaweb.Service.ComentarioService;
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
    private final ComentarioService comentarioService;

    public ControllerPublicacion(PublicacionesService publicacionesService, ComentarioService comentarioService) {
        this.publicacionesService = publicacionesService;
        this.comentarioService = comentarioService;
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

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarPublicacion(@PathVariable Long id){
        if(!publicacionesService.deletePublicacion(id)){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.notFound().build();
    }



    /*Vamos a implementar el crear comentarios para una publicacion entonces necesitamos la publicacion que
    * queremos comentar y eso*/

    @PostMapping("/{id}/comentarios")
    public ResponseEntity<Comentario> crearComentarioPublicacion(@PathVariable Long id, @RequestBody Comentario nuevoComentario){
        /*debemos obtener la publicacion donde queremos colocar ese comentario*/
        Publicacion encontrar = publicacionesService.getPublicacionById(id);
        if(encontrar == null){
            return ResponseEntity.notFound().build();
        }
        publicacionesService.crearComentario(nuevoComentario, encontrar);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoComentario);
    }


    /*Aqui solo vamos a trabajar con el filtrado de lo que el usuario requiera buscar*/

    @GetMapping("/detalle/filtrado")
    public List<Publicacion> filtrarPublicaciones(@RequestParam(required = false) String curso,
                                                  @RequestParam(required = false) String catedratico,
                                                  @RequestParam(required = false) Long idCurso,
                                                  @RequestParam(required = false) Long idCatedratico){
        return publicacionesService.filtrar(curso, catedratico, idCurso, idCatedratico);
    }







}
