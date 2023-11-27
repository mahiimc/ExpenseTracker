import  ExpAxios  from '../common/ExpAxios'
import {API_URLS} from '../config'

export async function login(body) {
    const response = await ExpAxios.post(API_URLS.login, body);
    return response.data;
}

export async function register(body) {
    const response = await ExpAxios.post(API_URLS.register,body);
    return response.data;
}

export async function findUserByUsername() {
    const response = await ExpAxios.get(API_URLS.profile);
   return response.data;
}

export async function findExpensesByUsername() {
    const response = await ExpAxios.get(API_URLS.expense);
    return response.data;
}

export async function saveExpense(body) {
    const response = await ExpAxios.post(API_URLS.expense,body);
    return response.data;
}

export async function findCategoryWiseExpenses() {
    const response = await ExpAxios.get(API_URLS.chart);
    return response.data;
}
export async function findAllCategories() {
    const response = await ExpAxios.get(API_URLS.categories+"/");
    return response.data;
}
export async function saveCategory(category) {
    const response = await ExpAxios.post(API_URLS.categories+'/'+category);
    return response.data;
}