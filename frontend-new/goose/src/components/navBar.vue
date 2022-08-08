<template>
  <nav class="navbar navbar-expand-lg bg-light">
    <div class="container-fluid">
      <router-link to="/home" class="navbar-brand"><img src="../assets/Goose-1.png" style="height: 200px; width: 200px;" alt=""></router-link>
    <!-- 로그인 안되있을 경우 -->
    <ul v-if="!isLoggedIn" class="navbar-nav me-auto mb-2 mb-lg-0">
      
      <li class="nav-item">
        <login-modal></login-modal>
      </li>
      <li class="nav-item">
        <router-link to="/signup" class="nav-link active" aria-current="page" href="#">회원가입</router-link>
      </li>
      <li class="nav-item">
        <router-link to="/test" class="nav-link active" href="#">테스트</router-link>
      </li>
        
    </ul>
    <!-- 로그인 안되있을 경우 끝 -->
    <!-- 로그인 되있을 경우 -->
    <ul v-if="isLoggedIn" class="navbar-nav me-auto mb-2 mb-lg-0">
      <li class="nav-item">
        <router-link to="/" class="nav-link active" aria-current="page" href="#">스터디 참여하기</router-link>
      </li>
      <li class="nav-item">
          <router-link to="/articles" class="nav-link active" aria-current="page" href="#">게시판</router-link>
      </li>
      <li class="nav-item">
        <router-link to="/createStudy" :page="create" class="nav-link active" aria-current="page" href="#">스터디 만들기</router-link>
      </li>
      <!-- <div>
        <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#createStudyModal">스터디 만들기2</button>
        <createStudy />
      </div> -->


      <li class="nav-item">
        <router-link to="/:id/profile" class="nav-link" href="#">마이 페이지</router-link>
      </li>
      <li class="nav-item">
        <!-- <router-link class="nav-link" @click="clickLogout" href="#">로그아웃</router-link> -->
        <a href="#" @click="clickLogout">로그아웃</a>
      </li>
      <li class="nav-item">
        <router-link to="/:id/delete" class="nav-link" href="#">회원탈퇴</router-link>
      </li>
      <li class="nav-item">
        <router-link to="/test" class="nav-link" href="#">테스트</router-link>
      </li>
    </ul>
    <!-- 로그인 되있을 경우 끝 -->
  </div>
        <div v-if="isLoggedIn">
          {{ loginUser.id }}
          {{ loginUser.userId }}님 안녕하세요
        </div>
        <div v-if="!isLoggedIn">
          Guest님 안녕하세요
        </div>
</nav>
</template>

<script>
// import { useRouter } from 'vue-router'
import { useStore } from "vuex"
import { computed } from "vue"
import loginModal from "@/views/accounts/loginModal.vue"
// import createStudy from "@/components/StudyPage/studyCreate.vue"


export default {
    components: {
      loginModal
      // createStudy,
    },

    setup() {
      const store = useStore()
      const isLoggedIn = computed(()=> store.getters.isLoggedIn)
      const loginUser = computed(()=> store.getters.loginUser)
      const clickLogout = function(){
        store.dispatch("logout")
      }

      return { 
        isLoggedIn,
        clickLogout,loginUser
      }
    },
}
</script>

<style>
@import url(https://cdn.jsdelivr.net/gh/moonspam/NanumSquare@1.0/nanumsquare.css);
  .nav-item {
    font-family: 'NanumSquare', sans-serif;
  }
</style>
