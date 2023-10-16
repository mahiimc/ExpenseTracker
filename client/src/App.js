import './App.css';
import Main from './components/main/Main';
import { BrowserRouter, Route, Routes } from 'react-router-dom';
import Register from './components/auth/Register';
import Login from './components/auth/Login';
import NotFound from './utils/NotFound';

function App() {
  return (
    <BrowserRouter>
      <Routes>
          <Route index element={<Main/>} />
          <Route path='register' element={<Register/>} />
          <Route path='login' element={<Login/>} />
          <Route path='*' element={<NotFound/>} />
      </Routes>
    </BrowserRouter>
  );
}

export default App;
