package org.example.practicaweb.Controller;

import org.example.practicaweb.Model.Catedratico;
import org.example.practicaweb.Service.CatedraticoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")

@RestController
@RequestMapping("/api/catedraticos")
public class ControllerCatedratico {
    private final CatedraticoService catedraticoService;

    public ControllerCatedratico(CatedraticoService catedraticoService) {
        this.catedraticoService = catedraticoService;
    }

    @GetMapping()
    public List<Catedratico> obtenerListaCatedratico(){
        return catedraticoService.getAllCatedraticos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Catedratico> obtenerCatedraticoId(@PathVariable Long id){
        Catedratico existe = catedraticoService.getCatedraticoById(id);
        if(existe == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(existe);
    }

    @PostMapping
    public ResponseEntity<Catedratico> crearCatedratico(@RequestBody Catedratico nuevoCatedratico){
        Catedratico nuevo = catedraticoService.createCatedratico(nuevoCatedratico);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
    }


}
