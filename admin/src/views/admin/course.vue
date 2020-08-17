<template>
    <div>
        <p>
            <!--添加-->
            <button v-on:click="add()" class="btn btn-white btn-default btn-round">
                <i class="ace-icon fa fa-edit"></i>
                新增
            </button>&nbsp;
            <!--刷新-->
            <button v-on:click="list(1)" class="btn btn-white btn-default btn-round">
                <i class="ace-icon fa fa-refresh"></i>
                刷新
            </button>
        </p>
        <!--pagination是子组件,下面是用来引入子组件的方法，需要先引入-->
        <!--ref就是相当于起了一个别名，然后就可以在js中this.$refs.别名进行调用或修改属性了-->
        <pagination ref="pagination" v-bind:list="list" v-bind:itemCount="8"></pagination>

        <table id="simple-table" class="table  table-bordered table-hover">
            <thead>
            <tr>
                <th class="center">id</th>
                <th class="center">名称</th>
                <th class="center">概述</th>
                <th class="center">时长</th>
                <th class="center">价格(元)</th>
                <th class="center">封面</th>
                <th class="center">级别</th>
                <th class="center">收费</th>
                <th class="center">状态</th>
                <th class="center">报名数</th>
                <th class="center">顺序</th>
                <th class="center">操作</th>
            </tr>
            </thead>

            <tbody>
            <tr v-for="course in courses">

                <td class="center">{{course.id}}</td>

                <td class="center">{{course.name}}</td>

                <td class="center">{{course.summary}}</td>

                <td class="center">{{course.time}}</td>

                <td class="center">{{course.price}}</td>

                <td class="center">{{course.image}}</td>

                <td class="center">{{COURSE_LEVEL|optionKV(course.level)}}</td>

                <td class="center">{{COURSE_CHARGE|optionKV(course.charge)}}</td>

                <td class="center">{{COURSE_STATUS|optionKV(course.status)}}</td>

                <td class="center">{{course.enroll}}</td>

                <td class="center">{{course.sort}}</td>

                <td class="center">
                    <div class="hidden-sm hidden-xs btn-group">
                        <button v-on:click="edit(course)" class="btn btn-xs btn-info">
                            <i class="ace-icon fa fa-pencil bigger-120"></i>
                        </button>

                        <button v-on:click="del(course.id)" class="btn btn-xs btn-danger">
                            <i class="ace-icon fa fa-trash-o bigger-120"></i>
                        </button>
                    </div>

                    <!--当屏幕显示为小屏的时候显示下面按钮,在这个工程用不到-->
                    <div class="hidden-md hidden-lg">
                        <div class="inline pos-rel">
                            <button class="btn btn-minier btn-primary dropdown-toggle" data-toggle="dropdown"
                                    data-position="auto">
                                <i class="ace-icon fa fa-cog icon-only bigger-110"></i>
                            </button>

                            <ul class="dropdown-menu dropdown-only-icon dropdown-yellow dropdown-menu-right dropdown-caret dropdown-close">
                                <li>
                                    <a href="#" class="tooltip-info" data-rel="tooltip" title="View">
                                        <span class="blue">
                                            <i class="ace-icon fa fa-search-plus bigger-120"></i>
                                        </span>
                                    </a>
                                </li>

                                <li>
                                    <a href="#" class="tooltip-success" data-rel="tooltip" title="Edit">
                                        <span class="green">
                                            <i class="ace-icon fa fa-pencil-square-o bigger-120"></i>
                                        </span>
                                    </a>
                                </li>

                                <li>
                                    <a href="#" class="tooltip-error" data-rel="tooltip" title="Delete">
                                        <span class="red">
                                            <i class="ace-icon fa fa-trash-o bigger-120"></i>
                                        </span>
                                    </a>
                                </li>
                            </ul>
                        </div>
                    </div>
                </td>
            </tr>
            </tbody>
        </table>

        <!--下面是模态框注册框-->
        <div id="form-modal" class="modal fade" tabindex="-1" role="dialog">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                                aria-hidden="true">&times;</span></button>
                        <h4 class="modal-title">表单</h4>
                    </div>
                    <div class="modal-body">
                        <!--form表单代码-->
                        <form class="form-horizontal">

                            <div class="form-group">
                                <label class="col-sm-2 control-label">名称</label>
                                <div class="col-sm-10">
                                    <input v-model="course.name" class="form-control" placeholder="名称">
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-sm-2 control-label">概述</label>
                                <div class="col-sm-10">
                                    <input v-model="course.summary" class="form-control" placeholder="概述">
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-sm-2 control-label">时长</label>
                                <div class="col-sm-10">
                                    <input v-model="course.time" class="form-control" placeholder="时长">
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-sm-2 control-label">价格(元)</label>
                                <div class="col-sm-10">
                                    <input v-model="course.price" class="form-control" placeholder="价格(元)">
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-sm-2 control-label">封面</label>
                                <div class="col-sm-10">
                                    <input v-model="course.image" class="form-control" placeholder="封面">
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-sm-2 control-label">级别</label>
                                <div class="col-sm-10">
                                    <select v-model="course.level" class="form-control">
                                        <option v-for="o in COURSE_LEVEL" v-bind:value="o.key">
                                            {{o.value}}
                                        </option>
                                    </select>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-sm-2 control-label">收费</label>
                                <div class="col-sm-10">
                                    <select v-model="course.charge" class="form-control">
                                        <option v-for="o in COURSE_CHARGE" v-bind:value="o.key">
                                            {{o.value}}
                                        </option>
                                    </select>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-sm-2 control-label">状态</label>
                                <div class="col-sm-10">
                                    <select v-model="course.status" class="form-control">
                                        <option v-for="o in COURSE_STATUS" v-bind:value="o.key">
                                            {{o.value}}
                                        </option>
                                    </select>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-sm-2 control-label">报名数</label>
                                <div class="col-sm-10">
                                    <input v-model="course.enroll" class="form-control" placeholder="报名数">
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-sm-2 control-label">顺序</label>
                                <div class="col-sm-10">
                                    <input v-model="course.sort" class="form-control" placeholder="顺序">
                                </div>
                            </div>
                        </form>

                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                        <button type="button" v-on:click="save()" class="btn btn-primary">保存</button>
                    </div>
                </div><!-- /.modal-content -->
            </div><!-- /.modal-dialog -->
        </div><!-- /.modal -->
    </div>
