import * as Yup from 'yup';


const LoginSchema = Yup.object().shape({

    username: Yup.string()
      .min(3, 'Username must be between 3 and 60 characters length')
      .max(60, 'Username must be between 3 and 60 characters length')
      .required('Username is mandotory'),

    password: Yup.string()
    .min(6,"Password must be between 6 and 16 characters length")
    .max(16,"Password must be between 6 and 16 characters length")
    .required('Password is required.'),
  });

  export default LoginSchema