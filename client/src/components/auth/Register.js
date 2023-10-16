import React, { useState } from 'react'
import {Button, Stack, TextField, Typography } from '@mui/material'
import Auth from './Auth'
import { NavLink } from 'react-router-dom'
import axios from 'axios'
import { toast } from 'react-toastify'
function Register() {

    const [inputField, setInputField] = useState({
      userName:'',
      firstName: '',
      lastName:'',
      email:'',
      mobileNum:'',
      profileUrl:'',
      password:''
    })

    const inputHandler = (e) => {
      const {name , value } = e.target;  
      setInputField((prevState) => ({
        ...prevState,
        [name]: value,
      }));
    }

    function register() {
        axios.post('/api/v1/auth/register',inputField)
        .then((response) => {
          console.log(response);
          toast.success(response.data.message);
        })
        .catch(error => {
          console.log(error)
          const data = error.response.data;
          toast.error(data.message);
        })
    }
  return (
    <>
    <Auth/>
    <Stack
        p={2}
        m={2}
        spacing={2}
        sx={
        {
            width: '500px',
            margin: 'auto'
        }
        }
        className='register'
  >

<TextField 
      label="Username" 
      name='userName'
      variant="outlined"
      size='small'
      onChange={inputHandler}
      value={inputField.userName}
      >
      
    </TextField>
    <TextField 
      label="First Name" 
      name='firstName'
      variant="outlined"
      size='small'
      onChange={inputHandler}
      value={inputField.firstName}
      >
      
    </TextField>
    <TextField 
      name='lastName'
      label="Last Name" 
      variant="outlined" 
      size='small'
      onChange={inputHandler}
      value={inputField.lastName}
      >


    </TextField>
    <TextField 
      label="Email"
      name='email'
      variant="outlined"
      size='small'
      onChange={inputHandler}
      value={inputField.email}
      >

      </TextField>

      <TextField 
      label="Password"
      type='password'
      name='password'
      variant="outlined"
      size='small'
      onChange={inputHandler}
      value={inputField.password}
      >

      </TextField>
    <TextField 
      label="Mobile" 
      variant="outlined"
      name='mobileNum'
      size='small'
      onChange={inputHandler}
      value={inputField.mobile}
      >
      </TextField>
      <Stack direction='column' spacing={2}>
        <Button 
          variant='contained'
          disableRipple
          onClick={register}
        >Register</Button>
        <Typography>Already Registered , Login Here <NavLink to='/login'>Login</NavLink> </Typography>
      </Stack>
  </Stack>
  </>
  )
  
}

export default Register