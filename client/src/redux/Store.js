import AuthSlice from "./slices/AuthSlice";

const { configureStore } = require ("@reduxjs/toolkit");

const store = configureStore(
    {
        reducer: {
            auth: AuthSlice
        }
    }
)
export default store
