import { useEffect, useState } from "react";
import { getpublicaciones } from "../service/publicacionesService";
import { getComentariosByPublicacion } from "../service/publicacionesService";
import { obtenerCursos } from "../service/cursos";
import {obtenerCatedraticos } from "../service/catedratidos"; 
import {crearPublicacionesNuevas} from "../service/publicacionesService"
import "../styles/publicaciones.css";
import ComentarioNuevo from "./crearComentario";
import Filtrar from "./Filtrar";

export default function Publicaciones() {
    //Estados Principales 
    const [publicaciones, setPublicaciones] = useState([]);
    const [loading, setLoading] = useState(true);
    const [comentarios, setComentarios] = useState({});
    const [comentarioVisible, setComentarioVisible] = useState({});


    const[titulo, setTitulo] = useState(""); 
    const[contenido, setContenido] = useState(""); 
    const[cursos, setCursos] = useState([]); 
    const[catedraticos, setCatedraticos] = useState([]); 
    const[error, setError] = useState(null); 


    const[cursoSeleccionado, setCursoSeleccionado] = useState(""); 
    const[catedraticoSeleccionado, setCatedraticoSeleccionado] = useState(""); 

        //se ejecuta cuando se carga el componente
    useEffect(() => {
        cargarPublicaciones();
        cargarCursos();
        cargarCatedraticos(); 
    }, []);


    //aqui vamos a guardar nuestra nueva publicacion 

    const handleSubmi = async(e) =>{
        e.preventDefault()

        if(!titulo || !contenido){
            setError("Campos Incompletos"); 
            return; 
        }

        if(cursoSeleccionado && catedraticoSeleccionado){
            setError("Solo puede escoger un curso o catedratico"); 
            return; 
        }

    
        const nuevaPublicacion = {
        titulo : titulo, 
        contenido : contenido, 
        fechaCreacion : new Date().toISOString().split("T")[0]}; 


        /*basicamente es que si el backend va a recibir algo lo que acepta es Null o algo pero no recibe texto vacio 
        porque al momento de buscar en la base de Datos pues no va a encontrar entonces lo que hacemos aqui es basicmaente 
        restringir que el frontend no envie texto vacio porque eso hara que el backend explote entonces basicamente 
        solo acepta cosas validas pero entonces que pasa si no cumple con la condicion simplemente manda un NULL y no solo 
        un texto y eso evita que el backend mande error.
        */

        if(cursoSeleccionado){
            nuevaPublicacion.curso = {id : Number(cursoSeleccionado)}; 
        }


        if(catedraticoSeleccionado){
            nuevaPublicacion.catedraticos = {id : Number(catedraticoSeleccionado)};
        }



        try{

            await crearPublicacionesNuevas(nuevaPublicacion); 
            cargarPublicaciones(); 
            setTitulo("")
            setContenido("")
            setCatedraticoSeleccionado("")
            setCursoSeleccionado("")

        }catch(error){
            console.error("Error: ", error)
        }}; 



    const cargarCursos = async() =>{
        try {

            const response =  await obtenerCursos(); 
            setCursos(response); 
        } catch (error) {
            console.log(error)
        }
    }


    const cargarCatedraticos = async() =>{
        try {
            const res = await obtenerCatedraticos(); 
            setCatedraticos(res); 
            console.log(res) // vamos a ver que nos enviar el backend con los catedraticos 
        } catch (error) {
            console.log(error)
        }
    }


    const cargarPublicaciones = async () => {
        try {
            const data = await getpublicaciones();
            setPublicaciones(data);
        } catch (error) {
            console.error("Error:", error);
        } finally {
            setLoading(false);
        }
    };


    const toggleComentarios = async (publicacionId) => {
        // Si ya están visibles, los ocultamos
        if (comentarioVisible[publicacionId]) {
            setComentarioVisible({
                ...comentarioVisible,
                [publicacionId]: false
            });
            return;
        }

        // Si no están cargados, los cargamos
        if (!comentarios[publicacionId]) {
            try {
                const data = await getComentariosByPublicacion(publicacionId);
                setComentarios({
                    ...comentarios,
                    [publicacionId]: data
                });
            } catch (error) {
                console.error("Error cargando comentarios:", error);
            }
        }



        // Mostramos los comentarios
        setComentarioVisible({
            ...comentarioVisible,
            [publicacionId]: true
        });
    };

    if (loading) return <div className="loading">Cargando publicaciones...</div>;

    return (


        <div className="publicaciones-container">

          
            <div className="form-publicacion"> {/*Estamos capturando la informacioon para poder guardar y publicar */}
                <h2>Crear Publicaciones</h2>

                <form onSubmit={handleSubmi}>
                    <input className="controls" type="text" placeholder="Titulo" value={titulo} onChange={(e) => setTitulo(e.target.value)} />
                    <textarea className="controls" placeholder="Contenido" value={contenido} onChange={(e) => setContenido(e.target.value)}></textarea>

                {/*Div para mi select y el diseño */}

                <div className="select">
                <select className="mi-select" value={cursoSeleccionado} onChange={(e) => setCursoSeleccionado(e.target.value)}>
                    <option value="">Seleccionar Curso</option>
                    {cursos.map(curso => (
                        <option key={curso.id} value={curso.id}>
                            {curso.nombre}
                        </option>
                    ))}
                </select>

                <select className="mi-select" value={catedraticoSeleccionado} onChange={(e) => setCatedraticoSeleccionado(e.target.value)}>
                    <option value="">Seleccionar Catedraticos</option>
                    {catedraticos.map(catedratico => (
                        <option key={catedratico.id} value={catedratico.id}>
                            {catedratico.nombre }
                        </option>
                    ))}
                </select>

                </div>

                <button type="submit">Publicar</button>
                </form>
            </div>

            {error && (
                <div style={{
                    background :"#2c2c2c", 
                    color: "#fff",
                    padding : "15px", 
                    borderRadius: "10px",
                    border : "1px solid #ff4d4d",
                    marginTop : "50px"
                    
                }}>
                    <strong>Error: {error}</strong>
                </div>
            )}

              < Filtrar />
            <h1>Publicaciones</h1>

            <div className="publicaciones-lista">
                {publicaciones.map(pub => (
                    <div key={pub.id} className="publicacion-card">
                        <h2>{pub.titulo}</h2>
                        <p>{pub.contenido}</p>
                        <p>{pub.fechaCreacion}</p>
                        <p>ID Publicacion:{pub.id}</p>
                        <small>Autor: {pub.autor}</small>
                        
                        <button 
                            className="btn-comentarios"
                            onClick={() => toggleComentarios(pub.id)}
                        >
                            {comentarioVisible[pub.id] ? 'Ocultar comentarios' : 'Ver comentarios'}
                        </button>

                        {comentarioVisible[pub.id] && (
                            <div className="comentarios-section">
                                <h4>Comentarios</h4>
                                {comentarios[pub.id]?.length > 0 ? (
                                    comentarios[pub.id].map(com => (
                                        <div key={com.id} className="comentario-item">
                                            <strong> ID Comentario: {com.id}:</strong>
                                            <p>Usuario que lo Publico: {pub.id}</p>
                                            <p>{com.mensaje}</p>
                                            <small>{new Date(com.fechaCreacion).toLocaleDateString()}</small>

                                        </div>
                                    ))
                                ) : (
                                    <p>No hay comentarios</p>

                                 
                                )}   
  

                            </div>

                            


                        )}

                         <ComentarioNuevo idPublicaciones={pub.id} />  
                    </div>
                ))}
            </div>
        </div>
        
    );}
