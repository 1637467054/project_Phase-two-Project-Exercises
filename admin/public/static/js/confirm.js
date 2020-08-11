Confirm = {
    //               标签内容,提示信息,执行的方法
    success:function (title,message,callback) {
        this.show(title,message,callback,"success");
    },
    error:function (title,message,callback) {
        this.show(title,message,callback,"error");
    },
    warning:function (title,message,callback) {
        this.show(title,message,callback,"warning");
    },
    info:function (title,message,callback) {
        this.show(title,message,callback,"info");
    },
    question:function (title,message,callback) {
        this.show(title,message,callback,"question");
    },
    show: function (title,message, callback,icon) {
        Swal.fire({
            title: '确定'+title+'吗？',
            text: message,//"你将无法恢复它！",
            icon: icon,
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            confirmButtonText: '确定'+title+'！',
            cancelButtonText: '取消'+title+'！',
            cancelButtonClass: 'btn btn-danger'
        }).then((result) => {
            if (result.value) {
                if (callback!=null) {
                    callback();
                }
            }
        });
    }
};