import React, { useRef, useState } from 'react'
import { Button, Stack, TextField, Typography } from '@mui/material'
import Auth from './Auth';
import { NavLink } from 'react-router-dom';
import axios from 'axios';
import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import ExpToast from '../../utils/ExpToast';


function Login() {


  const [inputField, setInputField] = useState({
    username:'',
    password:''
  })


  const inputHandler = (e) => {
    const {name , value } = e.target;  
    setInputField((prevState) => ({
      ...prevState,
      [name]: value,
    }));
  }
  
    function login() {
      const toastId = toast.loading("Loading...",{position: toast.POSITION.TOP_CENTER})
        axios.post('/api/v1/auth/login',inputField)
        .then(response => {
          ExpToast(toastId,response.data.data.message,"success");
          localStorage.setItem('authToken',response.data.data.token);
        })
        .catch(error => {
          ExpToast(toastId,error.response.data.message,"error");
        })
    }
  return (
    <>
    <ToastContainer/>
    <Auth/>
    <Stack direction='column'
       p={2}
       m={2}
       spacing={2}
       sx={
         {
           width: '500px',
           margin: 'auto'
         }
       }
       className='login'
      
      >
        <TextField 
          label='Username or Email'
          size='small'
          name='username'
          onChange={inputHandler}
          value={inputField.username}
          >
        </TextField>
        <TextField 
          name='password'
          label="Password"
          type='password'
          size='small'
          onChange={inputHandler}
          value={inputField.password}
          >
        </TextField>
        <Button 
          variant='contained'
          disableRipple
          onClick={login}
          >Login</Button>
        <Typography>Create Account Here <NavLink to='/register'>Register</NavLink></Typography>
      </Stack>
      </>
  )
}

export default Login