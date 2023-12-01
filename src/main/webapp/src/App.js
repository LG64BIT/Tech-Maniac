import Layout, { Content } from "antd/es/layout/layout";
import "./App.css";
import { LoginForm } from "./components/LoginForm";
import { Route, Routes } from "react-router-dom";
import { UserOverviewPage } from "./pages/UserOverviewPage";
import { HomePage } from "./pages/HomePage";
import { Navbar } from "./components/common/Navbar";

const App = () => {
  return (
    <Layout>
      <div className="App">
        <Navbar />
        <LoginForm />
      </div>
      <Content>
        <Routes>
          <Route exact path="/" element={<HomePage />} />
          <Route path="/users" element={<UserOverviewPage />} />
        </Routes>
      </Content>
    </Layout>
  );
};

export default App;
