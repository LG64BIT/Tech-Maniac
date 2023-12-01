import React from "react";
import { HomeOutlined, UserOutlined } from "@ant-design/icons";
import { Menu } from "antd";
import { useNavigate } from "react-router-dom";

export const Navbar = () => {
  const navigate = useNavigate();
  const items = [
    {
      label: "Početna",
      key: "home",
      icon: <HomeOutlined />,
      onClick: () => {
        navigate("/");
      },
    },
    {
      label: "Korisnici",
      key: "users",
      icon: <UserOutlined />,
      onClick: () => {
        navigate("/users");
      },
    },
  ];
  return <Menu mode="horizontal" items={items} />;
};
