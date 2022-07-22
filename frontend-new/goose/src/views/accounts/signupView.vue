<template>
    <div class="container mt-5">
        <form @submit.prevent="signupForm">
            <div class="inputBox">
                <label for="inputname" class="form-label">이름</label>
                <input type="text" id="inputname" class="form-control" placeholder="이름" v-model="state.name">
            </div>
            <div class="inputBox">
                <label for="inputId" class="form-label">ID</label>
                <input type="text" id="inputId" class="form-control" placeholder="ID를 입력해주세요" v-model="state.id">
            </div>
            <div class="inputBox">
                <label for="inputPassword" class="form-label">Password</label>
                <input type="password" id="inputPassword" class="form-control" placeholder="비밀번호를 입력해주세요" aria-describedby="passwordHelpBlock" v-model="state.password1">
                <div id="passwordHelpBlock" class="form-text">
                    Your password must be 8-20 characters long, contain letters and numbers, and must not contain spaces, special characters, or emoji.
                </div>
                <input type="password" id="inputPassword" class="form-control" placeholder="비밀번호 확인" v-model="state.password2">
            </div>
             <div class="inputBox">
                <label for="inputEmail" class="form-label">Email</label>
                <input type="email" class="form-control" id="inputEmail" v-model="state.email">
            </div>
            <div class="inputBox">
                <label for="inputInterest" class="form-label">Interest</label>
                <input type="text" id="inputInterest" class="form-control" placeholder="취미를 입력해주세요" v-model="state.interest">
            </div>
            <div class="inputBox">
              <input type="submit" value="Submit">
              <router-link to="/login" class="btn input-box-btn">Back</router-link>
            </div>
        </form>  
    </div>
</template>

<script>
import { reactive,computed } from 'vue'
import { useStore } from 'vuex'
import router from '@/router'

export default {
    name: 'signupView',

     setup() {
        const store = useStore()
        const state = reactive({
            name: '',
            id : '',
            password1: '',
            password2: '',
            email: '',
            interest: '',
        })
        
        const signupForm = function(){
            // dispatch 함수명 바뀔 경우 아래줄 수정
            console.log(state.id, state.password1, state.name, state.interest, state.email  )
            store.dispatch('signup',{'name': state.name, 'id': state.id, 'password': state.password1, 'email':state.email, 'interest': state.interest, 'photo':''})
            .then(function (result){
                // alert('accessToken: ' + result.data.Token)
                // router.push({name :'Login'})
            })
            .catch(function(err){
                alert(err)
            })
        // const moveToLogin = () => {
        //     //moveToLogin
        //     this.$router.replace({name:Login})
        // }
        }
        return {
            state,
            store,
            signupForm,
        }
    }
}
</script>

<style>
    .inputBox {
    width: 100%;
    margin-top: 20px;
    }

    .inputBox:nth-child(5) {
    display: flex;
    justify-content: space-evenly;
    }

    .inputBox input::placeholder {
    color: #666
    }

    .input-box-btn {
    background: #fff;
    color: #666;
    cursor: pointer;
    width: 100px;
    margin-bottom: 20px;
    font-weight: 600;
    text-align: center;
    }
    
    .inputBox input[type="submit"] {
    background: #fff;
    color: #666;
    cursor: pointer;
    width: 100px;
    margin-bottom: 20px;
    font-weight: 600;
    text-align: center;
    }

</style>