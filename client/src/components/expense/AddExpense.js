import { Input, Select, Button , DatePicker, Typography, Form as AntdForm, message, Modal } from 'antd';
import {  Formik , Form, Field   } from 'formik';
import React, { useEffect, useState } from 'react'
import { saveExpense, findAllCategories, saveCategory } from '../../api/Api';
import '../../css/expense.css'
import { useDispatch, useSelector } from 'react-redux';
import { addExpense, setCategories, addCategory } from '../../redux/slices/AuthSlice';
import ExpenseSchema from '../../validation/ExpenseSchema';
import { PlusOutlined } from '@ant-design/icons';
const AddExpense = () => {

    const dispatch = useDispatch();

    const categories = useSelector(state => state.auth.categories);
    const [isModelOpen , setIsModelOpen] = useState(false);
    const [category,setCategory] = useState('');

    const initialValues  = {
        category: '',
        date: '',
        description: '',
        amount: ''
      }

      const submitHandler = (props,{resetForm}) => {
        saveExpense(props).then(response => {
          message.success(response?.message);
          const data = response?.data;
          dispatch(addExpense(data));
          resetForm();
        })
        .catch(error => {
          console.log(error.response);
        })
    }

    useEffect(()=> {
      if(categories.length === 0) {
        findAllCategories().then(response => {  
            setCategories(response?.data)
            dispatch(setCategories(response?.data));
        })
        .catch(error => {
          message.error(error.response.message);
        })
      }
      else {
        setCategories(categories);
      }
    },[])

    const modalHandler = () => {
      setIsModelOpen(true);
    }

    const saveCat = () => {
        if(category !== '') {
          saveCategory(category)
          .then(response => {
            const data = response?.data;
            dispatch(addCategory(data));
            setIsModelOpen(false);
            message.success(response?.message);
          })
          .catch(error=> {
            setIsModelOpen(false);
            message.error(error?.response?.data?.message);
          });
           
        }
    }

  return (
    <div className='new-expense'>
    <Formik
      initialValues = {initialValues}
      onSubmit={submitHandler}
      validationSchema={ExpenseSchema}
  >
    {(props) => {
            const {description, amount } = props.values;
            return (
                <Form className='new-expense-form'>
                        
                        <div className='category'>
                              <div className='category-select'>
                              <Select
                                  style={{width:'150px'}}
                                  onChange={(selectedOption) => props.setFieldValue("category",selectedOption)}
                                  onBlur={() => props.setFieldTouched("category")}
                                  placeholder='Category'
                                  name='category'
                                  status={(props.errors.category && props.touched.category) ?'error':''}
                                  allowClear
                              >
                              {categories.map(category => (
                                  <Select.Option key={category.name} value={category.name}>
                                    {category.name}
                                  </Select.Option>
                                ))}
                                </Select>
                            <Modal 
                              open={isModelOpen} 
                              onOk={saveCat} 
                              onCancel={()=>{setIsModelOpen(false)}}
                              >
                            <Input
                              name='category'
                              size='large size'
                              placeholder='Category'
                              onChange={(e)=>setCategory(e.target.value)}
                              value={category}
                              required
                              style={{
                                // margin: '1rem',
                                width: '50%'
                              }}
                           />
                            </Modal>
                          <span className='error'>{(props.errors.category && props.touched.category) ? props.errors.category :''}</span>
                          </div>
                          <div>
                            <Button type='primary' icon={<PlusOutlined/>} onClick={modalHandler}> New Category</Button>
                          </div>
                     </div>

                    <Input.TextArea 
                        name='description'
                        size='large size'
                        placeholder='Description'
                        onChange={props.handleChange}
                        onBlur={props.handleBlur}
                        value={description}
                        style={{maxHeight:'120px'}}
                    />
                   
                   <Field name="date" className='date-picker'>
                    {({ field }) => (
                      <DatePicker
                       {...field}
                        onChange={(date) => props.setFieldValue('date', date)}
                        status={(props.errors.date && props.touched.date) ?'error':''}
                      />
                    )}
                  </Field>

                  <span className='error'>{(props.errors.date && props.touched.date) ? props.errors.amount:''}</span>
                  
                 
                    <Input
                      name='amount'
                      size='large size'
                      placeholder='Amount'
                      onChange={props.handleChange}
                      onBlur={props.handleBlur}
                      value={amount}
                      status={(props.errors.amount && props.touched.amount) ?'error':''}
                      required
                    />
                     <span className='error'>{(props.errors.amount && props.touched.amount) ? props.errors.amount:''}</span>
                    <Button type="primary" htmlType='submit' block>Add Expense</Button>
                </Form>
            );
            }}
    </Formik>
</div>
  )
}

export default AddExpense
