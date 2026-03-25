package org.example.practicaweb.Service;

import org.example.practicaweb.Model.CursoAprobado;
import org.example.practicaweb.Model.Usuario;
import org.example.practicaweb.Repository.CursoAprobadoRepository;
import org.example.practicaweb.Repository.UsuarioRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CursoAprobadoService {
    private final CursoAprobadoRepository cursoAprobadoRepository;
    private final UsuarioRepository usuarioRepository;

    public CursoAprobadoService(CursoAprobadoRepository cursoAprobadoRepository, UsuarioRepository usuarioRepository) {
        this.cursoAprobadoRepository = cursoAprobadoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public CursoAprobado createCursoAprobado(CursoAprobado curso){
        /*como tal necesito digamos crear cursos solo con el usuario que a hecho el login no
        * con otra persona la otra persona solo podra ver los cursos o perfiles que el usuario
        * ha aprobado
        * Entonces necesitamos nuestro usuario logueado*/

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String registroAcademico = authentication.getName();
        System.out.println("AUTH NAME: " + registroAcademico);
        Usuario usuario = usuarioRepository.findByRegistroAcademico(Integer.parseInt(registroAcademico));
        curso.setUsuario(usuario);
        System.out.println("USUARIO: " + usuario);
        int aux = curso.getCreditos() + usuario.getTotalCreditos();
        usuario.setTotalCreditos(aux); //aquí incrementamos los creditos con los cursos agregados
        return cursoAprobadoRepository.save(curso);
    }

    public List<CursoAprobado> getAllCursosAprobados(){
        return cursoAprobadoRepository.findAll();
    }


    //necesito un buscador de cursos un enpoint para buscar publicaciones de cursos aprobados por ID

    public List<CursoAprobado> cursosAprobadosId(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String registroAcademico = authentication.getName(); //logueado
        Usuario usuario = usuarioRepository.findByRegistroAcademico(Integer.parseInt(registroAcademico));
        return cursoAprobadoRepository.findByUsuario(usuario);
    }


}
