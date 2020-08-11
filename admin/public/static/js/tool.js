Tool = {
    /**
     * 空校验null或""都返回true
     * @param obj
     * @returns {boolean}
     */
    isEmpty: function (obj) {
        if ((typeof obj == 'string')) {
            //.replace是用来把obj字符串中的一些东西替换为另外的一些东西,其中g是全局替换,\s是查找空白字符串
            //只要出现空白就匹配,+号表示匹配前面的子表达式一次或多次(大于等于1次).
            return !obj || obj.replace(/\s+/g, "") == "";
        } else {
            //JSON.stringify方法是可以用来把对象或列表转换为字符串的方法,这里转换主要是为了检测对象是否为空
            //obj.length主要是为了检查列表的长度是否为0,以此来检测列表是否为空
            return (!obj || JSON.stringify(obj) === "{}" || obj.length === 0)
        }
    },
    /**
     * 非空校验
     * @param obj
     * @returns {boolean}
     */
    isNotEmpty: function (obj) {
        return !this.isEmpty(obj);
    },
    /**
     * 长度校验
     * @param str
     * @param min
     * @param max
     * @returns {boolean}
     */
    isLength: function (str, min, max) {
        //在IE8及以下版本的浏览器不支持js原生的trim,所以用jQuery的$.trim()方法更容易向下兼容
        //trim方法是去掉字符串前后的空格而不改变字符串的本身内容
        return $.trim(str).length >= min && $.trim(str).length <= max;
    }
};