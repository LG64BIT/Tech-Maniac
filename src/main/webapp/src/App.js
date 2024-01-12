import Layout, { Content, Footer } from "antd/es/layout/layout";
import "./App.css";
import { Route, Routes } from "react-router-dom";
import { UserOverviewPage } from "./pages/UserOverviewPage";
import { HomePage } from "./pages/HomePage";
import { Navbar } from "./components/common/Navbar";
import { AuthenticationPage } from "./pages/AuthenticationPage";
import { ReviewPage } from "./pages/ReviewPage";
import { BlankPage } from "./pages/BlankPage";
import { useEffect, useState } from "react";
import { getUserRole } from "./utils/authUtils";

const App = () => {
  const [userRole, setUserRole] = useState(null);
  useEffect(() => {
    getUserRole(setUserRole);
  }, []);
  return (
    <Layout>
      <div className="App">
        <Navbar />
      </div>
      <Content style={{ marginLeft: 16, marginRight: 16, marginBottom: 64 }}>
        <Routes>
          <Route exact path="/" element={<HomePage />} />
          {userRole === "ADMIN" ? (
            <Route path="/users" element={<UserOverviewPage />} />
          ) : (
            ""
          )}
          <Route path="/authentication" element={<AuthenticationPage />} />
          <Route path="/reviews" element={<ReviewPage />} />
          <Route path="*" element={<BlankPage />} />
        </Routes>
      </Content>
      <Footer
        style={{
          textAlign: "center",
          backgroundColor: "#001529",
          position: "fixed",
          width: "100%",
          bottom: 0,
        }}
      >
        <span style={{ color: "white" }}>
          Tech Maniac ©{new Date().getFullYear()} Project by Byte Benders
        </span>
      </Footer>
    </Layout>
  );
};

export default App;
