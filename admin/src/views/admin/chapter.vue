<template>
    <div>
        <p>
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
                <th class="center">名称</th>
                <th class="center">课程id</th>
                <th class="center">操作</th>
            </tr>
            </thead>

            <tbody>
            <tr v-for="chapter in chapters">
                <td class="center">
                    {{chapter.id}}
                </td>
                <td class="center">
                    {{chapter.name}}
                </td>
                <td class="center">
                    {{chapter.courseId}}
                </td>

                <td class="center">
                    <div class="hidden-sm hidden-xs btn-group">
                        <button class="btn btn-xs btn-success">
                            <i class="ace-icon fa fa-check bigger-120"></i>
                        </button>

                        <button class="btn btn-xs btn-info">
                            <i class="ace-icon fa fa-pencil bigger-120"></i>
                        </button>

                        <button class="btn btn-xs btn-danger">
                            <i class="ace-icon fa fa-trash-o bigger-120"></i>
                        </button>

                        <button class="btn btn-xs btn-warning">
                            <i class="ace-icon fa fa-flag bigger-120"></i>
                        </button>
                    </div>

                    <div class="hidden-md hidden-lg">
                        <div class="inline pos-rel">
                            <button class="btn btn-minier btn-primary dropdown-toggle" data-toggle="dropdown" data-position="auto">
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
    </div>
</template>

<script>
    //用来引入自己写的主键pagination的
    import Pagination from "../../components/pagination";
    export default {
        name: "chapter",
        //components是用来注册组件的(注册之后就可以在html里面使用该标签了)
        components: {Pagination},
        data:function(){
            return{
                chapters:[]
            }
        },
        mounted:function () {
            let _this = this;
            //初始化为5条每页,我们写的代码的默认是10条煤业
            _this.$refs.pagination.size=5;
            _this.list(1);
            //激活本页面的左侧导航栏标签(因为我们已经在admin中设置所以不用这个了,这个每个子模块中都得写,太麻烦)
            // this.$parent.activeSidebar("business-chapter-sidebar");

        },
        methods:{
            list(page){
                let _this = this;
                _this.$ajax.post('http://127.0.0.1:9000/business/admin/chapter/list',{
                    page:page,
                    //$refs是根据名字获取子组件，这里是获取组件里面一个size的变量
                    //this.$refs.子组件名字
                    size:_this.$refs.pagination.size
                }).then((response)=>{
                    console.log("查询大章结果：",response);
                    _this.chapters=response.data.list;
                    //调用分页组件中的渲染分页组件方法
                    _this.$refs.pagination.render(page,response.data.total);
                })
            }
        }
    }
</script>

<style scoped>

</style>