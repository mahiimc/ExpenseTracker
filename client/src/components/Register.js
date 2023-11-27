import { Button, Divider, Input, message } from 'antd'
import React from 'react'
import { UserOutlined, LockOutlined, GoogleOutlined, FacebookOutlined, GithubOutlined, MailOutlined, EyeInvisibleOutlined,EyeTwoTone } from '@ant-design/icons';
import { Link } from 'react-router-dom';
import '../css/login.css';
import { Formik, Form } from 'formik';
import RegisterSchema from '../validation/RegisterSchema';
import { register } from '../api/Api';

const Register = () => {


    const registerHandler  = (props) => {
        delete props.confirmPassword;
        console.log(props)
       register(props).then(response => {
            message.success(response.message);
       })
       .catch(error => {
            message.error(error?.response?.data?.message);
       })
    }

    const resetHandler = () => {
        console.log("Reset");
    }

    const initialValues= {
        userName: '',
        firstName: '',
        lastName: '',
        email: '',
        password: '',
        confirmPassword:''
    }


  return (

    <Formik
        initialValues={initialValues}
        onSubmit={registerHandler}
        validationSchema={RegisterSchema}
        onReset={resetHandler}
        
    >
    {(props) => {
            const { userName, firstName, lastName, email, password, confirmPassword } = props.values;
            return (


            <div className='login-content-right'>
                <Form className='login-content-right-form'>
                    <Input
                        name='userName'
                        size='large size'
                        placeholder='Username'
                        prefix = {<UserOutlined/>}
                        onChange={props.handleChange}
                        onBlur={props.handleBlur}
                        value={userName}
                        status={(props.errors.userName && props.touched.userName) ?'error':''}
                        required
                    />
                    <span className='error'>{(props.errors.userName && props.touched.userName) ? props.errors.userName :''}</span>
                    <Input
                        name='email'
                        size='large size'
                        placeholder='Email'
                        prefix = {<MailOutlined />}
                        onChange={props.handleChange}
                        onBlur={props.handleBlur}
                        value={email}
                        status={(props.errors.email && props.touched.email) ?'error':''}
                        required
                    />
                    <span className='error'>{(props.errors.email && props.touched.email) ? props.errors.email :''}</span>

                    <Input
                        name='firstName'
                        size='large size'
                        placeholder='Firstname'
                        prefix={<UserOutlined />}
                        onChange={props.handleChange}
                        onBlur={props.handleBlur}
                        value={firstName}
                        status={(props.errors.firstName && props.touched.firstName) ?'error':''}
                        required
                    />

                <span className='error'>{(props.errors.firstName && props.touched.firstName) ? props.errors.firstName :''}</span>


                    <Input
                        name='lastName'
                        size='large size'
                        placeholder='Lastname'
                        prefix={<UserOutlined />}
                        onChange={props.handleChange}
                        onBlur={props.handleBlur}
                        value={lastName}
                        status={(props.errors.lastName && props.touched.lastName) ?'error':''}
                        required
                    />
                <span className='error'>{(props.errors.lastName && props.touched.lastName) ? props.errors.lastName :''}</span>

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

                    <Input.Password
                        name='confirmPassword'
                        size='large size'
                        type='password'
                        placeholder='Confirm Password'
                        prefix = {<LockOutlined/>}
                        onChange={props.handleChange}
                        onBlur={props.handleBlur}
                        value={confirmPassword}
                        status={(props.errors.confirmPassword && props.touched.confirmPassword) ?'error':''}
                        iconRender={
                            (visible) => (visible ? <EyeTwoTone /> : <EyeInvisibleOutlined />)
                        }
                    />
                    <span className='error'>{(props.errors.confirmPassword && props.touched.confirmPassword) ? props.errors.confirmPassword :''}</span>
                    <Button type="primary" htmlType='submit' block>Register</Button>
                    <div className='form-links'>
                        <p>Do not have account? <Link to={'../'} >Login</Link></p>
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

export default Register
