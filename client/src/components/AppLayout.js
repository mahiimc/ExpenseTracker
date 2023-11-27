import { Outlet } from "react-router-dom";
import SideNav from "./SideNav";

  const AppLayout = () => {
   
    return (
      <div className="app-layout">
        <div className="app-layout-side-nav">
          <SideNav/>
        </div>
        <div className="app-layout-outlet">       
           <Outlet/>
        </div>
      </div>
    );
  }


  export default AppLayout