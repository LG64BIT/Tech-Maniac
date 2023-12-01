import { Table, message } from "antd";
import { useEffect, useState } from "react";
import { axiosInstance } from "../utils/axiosInstance";

export const UserOverviewPage = () => {
  const [userData, setUserData] = useState([]);
  const [isLoading, setIsLoading] = useState(false);

  const columns = [
    {
      title: "Identifikacijski broj",
      dataIndex: "id",
    },
    {
      title: "Korisnično ime",
      dataIndex: "username",
    },
  ];

  const fetchUsers = () => {
    setIsLoading(true);
    axiosInstance
      .get("/users")
      .then((response) => {
        setUserData(response.data);
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
