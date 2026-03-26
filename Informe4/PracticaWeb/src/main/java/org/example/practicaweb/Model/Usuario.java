package org.example.practicaweb.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor /*Constructor vacio*/
@Entity /* le dice a Mysql que va a crear una tabla acerda de esta clase osea Tabla Usuario*/
public class Usuario {

    /*Colocamos un Lombook para gener los setter y Getters de manera mas sencilla
    * @AllArgsConstructor crear un constructor con todos los parametros*/

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) /*va a generar el id automaticamente*/
    private Long id;

    @Digits(integer = 9, fraction = 0, message = "Registro academico debe ser entre 0-9 y no debe ser decimal")
    @Min(value = 1, message = "Numero no puede ser Negativo")
    private Integer registroAcademico;


    /*Como dato de Aprendizaje utilizar Integer en lugar de Int, ya que al ser un tipo de dato primitivo int
    * no permite registros null solo 0, integer si permite y es una buena práctica utilizarlo si no sabemos si puede
    * venir datos null (int utilizarlo en cosas que si estábamos seguros que van a venir como contadores) */


    @NotBlank
    private String nombre;

    @NotBlank
    private String apellido;


    @Email(message = "Formato Invalido del Correo")
    private String correo;


    @Size(min = 3, message = "Contraseña mínimo 3 caracteres")
    @NotBlank(message = "La contraseña es Obligatoria")
    private String password;


    private int totalCreditos;


    /*Relación dice un Usuario puede hacer muchos Comentarios como también puede hacer puede tener muchas publicaciones
    * entonces
    * -Datos Importantes:
    * cascade = CascadeType.ALL se va a relacionar junto a los comentarios que haga este usuario
    * digamos si el usuario hizo 5 comentarios y el usuario es eliminado estos 5 comentarios también se eliminan
    * mappeBy = debe coincidir con el nombre del atributo de la otra clase*/

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Comentario> listaComentarios = new ArrayList<>();


    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Publicacion> listaPublicaciones = new ArrayList<>();


    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<CursoAprobado> listaCursosAprobados = new ArrayList<>();


    public Usuario(Integer registroAcademico, String nombre, String apellido, String correo, String password){
        this.registroAcademico = registroAcademico;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.password = password;
    }


}
