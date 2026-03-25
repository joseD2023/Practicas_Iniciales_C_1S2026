package org.example.practicaweb.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Curso {

    /*las validaciones para los atributos los vamos hacer con la dependencia valideccion*/
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /*validacion de que no queremos que este campo sea blanco o nulo debe contener caracteres*/
    @NotBlank()
    private String nombre;

    /*aquí estamos diciendo que los creditos no sean decimales y que no sean mayor a 2 digitos es
     decir puede ser 1 o 2 o 3 pero no puede ser (122, 222) o numero de tres digitos y que el valor

    * minimo del los creditos es 1*/
    @Digits(integer = 2, fraction = 0, message = "Campo debe ser sin decimales")
    @Min(value = 1, message = "Los Creditos no pueden ser menor a 1")
    private int creditos;

    @NotBlank()
    private String area;

    private int totalCreditos;

    /*Vamos a hacer la relación entre el Curso y las publicaciones
    * un curso puede tener varias publicaciones */

    @OneToMany(mappedBy = "curso", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Publicacion> listaPublicaciones = new ArrayList<>();



    public Curso(String nombre, int creditos, String area){
        this.nombre = nombre;
        this.creditos = creditos;
        this.area = area;
        this.totalCreditos =0;
    }


    public void setCreditostotales(int creditos){
        this.totalCreditos += creditos;
    }








}
