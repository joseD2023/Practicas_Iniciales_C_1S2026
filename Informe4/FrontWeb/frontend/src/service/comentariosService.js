//utilizacion de los comentarios aqui funcionamiento mostrar solo los comentarios en un enpoint difenrente 

const API_URL = "http://localhost:8080/api/comentarios"; 

export const getComentariosByPublicacion = async (publicacion) =>{
    const response = await fetch(`${API_URL}/${publicacion}`,{
        method : "GET", 
        credentials: "include"
    }
    
    

    )


    
}

