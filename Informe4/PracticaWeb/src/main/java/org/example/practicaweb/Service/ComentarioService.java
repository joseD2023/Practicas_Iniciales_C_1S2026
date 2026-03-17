package org.example.practicaweb.Service;


import org.example.practicaweb.Model.Comentario;
import org.example.practicaweb.Model.Curso;
import org.example.practicaweb.Repository.ComentarioRepository;
import org.example.practicaweb.Repository.CursoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComentarioService {


    private final ComentarioRepository comentarioRepository;

    public ComentarioService(ComentarioRepository comentarioRepository) {
        this.comentarioRepository = comentarioRepository;
    }

    public List<Comentario> getAllComentarios(){
        return comentarioRepository.findAll();
    }


    public Comentario getComentarioById(Long id){
        return comentarioRepository.findById(id).orElse(null);
    }


    public Comentario createComentario(Comentario comentario){
        return comentarioRepository.save(comentario);
    }



}
