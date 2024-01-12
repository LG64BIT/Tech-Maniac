import Cookies from "js-cookie";

export const getCookie = (cookieName) => {
  return document.cookie
    .split("; ")
    .find((row) => row.startsWith(`${cookieName}=`))
    ?.split("=")[1];
};

export const setCookie = (cookieName, cookieValue) => {
  Cookies.set(cookieName, cookieValue);
};

export const removeCookie = (cookieName) => {
  Cookies.remove(cookieName);
};
