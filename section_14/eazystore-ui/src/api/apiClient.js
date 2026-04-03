import axios from "axios";

const apiClient=axios.create({
    baseURL: import.meta.env.VITE_API_BASE_URL,
    headers: {
        "Content-Type": "application/json", //The requests that we are trying to send will be in the format of json
        Accept: "application/json", // convey the backend that the UI application is going to accept the response only in a format of json
    },
    timeout: 10000,
});

apiClient.interceptors.request.use(
    async (config) => {
        const jwtToken = localStorage.getItem("jwtToken");
        if (jwtToken) {
            config.headers.Authorization = `Bearer ${jwtToken}`;
        }
        return config;
    },
    (error) => Promise.reject(error)
);

export default apiClient;
