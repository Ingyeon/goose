<template>
  <div>
  <button type="button" class="login" data-bs-toggle="modal" data-bs-target="#Modal">
    로그인
  </button>
    <div class="modal fade" id="Modal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title" id="exampleModalLabel">로그인</h5>
            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
          </div>
          <div class="modal-body">
            <div class="container">
            <form>
    <div class="input-Box">
        <label for="IdInput" class="form-label">ID</label>
        <input type="text" class="form-control" id="IdInput" v-model="state.form.id" @blur="idValid">
        <div v-if="!state.idValidFlag" class="form-text">존재하지 않는 ID입니다. 다시 한 번 확인해주세요</div>    
    </div>
      <div class="mb-3">
      <div class="input-Box">
        <label for="passwordInput" class="form-label">Password</label>
        <input type="password" class="form-control" id="passwordInput" v-model="state.form.password">
      </div>
      </div>
          <div class="input-Box">
      <button type="submit" @click.prevent="clickLogin" data-bs-dismiss="modal" class="btn btn-primary">로그인</button>
      <button type="submit" @click.prevent="clickSignup" data-bs-dismiss="modal" class="btn btn-secondary">회원가입</button>
      </div>
    </form>
          </div>
          <!-- <div class="modal-footer">
            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
          </div> -->
        </div>
      </div>
    </div>
  </div>
  </div>
</template>

<script>

import {reactive,computed} from 'vue'
import {useStore} from 'vuex'
import { useRouter } from 'vue-router'
import axios from 'axios'
export default {

  setup(){
    const router = useRouter()
    const store = useStore()
    const token = computed(() => store.getters.isLoggedIn)

    const state = reactive({
        form:{
            id: '',
            password: '',
        },
        idValidFlag : true
    })
    const idValid = async function() {
      try{
        let data = await axios.get(`http://localhost:8080/api/v1/users/{userId}?userId=${state.form.id}`)
        state.idValidFlag=true
      } catch(err) {
        state.idValidFlag=false
      }
    }
    const clickLogin = async function(){
      // dispatch 함수명 바뀔 경우 아래줄 수정
      console.log(state.form.id, state.form.password)
      await store.dispatch('login',{id: state.form.id, password: state.form.password})
        
      router.push()
      .then(function (result){
        
        console.log(result)
      })
      .catch(function(err){
          alert(err)
      })
    }
    const clickSignup = async function(){
      await router.push({name:'Signup'})
    }

    return {
      state,
      idValid,
      clickLogin,
      store,
      token,
      clickSignup}
  }
}
</script>

<style scoped>
*{
  font-family: 'NanumSquare', sans-serif;
}
  .input-Box:nth-child(3) {
    display: flex;
    /* justify-content: space-evenly; */
    justify-content: end;
    }

    input[type=password]{
      font-family:"굴림";
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
  /* button[class="login"]{
  background: #ffd700;
  color: #000000;
  cursor: pointer;
  width: 100px;
  margin-bottom: 20px;
  font-weight: 600;
  text-align: center;
  border-radius: 80px 40px;
  } */
  button[class="login"]{
  background: #ffd700;
  border-radius: 80px 40px;
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
</style>