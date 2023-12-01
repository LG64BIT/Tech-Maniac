import axios from "axios";
import { SERVER_URL } from "../constants/constants";
import { getCookie } from "./cookieHelper";

export const axiosInstance = axios.create({
  baseURL: SERVER_URL + "/api",
  headers: {
    Authorization: `Bearer ${getCookie("token")}`,
  },
});
