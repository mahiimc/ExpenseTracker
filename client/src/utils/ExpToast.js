import { toast } from "react-toastify";

function ExpToast(toastId, message,severity) {
    switch(severity) {
        case 'error':
            toast.update(toastId,{
                render: message,
                type:severity,
                isLoading: false,
                autoClose: true,
                closeButton: true,
                closeOnClick: true,
                hideProgressBar: false,
                pauseOnHover: false,
                position: toast.POSITION.TOP_CENTER
             });
             break;
        case 'success':
            toast.update(toastId,{
                render: message,
                type:severity,
                isLoading: false,
                autoClose: true,
                closeButton: true,
                closeOnClick: true,
                hideProgressBar: false,
                pauseOnHover: false,
                position: toast.POSITION.TOP_CENTER
             });
             break;
        default:
            console.log("Not yet implemented.")
    }
}

export default ExpToast
