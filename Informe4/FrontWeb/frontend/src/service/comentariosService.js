//utilizacion de los comentarios aqui funcionamiento mostrar solo los comentarios en un enpoint difenrente 

const API_URL = "http://localhost:8080/api/comentarios"; 

export const getComentariosByPublicacion = async (publicacion) =>{
    const response = await fetch(`${API_URL}/${publicacion}`,{
        method : "GET", 
        credentials: "include"
    }
    )
}



/*aqui necesito el id de la publicacion para hacer el comentario entonces una vez recibido el id yo podre comentar*/


export const getNuevoComentario = async(NuevoComentario) =>{ //aqui debemos mandar todopara no complicarnos
    console.log("Frontend Enviando Comentario: ", NuevoComentario)
    try {
        const response = await fetch(`${API_URL}`, {
            method : "POST",
            credentials: "include",
            headers : {
                'Content-Type': 'application/json'
            }, 
            body : JSON.stringify(NuevoComentario)
            
        } ) //basicamente lo que hacemos aqui es acceder a la publicacion 

        if(!response.ok){
            throw new Error("No se Pudo crear el comentario");
            
        }

        return await response.json()
        
    } catch (error) {
        
    }

}
