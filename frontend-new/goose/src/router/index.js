import { createWebHistory, createRouter } from 'vue-router';
import Home from '@/views/home/home.vue'
import Login from '@/views/accounts/loginview.vue'

const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home,
  },
  {
    path: '/login',
    name: 'Login',
    component: Login,
  },
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;