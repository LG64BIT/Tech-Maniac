import { Button, Form, Input, message } from "antd";
import { axiosInstance } from "../utils/axiosInstance";
import { useNavigate } from "react-router-dom";
import { setCookie } from "../utils/cookieHelper";

export const LoginForm = () => {
  const navigate = useNavigate();

  const handleLogin = ({ username, password }) => {
    const data = { username, password };
    axiosInstance
      .post("/auth/authenticate", data)
      .then((response) => {
        setCookie("token", response.data.token);
        navigate("/");
        window.location.reload();
      })
      .catch((error) => {
        message.open({
          type: "error",
          content: "Neuspjelo logiranje!",
        });
        console.error("Error: ", error);
      });
  };

  return (
    <Form onFinish={handleLogin} layout="vertical">
      <Form.Item
        label="Korisničko ime"
        name="username"
        rules={[
          {
            required: true,
            message: "Molimo unesite korisničko ime",
          },
        ]}
      >
        <Input />
      </Form.Item>
      <Form.Item
        label="Lozinka"
        name="password"
        rules={[
          {
            required: true,
            message: "Molimo unesite lozinku",
          },
        ]}
      >
        <Input type="password" />
      </Form.Item>
      <Button type="primary" htmlType="submit" style={{ float: "right" }}>
        Prijavi se
      </Button>
    </Form>
  );
};
