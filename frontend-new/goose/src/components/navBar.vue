<template>
  <nav class="navbar navbar-expand-lg bg-light">
  <div class="container-fluid">
    <router-link to="/" class="navbar-brand">Goose</router-link>
    <!-- 로그인 안되있을 경우 -->
    <ul v-if="!isLoggedIn" class="navbar-nav me-auto mb-2 mb-lg-0">
      <li class="nav-item">
        <router-link to="/login" class="nav-link active" aria-current="page" href="#">로그인</router-link>
      </li>
      <li class="nav-item">
        <router-link to="/signup" class="nav-link active" aria-current="page" href="#">회원가입</router-link>
      </li>
      <li class="nav-item">
        <router-link to="/test" class="nav-link" href="#">테스트</router-link>
      </li>
        
    </ul>
    <!-- 로그인 안되있을 경우 끝 -->
    <!-- 로그인 되있을 경우 -->
    <ul v-if="isLoggedIn" class="navbar-nav me-auto mb-2 mb-lg-0">
      <li class="nav-item">
        <router-link to="/" class="nav-link active" aria-current="page" href="#">스터디 참여하기</router-link>
      </li>
      <li class="nav-item">
        <router-link to="/createStudy" class="nav-link active" aria-current="page" href="#">스터디 만들기</router-link>
      </li>

      <li class="nav-item">
        <router-link to="/test" class="nav-link" href="#">마이 페이지</router-link>
      </li>
      <li class="nav-item">
        <!-- <router-link class="nav-link" @click="clickLogout" href="#">로그아웃</router-link> -->
        <a href="#" @click="clickLogout">로그아웃</a>
      </li>
      <li class="nav-item">
        <router-link to="/test" class="nav-link" href="#">테스트</router-link>
      </li>
    </ul>
    <!-- 로그인 되있을 경우 끝 -->
  </div>
</nav>
</template>

<script>
// import { useRouter } from 'vue-router'
import { useStore } from "vuex"
import { computed } from "vue"

export default {
    setup() {
      const store = useStore()
      const isLoggedIn = computed(()=> store.getters.isLoggedIn)
      const clickLogout = function(){
        store.dispatch("logout")
      }

      return { 
        isLoggedIn,
        clickLogout,
      }
    },
}
</script>

<style>

</style>
