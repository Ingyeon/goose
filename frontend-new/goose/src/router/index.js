import { createWebHistory, createRouter } from "vue-router";
import Home from "@/views/home/home.vue";
import test from "@/views/home/test.vue";

//accounts
import Signup from "@/views/accounts/signupView.vue";
import UserDelete from "@/views/accounts/userDelete.vue";
import UserProfile from "@/views/accounts/profileView.vue";
import ProfileUpdate from "@/views/accounts/profileUpdate.vue";
//Article
import ArticleList from "@/views/article/articleList_2.vue";
import NewArticle from "@/views/article/newArticle.vue";
import ArticleDetail from "@/views/article/articleDetail.vue";
import ArticleEdit from "@/views/article/articleEdit.vue";
//Study
import CreateStudy from "@/views/StudyPage/createStudy";
import StudyHome from "@/views/StudyPage/studyHome.vue";

const routes = [
  {
    path: "/home",
    name: "Home",
    component: Home,
  },
  { path: "/test", name: "Test", component: test },
  {
    path: "/signup",
    name: "Signup",
    component: Signup,
  },
  {
    path: "/:id/delete",
    name: "UserDelete",
    component: UserDelete,
  },
  {
    path: "/:id/profile",
    name: "UserProfile",
    component: UserProfile,
  },
  {
    path: "/:id/profileupdate",
    name: "ProfileUpdate",
    component: ProfileUpdate,
  },
  { path: "/articles", name: "ArticleList", component: ArticleList },

  { path: "/articlefix/:id", name: "article_edit", component: ArticleEdit },

  { path: "/newarticle", name: "NewArticle", component: NewArticle },

  { path: "/article/:id", name: "ArticleDetail", component: ArticleDetail },
  {
    path: "/createStudy",
    name: "createStudy",
    component: CreateStudy,
  },

  {
    path: "/studyHome",
    name: "StudyHome",
    component: StudyHome,
  },
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;