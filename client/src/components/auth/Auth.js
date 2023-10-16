import { Button, Stack } from '@mui/material'

import { useNavigate } from 'react-router-dom'

function Auth() {

  const navigate = useNavigate()
 
  return (
    <>
    <Stack 
      direction="row" 
      m={2} 
      p={2} 
      spacing={2}
      justifyContent='center'
      alignContent='center'
      sx={
        {
          marginLeft: 'auto',
          marginRight: 'auto',
          marginTop: '10px',
          width: '50%'
        }
      }
      >
        <Button 
            variant='outlined'
            disableRipple
            sx={
              {
                minWidth: 250
              }
            }
            name='login'
            onClick={() => navigate('/login')}
            > 
            Login 
        </Button>
        <Button 
            variant='contained'
            disableRipple
            sx = {
              {
                minWidth: 250
              }
            }
            onClick={() => navigate('/register')}
            login='register'
            > 
            Register 
        </Button>
    </Stack>    
    </>
  )
}

export default Auth