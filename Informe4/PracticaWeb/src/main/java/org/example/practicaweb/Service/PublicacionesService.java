package org.example.practicaweb.Service;

import org.example.practicaweb.Model.Comentario;
import org.example.practicaweb.Model.Curso;
import org.example.practicaweb.Model.Publicacion;
import org.example.practicaweb.Repository.ComentarioRepository;
import org.example.practicaweb.Repository.PublicacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;


@Service
public class PublicacionesService {

    private final PublicacionRepository publicacionRepository;
    private final ComentarioRepository comentarioRepository;

    public PublicacionesService(PublicacionRepository publicacionRepository, ComentarioRepository comentarioRepository) {
        this.publicacionRepository = publicacionRepository;
        this.comentarioRepository = comentarioRepository;
    }

    /*Entonces lo que debemos hacer es crear publicaciones
    * Vamos a poder publicar y vamos poder ver las vistas de las publicaciones
    * que cada usuario ha hecho y que tenga que mostrar los catedratico, usuario y curso*/

    /*Necesitamos Crear y eliminar los que son las publicaciones como tal*/

    public List<Publicacion> obtenerTodasPublicacion(){
        return publicacionRepository.findAll();
    }

    public Publicacion createPublicacion(Publicacion publicacion){
        return publicacionRepository.save(publicacion);
    }

    public Publicacion getPublicacionById(Long id){
        return publicacionRepository.findById(id).orElse(null);
    }


    /*Necesitamos saber que comentarios tiene esa publicacion*/
    public List<Comentario> obtenerComentarioPublicacion(Long id){
        List<Comentario> guardarComentarios = new ArrayList<>();
        for(Comentario e : comentarioRepository.findAll()){
            if(id.equals(e.getPublicacion().getId())){
                guardarComentarios.add(e);
            }
        }
        return guardarComentarios;
    }

























}
