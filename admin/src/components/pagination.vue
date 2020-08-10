<template>
    <div class="pagination" role="group" aria-label="分页">
        <button type="button" class="btn btn-default btn-white btn-round"
                v-bind:disabled="page === 1"
                v-on:click="selectPage(1)">
                <!--v-bind用于给标签设置属性(这里设置的是如果page全等于1就触发disabled[禁用])-->
            1
        </button>
        <button type="button" class="btn btn-default btn-white btn-round"
                v-bind:disabled="page === 1"
                v-on:click="selectPage(page - 1)">
            上一页
        </button>
        <button v-for="p in pages" v-bind:id="'page-' + p"
                type="button" class="btn btn-default btn-white btn-round"
                v-bind:class="{'btn-primary active':page == p}"
                v-on:click="selectPage(p)">
            <!--在v-bind:class="{'btn-primary active':page == p}"中如果如果page==p为true则在class标签中添加
            btn-primary active两个类属性,否则不添加这两个类属性-->
            {{p}}
        </button>
        <button type="button" class="btn btn-default btn-white btn-round"
                v-bind:disabled="page === pageTotal"
                v-on:click="selectPage(page + 1)">
            下一页
        </button>
        <button type="button" class="btn btn-default btn-white btn-round"
                v-bind:disabled="page === pageTotal"
                v-on:click="selectPage(pageTotal)">
            {{pageTotal||1}}
            <!--当pageTotal>0的时候返回pageTotal(js中>0转换为Boolean只为true),如果pageTotal<=0则显示1-->
        </button>
        &nbsp;
        <span class="m--padding-10">
        每页
        <select v-model="size">
            <option value="1">1</option>
            <option value="5">5</option>
            <option value="10">10</option>
            <option value="15">15</option>
            <option value="20">20</option>
            <option value="25">25</option>
        </select>
        条，共【{{total}}】条
    </span>
    </div>
</template>

<script>
    export default {
        name: 'pagination',
        props: {
            /*
            * 父组件通过属性props向下传递数据给子组件，当子组件需要某个数据，就在内部定义一个prop属性，
            * 然后父组件就像给html元素指定特性值一样，把自己的data属性传递给子组件的这个属性。而当子组
            * 件内部发生了什么事情的时候，就通过自定义事件来把这个事情涉及到的数据暴露出来，供父组件处理。
            * */
            list: {
                type: Function,
                default: null
            },
            itemCount: Number // 显示的页码数，是指显示几个页码数标签，其它的页码标签用省略号表示
        },
        data: function () {
            return {
                /**
                 * 之所以要在这里使用return是因为不使用return包裹的数据会在项目的
                 * 全局可见，会造成变量污染(多个地方共享同一个data对象)，使用return
                 * 包裹后数据中的变量只会在当前组件生效，不会影响其它组件(也就是所
                 * 如果在组件中使用的vue最好使用这种函数的方法来定义变量)
                 */
                total: 0, // 总行数
                size: 10, // 每页条数
                page: 0, // 当前页码
                pageTotal: 0, // 总页数
                pages: [], // 显示的页码数组
            }
        },
        methods: {
            /**
             * 渲染分页组件
             * @param page
             * @param total
             */
            render(page, total) {
                let _this = this;
                _this.page = page;
                _this.total = total;
                //ceil()是天花板函数
                _this.pageTotal = Math.ceil(total / _this.size);
                _this.pages = _this.getPageItems(_this.pageTotal, page, _this.itemCount || 5);
            },

            /**
             * 查询某一页
             * @param page
             */
            selectPage(page) {
                let _this = this;
                if (page < 1) {
                    page = 1;
                }
                if (page > _this.pageTotal) {
                    page = _this.pageTotal;
                }
                if (this.page !== page) {
                    _this.page = page;
                    if (_this.list) {
                        _this.list(page);
                    }
                }
            },

            /**
             * 当前要显示在页面上的页码
             * @param total     总页数
             * @param current   当前页数
             * @param length    显示几个页码数标签
             * @returns {Array}
             */
            getPageItems(total, current, length) {
                let items = [];
                if (length >= total) {
                    //如果要显示的页码数大于总页码数，那么显示全部的页码
                    for (let i = 1; i <= total; i++) {
                        items.push(i);
                    }
                } else {
                    let base = 0;
                    // 前移，floor是地板函数
                    //如果当前页数大于要显示的页码数的标签的一半
                    if (current - 0 > Math.floor((length - 1) / 2)) {
                        // 后移 取      总页数    当前页数+向上取整((要显示的页数个数-1)/2)  其中的小值  -  要显示的页数
                        base = Math.min(total, current - 0 + Math.ceil((length - 1) / 2)) - length;
                    }
                    for (let i = 1; i <= length; i++) {
                        items.push(base + i);
                    }
                }
                return items;
            }
        }
    }
</script>

<style scoped>
    .pagination {
        vertical-align: middle !important;
        font-size: 16px;
        margin-top: 0;
        margin-bottom: 10px;
    }

    .pagination button {
        margin-right: 5px;
    }

    /*这是交集选择器，表示既有.btn-primary又有.active*/
    .btn-primary.active {
        background-color: #2f7bba !important;
        border-color: #27689d !important;
        color: white !important;
        font-weight: 600;
    }
</style>
