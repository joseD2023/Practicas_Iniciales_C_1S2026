package org.example.practicaweb.Controller;


import org.example.practicaweb.Model.Curso;
import org.example.practicaweb.Service.CursosService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")

@RestController
@RequestMapping("/api/cursos")
public class ControllerCurso {

    private final CursosService cursosService;

    public ControllerCurso(CursosService cursosService) {
        this.cursosService = cursosService;
    }


    @GetMapping()
    public List<Curso> listarCursos(){
        return cursosService.getAllCursos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Curso> obtenerCursoById(@PathVariable Long id){
        Curso curso = cursosService.getCursoById(id);
        if(curso == null){
            return ResponseEntity.notFound().build();
        }
        return  ResponseEntity.ok(curso);
    }

    @PostMapping()
    public ResponseEntity<Curso> createCurso(@RequestBody Curso nuevoCurso){
        Curso c = cursosService.crearNuevoCurso(nuevoCurso);
        return ResponseEntity.status(HttpStatus.CREATED).body(c);
    }
}
