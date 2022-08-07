<template>
<div>
    <!-- <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Navbar Design</title>
  </head> -->
  <!-- <body> -->
    <header>
      <span class="image-clickable">
        <router-link to="/home">
          <img src="../assets/Goose-1.png" alt="main-logo" class="logo" />
        </router-link>
      </span>
      <nav class="navbar">
        <ul v-if="!isLoggedIn" class="nav-links">
          <!-- <li><router-link to="#"><button>로그인</button></router-link></li> -->
          <li><login-modal class="button"></login-modal></li>
          <!-- <li><a to="#"><button></button></a></li>
          <li><a to="#"><button>게시판</button></a></li> -->
        </ul>
        <ul v-if="isLoggedIn" class="nav-links">
          <li class="nav-id"><router-link to="/createStudy"><button>스터디생성하기</button></router-link></li>
          <li class="nav-id"><router-link to="/newarticle"><button>모집글쓰기</button></router-link></li>
          
          <li class="nav-id"><router-link to="/articles"><button>게시판</button></router-link></li>
          <li class="dropdown">
            <a class="dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                <img :src="state.photo" alt="profilephoto" class="profilephoto">
            </a>
            <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">    
              <router-link to="/:id/profile"><button class="dropdown-tag">마이페이지</button></router-link>
              <button class="dropdown-tag" @click="clickLogout">로그아웃</button>
              <hr>
              <router-link to="/:id/updatepassword"><button class="dropdown-tag">비밀번호 수정</button></router-link>
              <router-link to="/:id/delete"><button class="dropdown-tag">회원탈퇴</button></router-link>
            </div>
           
          </li>
        </ul>
        <li class="min-dropdown">
            <a class="dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                <img :src="state.photo" alt="profilephoto" class="profilephoto">
            </a>
            <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
              <router-link to="/createStudy"><button class="dropdown-tag">스터디<br>생성하기</button></router-link>
              <hr>
              <router-link to="/newarticle"><button class="dropdown-tag">모집글쓰기</button></router-link>
              
              <router-link to="/articles"><button class="dropdown-tag">게시판</button></router-link>
              <router-link to="/:id/profile"><button class="dropdown-tag">마이페이지</button></router-link>
              <button class="dropdown-tag" @click="clickLogout">로그아웃</button>
              <hr>
              <router-link to="/:id/updatepassword"><button class="dropdown-tag">비밀번호 수정</button></router-link>
              <router-link to="/:id/delete"><button class="dropdown-tag">회원탈퇴</button></router-link>
            </div>
        </li>
      </nav>
    </header>
</div>
</template>

<script>
import { useStore } from "vuex"
import { reactive, computed } from "vue"
import loginModal from "@/views/accounts/loginModal.vue"
import profile1 from '../assets/profile1.png'
import profile2 from '../assets/profile2.jpg'
import profile4 from '../assets/profile4.png'
import { useRouter } from 'vue-router'

export default {
    components: {
      loginModal
      // createStudy,
    },

    setup() {
      const store = useStore()
      const isLoggedIn = computed(()=> store.getters.isLoggedIn)
      // const loginUser = computed(()=> store.getters.loginUser)
      const originPhoto = computed(()=> store.getters.loginUser.photo)
      // const loginUser = store.getters.loginUser
      const state = reactive({
        photo: ''
      })
      const clickLogout = function(){
        console.log(store.getters.loginUser)
        store.dispatch("logout")
        alert('로그아웃 되었습니다.')
      }
      const savePhoto = function() {
        // console.log(loginUser)
          if (store.getters.loginUser.photo === '../../assets/profile1.png') {
            state.photo = profile1
          } 
          else if (store.getters.loginUser.photo === '../../assets/profile2.jpg') {
            state.photo = profile2
          } else {
            state.photo = profile4
          }
        }
      savePhoto()
      return { 
        state,
        isLoggedIn,
        clickLogout,
        // loginUser,
        savePhoto,
        originPhoto
      }
    },
    watch:{
      originPhoto:{
        handler(){
          this.savePhoto()
        }
      }
    }
}
</script>

<style scoped>
@import url("https://fonts.googleapis.com/css2?family=Montserrat:wght@500&display=swap");
img[class="logo"]{
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
  border-bottom: solid 3px #ffd700;
}

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
.min-dropdown {
  list-style: none;
  display:none;
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


button:not(.dropdown-tag) {
  background: #ffd700;
  border-radius: 80px 40px;
}

button{
  cursor: pointer;
  padding: 9px 20px;
  border: none;
  /* border-radius: 50px; */
  font-family: "NanumSquare", sans-serif;
  font-weight: bold;
  font-size: 1rem;
  transition: all 0.5s ease 0s;
  margin-left: 20px;
  
}

button:hover {
  background: rgba(0, 173, 181, 0.8);
}
/* a[class="dropdown-toggle"]{
    width: 150px;
    height: 150px; 
    border-radius: 0%;
    overflow: hidden;
} */
img[class="profilephoto"]{
    width: 6rem;
    height: 6rem;
    border-radius: 70%;
    object-fit: cover;
}
/* 
@media screen and (max-width:1048px) {
  .navbar {
    flex-direction: column;
    align-items: flex-start;
    margin: 0;
  }
  ul[class="nav-links"] {
    display: none;
    flex-direction: column;
    align-items: center;
    width: 100%;
  }
  .nav-links li {
  width: 100%;
  text-align: center;
  }
} */
@media screen and (max-width:1048px) {
  .navbar {
    flex-direction: column;
    align-items: flex-start;
    margin: 0;
  }
  ul[class="nav-links"] {
    display: none;
    flex-direction: column;
    align-items: center;
    width: 100%;
  }
  .nav-links li {
  width: 100%;
  text-align: center;
  }
  .min-dropdown {
  /* list-style: none; */
  display:block;
  }
}
</style>>

