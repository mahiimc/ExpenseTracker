import { useDispatch, useSelector } from 'react-redux';
import '../css/profile.css'
import ProfileShimmer from '../shimmers/ProfileShimmer';
import { Button, message } from 'antd';
import { useEffect } from 'react';
import { findUserByUsername } from '../api/Api';
import { addUser } from '../redux/slices/AuthSlice';

const Profile = () => {

 const user = useSelector(state => state.auth.user);
 const loggedInUser = useSelector(state => state.auth.loggedInUser);
 const dispatch = useDispatch();

 useEffect(()=> {
    if(!user || Object.keys(user).length === 0) {
        findUserByUsername(loggedInUser).then(response => {
                dispatch(addUser(response?.data));
            }
        )
        .catch(error => {
            message.error(error?.response?.message);
        })
    }
 },[loggedInUser]);



  return !user?<ProfileShimmer/> :  (
    <div className='profile'>

        <div className='profile-pic'>
           
        </div>
        <div className='profile-details'>
            <div className='profile-details-item'>
                <p>First Name</p>
                <p>{user?.firstName}</p>
            </div>

            <div className='profile-details-item'>
                <p>Last Name</p>
                <p>{user?.lastName}</p>
            </div>

            <div className='profile-details-item'>
                <p>Email</p>
                <p>{user?.email}</p>
            </div>

            <div className='profile-details-item'>
                <p>Mobile Num</p>
                <p>{user?.mobileNum?user?.mobileNum:"+91 XXX XXX XXXX"}</p>
            </div>

            <div>
                <Button type='primary' >Edit Profile</Button>
                <Button danger> Deactive Profile </Button>
            </div>
          
        </div>
     
    </div>
  )
}

export default Profile
