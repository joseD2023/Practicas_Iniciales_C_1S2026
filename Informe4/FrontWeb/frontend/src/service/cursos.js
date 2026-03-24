//aqui vamos a cargar los cursos disponibles 
const API_URL = "http://localhost:8080/api/cursos"



export const obtenerCursos = async() =>{
    try {

        const response = await fetch(API_URL, {
            method: "GET", 
            credentials : "include"
        }); 

        if(!response.ok){
            throw new Error("No se Cargaron los Cursos");
            
        }
        
        return await response.json(); 
    } catch (error) {
        throw new Error("Error Servidor: ", error);
           
    }
}