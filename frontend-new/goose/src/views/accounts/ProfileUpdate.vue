<template>
    <div class="container mt-5">
        <form @submit.prevent="ConfirmForm">
            <div class="name">
                <span>이름</span>
                    <!-- {{ this.$route.params.username }} -->
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
            <div class="article-content">
                <div> 자기소개 글 </div>
                <textarea v-model="content" name="article-content" id="article-content" cols="40" rows="5">
                    자기소개 글 작성
                </textarea>
            </div>
            <div class="inputBox">
              <input type="submit" value="Confirm">
              <input type="button" value="Back" @click="moveToProfile">
            </div>
        </form>  
    </div>
</template>

<script>
import { reactive } from 'vue'

export default {
     setup() {
        const status = reactive({
            id : '',
            password1: '',
            password2: '',
            content: '',
        })
        const ConfirmForm = () => {
            //submit
        }
        
        const moveToProfile = () => {
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

  .article-content{
    height: 60%;
    display: flex;
    flex-direction: column;
    align-items: flex-start;
  }


  .article-content textarea {
    margin-top: 20px;
    width: 100%;
    height: 80%;
    border-radius: 10px;
    /* outline: none; */
    /* border:none; */
    padding: 20px;
  }
</style>