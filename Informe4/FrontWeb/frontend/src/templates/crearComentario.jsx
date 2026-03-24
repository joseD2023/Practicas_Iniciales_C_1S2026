import { useEffect, useState } from "react";
import { getNuevoComentario } from "../service/comentariosService";
import "../styles/comentarios.css"



export default function ComentarioNuevo({idPublicaciones}) {

    //necesito estados 
    const[mensaje, setMensajes] = useState("") 
    const[error, setError] = useState(null)

    console.log("ID RECIBIDO: ", idPublicaciones)


    const handleComentario = async() =>{
        if(!mensaje.trim()){
            console.log("Mensaje Vacio"); 
            return; 
        }


        const nuevoComentario = {
            mensaje : mensaje, 
            fechaCreacion:  new Date().toISOString().split("T")[0],
            publicacion : {id : idPublicaciones}
        }


        console.log("Estructura del comentario a Enviar", nuevoComentario)

        try {
            await getNuevoComentario(nuevoComentario); 

            setMensajes("")
            console.log("Comentario Enviado")
            
        } catch (error) {
            console.log(error)
            
        }

    }

    return (
        <div className="card">
            <textarea className="text-area" placeholder="Escribir Comentario..." value={mensaje} onChange={(e) => setMensajes(e.target.value)}>
            </textarea>

            <button onClick={handleComentario}>Comentar</button>


        </div>
    )
    
}