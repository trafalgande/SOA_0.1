import {toast, ToastBar, Toaster} from "react-hot-toast";

export const _Toaster = () => {
    return (
        <Toaster>
            {(t) => (
                <ToastBar
                    toast={t}
                    style={{
                        ...t.style,
                        animation: t.visible ? 'custom-enter 1s ease' : 'custom-exit 1s ease',
                    }}

                />
            )}
        </Toaster>
    )
}

export const notify = (message, status) => {
    switch (status) {
        case 'error': {
            toast.error(message, {
                position: 'top-right'
            })
            break
        }
        case 'yay': {
            toast.success(message, {
                position: 'top-right'
            })
            break
        }
    }
}