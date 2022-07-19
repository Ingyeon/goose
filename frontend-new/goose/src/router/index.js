import { createWebHistory, createRouter } from 'vue-router';
import Home from '@/views/home/home.vue'
import Test from '@/views/home/test'

const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home,
  },
  {
    path: '/test',
    name: 'Test',
    component: Test,
  },
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;