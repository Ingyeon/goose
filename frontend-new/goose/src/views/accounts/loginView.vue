<template>
<h1>로그인</h1>
<div class="container">
  <form @submit.prevent="clickLogin">
    <div class="input-Box">
      <label for="IdInput" class="form-label">ID</label>
      <input type="text" class="form-control" id="IdInput" v-model="state.form.id">
    </div>
    <div class="input-Box">
      <label for="passwordInput" class="form-label">Password</label>
      <input type="password" class="form-control" id="passwordInput" v-model="state.form.password">
    </div>
    <div class="input-Box">
      <button @click.prevent="clickSignup">회원가입</button>
      <input type="submit" value="로그인">
      <!-- <router-link to="/signup" class="btn">회원가입</router-link> -->
    </div>
  </form>
</div>
</template>

<style>
  
  .input-Box:nth-child(3) {
    display: flex;
    /* justify-content: space-evenly; */
    justify-content: end;

    }
    .input-Box button{
    background: #ffd700;
    color: #000000;
    cursor: pointer;
    width: 100px;
    margin-bottom: 20px;
    font-weight: 600;
    text-align: center;
    border-radius: 80px 40px;
    margin-right: 30px;
  }
</style>

<script>
import {reactive,computed} from 'vue'
import {useStore} from 'vuex'
import { useRouter } from 'vue-router'

export default {
    name: 'loginView',
    
    setup(){
    const store = useStore()
    const token = computed(() => store.getters.isLoggedIn)
    const router = useRouter()

    const state = reactive({
        form:{
            id: '',
            password: '',
        }
    })
    // store 및 서버 설정 끝나면 아래 코드 지우고 주석 코드로 대체
    // const clickLogin = function(event){
    //     event.preventDefault()
    //     alert(JSON.stringify(state.form))

    // }
    const clickSignup = function(){
      router.push({name:"Signup"})
    }
    const clickLogin = function(){
        // dispatch 함수명 바뀔 경우 아래줄 수정
        console.log(state.form.id, state.form.password)
        store.dispatch('login',{id: state.form.id, password: state.form.password})
        .then(function (result){
          console.log(result)
        })
        .catch(function(err){
            alert(err)
        })
      }
    return {router, state,clickLogin,store,token, clickSignup}
    }
}
</script>