import axios, { type AxiosInstance } from "axios";

/**
 * Create an Axios instance for the api.
 */
const createAPI = (): AxiosInstance => {
  return axios.create({ baseURL: import.meta.env.VITE_REACT_APP_BASEURL });
};

/**
 * api constant is the axios-instance used for all requests to the rest-api.
 */
const api: AxiosInstance = createAPI();

/**
 * Set the Authorization header on each request equal to the token which
 * is stored inside the localStorage if a user is authenticated.
 */
api.interceptors.request.use(
  (request) => {
    const token = localStorage.getItem("token");
    if (token) {
      request.headers.Authorization = token;
    }
    return request;
  },
  (error) => {
    return Promise.reject(error);
  }
);

export default api;
