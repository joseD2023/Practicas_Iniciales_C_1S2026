package org.example.practicaweb.Model;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.example.practicaweb.Model.Usuario;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Comentario {
    /*Vamos a trabajar con con que un usuario puede tener muchos comentarios*/
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    @NotBlank(message = "Invalido espacio en Blanco")
    @Size(min = 1, max = 300, message = "Cantidad de Caracteres 1-300")
    private String mensaje;


    @NotNull(message = "La fecha es Obligatoria")
    private LocalDate fechaCreacion;


    /*vamos a hacer las relaciones de uno a muchos
    * Entonces lo que estamos haciendo es que vamos a tener una columna que tenga
    * el usuario_id y publicacion_id que va a contener el objeto de cada uno y lo va almacenar ahi
    * ManyToOne es decir muchos comentarios pueden tener un usuario o una publicacion*/


    @ManyToOne
    @JoinColumn(name = "usuario_id") //esto se va a crear automaticamente en un columna de la base de datos
    //@JsonIgnore
    //@JsonBackReference //muy importante para que no duplique no los datos si no la visualizacion de usuarios
    //cuando relacionamos comentarios y publicaciones
    private Usuario usuario;



    /*En que publicacion está el comentario y qué usuario fue el que lo comento */
    @ManyToOne
    @JoinColumn(name = "publicacion_id")
    private Publicacion publicacion;


    public Comentario(Usuario usuario, Publicacion publicacion, String mensaje, LocalDate fechaCreacion){
        this.usuario = usuario;
        this.publicacion = publicacion;
        this.mensaje = mensaje;
        this.fechaCreacion = fechaCreacion;
    }



}
