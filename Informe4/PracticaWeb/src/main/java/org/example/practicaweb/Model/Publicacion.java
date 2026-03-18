package org.example.practicaweb.Model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Publicacion {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String titulo;

    @NotBlank(message = "Contenido no puede ser Vacio")
    private String contenido;



    @NotNull(message = "La fecha es obligatoria")
    private LocalDate fechaCreacion;


    //------------------------------------------------------------------------------------------------------
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;


    @ManyToOne
    @JoinColumn(name="curso_id")
    private Curso curso;

    @ManyToOne()
    @JoinColumn(name = "catedratico_id")
    private Catedratico catedratico;
    //------------------------------------------------------------------------------------------------------


    @OneToMany(mappedBy = "publicacion", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Comentario> comentario = new ArrayList<>();



    public Publicacion (String titulo, String contenido, LocalDate fechaCreacion, Usuario usuario, Catedratico catedratico){
        this.titulo = titulo;
        this.contenido = contenido;
        this.fechaCreacion = fechaCreacion;
        this.usuario = usuario;
        this.catedratico = catedratico;
    }


    public Publicacion (String titulo, String contenido, LocalDate fechaCreacion, Usuario usuario, Curso curso){
        this.titulo = titulo;
        this.contenido = contenido;
        this.fechaCreacion = fechaCreacion;
        this.usuario = usuario;
        this.curso = curso;
    }





}
