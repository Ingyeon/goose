<template>
<div>
    <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <!-- <link rel="stylesheet" href="styles.css" /> -->
    <title>Navbar Design</title>
  </head>
  <body>
    <header>
      <span class="image-clickable">
        <router-link to="/home">
          <img src="../assets/Goose-1.png" alt="main-logo" class="logo" />
        </router-link>
      </span>
      <nav>
        <ul v-if="!isLoggedIn" class="nav-links">
          <!-- <li><router-link to="#"><button>로그인</button></router-link></li> -->
          <li><login-modal class="button"></login-modal></li>
          <!-- <li><a to="#"><button></button></a></li>
          <li><a to="#"><button>게시판</button></a></li> -->
        </ul>
        <ul v-if="isLoggedIn" class="nav-links">
          <li><router-link to="/createStudy"><button>스터디생성하기</button></router-link></li>
          <li><router-link to="#"><button @click="clickLogout">로그아웃</button></router-link></li>
          <li><router-link to="#"><button>게시판</button></router-link></li>
        </ul>
      </nav>
      <a href="#"><button>Contact Us</button></a>
    </header>
  </body>
</div>
</template>

<script>
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
        console.log(store.getters.loginUser)
        store.dispatch("logout")
        alert('로그아웃 되었습니다.')
      }

      return { 
        isLoggedIn,
        clickLogout,loginUser
      }
    },
}
</script>

<style scoped>
@import url("https://fonts.googleapis.com/css2?family=Montserrat:wght@500&display=swap");
img {
    width: 7rem;
    height: 7rem;
 /* max-width: 100%;
 max-height: 100%; */
}
* {
  box-sizing: border-box;
  margin: 0;
  padding: 0;
  background-color: #fff;
}

header {
  display: flex;
  justify-content: flex-end;
  align-items: center;
  margin: 0rem 1.5rem;
}

a,
button {
  color: black;
}

.image-clickable {
  margin-right: auto;
}

.logo {
  /* height: 4.2rem; */
  margin-right: auto;
}

.nav-links {
  list-style: none;
}

.nav-links li {
  display: inline-block;
  padding: 0 1.25rem;
}

.nav-links li a {
  text-decoration: none;
  font-family: "Montserrat", sans-serif;
  font-size: 1rem;
  transition: all 0.5s ease 0s;
}

.nav-links li a:hover {
  color: rgba(0, 173, 181, 0.8);
}


button {
  background: #ffd700;
  cursor: pointer;
  padding: 9px 20px;
  border: none;
  /* border-radius: 50px; */
  font-family: "NanumSquare", sans-serif;
  font-weight: bold;
  font-size: 1rem;
  transition: all 0.5s ease 0s;
  margin-left: 20px;
  border-radius: 80px 40px;
}

button:hover {
  background: rgba(0, 173, 181, 0.8);
}
</style>>

