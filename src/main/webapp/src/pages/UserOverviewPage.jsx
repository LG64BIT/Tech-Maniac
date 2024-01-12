import { Button, Table, message } from "antd";
import { useEffect, useState } from "react";
import { axiosInstance } from "../utils/axiosInstance";
import { UserDeleteOutlined } from "@ant-design/icons";

export const UserOverviewPage = () => {
  const [userData, setUserData] = useState([]);
  const [isLoading, setIsLoading] = useState(false);

  const deleteUser = (id) => {
    axiosInstance
      .delete("/users/" + id)
      .then(() => {
        message.open({
          type: "success",
          content: "Korisnik uspješno obrisan!",
        });
        fetchUsers();
      })
      .catch((error) => {
        message.open({
          type: "error",
          content: "Pogreška prilikom brisanja korisnika!",
        });
        console.log("Error: ", error);
      });
  };

  const columns = [
    {
      title: "Identifikacijski broj",
      dataIndex: "id",
      align: "center",
    },
    {
      title: "Korisnično ime",
      dataIndex: "username",
      align: "center",
    },
    {
      title: "Privilegije",
      align: "center",
      dataIndex: "role",
    },
    {
      title: "Datum registracije",
      align: "center",
      dataIndex: "createdAt",
    },
    {
      title: "Ukloni korisnika",
      align: "center",
      render: (item) => {
        return (
          <Button onClick={() => deleteUser(item.id)} danger>
            <UserDeleteOutlined />
          </Button>
        );
      },
    },
  ];

  const fetchUsers = () => {
    setIsLoading(true);
    axiosInstance
      .get("/users")
      .then((response) => {
        setUserData(
          response.data.map((user) => {
            if (user.role === "ADMIN") user.role = "Administrator";
            else user.role = "Korisnik";
            return user;
          })
        );
      })
      .catch((error) => {
        message.open({
          type: "error",
          content: "Error while fetching users",
        });
        console.error("Error: ", error);
      })
      .finally(setIsLoading(false));
  };

  useEffect(() => {
    fetchUsers();
  }, []);

  return (
    <Table
      rowKey={(data) => data.id}
      loading={isLoading}
      columns={columns}
      dataSource={userData}
    />
  );
};
