import Vue from "vue"
import Router from "vue-router"
import Login from "./views/login.vue"

Vue.use(Router);

export default new Router({
    mode: "history",//history直接在端口后面跟地址(好看),hash在端口后有个#号,#后面跟地址
    base: process.env.BASE_URL,
    routes:[{
        path: '*',
        redirect: '/login',
    },{
        path: '/login',
        component:Login
    }]
})