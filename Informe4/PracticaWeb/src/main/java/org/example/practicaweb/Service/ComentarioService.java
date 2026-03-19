package org.example.practicaweb.Service;


import org.example.practicaweb.Model.Comentario;
import org.example.practicaweb.Model.Curso;
import org.example.practicaweb.Model.Usuario;
import org.example.practicaweb.Repository.ComentarioRepository;
import org.example.practicaweb.Repository.CursoRepository;
import org.example.practicaweb.Repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.Authentication;

import java.util.List;

@Service
public class ComentarioService {


    private final ComentarioRepository comentarioRepository;
    private final UsuarioRepository usuarioRepository;

    public ComentarioService(ComentarioRepository comentarioRepository,
                             UsuarioRepository usuarioRepository) {

        this.comentarioRepository = comentarioRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public List<Comentario> getAllComentarios(){
        return comentarioRepository.findAll();
    }


    public Comentario getComentarioById(Long id){
        return comentarioRepository.findById(id).orElse(null);
    }


    public Comentario createComentario(Comentario comentario){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String registroAcademmico = authentication.getName();
        Usuario usuario = usuarioRepository.findByRegistroAcademico(Integer.parseInt(registroAcademmico));
        comentario.setUsuario(usuario);
        return comentarioRepository.save(comentario);
    }

    public boolean deleteComentarios(long id){
        Comentario existeComentario = comentarioRepository.findById(id).orElse(null);
        if(existeComentario == null){
            return false;
        }
        return true;
    }



}
