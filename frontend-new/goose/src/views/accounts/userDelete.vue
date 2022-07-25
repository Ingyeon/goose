<template>
    <form @submit.prevent="DeleteForm">
        <div class="inputBox">
            <label for="inputPassword" class="form-label">Password</label>
            <input type="password" id="inputPassword" class="form-control" placeholder="비밀번호를 입력해주세요" v-model="status.password1">
            <input type="password" id="inputPassword" class="form-control mt-3" placeholder="비밀번호 확인" v-model="status.password2">
        </div>
        
        <div class="inputBox">
            <input type="submit" value="탈퇴">
            <input type="button" value="Back" @click="moveToHome">
        </div>
    </form>

</template>

<script>
import { reactive } from 'vue'

export default {
     setup() {
        const status = reactive({
            password1: '',
            password2: '',
        })
        const DeleteForm = () => {
            //submit
        }
        
        const moveToHome = () => {
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