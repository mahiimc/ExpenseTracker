import axios from "axios";

const ExpAxios = axios.create({
    timeout: 50000,
    headers: {
        'Content-Type' : 'application/json'
    }
});

const setAuthorizationToken = () => {
    const token = localStorage.getItem("authToken");
    if(token) {
        ExpAxios.defaults.headers.common['Authorization'] = `Bearer ${token}`;
    }
    else {
        delete ExpAxios.defaults.headers.common['Authorization'];
    }
};

setAuthorizationToken();


export default ExpAxios