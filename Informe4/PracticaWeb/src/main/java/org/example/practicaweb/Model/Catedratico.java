package org.example.practicaweb.Model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Catedratico {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Pattern(regexp = "^(?=.*[A-Za-z])[A-Za-z ]+$")
    private String nombre;

    @Pattern(regexp = "^(?=.*[A-Za-z])[A-Za-z ]+$")
    private String apellido;

    @Email(message = "Correo Invalido")
    private String correo;

    /*relaciones con publicaciones*/
    @OneToMany(mappedBy = "catedratico", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Publicacion> publicaciones = new ArrayList<>();


    public Catedratico(String nombre, String apellido, String correo){
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
    }



}
