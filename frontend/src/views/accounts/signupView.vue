<template>
    <div class="container mt-5">
        <form @submit.prevent="signupForm">
            <div class="input-Box">
                <label for="inputname" class="form-label">이름</label>
                <input type="text" id="inputname" class="form-control" placeholder="이름" v-model="state.form.name">
            </div>
            <div class="input-Box">
                <label for="inputId" class="form-label">ID</label>
                <input type="text" id="inputId" class="form-control" placeholder="ID를 입력해주세요" v-model="state.form.id" maxlength=16 @blur="idValid">
                <div v-if="!state.idValidFlag" class="form-text">유효하지 않은 아이디 입니다. 영문과 숫자를 조합하여 8-16자 이내로 만드세요.</div>
                <div v-if="state.idCommonFlag == true" class="form-text">이미 사용되고 있는 아이디입니다.</div>
                <div v-if="state.idCommonFlag == false" class="form-text">사용 가능한 아이디입니다.</div>
            </div>
            <div class="input-Box" id='no-flex'>
                <label for="inputPassword" class="form-label">Password</label>
                <input type="password" id="inputPassword" class="form-control" placeholder="비밀번호를 입력해주세요" aria-describedby="passwordHelpBlock" v-model="state.form.password1" @blur="passwordValid">
                <div id="passwordHelpBlock" class="form-text mb-3">
                    공백을 제외한 특수문자, 영문, 숫자를 이용하여 8-16자 이내로 만드세요.
                </div>
                <div v-if="!state.passwordValidFlag" class="form-text">유효하지 않은 비밀번호입니다.</div>
                <input type="password" id="inputPassword2" class="form-control" placeholder="비밀번호 확인" v-model="state.form.password2" @blur="passwordCheckValid">
                <div v-if="!state.passwordCheckFlag" class="form-text">비밀번호가 동일하지 않습니다.</div>
            </div>
            <hr class="mt-5 mb-4">
             <div class="input-Box">
                <label for="inputEmail" class="form-label">Email</label>
                <input type="email" class="form-control" id="inputEmail" v-model="state.form.email" @blur="emailValid">
                <div v-if="!state.emailValidFlag" class="form-text">이메일을 제대로 입력해주세요.</div>
                <div v-if="state.emailCommonFlag == true" class="form-text">이미 사용되고 있는 이메일입니다.</div>
                <div v-if="state.emailCommonFlag == false" class="form-text">사용 가능한 이메일입니다.</div>
            </div>
            <div class="input-Box">
                <label for="selectcategory" class="custom-label">관심 언어</label>
                <select v-model="state.form.interest" id="selectcategory" class="form-select" aria-label="Default select example">
                    <!-- <option selected>분류</option> -->
                    <option value="what" selected>관심언어를 골라주세요</option>
                    <option value="C">C</option>
                    <option value="C++">C++</option>
                    <option value="JAVA">JAVA</option>
                    <option value="JavaScript">JavaScript</option>
                    <option value="Python">Python</option>
                    </select>
            </div>
            <div class="input-Box">

              <router-link to="/home"><button> 뒤로가기</button></router-link>
              <input type="submit" value="회원가입">  

            </div>
        </form>  
    </div>
</template>

<script>
import { reactive } from 'vue'
import http from "@/util/http-common.js";
import { useStore } from 'vuex'
import axios from 'axios'

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
                interest: "what",
            },
            idValidFlag: true,
            passwordValidFlag: true,
            passwordCheckFlag: true,
            emailValidFlag : true,
            idCommonFlag : null,
            emailCommonFlag :null,

     })
        const signupForm = function(){
            if (state.form.name == null || state.form.id == null || state.form.password1 == null || state.form.password2 == null || state.form.email == null || state.form.interest == null) {
                alert('필수값을 입력해주세요')
                return
            }
            if (!state.idValidFlag || !state.passwordValidFlag || !state.passwordCheckFlag || !state.emailValidFlag || state.idCommonFlag == true) {
                alert('중복되거나 올바르지 않은 양식이 있습니다.')
                return
            }
            console.log(state.form.id, state.form.password1, state.form.name, state.form.interest, state.form.email  )
            store.dispatch('signup',{
                "email":state.form.email,
                "id":state.form.id,
                "interest":state.form.interest,
                "name":state.form.name, 
                "password":state.form.password1, 
                "photo": "../../assets/profile4.png"})
            .then(function (){
                alert('Goose에 회원가입 해주셔서 감사합니다!')
                store.dispatch('login',{
                    'id': state.form.id,
                    'password': state.form.password1
                })
            })
            .catch(function(err){
                alert(err)
            })}

        const idValid = async function() {
            if (/^[a-z]+[a-z0-9]{5,19}$/.test(state.form.id)){
                if (state.form.id.includes(' ')){
                    state.idValidFlag = false
                }
                else {
                    state.idValidFlag = true
                    state.checkValidFlag = false
                    try{
                        let data = await http({
                            method: 'get',
                            url: `/users/{userId}?userId=${state.form.id}`
                        })
                        console.log(data.status)
                        state.idCommonFlag = true
                    } catch(err) {
                        state.idCommonFlag = false
                        console.log(err.status)
                    }
                }
                
            } else {
                state.idValidFlag = false
                state.idCommonFlag = null
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
            if (state.form.password1 === state.form.password2) {
                state.passwordCheckFlag = true
            } else {
                state.passwordCheckFlag = false
            }
        }

        const emailValid = async function() {
            if (/^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/.test(state.form.email)) {
                state.emailValidFlag = true
                try{
                    let email_data = await http({
                        method: 'get',
                        url: `/users/email?email=${state.form.email}`
                    })
                    if (email_data.data==="OK") {
                        state.emailCommonFlag = false
                    } else {
                        console.log(email_data)
                        state.emailCommonFlag = true
                    }
                    } catch(err) {
                    console.log(err.status)
                }
            } else {
                state.emailValidFlag = false
                state.emailCommonFlag = null
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
        }}
</script>

<style scoped>
input[type="password"]{
    font-family: "Gulim"
}
.form-select {
    width: 100%;
    height: 50px;
    margin-top: 3px;
    border: solid 1px #000;
}
.input-Box:nth-child(7) {
display: flex;
/* justify-content: space-evenly; */
justify-content: end;
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