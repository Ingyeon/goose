import { createWebHistory, createRouter } from 'vue-router';
import Home from '@/views/home/home.vue'
import test from '@/views/home/test.vue'

//accounts
import Login from '@/views/accounts/loginView.vue'
import Signup from '@/views/accounts/signupView.vue'
import UserDelete from '@/views/accounts/userDelete.vue'
//Article
import ArticleList from '@/views/article/articleList.vue'
import NewArticle from '@/views/article/newArticle.vue'
import ArticleDetail from '@/views/article/articleDetail.vue'
//Study
import CreateStudy from '@/views/StudyPage/createStudy'

import Conf from '@/views/conf/conf'

const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home,
  },
  {
    path: '/conf',
    name: 'Conf',
    component: Conf,
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
    {
    path: '/:id/delete',
    name: 'UserDelete',
    component: UserDelete
  },
  {path: '/articles',
   name: ArticleList,
  component: ArticleList},
  
  {
  path: '/createStudy',
  name: 'createStudy',
  component: CreateStudy
  },
  
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