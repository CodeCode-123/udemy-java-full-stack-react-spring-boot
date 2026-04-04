import axios from "axios";
import Cookies from "js-cookie";

const apiClient=axios.create({
    baseURL: import.meta.env.VITE_API_BASE_URL,
    headers: {
        "Content-Type": "application/json", //The requests that we are trying to send will be in the format of json
        Accept: "application/json", // convey the backend that the UI application is going to accept the response only in a format of json
    },
    timeout: 10000,
    withCredentials: true, // automatically attach all the cookies to the request that is being sent to the backend server
});

apiClient.interceptors.request.use(
    async (config) => {
        const jwtToken = localStorage.getItem("jwtToken");
        if (jwtToken) {
            config.headers.Authorization = `Bearer ${jwtToken}`;
        }
        // Only fetch CSRF token for non-safe methods
        const safeMethods = ["GET", "HEAD", "OPTIONS"];
        if (!safeMethods.includes(config.method.toUpperCase())) {
            let csrfToken = Cookies.get("XSRF-TOKEN");
            // if csrfToken is a null value, make a request to the backend to generate the csrfToken
            if (!csrfToken) {
                await axios.get(`${import.meta.env.VITE_API_BASE_URL}/csrf-token`, {
                    withCredentials: true,
                });
                csrfToken = Cookies.get("XSRF-TOKEN");
                if (!csrfToken) {
                    throw new Error("Failed to retrieve CSRF token from cookies");
                }
            }
            config.headers["X-XSRF-TOKEN"] = csrfToken;
        }
        return config;
    },
    (error) => Promise.reject(error)
);

export default apiClient;
