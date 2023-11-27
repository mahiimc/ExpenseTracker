import * as Yup from 'yup';


const RegisterSchema = Yup.object().shape({

    userName: Yup.string()
      .min(3, 'Username must be between 3 and 60 characters length')
      .max(60, 'Username must be between 3 and 60 characters length')
      .required('Username is mandotory'),
    firstName: Yup.string()
      .min(3, 'Firstname must be between 3 and 60 characters length')
      .max(60, 'Firstname must be between 3 and 60 characters length')
      .required('Firstname is required'),

    lastName: Yup.string()
      .min(3, 'Lastname must be between 3 and 60 characters length')
      .max(60, 'Lastname must be between 3 and 60 characters length')
      .required('Lastname is required'),

    email: Yup.string().email('Invalid email').required('Email is required'),
    
    password: Yup.string()
    .min(6,"Password must be between 6 and 16 characters length")
    .max(16,"Password must be between 6 and 16 characters length")
    .required('Password is required.'),

    confirmPassword: Yup.string()
    .oneOf([Yup.ref('password'),null],"Password must match")
  });

  export default RegisterSchema