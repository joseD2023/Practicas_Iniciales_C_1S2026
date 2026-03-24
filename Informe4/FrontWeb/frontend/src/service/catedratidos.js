const API_URL = "http://localhost:8080/api/catedraticos"

export const obtenerCatedraticos = async() =>{
    try {
        const response = await fetch(API_URL, {
            method: "GET", 
            credentials: "include"
        }); 

        console.log("Respuesta del Backent catedraticos" , response)

        if(!response.ok){
            throw new Error("Error: No pudimos Obtener Catedraticos");   
        }

        return await response.json(); 
        
    } catch (error) {
        throw new Error("Error:", error);
        
        
    }
}