<template>
<div class="container">
    <div v-if="!status.validcheck">
        <div class="input-Box">
            <label for="inputName" class="form-label">ID</label>
            <input type="text" id="inputName" class="form-control" placeholder="회원정보에 등록한 아이디를 입력해주세요" v-model="status.id">
        </div>
        <div class="input-Box">
            <label for="inputName" class="form-label">이메일</label>
            <input type="email" id="inputName1" class="form-control" placeholder="회원정보에 등록한 이메일을 입력해주세요" v-model="status.email">
        </div>
        
        <div class="input-Box">
            <button @click="FindForm">인증하기</button>
        <router-link to="/idpasswordselect" class="button-link"> <button>이전 페이지</button> </router-link>
    </div>
    </div>
    <div v-if="status.validcheck">
        <div class="input-Box">
            <label for="inputName" class="form-label">ID</label>
            <input type="text" id="inputName" class="form-control" disabled="disabled" placeholder="회원정보에 등록한 아이디를 입력해주세요" v-model="status.id">
        </div>
        <div class="input-Box">
            <label for="inputPassword" class="form-label">새로운 비밀번호</label>
            <input type="password" id="inputPassword1" class="form-control" aria-describedby="passwordHelpBlock" placeholder="비밀번호를 입력해주세요" v-model="status.password1" @blur="passwordValid">
            <div id="passwordHelpBlock" class="form-text mb-3">
                    공백을 제외한 특수문자, 영문, 숫자를 이용하여 8-16자 이내로 만드세요.
            </div>
            <div class="form-text" v-if="!status.passwordValidFlag">올바르지 않은 형식입니다.</div>
            <input type="password" id="inputPassword2" class="form-control" placeholder="비밀번호 확인" v-model="status.password2" @blur="passwordCheckValid">
            <div class="form-text" v-if="!status.passwordCheckFlag">비밀번호가 동일하지 않습니다.</div>
        </div>
        
        <div class="input-Box">
            <button class="btn-refresh" @click="changeForm">비밀번호 재발급받기</button>
        <router-link to="/idpasswordselect" class="button-link"> <button>이전 페이지</button> </router-link>
        </div>
    </div>

</div>
</template>

<script>
import { reactive, computed } from 'vue'
import http from "@/util/http-common.js";
import {useStore} from 'vuex'
import axios from 'axios'
import { useRouter } from 'vue-router'
export default {
     setup() {
        const store = useStore()
        const router = useRouter()
        // const token = computed(() => store.getters.isLoggedIn)
        const findid = computed(()=>store.getters.findId)
        const status = reactive({
            validcheck: 0,
            id : '',
            email : '',
            password1: '',   
            password2: '',
            passwordValidFlag: true,
            passwordCheckFlag: true   
        })
        const changeForm = function() {
            store.dispatch('refreshPassword',{
                "new_password":status.password1,
                "user_id": status.id
            })
        }
        const FindForm = async function(){
            try {
                let data = await http({
                    method: 'get',
                    url:`/users/findpw?email=${status.email}&userId=${status.id}`,
                })
                alert('인증되었습니다.')
                status.validcheck = 1
            }
            catch(err){ 
                console.log(err)
                alert('ID와 이메일을 확인해주세요')
                status.validcheck = 0
            }
        }
        
        const passwordValid = function() {
            if (/^(?=.*[a-zA-z])(?=.*[0-9])(?=.*[$`~!@$!%*#^?&\\(\\)\-_=+]).{8,16}$/.test(state.form.password1)) {
                if (state.form.password1.includes(' ')){
                    state.passwordValidFlag = false
                }
                else {
                    state.passwordValidFlag = true
                    if (state.form.password1 === state.form.password2) {
                        state.passwordCheckFlag = true
                    } else {
                        state.passwordCheckFlag = false
                    }
                }
            } else {
                state.passwordValidFlag = false
                if (state.form.password1 === state.form.password2) {
                        state.passwordCheckFlag = true
                    } else {
                        state.passwordCheckFlag = false
                    }
            }
        }
        const passwordCheckValid = function() {
            if (status.password1 === status.password2) {
                status.passwordCheckFlag = true
            } else {
                status.passwordCheckFlag = false
            }
        }
        return {
            changeForm,
            status,
            router,
            store,
            FindForm,
            findid,
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
    input[type="submit"] {
        background: #ffd700;
        color: #000000;
        cursor: pointer;
        width: 100px;
        margin-bottom: 20px;
        font-weight: 600;
        text-align: center;
        border-radius: 80px 40px;
        border: none;
    }
    button:not(.btn-refresh) {
        background: #ffd700;
        color: #000000;
        cursor: pointer;
        width: 110px;
        height: 40px;
        margin-bottom: 20px;
        font-size: 1.25rem;
        font-weight: bold;
        text-align: center;
        border-radius: 80px 40px;
        border: none;
    }
    .btn-refresh {
        background: #ffd700;
        color: #000000;
        cursor: pointer;
        width: 200px;
        height: 40px;
        margin-bottom: 20px;
        font-size: 1.25rem;
        font-weight: bold;
        text-align: center;
        border-radius: 80px 40px;
        border: none;
    }
</style>