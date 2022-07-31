<template>

<button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#Modal">
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
  <div class="mb-3">
    <label for="IdInput" class="form-label">Id</label>
    <input type="text" class="form-control" id="IdInput" v-model="state.form.id">
  </div>
  <div class="mb-3">
    <label for="passwordInput" class="form-label">Password</label>
    <input type="password" class="form-control" id="passwordInput" v-model="state.form.password">
  </div>
  <button type="submit" @click.prevent="clickLogin" data-bs-dismiss="modal" class="btn btn-primary">로그인</button>
  <button type="submit" @click.prevent="clickSignup" data-bs-dismiss="modal" class="btn btn-secondary">회원가입</button>
</form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
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

export default {

  setup(){
    const router = useRouter()
    const store = useStore()
    const token = computed(() => store.getters.isLoggedIn)

    const state = reactive({
        form:{
            id: '',
            password: '',
        }
    })
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
      const clickSignup = function(){
        router.push({name:'Signup'})
      }

    return {state,clickLogin,store,token,clickSignup}
  }
}
</script>

<style>

</style>