import { Button, Form, Input, message } from "antd";
import { axiosInstance } from "../utils/axiosInstance";
import { useNavigate } from "react-router-dom";

export const RegisterForm = () => {
  const navigate = useNavigate();

  const handleRegister = (formData) => {
    axiosInstance
      .post("/auth/register", formData)
      .then((response) => {
        document.cookie = `token=${response.data.token};`;
        navigate("/");
        window.location.reload();
      })
      .catch((error) => {
        message.open({
          type: "error",
          content: "Pogreška prilikom registracije!",
        });
        console.error("Error: ", error);
      });
  };

  return (
    <Form onFinish={handleRegister} layout="vertical">
      <Form.Item
        label="Ime"
        name="firstName"
        rules={[
          {
            required: true,
            message: "Molimo unesite ime",
          },
        ]}
      >
        <Input />
      </Form.Item>
      <Form.Item
        label="Prezime"
        name="lastName"
        rules={[
          {
            required: true,
            message: "Molimo unesite prezime",
          },
        ]}
      >
        <Input />
      </Form.Item>
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
      <Form.Item label="Nešto o meni" name="biography">
        <Input.TextArea maxLength={500} rows={3} />
      </Form.Item>
      <Button type="primary" htmlType="submit" style={{ float: "right" }}>
        Registriraj se
      </Button>
    </Form>
  );
};
