package org.example.practicaweb.Controller;


import org.example.practicaweb.Model.CursoAprobado;
import org.example.practicaweb.Service.CursoAprobadoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")

@RestController
@RequestMapping("/api/cursos-aprobados")
public class ControllerCursoAprobado {
    private final CursoAprobadoService cursoAprobadoService;

    public ControllerCursoAprobado(CursoAprobadoService cursoAprobadoService) {
        this.cursoAprobadoService = cursoAprobadoService;
    }

    @GetMapping("/perfil")
    public List<CursoAprobado> obtenerCursosAprobados(){
        return cursoAprobadoService.getAllCursosAprobados();
    }


    @PostMapping()
    public ResponseEntity<CursoAprobado> crearCursoAprobado(@RequestBody CursoAprobado cursoNuevo){
        CursoAprobado curso = cursoAprobadoService.createCursoAprobado(cursoNuevo);
        return ResponseEntity.ok(curso);
    }



}
