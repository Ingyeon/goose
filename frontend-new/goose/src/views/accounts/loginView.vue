<template>
<h1>로그인</h1>
<form>
  <div class="mb-3">
    <label for="IdInput" class="form-label">Id</label>
    <input type="text" class="form-control" id="IdInput" v-model="state.form.id">
  </div>
  <div class="mb-3">
    <label for="passwordInput" class="form-label">Password</label>
    <input type="password" class="form-control" id="passwordInput" v-model="state.form.password">
  </div>
  <button type="submit" @click.prevent="clickLogin" class="btn btn-primary">로그인</button>
  <router-link to="/signup" class="btn btn-secondary">회원가입</router-link>
</form>

</template>

<script>
import {reactive,computed} from 'vue'
import {useStore} from 'vuex'

export default {
    name: 'loginView',
    
    setup(){
    const store = useStore()
    const token = computed(() => store.getters.isLoggedIn)

    const state = reactive({
        form:{
            id: '',
            password: '',
        }
    })
<<<<<<< HEAD
=======
    // store 및 서버 설정 끝나면 아래 코드 지우고 주석 코드로 대체
    // const clickLogin = function(event){
    //     event.preventDefault()
    //     alert(JSON.stringify(state.form))

    // }
>>>>>>> 2e09ea8d (feat: 스터디 게시판 + 댓글)

    const clickLogin = function(){
        // dispatch 함수명 바뀔 경우 아래줄 수정
        console.log(state.form.id, state.form.password)
        store.dispatch('login',{id: state.form.id, password: state.form.password})
        .then(function (result){
          console.log(result)
            // alert('accessToken: ' + result.data.Token)
        })
        .catch(function(err){
            alert(err)
        })
      }
    return {state,clickLogin,store,token}
    }
}
</script>