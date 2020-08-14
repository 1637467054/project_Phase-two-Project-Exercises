Validator={
    require:function (value,text) {
        //为空返回false
        if (Tool.isEmpty(value)){
            Toast.warning(text+"不能为空");
            return false;
        } else {
            return true;
        }
    },
    length:function (value,text,min,max) {
        //如果只为空我们就直接不做校验了
        if (Tool.isEmpty(value)){
            return true;
        }
        // console.log(value+":"+text+":"+min+":"+max);
        if (!Tool.isLength(value,min,max)){
            Toast.warning(text+"长度必须为"+min+"~"+max+"位");
            return false;
        } else {
            return true;
        }
    }
};