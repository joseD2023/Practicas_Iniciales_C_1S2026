package org.example.practicaweb.Service;

import org.example.practicaweb.Model.Catedratico;
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
    private final ComentarioService comentarioService;

    public PublicacionesService(PublicacionRepository publicacionRepository, ComentarioRepository comentarioRepository, ComentarioService comentarioService) {
        this.publicacionRepository = publicacionRepository;
        this.comentarioRepository = comentarioRepository;
        this.comentarioService = comentarioService;
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



    /*Para eliminar lo que son las publicaciones*/
    public boolean deletePublicacion(Long id){
        Publicacion existeParaEliminar = publicacionRepository.findById(id).orElse(null);
        if(existeParaEliminar != null){
            publicacionRepository.deleteById(id);
            return true;
        }
        return false;
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



    /*vamos a utilizar esto para guardar los comentarios*/
    public Comentario crearComentario(Comentario nuevoComentario, Publicacion publicacion){
        nuevoComentario.setPublicacion(publicacion); //aqui le asignamos la relación con la publicacion y el comentario
        return comentarioService.createComentario(nuevoComentario);
    }


    /*Ahora vamos a emplear los que son filtros desde el backend para poder observar los comentarios o usuarios
    * podemos filtrar por: Curso, Catedrático, Nombre de Curso, Nombre catedratico*/

    public List<Publicacion> filtrar(String curso, String catedratico, Long idCurso, Long idCatedratico){
        /*Entonces vamos a trabajar directamente en el filtrado de lo que es curso o catedrático por ID*/
        List<Publicacion> publicacionesFiltro = new ArrayList<>();

        if(curso == null && catedratico == null && idCurso == null && idCatedratico == null){
            return publicacionRepository.findAll();
        }

        System.out.println("Si lleva Datos el Enpoint");

        for(Publicacion p : publicacionRepository.findAll()){
            /*aquí vamos a hacer el filtro total */
            boolean cumple = true;

            if(curso != null ){
                if(p.getCurso() == null || !p.getCurso().getNombre().equalsIgnoreCase(curso.trim())){
                    cumple = false;
                }}

            if(catedratico != null){
                if(p.getCatedratico() == null || !p.getCatedratico().getNombre().equalsIgnoreCase(catedratico.trim())){
                    cumple = false;
                }}

            if(idCatedratico != null){
                if (p.getCatedratico() == null || !p.getCatedratico().getId().equals(idCatedratico) ){
                    cumple = false;
                }}

            if(idCurso != null){
                if (p.getCurso() == null || !p.getCurso().getId().equals(idCurso)){
                    cumple = false;
                }}

            if(cumple){
                publicacionesFiltro.add(p);
            }
        }

        return publicacionesFiltro;

    }

























}
