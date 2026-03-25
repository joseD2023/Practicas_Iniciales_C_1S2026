/*Vamos a trabajar con los enpoints de los Usuarios */

const API_URL = "http://localhost:8080/api"; //uuidado aqui que se me olvido colocar el enpoint completo


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


//esto me va ayudar basicamente a recopilar el usuario que esta logueado nada mas no tiene otra funcion
export const miUsuario = async() =>{
    try {

        const response = await fetch(`${API_URL}/usuarios/mi-usuario`, {
            method: "GET", 
            credentials: "include"
        })

        if(!response.ok){
            throw new Error("Error:  404");
            
        }

        return await response.json(); 
        
    } catch (error) {
        console.log(error)
        
    }
}
