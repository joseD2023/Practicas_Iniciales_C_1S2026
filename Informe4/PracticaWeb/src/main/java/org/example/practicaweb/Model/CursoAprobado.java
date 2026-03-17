package org.example.practicaweb.Model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class CursoAprobado {

    /*aqui tenemos un problema porque la relacion es entre el id y no directamente con el objeto
    * usuario entonces tenemos que delimitar esa conexion*/
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nombreCursos;

    private int creditos;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    private LocalDate fechaAprobacion;

    public CursoAprobado(String nombreCursos, int creditos, Usuario usuario, LocalDate fechaAprobacion){
        this.nombreCursos = nombreCursos;
        this.creditos = creditos;
        this.usuario = usuario;
        this.fechaAprobacion = fechaAprobacion;
    }



}
