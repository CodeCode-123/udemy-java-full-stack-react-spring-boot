import React, {useEffect} from 'react';
import { Navigate, Outlet, useLocation } from 'react-router-dom';
import { useAuth } from "../store/auth-context";

export default function ProtectedRoute() {
  const { isAuthenticated } = useAuth();
  const location = useLocation();

  useEffect(() => {
    // try to avoid redirect to the My Profile page after changing the email and login
    // try to avoid redirect to the payment successful page after logout and login again
    const skipRedirect = sessionStorage.getItem("skipRedirectPath") === "true";
    if (!isAuthenticated && location.pathname !== "/login" && !skipRedirect) {
      sessionStorage.setItem("redirectPath", location.pathname);
    }
  }, [isAuthenticated, location.pathname]);
  
  return isAuthenticated? <Outlet/>: <Navigate to="/login"/>;
}
