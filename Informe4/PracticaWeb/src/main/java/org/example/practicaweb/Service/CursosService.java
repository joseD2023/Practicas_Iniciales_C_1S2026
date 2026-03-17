package org.example.practicaweb.Service;


import org.example.practicaweb.Model.Curso;
import org.example.practicaweb.Repository.CursoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CursosService {
    private final CursoRepository cursoRepository;

    public CursosService(CursoRepository cursoRepository) {
        this.cursoRepository = cursoRepository;
    }


    /*Vamos hacer las funcionalidades de Crear Cursos, ver la lista de cursos y buscar por id Curso*/

    public Curso crearNuevoCurso(Curso curso){
        return cursoRepository.save(curso);
    }
    public List<Curso> getAllCursos(){
        return cursoRepository.findAll();
    }

    public Curso getCursoById(Long id){
        return cursoRepository.findById(id).orElse(null);
    }



}
