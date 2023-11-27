
import React from 'react'
import { useSelector } from 'react-redux'

const Home = () => {

  const { loggedInUser } = useSelector(state => state.auth)

  return (
    <div>
        <h1>Welcome home {loggedInUser} </h1>

    </div>
  )
}

export default Home
