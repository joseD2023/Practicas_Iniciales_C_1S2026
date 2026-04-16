//aqui vamos hacer un componente que me de la informacion del usuario logueado este componente tambien me va a servir para buscar usuario

import { useEffect, useState } from "react";
import { miUsuario } from "../service/usuariosService";
import "../styles/miUsuario.css"



export default function MostrarMiUsuario(){
    const[Usuario, setUsuario] = useState(null); //porque devuelve un objeto 
    const[error, setError] = useState(""); 

    useEffect(()=>{
        cargarMiUsuario()
    }, [])

    //como no queremos captura datos en un formulario no es necesario un handle 

    const cargarMiUsuario = async() =>{
        try {

            const data = await miUsuario(); //obtengo la respuesta del backend 

            if(!data){
                setError("No respondio nada")
            }

            setUsuario(data); //guardo esa respuesta del backend
 
        } catch (error) {

            setError("No se pudo Obtener El Usuario Logueado")
            
        }
    }

    //cuando utilizamos objetos dentro de react solo debemos acceder a sus atributos no recorrerlos como un array 

    return (
        <div className="raiz-usuario">

            {Usuario && (
                <div className="card-usuario">
        
                    <p>Registro Academico: {Usuario.registroAcademico}</p>
                    <p>Nombre Usuario: {Usuario.nombre } {Usuario.apellido}</p>
                    <p>Correo Electronico: {Usuario.correo}</p>
                    <p>Total de Creditos: {Usuario.totalCreditos}</p>
                </div>
            )}


        </div>
    )

}