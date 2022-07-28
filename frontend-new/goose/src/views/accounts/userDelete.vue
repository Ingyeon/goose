<template>
    <form @submit.prevent="DeleteForm">
        <div class="inputBox">
            <label for="inputPassword" class="form-label">Password</label>
            <input type="password" id="inputPassword1" class="form-control" placeholder="비밀번호를 입력해주세요" v-model="status.password1" @blur="passwordValid">
            <div class="form-text" v-if="!status.passwordValidFlag">비밀번호가 틀렸습니다.</div>
            <input type="password" id="inputPassword2" class="form-control mt-3" placeholder="비밀번호 확인" v-model="status.password2" @blur="passwordCheckValid">
            <div class="form-text" v-if="!status.passwordCheckFlag">비밀번호가 동일하지 않습니다.</div>
        </div>
        
        <div class="inputBox">
            <input type="submit" value="탈퇴">
            <!-- <input type="button" value="Back" @click="moveToHome"> -->
            <router-link to="/" class="nav-link active" href="#">Back</router-link>
        </div>
    </form>

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
            store.dispatch("user_delete")
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
            if (status.password1 === status.password2) {
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

<style>
    .inputBox {
    width: 100%;
    margin-top: 20px;
    }

    .inputBox:nth-child(2) {
    display: flex;
    justify-content: space-evenly;
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

    .inputBox input[type="button"] {
    background: #fff;
    color: #666;
    cursor: pointer;
    width: 100px;
    margin-bottom: 20px;
    font-weight: 600;
    text-align: center;
    }
</style>