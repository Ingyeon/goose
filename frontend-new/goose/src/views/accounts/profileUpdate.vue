<template>
  <div class="container mt-5">
        <form @submit.prevent="updateForm">
            <div class="inputBox">
                <label for="inputname" class="form-label">이름</label>
                <input type="text" id="inputname" class="form-control" placeholder="이름" v-model="state.form.name">
            </div>
            <div class="inputBox">
                <label for="inputId" class="form-label">ID</label>
                <input type="text" id="inputId" class="form-control" placeholder="ID를 입력해주세요" v-model="state.form.id" maxlength=16>
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
              <router-link to="/profile" class="btn input-box-btn">Back</router-link>
            </div>
        </form>  
    </div>
</template>

<script>
export default {
    setup() {
        const updateForm = function() {
        
        }
        return {
            updateForm
        }
    }
}
</script>

<style>

</style>