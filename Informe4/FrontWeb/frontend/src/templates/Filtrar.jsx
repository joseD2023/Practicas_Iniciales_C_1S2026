import { useState } from "react";
import { filtrarPublicaciones, getComentariosByPublicacion } from "../service/publicacionesService";
import "../styles/filtros.css"




export default function Filtrar(){
    const[tipo, setTipo] = useState("curso"); 
    const[valor, setValor] = useState(""); 
    const[publicaciones, setPublicaciones] = useState([]); 
    const [loading, setLoading] = useState(true);
    const [comentarios, setComentarios] = useState({});
    const [comentarioVisible, setComentarioVisible] = useState({});


    const handleFiltrar = async() =>{

        if(!valor){
            console.log("Filtro Invalido"); 
            return; 
        }
        const data = await filtrarPublicaciones(tipo, valor);
        setPublicaciones(data); 
    }


    const obtenerComentarios = async(publicacionId) =>{



        if(comentarioVisible[publicacionId] == true){
            let nuevoEstado = {}; 

            for(let id in comentarioVisible){
                nuevoEstado[id] = comentarioVisible[id]; 
            }
            nuevoEstado[publicacionId] = false; 
            setComentarioVisible(nuevoEstado); 
            return;  
        }


        if(comentarios[publicacionId] == undefined){
            try {
                const data = await getComentariosByPublicacion(publicacionId)
                let nuevoComentarios ={}; 

                for(let id in comentarios){
                    nuevoComentarios[id] = comentarios[id]; 
                }

                nuevoComentarios[publicacionId] = data; 

                setComentarios(nuevoComentarios)
                
            } catch (error) {
                console.error("Error cargando comentarios:", error);
                return;
                
            }
        }

        let nuevosEstados = {}; 

        for(let id in comentarioVisible){
            nuevosEstados[id] = comentarioVisible[id]; 
        }

        nuevosEstados[publicacionId] = true; 
        setComentarioVisible(nuevosEstados)
    }


    return (
        <div className="container">

           <div className="select-origin">
               <div className="select-card">
                   <select  onChange={(e) => setTipo(e.target.value)}>
                      <option value="curso">curso</option>
                      <option value="catedratico">catedratico</option>
                      <option value="idCurso">ID curso</option>
                      <option value="idCatedratico">ID catedratico</option>
                    </select>
                   <input type="text" placeholder="buscar" onChange={(e) => setValor(e.target.value)} />
                   <button onClick={handleFiltrar}>Filtrar</button>
                 </div>                
            </div>


            <div className="publicaciones-lista">
                {publicaciones.map(pub => (
                    <div key={pub.id} className="publicacion-card">
                        <h2>{pub.titulo}</h2>
                        <p>{pub.contenido}</p>
                        <p>{pub.fechaCreacion}</p>
                        <p>ID Publicacion:{pub.id}</p>

                        <button 
                            className="btn-comentarios"
                            onClick={() => obtenerComentarios(pub.id)}
                        >
                            {comentarioVisible[pub.id] ? 'Ocultar comentarios' : 'Ver comentarios'}
                        </button>

                        {comentarioVisible[pub.id] && (
                            <div className="comentarios-section">
                                <h4>Comentarios</h4>
                                {comentarios[pub.id]?.length > 0 ? (
                                    comentarios[pub.id].map(com => (
                                        <div key={com.id} className="comentario-item">
                                            <strong> ID Usuario: {com.id}:</strong>
                                            <p>{com.mensaje}</p>
                                            <small>{new Date(com.fechaCreacion).toLocaleDateString()}</small>
                                        </div>
                                    ))
                                ) : (
                                    <p>No hay comentarios</p>
                                 
                                )}

                            </div>
                        )}


                    </div>

                ))}

            </div>


        </div>
    )



}