import { createBrowserRouter } from "react-router-dom";
import NotFound from "../utils/NotFound";
import App from "../App";

const Routes = createBrowserRouter([
    {
        path: '/',
        element: <App/>,
        errorElement:<NotFound/>,
    }
    
]
);
export default Routes