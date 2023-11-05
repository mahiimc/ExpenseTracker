import React from 'react'
import { Link, Navigate, useNavigate } from 'react-router-dom'

function NotFound() {

  const navigate = useNavigate();
  return (
    <h1>
        404 - Not Found.
    </h1>
  )
}

export default NotFound