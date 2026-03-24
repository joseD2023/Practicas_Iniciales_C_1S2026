import { useState, useEffect } from "react";
import { obtenerUsuarios } from "../service/usuariosService";
import "../styles/usuarios.css"


export default function MostrarUsuarios(){

    /*vamos a obtener los Usuarios cargados en las Base de Datos */

    const[usuarios, setUsuarios] = useState([]); 

    useEffect(()=>{
        cargarUsuarios(); 
    }, []); 

    const cargarUsuarios = async() =>{
        try {
            const data = await obtenerUsuarios(); 
            setUsuarios(data)
        } catch (error) {
            console.log(error)
        }
    }

    return (
        
        <div className="raiz">

            <h2>Usarios Usac System</h2>
            <div className="usuario-view">
                {usuarios.map(u =>(
                    <div key={u.id} className="usuarios-card">
                        <p>ID: {u.id}</p>
                        <p>Registro Academico: {u.registroAcademico}</p>
                        <p>Nombre: {u.nombre}{u.apellido}</p>
                        <p>Correo Electronico: {u.correo}</p>
                    </div>

                ))}

            </div>

        </div>
    )

}