</template>

<script>
    //用来引入自己写的主键pagination的
    import Pagination from "../../components/pagination";

    export default {
        name: "business-course",
        //components是用来注册组件的(注册之后就可以在html里面使用该标签了)
        components: {Pagination},
        data: function () {
            return {
                courses: [],
                course: {},
                COURSE_LEVEL:COURSE_LEVEL,
                COURSE_CHARGE:COURSE_CHARGE,
                COURSE_STATUS:COURSE_STATUS
            }
        },
        mounted: function () {
            let _this = this;
            //初始化为5条每页,我们写的代码的默认是10条煤业
            _this.$refs.pagination.size = 5;
            _this.list(1);
            //激活本页面的左侧导航栏标签(因为我们已经在admin中设置所以不用这个了,这个每个子模块中都得写,太麻烦)
            // this.$parent.activeSidebar("business-course-sidebar");

            //用来设置模态框在点击空白的地方不会关闭(默认的时候打开模态框在点击空白的地方就会关闭)
            // $("#form-modal").modal({backdrop:'static'});
        },
        methods: {
            add() {
                let _this = this;
                _this.course={};
                $("#form-modal").modal("show");
            },

            edit(course) {
                let _this = this;
                /*如果直接赋值的话对原来的变量做修改就会影响到原来的变量和数据,所以我们用$.extend
                来把course变量复制到{}这个空变量中再赋值给_this.course中,就不会发生改变input框中
                的变量表格中的数据和变量也会跟着发生变化
                 */
                _this.course=$.extend({},course);
                $("#form-modal").modal("show");
            },

            list(page) {
                let _this = this;
                Loading.show();
                _this.$ajax.post(process.env.VUE_APP_SERVER+'/business/admin/course/list', {
                    page: page,
                    //$refs是根据名字获取子组件，这里是获取组件里面一个size的变量
                    //this.$refs.子组件名字
                    size: _this.$refs.pagination.size
                }).then((response) => {
                    Loading.hide();
                    // console.log("查询结果：", response);
                    let resp = response.data;
                    _this.courses = resp.content.list;
                    //调用分页组件中的渲染分页组件方法
                    _this.$refs.pagination.render(page, resp.content.total);
                })
            },

            save() {
                let _this = this;
                if (1!=1
                ||!Validator.require(_this.course.name,"名称")
                ||!Validator.length(_this.course.name,"名称",1,50)
                ||!Validator.length(_this.course.summary,"概述",1,2000)
                ||!Validator.length(_this.course.image,"封面",1,100)
                ){
                    return;
                }
                Loading.show();
                _this.$ajax.post(process.env.VUE_APP_SERVER+'/business/admin/course/save', _this.course)
                    .then((response) => {
                        Loading.hide();
                        // console.log("查询结果：", response);
                        let resp = response.data;
                        if (resp.success) {
                            $("#form-modal").modal("hide");
                            _this.list(1);
                            Toast.success("保存成功!");
                        }else {
                            Toast.warning(resp.message)
                        }
                    })
            },
            del(id) {
                let _this = this;
                Confirm.warning("删除","您将无法恢复它",function () {
                    Loading.show();
                    _this.$ajax.delete(process.env.VUE_APP_SERVER+'/business/admin/course/delete/'+id)
                    .then((response) => {
                        Loading.hide();
                        // console.log("删除列表结果：", response);
                        let resp = response.data;
                        if (resp.success) {
                            _this.list(1);
                            Toast.success("删除成功!");
                        }
                    });
                });
            },

        }
    }
</script>

<style scoped>

</style>