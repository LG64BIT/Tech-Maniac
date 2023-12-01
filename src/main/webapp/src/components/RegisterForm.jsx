import { Button } from "antd";
import { axiosInstance } from "../utils/axiosInstance";

export const RegisterForm = () => {
  const handleRegister = () => {
    const data = { username: "lg64bit", password: "12345678" };
    axiosInstance
      .post("/auth/register", data)
      .then((response) => {
        console.log(response.data.token);
        document.cookie = `Bearer ${response.data.token}`;
      })
      .catch((error) => {
        console.error("Error: ", error);
      });
  };

  //TODO: Create register form, validation, pass credentials to request, optional: separate to page or modal
  return <Button onClick={handleRegister}>Register</Button>;
};
