import { Button, Divider, Input, message } from 'antd'
import React from 'react'
import { UserOutlined, LockOutlined, GoogleOutlined, FacebookOutlined, GithubOutlined,EyeInvisibleOutlined, EyeTwoTone } from '@ant-design/icons';
import { Link, useNavigate } from 'react-router-dom';
import { Form, Formik } from 'formik';
import LoginSchema  from '../validation/LoginSchema'
import { findUserByUsername, login } from '../api/Api'
import { useDispatch } from 'react-redux';
import { addCredential, removeCredentils, addUser } from '../redux/slices/AuthSlice';


const Login = () => {
    
      const dispatch = useDispatch();
      const navigate = useNavigate();

      const initialValues= {
            username: '',
            password: ''
        }

    const loginHandler = (props) => {
         login(props).then(data => {
            localStorage.removeItem('authToken');
            localStorage.setItem('authToken',data?.data?.token);
            message.success(data?.data?.message);
            dispatch(addCredential(data?.data));
            navigate("/");
            findUserByUsername().then(response => {
                dispatch(addUser(response?.data))
            })
            .catch(error => {
               message.error(error?.response?.data?.message);
            });
         })
         .catch(error=> {
            dispatch(removeCredentils());
            message.error(error?.response?.data?.message);
         })
       
    }
    
  return (
    <Formik
        initialValues={initialValues}
        onSubmit={loginHandler}
        validationSchema={LoginSchema}
        
    >
        {(props) => {
                const { username, password } = props.values;
                return (
                <div className='login-content-right'>
                  <Form className='login-content-right-form'>
                        <Input
                            name='username'
                            size='large size'
                            placeholder='Username or Email'
                            prefix = {<UserOutlined/>}
                            onChange={props.handleChange}
                            onBlur={props.handleBlur}
                            value={username}
                            status={(props.errors.username && props.touched.username) ?'error':''}
                            required
                         />
                    <span className='error'>{(props.errors.username && props.touched.username) ? props.errors.username :''}</span>
                        <Input.Password
                            name='password'
                            size='large size'
                            placeholder='Password'
                            prefix = {<LockOutlined/>}
                            onChange={props.handleChange}
                            onBlur={props.handleBlur}
                            value={password}
                            type='password'
                            status={(props.errors.password && props.touched.password) ?'error':''}
                            required
                            iconRender={
                                (visible) => (visible ? <EyeTwoTone /> : <EyeInvisibleOutlined />)
                            }
                         />
                        <span className='error'>{(props.errors.password && props.touched.password) ? props.errors.password :''}</span>
                        <Button type="primary" htmlType='submit' block>Login</Button>
                        <div className='form-links'>
                            <p>Do not have account? <Link to={'register'} >Register</Link></p>
                            <p><Link> Forgot Password?</Link> </p>
                        </div>
                    <Divider> OR </Divider>
                    <div className='oauth-links'>
                        <GoogleOutlined   className='oauth-link google'/>
                        <FacebookOutlined className='oauth-link blue'/>
                        <GithubOutlined   className='oauth-link'/>
                    </div>
                  </Form>
                  </div>
                );
              }}
    </Formik>
  )
}

export default Login
