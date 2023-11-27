import { message } from "antd";
import axios from "axios";


const ExpAxios = axios.create({
    timeout: 50000,
    headers: {
        'Content-Type' : 'application/json'
    }
});

ExpAxios.interceptors.request.use(function(config) {
    const token = localStorage.getItem("authToken");
    config.headers["Authorization"] = `Bearer ${token}`;
    return config; 
});

ExpAxios.interceptors.response.use(
    (response) => {
        return response;
    },
    (error) => {
      if (error.response && error.response.status === 401) {
        message.error('Token expired')
      }
      return Promise.reject(error);
    }
);

export default ExpAxios