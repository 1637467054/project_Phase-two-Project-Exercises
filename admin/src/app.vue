<template>
  <!--app对应的是public/index.html中id为app的div标签,这个标签里的内容就会替代原来public/index.html
  中的的空app标签-->
  <div id="app">
    <!--如果要使用集成路由需要使用这个标签，之后的跳转内容就会替代这个标签-->
    <router-view/>
  </div>
</template>