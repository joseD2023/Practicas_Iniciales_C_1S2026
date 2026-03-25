const API_URL = "http://localhost:8080/api/auth"; /*Este enpoints lo coloque porque es de donde react 
va a consumir al backend a traves de API REST */

export const login = async (registroAcademico, password) => {

  /*creamos una funcion que reciba los datos del usuario */

  const response = await fetch(`${API_URL}/login`, { //hace peticion al backend
    method: "POST", //porque estamso enviando datos 
    headers: {  //estamos especificando que estamos mandando un JSON 
      "Content-Type": "application/json"
    },
    credentials: "include",   //permite cookies del security del backend 
    body: JSON.stringify({  //lo convertimos a un Json real tipo {"registroAcademico": 200001 , "password" = 123}
      registroAcademico,
      password
    })
  });

  console.log(response)

  return response; //devuelve lo que diga el backend en formato texto 
};


/* Nota importante en service en react basicamente es la logica con la conexion del backend  
export esta funcion puede utilizarse en otro lado 
async esta funcio va a tardar porque usa internet porque fetch no responde al instante tarda 
await espera hasta que el backend responda. */


export const registrarUsuario = async(registroAcademico, nombre, apellido, correo, password) =>{

  const response = await fetch(`${API_URL}/register`, {
      method: "POST", 
      headers : {
        "Content-Type": "application/json"
      },
      credentials : "include",
      body: JSON.stringify({
        registroAcademico, 
        nombre,
        apellido, 
        correo,  
        password
      })
    }); 

  return response; 
}; 



/*Ahora vamos a trabajar con recuperar password */

export const recuperarPasswordUsuario = async ({registroAcademico, correoElectronico, password}) =>{

  console.log("Datos Enviados por el Frontend:", registroAcademico , " ", correoElectronico, " ", password)


  try {
    const response = await fetch(`${API_URL}/recuperar-password`,{
    method : "POST", 
    headers : {
      "Content-Type": "application/json"
    }, 
    credentials : "include", 
    body : JSON.stringify({
      registroAcademico, 
      correoElectronico, 
      password
    })
  }); 

  if(!response.ok){
    throw new Error("Algo paso que el backend mano un error");
    
  }

  console.log("Datos finales Enviados: " , response)

  return response.json();
    
  } catch (error) {
    throw new Error(error);
    
    
  }

}; 


export const cerrarSesion = async() =>{
  try {

    const response = await fetch(`${API_URL}/logout`,{
      method: "POST", 
      credentials: "include"
    }); 

    if(!response.ok){
      throw new Error("Surgio un Error para Cerrar Sesion");
      
    }

    console.log("Sesion Cerrada Exitosamente!!"); 

  } catch (error) {

    console.log(error); 
    
  }
}








