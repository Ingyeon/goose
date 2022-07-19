import { createApp } from 'vue'
import App from './App.vue'
import "bootstrap/dist/css/bootstrap.min.css"
import "bootstrap"
import router from '@/router/index'

// createApp(App).mount('#app')
// App.use(router)


const app = createApp(App);

// app.use(store);
app.use(router); // 사용 설정 하기

app.mount('#app');