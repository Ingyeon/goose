<template>
    <div class="container mt-5">
        <form @submit.prevent="submitForm">
            <div class="inputBox">
                <label for="inputname" class="form-label">이름</label>
                <input type="text" id="inputname" class="form-control" placeholder="이름" v-model="status.username">
            </div>
            <div class="inputBox">
                <label for="inputId" class="form-label">ID</label>
                <input type="text" id="inputId" class="form-control" placeholder="ID를 입력해주세요" v-model="status.id">
            </div>
            <div class="inputBox">
                <label for="inputPassword" class="form-label">Password</label>
                <input type="password" id="inputPassword" class="form-control" placeholder="비밀번호를 입력해주세요" aria-describedby="passwordHelpBlock" v-model="status.password1">
                <div id="passwordHelpBlock" class="form-text">
                    Your password must be 8-20 characters long, contain letters and numbers, and must not contain spaces, special characters, or emoji.
                </div>
                <input type="password" id="inputPassword" class="form-control" placeholder="비밀번호 확인" v-model="status.password2">
            </div>
             <div class="inputBox">
                <label for="inputEmail" class="form-label">Email</label>
                <input type="email" class="form-control" id="inputEmail">
            </div>
            <div class="inputBox">
              <input type="submit" value="Submit">
              <input type="button" value="Back" @click="moveToLogIn">
            </div>
        </form>  
    </div>
</template>

<script>
import { reactive } from 'vue'

export default {
     setup() {
        const status = reactive({
            username: '',
            id : '',
            password1: '',
            password2: '',
            email: '',
        })
        const submitForm = () => {
            //submit
        }
        
        const moveToLogin = () => {
            //moveToLogin
        }
        return {
            status,
        }
    },
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

    /* .inputBox input {
    width: 100%;
    background: rgba(255, 255, 255, 0.2);
    border: none;
    outline: none;
    padding: 10px 20px;
    border-radius: 35px;
    border: 1px solid rgba(255, 255, 255, 0.5);
    border-right: 1px solid rgba(255, 255, 255, 0.2);
    border-bottom: 1px solid rgba(255, 255, 255, 0.2);
    font-size: 16px;
    color: #666;
    box-shadow: 0 5px 15px rgba(255, 255, 255, 0.05);
    } */

    .inputBox input::placeholder {
    color: #666
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