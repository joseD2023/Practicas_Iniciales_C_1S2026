import { useEffect, useState } from "react";
import { obtenerCatedraticos} from "../service/catedratidos"
import "../styles/catedraticos.css"



export default function MostrarCatedraticos(){
    const[catedraticos, setCatedraticos] = useState([]); 

    useEffect(()=>{
        cargarCatedraticos()
    },[]); 

    const cargarCatedraticos = async() =>{
        try {
            const data = await obtenerCatedraticos(); 
            setCatedraticos(data)
        
        } catch (error) {
            console.log(error); 
        }
    }

    return(
        <div className="raiz">
            <h2>Catedraticos System Usac</h2>
            <div className="catedraticos-view">
                {catedraticos.map(cate => (
                    <div key={cate.id} className="catedraticos-card">
                        <p>Nombre: {cate.nombre}{cate.apellido}</p>
                        <p>Contacto: {cate.correo} </p>
                    </div>
                ))}

            </div>
        </div>
    )
}