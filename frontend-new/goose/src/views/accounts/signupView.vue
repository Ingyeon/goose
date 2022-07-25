<template>
    <div class="container mt-5">
        <form @submit.prevent="signupForm">
            <div class="inputBox">
                <label for="inputname" class="form-label">이름</label>
                <input type="text" id="inputname" class="form-control" placeholder="이름" v-model="state.form.name">
            </div>
            <div class="inputBox">
                <label for="inputId" class="form-label">ID</label>
                <input type="text" id="inputId" class="form-control" placeholder="ID를 입력해주세요" v-model="state.form.id" maxlength=16 @blur="idValid">
                <div v-if="!state.idValidFlag">유효하지 않은 아이디 입니다. 영문과 숫자를 조합하여 8-16자 이내로 만드세요.</div>
            </div>
            <div class="inputBox">
                <label for="inputPassword" class="form-label">Password</label>
                <input type="password" id="inputPassword" class="form-control" placeholder="비밀번호를 입력해주세요" aria-describedby="passwordHelpBlock" v-model="state.form.password1" @blur="passwordValid">
                <div id="passwordHelpBlock" class="form-text">
                    공백을 제외한 특수문자, 영문, 숫자를 이용하여 8-16자 이내로 만드세요.
                </div>
                <div v-if="!state.passwordValidFlag" class="form-text">유효하지 않은 비밀번호입니다.</div>

                <input type="password" id="inputPassword2" class="form-control" placeholder="비밀번호 확인" v-model="state.form.password2" @blur="passwordCheckValid">
                <div v-if="!state.passwordCheckFlag" class="form-text">비밀번호가 동일하지 않습니다.</div>

            </div>
             <div class="inputBox">
                <label for="inputEmail" class="form-label">Email</label>
                <input type="email" class="form-control" id="inputEmail" v-model="state.form.email" @blur="emailValid">
                <div v-if="!state.emailValidFlag" class="form-text">이메일을 제대로 입력해주세요.</div>
            </div>
            <div class="inputBox">
                <label for="inputInterest" class="form-label">Interest</label>
                <input type="text" id="inputInterest" class="form-control" placeholder="취미를 입력해주세요" v-model="state.form.interest">
            </div>
            <div class="inputBox">
              <input type="submit" value="Submit">  
              <router-link to="/login" class="btn input-box-btn">Back</router-link>
            </div>
        </form>  
    </div>
{{ state.form }}
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
            form:{
                name: null,
                id : null, 
                password1: null,
                password2: null,
                email: null,
                interest: null,
            },
            idValidFlag: true,
            passwordValidFlag: true,
            passwordCheckFlag: true,
            emailValidFlag: true,
        })
 

        const signupForm = function(){
            // dispatch 함수명 바뀔 경우 아래줄 수정
            if (state.form.name == null || state.form.id == null || state.form.password1 == null || state.form.password2 == null || state.form.email == null || state.form.interest == null) {
                alert('필수값 누락')
                return
            }
            if (!state.idValidFlag || !state.passwordValidFlag || !state.passwordCheckFlag || !state.emailValidFlag) {
                alert('유효성 확인')
                return
            }
            console.log(state.form.id, state.form.password1, state.form.name, state.form.interest, state.form.email  )
            // store.dispatch('signup',{"name": state.form.name, 'id': state.form.id, 'password': state.form.password1, 'email':state.form.email, 'interest': state.form.interest, 'photo':'d'})
            store.dispatch('signup',{"email":state.form.email,"id":state.form.id,"interest":state.form.interest,"name":state.form.name, "password":state.form.password1, "photo": "(String) 사진 사용X"    })
            .then(function (result){
                // alert('accessToken: ' + result.data.Token)
                router.push({name :'Login'})
            })
            .catch(function(err){
                alert(err)
            })
        }

        const idValid = function() {
            if (/^[a-z]+[a-z0-9]{5,19}$/.test(state.form.id)){
                if (state.form.id.includes(' ')){
                    state.idValidFlag = false
                }
                else {
                    state.idValidFlag = true
                }
            } else {
                state.idValidFlag = false
            }
        }

        const passwordValid = function() {
            if (/^(?=.*[a-zA-z])(?=.*[0-9])(?=.*[$`~!@$!%*#^?&\\(\\)\-_=+]).{8,16}$/.test(state.form.password1)) {
                if (state.form.password1.includes(' ')){
                    state.passwordValidFlag = false
                }
                else {
                    state.passwordValidFlag = true
                }
            } else {
                state.passwordValidFlag = false
            }
        }

        const passwordCheckValid = function() {
            if (state.form.password1 === state.form.password2) {
                state.passwordCheckFlag = true
            } else {
                state.passwordCheckFlag = false
            }
        }

        const emailValid = function() {
            if (/^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/.test(state.form.email)) {
                state.emailValidFlag = true
            } else {
                state.emailValidFlag = false
            }
        }

        return {
            state,
            store,
            signupForm,
            idValid,
            passwordValid,
            passwordCheckValid,
            emailValid,
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