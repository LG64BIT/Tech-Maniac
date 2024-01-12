import React, { useEffect, useState } from "react";
import {
  HomeOutlined,
  UserOutlined,
  LoginOutlined,
  LogoutOutlined,
  FormOutlined,
} from "@ant-design/icons";
import { Menu } from "antd";
import { useNavigate } from "react-router-dom";
import { getCookie, removeCookie } from "../../utils/cookieHelper";
import {
  getUserRole,
  isUserAuthenticated,
  parseJwt,
} from "../../utils/authUtils";

export const Navbar = () => {
  const navigate = useNavigate();
  const [userRole, setUserRole] = useState(null);
  const user = isUserAuthenticated() ? parseJwt(getCookie("token")) : null;

  const items = [
    {
      label: "Početna",
      key: "home",
      icon: <HomeOutlined />,
      onClick: () => {
        navigate("/");
      },
    },
  ];

  const guestItems = [
    {
      label: "Prijava/Registracija",
      key: "authentication",
      icon: <LoginOutlined />,
      onClick: () => {
        navigate("/authentication");
      },
    },
  ];

  const userItems = [
    {
      label: "Recenzije",
      key: "reviews",
      icon: <FormOutlined />,
      onClick: () => {
        navigate("/reviews");
      },
    },
    {
      label: "Odjava",
      key: "logout",
      icon: <LogoutOutlined />,
      danger: true,
      onClick: () => {
        removeCookie("token");
        navigate("/");
        window.location.reload();
      },
    },
    {
      label: `Pozdrav, ${user?.firstName} ${user?.lastName}!`,
      key: "greeting",
    },
  ];

  const adminItems = [
    {
      label: "Korisnici",
      key: "users",
      icon: <UserOutlined />,
      onClick: () => {
        navigate("/users");
      },
    },
  ];

  useEffect(() => {
    if (isUserAuthenticated()) getUserRole(setUserRole);
  }, []);

  return (
    <Menu
      mode="horizontal"
      style={{ display: "flow", textAlign: "center" }}
      items={
        isUserAuthenticated()
          ? userRole === "ADMIN"
            ? [...items, ...adminItems, ...userItems]
            : [...items, ...userItems]
          : [...items, ...guestItems]
      }
    />
  );
};
