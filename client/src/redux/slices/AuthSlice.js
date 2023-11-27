import { decodeToken } from "react-jwt";

const { createSlice } = require("@reduxjs/toolkit");

const authSlice = createSlice(
    {
        name: 'auth',
        initialState: {
            loggedInUser: '',
            isAuthenticated: '',
            token: '',
            user:{},
            expenses: [],
            categoryWiseData: [],
            categories: [],
        },
        reducers: {
            addCredential: (state,action) => {
                const token = action.payload.token;
                const decoded = decodeToken(token);
                state.loggedInUser = decoded?.sub;
                state.isAuthenticated = true;
                state.token = token;
            },
            addUser: (state,action) => {
                state.user = action.payload
            },
            removeCredentils: (state) => {
                state.loggedInUser = '';
                state.isAuthenticated=false;
                state.token = '';
            },
            resetStore: (state) => {
                state.loggedInUser = '';
                state.isAuthenticated=false;
                state.token = '';
                state.user = {};
                state.expenses= [];
                state.categoryWiseData= [];
                state.categories=[];
            },
            addExpenseList : (state,action) => {
                state.expenses = action.payload;
            },
            addExpense: (state,action) => {
                state.expenses.push(action.payload);
            },
            setCategoryWiseData: (state,action) => {
                state.categoryWiseData = action.payload;
            },
            setCategories : (state,action) => {
                state.categories = action.payload;
            },
            addCategory : (state,action) => {
                state.categories.push(action.payload);
            },

        }
    }
)

export const  { addCredential, removeCredentils, addUser, addExpenseList,
     addExpense, setCategoryWiseData, resetStore, setCategories, addCategory }  = authSlice.actions;

export default authSlice.reducer