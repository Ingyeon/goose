<template>
<div class="container">
    <form @submit.prevent="changeForm">
        <div class="input-Box">
            <label for="inputcurrentPassword" class="form-label">현재 비밀번호</label>
            <input type="password" id="inputPassword" class="form-control" placeholder="비밀번호를 입력해주세요" v-model="status.current_password">
        </div>
        <div class="input-Box">
            <label for="inputPassword" class="form-label">새로운 비밀번호</label>
            <input type="password" id="inputPassword1" class="form-control" aria-describedby="passwordHelpBlock" placeholder="비밀번호를 입력해주세요" v-model="status.new_password1" @blur="passwordValid">
            <div id="passwordHelpBlock" class="form-text mb-3">
                    공백을 제외한 특수문자, 영문, 숫자를 이용하여 8-16자 이내로 만드세요.
            </div>
            <div class="form-text" v-if="!status.passwordValidFlag">올바르지 않은 형식입니다.</div>
            <input type="password" id="inputPassword2" class="form-control" placeholder="비밀번호 확인" v-model="status.new_password2" @blur="passwordCheckValid">
            <div class="form-text" v-if="!status.passwordCheckFlag">비밀번호가 동일하지 않습니다.</div>
        </div>
        
        <div class="input-Box">
            <input type="submit" value="변경하기">
            <!-- <input type="button" value="Back" @click="moveToHome"> -->
            <router-link to="/home" class="button-link"> <button>뒤로 가기</button> </router-link>
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
            current_password: '',
            new_password1: '',
            new_password2: '',
            passwordValidFlag: true,
            passwordCheckFlag: true
        })
        const changeForm = () => {
            if (!status.passwordValidFlag || !status.passwordCheckFlag){
                alert('중복되지 않거나 올바르지 않은 양식이 있습니다.')
                return
            }
            store.dispatch("passwordUpdate", {"current_password": status.current_password,
                                             "new_password": status.new_password1})
        }

        // const passwordValid = function() {
            // console.log(token)
            // console.log(this.$route.param)
            // if (status.password1 === store.loginUser.password) {
            //     status.passwordValidFlag = true
            // } else {
            //     status.passwordValidFlag = false
            // }
        // }
        const passwordValid = function() {
            if (/^(?=.*[a-zA-z])(?=.*[0-9])(?=.*[$`~!@$!%*#^?&\\(\\)\-_=+]).{8,16}$/.test(status.new_password1)) {
                if (status.new_password1.includes(' ')){
                    status.passwordValidFlag = false
                }
                else {
                    status.passwordValidFlag = true
                }
            } else {
                status.passwordValidFlag = false
            }
        }
        const passwordCheckValid = function() {
            if (status.new_password1 === status.new_password2) {
                status.passwordCheckFlag = true
            } else {
                status.passwordCheckFlag = false
            }
        }

        return {
            status,
            store,
            changeForm,
            passwordValid,
            passwordCheckValid,
        }
    },
}
</script>

<style scoped>
    .input-Box:nth-child(3) {
    display: flex;
    justify-content: space-evenly;

    }
    .form-label {
        font-family: "NanumSquare", sans-serif;
    }
    input[type="password"] {
        font-family: "Gulim"
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