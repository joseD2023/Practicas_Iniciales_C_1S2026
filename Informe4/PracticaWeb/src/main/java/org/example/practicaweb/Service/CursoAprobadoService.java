package org.example.practicaweb.Service;

import org.example.practicaweb.Model.CursoAprobado;
import org.example.practicaweb.Repository.CursoAprobadoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CursoAprobadoService {
    private final CursoAprobadoRepository cursoAprobadoRepository;

    public CursoAprobadoService(CursoAprobadoRepository cursoAprobadoRepository) {
        this.cursoAprobadoRepository = cursoAprobadoRepository;
    }

    public CursoAprobado createCursoAprobado(CursoAprobado curso){
        return cursoAprobadoRepository.save(curso);
    }

    public List<CursoAprobado> getAllCursosAprobados(){
        return cursoAprobadoRepository.findAll();
    }
}
