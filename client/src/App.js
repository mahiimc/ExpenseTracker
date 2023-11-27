import { Outlet } from 'react-router-dom';
import './App.css';



function App() {
  return (
    <div className='root-container'>
      <Outlet/>
    </div>
  );
}

export default App;
