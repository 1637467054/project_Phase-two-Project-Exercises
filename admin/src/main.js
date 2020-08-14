import Vue from 'vue'
import App from './app.vue'
import router from "./router.js"
import axios from 'axios'

Vue.config.productionTip = false;
Vue.prototype.$ajax=axios;

/**
 * axios拦截器
 */
axios.interceptors.request.use(function (config) {
  console.log("请求：",config);
  return config;
},error => {});
axios.interceptors.response.use(function (response) {
  console.log("返回参数：",response);
  return response;
},error => {});

new Vue({
  router,
  render: h => h(App),
}).$mount('#app');

console.log("开发环境：",process.env.NODE_ENV);
console.log("ip地址：",process.env.VUE_APP_SERVER);
