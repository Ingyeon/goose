<template>
<form>
  <div class="mb-3">
    <label for="IdInput" class="form-label">Id</label>
    <input type="text" class="form-control" id="IdInput" v-model="state.form.id">
  </div>
  <div class="mb-3">
    <label for="passwordInput" class="form-label">Password</label>
    <input type="password" class="form-control" id="passwordInput" v-model="state.form.password">
  </div>
  <button type="submit" class="btn btn-primary">로그인</button>
    <button type="submit" class="btn btn-secondary">회원가입</button>
</form>

<!-- 확인용 -->
{{state.form}}
</template>

<script>
import {reactive} from 'vue'
// import {useStore} from 'vuex'

export default {
    name: 'loginview',
    
    setup(){
    // const store = useStore()
    // const loginForm = ref(null)

    const state = reactive({
        form:{
            id: '',
            password: '',
        }
    })
    // store 및 서버 설정 끝나면 아래 코드 지우고 주석 코드로 대체
    const clickLogin = function(event){
        event.preventDefault()
        alert(JSON.stringify(state.form))

    }
    // 원래 사용할 코드는 아래 주석으로 기재 
    // const clickLogin = function(){
    //     loginForm.value.validate(valid) => {
    //         if (valid){
    //             // dispatch 함수명 바뀔 경우 아래줄 수정
    //             store.dispatch('requestLogin',{id: state.form.id, password: state.form.password})
    //             .then(function (result){
    //                 alert('accessToken: ' + result.data.accessToken)
    //             })
    //             .catch(function(err){
    //                 alert(err)
    //             })
    //         }
    //         else {
    //             alert ('로그인 실패!')
    //         }
    //     }
    // }
    return {state,clickLogin}
    }
}
</script>