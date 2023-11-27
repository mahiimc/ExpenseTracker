import { createBrowserRouter } from "react-router-dom";
import NotFound from "../utils/NotFound";
import Login from "../components/Login";
import Home from "../components/Home";
import Register from "../components/Register";
import Auth from "../components/Auth";
import ProtectedRoutes from "./ProtectedRoutes";
import Profile from "../components/Profile";
import ListExpense from "../components/expense/ListExpense";
import Setting from "../components/Setting";

const Routes = createBrowserRouter([
    {
        
        element: <ProtectedRoutes/>,
        errorElement:<NotFound/>,
        children: [
            {
                path: '/',
                element: <Home/>
            },
            {
                path: 'profile',
                element: <Profile/>
            },
            {
                path: 'expense',
                element: <ListExpense/>,
                children:[
                    {
                        path:'add',
                        element: <NotFound/>
                    }
                ]
               
            },
            {
                path: 'settings',
                element: <Setting/>
            }
        ]
    },
    {
        path: '/auth',
        element: <Auth/>,
        children: [
            {
                path:'',
                element: <Login/>
            },
            {
                path: 'register',
                element: <Register/>
            }
        ]
    },
]
);
export default Routes