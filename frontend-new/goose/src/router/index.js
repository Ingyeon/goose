import { createWebHistory, createRouter } from 'vue-router';
import Home from '@/views/home/home.vue'
import Login from '@/views/accounts/loginView.vue'
import test from '@/views/home/test.vue'
import Signup from '@/views/accounts/signupView.vue'
import ArticleList from '@/views/article/articleList.vue'
import NewArticle from '@/views/article/newArticle.vue'
import ArticleDetail from '@/views/article/articleDetail.vue'

const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home,
  },
  {
    path: '/login',
    name: 'Login',
    component: Login,},
    {
    path: '/test',
    name: 'Test',
    component: test
  },
    {
    path: '/signup',
    name: 'Signup',
    component: Signup
  },
  {path: '/articles',
   name: ArticleList,
  component: ArticleList},
  
{path: '/newarticle',
name: NewArticle,
component: NewArticle},

{path: '/article/:id',
name: ArticleDetail,
component: ArticleDetail}
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;