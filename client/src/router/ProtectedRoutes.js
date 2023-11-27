import { useJwt } from "react-jwt";
import { useDispatch } from "react-redux";
import { Navigate } from "react-router-dom";
import { addCredential } from "../redux/slices/AuthSlice";
import AppLayout from "../components/AppLayout";


const ProtectedRoutes = () => {
    const dispatch = useDispatch();
    const token = localStorage.getItem('authToken');
    const {isExpired} = useJwt(token)
    
    if(token && !isExpired) {
        dispatch(addCredential({token:token}));
        return <AppLayout/>
    }
    else {
        return <Navigate to={'/auth'} replace/>
    }
}

export default ProtectedRoutes;