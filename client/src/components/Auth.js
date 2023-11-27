import React from 'react'
import '../css/login.css'
import { Outlet } from 'react-router-dom'
import { Layout } from 'antd'

const Auth = () => {
    const { Content } = Layout
  return (
    <Layout className='login-layout'>
        <Content className='login-content'>
            <div className='login-content-left'>
                <h3 className='title'>Expense Tracker</h3>
            </div>
            <Outlet/>
        </Content>
    </Layout>
  )
}

export default Auth
