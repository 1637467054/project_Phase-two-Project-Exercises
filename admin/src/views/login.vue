<template>
    <div class="main-container">
        <div class="main-content">
            <div class="row">
                <div class="col-sm-10 col-sm-offset-1">
                    <div class="login-container">
                        <div class="center">
                            <h1>
                                <i class="ace-icon fa fa-leaf green"></i>
                                <span class="red">控台登录</span>
                            </h1>
                            <h4 class="blue" id="id-company-text">&copy; Made In AAA教育</h4>
                        </div>

                        <div class="space-6"></div>

                        <div class="position-relative">
                            <div id="login-box" class="login-box visible widget-box no-border">
                                <div class="widget-body">
                                    <div class="widget-main">
                                        <h4 class="header blue lighter bigger">
                                            <i class="ace-icon fa fa-coffee green"></i>
                                            请输入账号和密码
                                        </h4>

                                        <div class="space-6"></div>

                                        <form>
                                            <fieldset>
                                                <label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<input type="text" class="form-control" placeholder="账号"/>
															<i class="ace-icon fa fa-user"></i>
														</span>
                                                </label>

                                                <label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<input type="password" class="form-control"
                                                                   placeholder="密码"/>
															<i class="ace-icon fa fa-lock"></i>
														</span>
                                                </label>

                                                <div class="space"></div>

                                                <div class="clearfix">
                                                    <label class="inline">
                                                        <input type="checkbox" class="ace"/>
                                                        <span class="lbl">记住密码</span>
                                                    </label>

                                                    <button v-on:click="login()" type="button"
                                                            class="width-35 pull-right btn btn-sm btn-primary">
                                                        <i class="ace-icon fa fa-key"></i>
                                                        <span class="bigger-110">登录</span>
                                                    </button>
                                                </div>

                                                <div class="space-4"></div>
                                            </fieldset>
                                        </form>

                                    </div><!-- /.widget-main -->

                                </div><!-- /.widget-body -->
                            </div><!-- /.login-box -->
                        </div><!-- /.position-relative -->
                    </div>
                </div><!-- /.col -->
            </div><!-- /.row -->
        </div><!-- /.main-content -->
    </div><!-- /.main-container -->
</template>

<script>
    export default {
        name: 'login',
        mounted: function () {
            /*
            vue主键声明周期中有很多钩子函数，mounted和created都是vue的初始函数
            参考：https://cn.vuejs.org/v2/guide/instance.html
            created执行更早,在界面渲染之前执行,常用于加载初始化数据。mounted在
            界面渲染之后执行，常用于对界面元素的初始化操作
            */
            // $('body').removeClass('login-layout light-login');
            $('body').attr('class', 'login-layout light-login');
        },
        methods: {
            //vue的html中的方法都是在这里实现
            //添加点击事件方法：在标签中添加   v-on:click="login()" 或 @click="login()"   属性
            login() {
                //这里时用来实现上面绑定的方法的
                this.$router.push("/welcome")
            }
        }
    }
</script>
