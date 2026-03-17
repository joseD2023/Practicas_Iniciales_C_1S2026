const API_URL = "http://localhost:8080/api/auth";

export const login = async (registroAcademico, password) => {

  const response = await fetch(`${API_URL}/login`, {
    method: "POST",
    headers: {
      "Content-Type": "application/json"
    },
    credentials: "include",   
    body: JSON.stringify({
      registroAcademico,
      password
    })
  });

  return response.text();
};
