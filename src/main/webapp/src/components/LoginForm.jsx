import { Button } from "antd";
import { axiosInstance } from "../utils/axiosInstance";

export const LoginForm = () => {
  const handleLogin = () => {
    const data = { username: "lg64bit", password: "12345678" };
    axiosInstance
      .post("/auth/authenticate", data)
      .then((response) => {
        document.cookie = `token=${response.data.token};`;
        window.location.reload();
      })
      .catch((error) => {
        console.error("Error: ", error);
      });
  };

  //TODO: Create login form, validation, pass credentials to request, optional: separate to page or modal
  return <Button onClick={handleLogin}>Login</Button>;
};
