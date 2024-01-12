import { Card, Col, Row } from "antd";
import { LoginForm } from "../components/LoginForm";
import { RegisterForm } from "../components/RegisterForm";

export const AuthenticationPage = () => {
  return (
    <Row gutter={[16, 16]} style={{ margin: 16 }}>
      <Col xs={24} sm={24} md={12} lg={12} xl={12}>
        <Card
          title="Novi korisnik? Registriraj se!"
          bordered={false}
          style={{ textAlign: "center" }}
        >
          <RegisterForm />
        </Card>
      </Col>
      <Col xs={24} sm={24} md={12} lg={12} xl={12}>
        <Card
          title="Prijavi se!"
          bordered={false}
          style={{ textAlign: "center" }}
        >
          <LoginForm />
        </Card>
      </Col>
    </Row>
  );
};
