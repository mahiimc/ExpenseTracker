import React from "react";
import { UserOutlined, SettingOutlined, PieChartOutlined, LogoutOutlined } from "@ant-design/icons";
import "../css/header.css";
import { Link, useNavigate } from "react-router-dom";
import { useDispatch } from "react-redux";
import { removeCredentils, resetStore } from "../redux/slices/AuthSlice";
import { message } from "antd";
function SideNav() {


  const dispatch = useDispatch();
  const navigate = useNavigate();

  const logoutHandler = () => {
      dispatch(resetStore());
      localStorage.removeItem('authToken');
      navigate('/auth')
      message.success("Logged Out")
  }


  return (
    <div className="side-nav">
      <h3>Expense Tracker</h3>
      <div className="side-nav-links">
        <ul className="exp-head-link-list">
          <li className="exp-head-link-list-item">
            <span className="exp-head-link-list-item-icon">
              <UserOutlined />
            </span>
            <Link to={'profile'}>Profile</Link>
          </li>

          <li className="exp-head-link-list-item">
            <span className="exp-head-link-list-item-icon">
              <PieChartOutlined />
            </span>
            <Link to={'expense'}>Expense</Link>
          </li>

          <li className="exp-head-link-list-item">
            <span className="exp-head-link-list-item-icon">
              <SettingOutlined />
            </span>
            <Link to={'settings'}>Settings</Link>
          </li>

          <li className="exp-head-link-list-item" onClick={logoutHandler}>
            <span className="exp-head-link-list-item-icon">
            <LogoutOutlined />
            </span>
            <Link>Logout</Link>
          </li>
        </ul>
      </div>
    </div>
  );
}

export default SideNav;
