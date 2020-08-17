<template>
    <div>
        <p>
            <!--添加小节-->
            <button v-on:click="add()" class="btn btn-white btn-default btn-round">
                <i class="ace-icon fa fa-edit"></i>
                新增
            </button>&nbsp;
            <!--刷新小节-->
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
                <th class="center">ID</th>
                <th class="center">标题</th>
                <th class="center">课程</th>
                <th class="center">大章</th>
                <th class="center">视频</th>
                <th class="center">顺序</th>
                <th class="center">收费</th>
                <th class="center">顺序</th>
                <th class="center">操作</th>
            </tr>
            </thead>

            <tbody>
            <tr v-for="section in sections">

                <td class="center">{{section.id}}</td>

                <td class="center">{{section.title}}</td>

                <td class="center">{{section.courseId}}</td>

                <td class="center">{{section.chapterId}}</td>

                <td class="center">{{section.video}}</td>

                <td class="center">{{section.time}}</td>

                <!--<td class="center">{{section.charge}}</td>-->
                <!--对CHARGE进行过滤，过滤出section.charge的元素-->
                <td class="center">{{CHARGE|optionKV(section.charge)}}</td>

                <td class="center">{{section.sort}}</td>

                <td class="center">
                    <div class="hidden-sm hidden-xs btn-group">
                        <button v-on:click="edit(section)" class="btn btn-xs btn-info">
                            <i class="ace-icon fa fa-pencil bigger-120"></i>
                        </button>

                        <button v-on:click="del(section.id)" class="btn btn-xs btn-danger">
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
                                <label class="col-sm-2 control-label">标题</label>
                                <div class="col-sm-10">
                                    <input v-model="section.title" class="form-control" placeholder="标题">
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-sm-2 control-label">课程</label>
                                <div class="col-sm-10">
                                    <input v-model="section.courseId" class="form-control" placeholder="课程">
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-sm-2 control-label">大章</label>
                                <div class="col-sm-10">
                                    <input v-model="section.chapterId" class="form-control" placeholder="大章">
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-sm-2 control-label">视频</label>
                                <div class="col-sm-10">
                                    <input v-model="section.video" class="form-control" placeholder="视频">
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-sm-2 control-label">顺序</label>
                                <div class="col-sm-10">
                                    <input v-model="section.time" class="form-control" placeholder="顺序">
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-sm-2 control-label">收费</label>
                                <div class="col-sm-10">
                                    <select v-model="section.charge" class="form-control">
                                        <option v-for="o in CHARGE" v-bind:value="o.key">
                                            {{o.value}}
                                        </option>
                                    </select>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-sm-2 control-label">顺序</label>
                                <div class="col-sm-10">
                                    <input v-model="section.sort" class="form-control" placeholder="顺序">
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
        name: "section",
        //components是用来注册组件的(注册之后就可以在html里面使用该标签了)
        components: {Pagination},
        data: function () {
            return {
                sections: [],
                section: {},
                CHARGE:[{key:"C",value:"收费"},{key:"F",value:"免费"}]
            }
        },
        mounted: function () {
            let _this = this;
            //初始化为5条每页,我们写的代码的默认是10条煤业
            _this.$refs.pagination.size = 5;
            _this.list(1);
            //激活本页面的左侧导航栏标签(因为我们已经在admin中设置所以不用这个了,这个每个子模块中都得写,太麻烦)
            // this.$parent.activeSidebar("business-section-sidebar");

            //用来设置模态框在点击空白的地方不会关闭(默认的时候打开模态框在点击空白的地方就会关闭)
            // $("#form-modal").modal({backdrop:'static'});
        },
        methods: {
            add() {
                let _this = this;
                _this.section={};
                $("#form-modal").modal("show");
            },

            edit(section) {
                let _this = this;
                /*如果直接赋值的话对原来的变量做修改就会影响到原来的变量和数据,所以我们用$.extend
                来把section变量复制到{}这个空变量中再赋值给_this.section中,就不会发生改变input框中
                的变量表格中的数据和变量也会跟着发生变化
                 */
                _this.section=$.extend({},section);
                $("#form-modal").modal("show");
            },

            list(page) {
                let _this = this;
                Loading.show();
                _this.$ajax.post(process.env.VUE_APP_SERVER+'/business/admin/section/list', {
                    page: page,
                    //$refs是根据名字获取子组件，这里是获取组件里面一个size的变量
                    //this.$refs.子组件名字
                    size: _this.$refs.pagination.size
                }).then((response) => {
                    Loading.hide();
                    // console.log("查询小节结果：", response);
                    let resp = response.data;
                    _this.sections = resp.content.list;
                    //调用分页组件中的渲染分页组件方法
                    _this.$refs.pagination.render(page, resp.content.total);
                })
            },

            save() {
                let _this = this;
                if (1!=1
                ||!Validator.require(_this.section.title,"标题")
                ||!Validator.length(_this.section.title,"标题",1,50)
                ||!Validator.length(_this.section.video,"视频",1,200)
                ){
                    return;
                }
                Loading.show();
                _this.$ajax.post(process.env.VUE_APP_SERVER+'/business/admin/section/save', _this.section)
                    .then((response) => {
                        Loading.hide();
                        // console.log("查询小节结果：", response);
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
                    _this.$ajax.delete(process.env.VUE_APP_SERVER+'/business/admin/section/delete/'+id)
                    .then((response) => {
                        Loading.hide();
                        // console.log("删除小节列表结果：", response);
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