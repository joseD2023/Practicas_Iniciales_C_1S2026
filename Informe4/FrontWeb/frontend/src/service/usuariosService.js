/*Vamos a trabajar con los enpoints de los Usuarios */

const API_URL = "http://localhost:8080/api";


export const obtenerUsuarios = async() =>{
    try {

        const response = await fetch(`${API_URL}/usuarios`, {
            method: "GET", 
            credentials: "include"
        })
        if(!response.ok){
            throw new Error("Usuarios No encontrados"); 
        }
        const data = response.json(); 
        return data; 
        
    } catch (error) {
        console.error("Ocurrio un Error en la Carga de Usuario", error); 
        throw error; 
    }
}
