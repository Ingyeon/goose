import { createApp } from 'vue'
import App from './App.vue'
import "bootstrap/dist/css/bootstrap.min.css"
// import "bootstrap"

import  BootstrapVue3  from 'bootstrap-vue-3'
import router from '@/router/index'
import store from "@/store/index"

import "bootstrap/dist/css/bootstrap.css";
import "bootstrap-vue-3/dist/bootstrap-vue-3.css";

// createApp(App).mount('#app')
// App.use(router)


const app = createApp(App);

// app.use(store);
app.use(router); // 사용 설정 하기
app.use(store);
app.use(BootstrapVue3);
app.mount('#app');