Toast={
    success:function (message) {
        Swal.fire({
            position: 'enter',
            icon: 'success',
            title: message,
            showConfirmButton: false,
            timer: 1500
        })
    },
    error:function (message) {
        Swal.fire({
            position: 'top-end',
            icon: 'error',
            title: message,
            showConfirmButton: false,
            timer: 1500
        })
    },
    warning:function (message) {
        Swal.fire({
            position: 'top-end',
            icon: 'warning',
            title: message,
            showConfirmButton: false,
            timer: 1500
        })
    },
    info:function (message) {
        Swal.fire({
            position: 'top-end',
            icon: 'info',
            title: message,
            showConfirmButton: false,
            timer: 1500
        })
    },
    question:function (message) {
        Swal.fire({
            position: 'top-end',
            icon: 'question',
            title: message,
            showConfirmButton: false,
            timer: 1500
        })
    }

};