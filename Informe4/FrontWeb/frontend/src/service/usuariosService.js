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



//aqui vamos a cargar los perfiles de los usuarios con sus cursos aprobados 

const API_URL1 = "http://localhost:8080/api/usuarios"


export const perfilUsuario = async(registro) =>{
    try {

        const response = await fetch(`${API_URL1}/registro/${registro}`, {
            method: "GET", 
            credentials: "include"
        })

        if(!response.ok){
            throw new Error("Error: No se encontro Usuario ");
            
        }

        return await response.json()
        
    } catch (error) {

        console.log(error)
        
    }
}


const API_URL2 = "http://localhost:8080/api/cursos-aprobados/usuario/registro"

export const cursosPerfiles = async(registro) =>{
    try {
        const response = await fetch(`${API_URL2}/${registro}`, {
            method: "GET", 
            credentials: "include"
        })

        if(!response.ok){
            throw new Error("Error: Cursos del Usuario No tiene");
            
        }

        return await response.json(); 
        
    } catch (error) {
        console.log(error)
    }
}



//actualizar mi perfil 

export const actualizarPerfil = async(data) =>{
    try {
        const response = await fetch(`${API_URL1}/me`,{
            method:"PUT",
            credentials: "include", 
            headers: {
                "Content-Type": "application/json" 
            }, 
            body : JSON.stringify(data)

        })

        if(!response.ok){
            throw new Error("Error al actualizar");
            
        }

        return await response.json(); 
        
    } catch (error) {
        console.log(error)
        
    }
}