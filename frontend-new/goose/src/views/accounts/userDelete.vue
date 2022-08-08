<template>
<div class="container">
    <form @submit.prevent="DeleteForm">
        <div class="input-Box">
            <label for="inputPassword" class="form-label">Password</label>
            <input type="password" id="inputPassword1" class="form-control" placeholder="비밀번호를 입력해주세요" v-model="status.password1" @blur="passwordValid">
            <div class="form-text" v-if="!status.passwordValidFlag">비밀번호가 틀렸습니다.</div>
            <input type="password" id="inputPassword2" class="form-control" placeholder="비밀번호 확인" v-model="status.password2" @blur="passwordCheckValid">
            <div class="form-text" v-if="!status.passwordCheckFlag">비밀번호가 동일하지 않습니다.</div>
        </div>
        
        <div class="input-Box">
            <input type="submit" value="탈퇴">
            <!-- <input type="button" value="Back" @click="moveToHome"> -->
            <router-link to="/home" class="button-link"> <button>Back</button> </router-link>
        </div>
    </form>
</div>

</template>

<script>
import { reactive, computed } from 'vue'
import {useStore} from 'vuex'

export default {
     setup() {
        const store = useStore()
        // const token = computed(() => store.getters.isLoggedIn)

        const status = reactive({
            password1: '',
            password2: '',
            passwordValidFlag: true,
            passwordCheckFlag: true
        })
        const DeleteForm = () => {
            if (status.password1 == '') {
                alert('비밀번호를 입력하세요.')
            }
                
            else if (status.passwordCheckFlag == true){
            store.dispatch("user_delete", status.password1)}
            status.password1 = '',
            status.password2 = ''
        }

        const passwordValid = function() {
            // console.log(token)
            // console.log(this.$route.param)
            // if (status.password1 === store.loginUser.password) {
            //     status.passwordValidFlag = true
            // } else {
            //     status.passwordValidFlag = false
            // }
        }

        const passwordCheckValid = function() {
            if ( status.password1 === status.password2) {
                status.passwordCheckFlag = true
            } else {
                status.passwordCheckFlag = false
            }
        }

        return {
            status,
            store,
            DeleteForm,
            passwordValid,
            passwordCheckValid,
        }
    },
}
</script>

<style scoped>
    .input-Box:nth-child(2) {
    display: flex;
    justify-content: space-evenly;

    }
    .form-label {
        font-family: "NanumSquare", sans-serif;
    }
    button {
        background: #ffd700;
        color: #000000;
        cursor: pointer;
        width: 100px;
        margin-bottom: 20px;
        font-weight: 600;
        text-align: center;
        border-radius: 40px 80px;
    }
</style>