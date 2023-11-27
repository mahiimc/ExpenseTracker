import * as Yup from 'yup';


const ExpenseSchema = Yup.object().shape({

    category: Yup.string()
      .required('Category is mandotory'),

    amount: Yup.string()
    .required('Amount is required.'),

    date: Yup.string()
    .required('Date is required.'),
  });

  export default ExpenseSchema