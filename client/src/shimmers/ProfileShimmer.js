import React from 'react'
import '../css/profile.css'

const ProfileShimmer = () => {
  return (
    <div className='profile'>
        <div className='profile-pic'>
        </div>
        {Array.from({length:10}).map((_,index)=>(
          <div className="line" key={index}></div>
        ))}
    </div>
  )
}

export default ProfileShimmer
