import { Button } from 'antd';
import React from 'react'

const ExpButton = (props) => {

const { ...restProps } =  props;
  return (
   <Button
        {...restProps}
    >
    {props.children}
   </Button>
  )
}

export default ExpButton
