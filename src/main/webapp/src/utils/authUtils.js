import { axiosInstance } from "./axiosInstance";
import { getCookie } from "./cookieHelper";

export const isUserAuthenticated = () => {
  return getCookie("token") !== undefined;
};

export const getUserRole = (setUserRole) => {
  axiosInstance
    .get("/auth/role")
    .then((response) => {
      setUserRole(response.data);
    })
    .catch((error) => {
      console.error("Error: ", error);
    });
};

export const parseJwt = (token) => {
  var base64Url = token.split(".")[1];
  var base64 = base64Url.replace(/-/g, "+").replace(/_/g, "/");
  var jsonPayload = decodeURIComponent(
    window
      .atob(base64)
      .split("")
      .map(function (c) {
        return "%" + ("00" + c.charCodeAt(0).toString(16)).slice(-2);
      })
      .join("")
  );

  return JSON.parse(jsonPayload);
};
