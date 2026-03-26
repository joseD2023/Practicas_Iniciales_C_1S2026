import { useEffect, useState } from "react"
import "../styles/home.css"
import "../styles/miUsuario.css"
import { crearCursosAprobados, obtenerCursosAprobados } from "../service/publicacionesService";
import { obtenerCursoId, obtenerCursos } from "../service/cursos";
import MostrarMiUsuario from "../components/miUsuario";
import BuscadorUsuario from "./perfilesUsuarios";

/*vamos a crear como la entrada principal de nuestra pagina o sea un componenete basic y le vamos agregar la opcion de 
ver cursos aprobados y crear cursos aprobados*/
export default function Home(){
    const[cursosAprobados, setCursosAprobados] = useState([]); // encontrar los cursos aprobados 
    const[cursoSeleccionado, setCursoSeleccionado] = useState(""); 
    const[creditos, setCreditos] = useState(""); 
    const[curso, setCurso] = useState([]);  //para obtener todos los cursos
    const[error, setError] = useState(""); 




    useEffect(()=>{
        cargarCursosAprobados(), 
        cargarCursos()
    },[])

    const handleCursosAprobados = async(e) =>{

         e.preventDefault()

         if(!cursoSeleccionado || !creditos){ 
            setError("Campos Incompletos"); 
            return; 
        }

   

       const cursoEncontrado = curso.find(c => c.id === Number(cursoSeleccionado));

       if(!cursoEncontrado){
        setError("Cursos No Encontrados")
        return; 
       }

         const cursosAprobados = {
            nombreCursos : cursoEncontrado.nombre,
            creditos :  Number(creditos), 

            fechaAprobacion : new Date().toISOString().split("T")[0]
        }; 


        try {

            await crearCursosAprobados(cursosAprobados); 
            cargarCursosAprobados(); 
            setCursoSeleccionado("")
            setCreditos("")
            cargarCursosAprobados()
            
        } catch (error) {
            console.log(error)
            
        }}


    const cargarCursosAprobados = async() =>{
        try {
            const response = await obtenerCursosAprobados()
            setCursosAprobados(response)
            
        } catch (error) {
            
        }
    }


    const cargarCursos = async() =>{
        try {
            const response = await obtenerCursos()
            setCurso(response)
            
        } catch (error) {
            console.log(error)
            
        }    

     }


     return (

    <div class="home-container">
        {/* aqui vamos a colocar un componente que muestre al usuario logueado */}
        < MostrarMiUsuario />

        <div className="cursos-aprobados-container">

            <div className="form-aprobados">
                <h2>Crear Cursos Aprobados</h2>

                <form onSubmit={handleCursosAprobados}>

                    {/*Vamos a hacer select para seleccionar los cursos disponibles y no se inventen. */}

                    <div className="select-aprobados">
                        <select className="mi-select" value={cursoSeleccionado} onChange={(e)=> setCursoSeleccionado(e.target.value)}>
                            <option value="">Seleccionar Cursos</option>
                            {curso.map( c =>(
                                <option key={c.id} value={c.id} >
                                    {c.nombre}
                                </option>
                            ))}
                        </select>
                    </div>
                    <br />
                    <input type="text" className="controls" placeholder="Creditos" value={creditos} onChange={(e)=> setCreditos(e.target.value)}/>

                    <button type="submit">Aprobado</button>

                </form>

            
            </div > 
     

            <div className="buscador-usu">

                 <BuscadorUsuario />

            </div>

           

            {/*Aqui seria como el otro bloque para trabajar */}

            <div className="curso-view">
                {cursosAprobados.map(ca => (
                    <div className="cursos-aprobados-card" key={ca.id} value={ca.id}>

                        <p>Nombre Curso: {ca.nombreCursos}</p>
                        <p>Creditos: {ca.creditos}</p>

                    </div>
                ))}

            </div>



        </div>

    </div>

        
     )


        











         }

    


