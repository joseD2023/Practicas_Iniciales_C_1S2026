import { useEffect, useState } from "react";
import { obtenerCursos } from "../service/cursos";
import "../styles/cursos.css"



export default function MostrarCursos (){

    const[cursos, setCursos] = useState([]); 
    const[error, setError] = useState(null); 

    useEffect(() =>{
        cargarCursos()
    }, []); 


    const cargarCursos = async() =>{
        try {
            const data = await obtenerCursos(); 
            setCursos(data); 
            
        } catch (error) {
            console.log(error)
            
        }
    }

    return (
        <div className="raiz">
            <h2>Cursos System Usac</h2>
            <div className="cursos-view">
                {cursos.map(curso =>(
                    <div  key={curso.id} className="cursos-card">
                        <p>ID Curso: {curso.id}</p>
                        <p>Nombre Curso: {curso.nombre}</p>
                        <p>Creditos: {curso.creditos}</p>
                        <p>Area Peteneciente: {curso.area}</p>
                    </div>
                ))}


            </div>
        </div>
    )

}