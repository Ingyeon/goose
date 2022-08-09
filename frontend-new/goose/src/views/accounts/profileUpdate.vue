<template>
  <div class="container mt-5">
        <form @submit.prevent="updateForm">
            <div class="input-Box">
                <label for="imagename" class="form-label">프로필이미지</label>
                
                <ul class="d-flex justify-content-evenly">
                    <li>
                        <input name="profile" v-model="state.form.photo" type="radio" value="../../assets/profile4.png" id="../../assets/profile4.png">
                        <label for="../../assets/profile4.png"><img src="../../assets/profile4.png"></label>
                    </li>
                    <li>
                        <input name="profile" v-model="state.form.photo" type="radio" value="../../assets/profile1.png" id="../../assets/profile1.png">
                        <label for="../../assets/profile1.png"><img src="../../assets/profile1.png"></label>
                    </li>
                    <li>
                        <input name="profile" v-model="state.form.photo" type="radio" value="../../assets/profile2.jpg" id="../../assets/profile2.jpg">
                        <label for="../../assets/profile2.jpg"><img src="../../assets/profile2.jpg"></label>
                    </li>
                    
                </ul>
            </div>
            <div class="input-Box">
                <label for="inputname" class="form-label">이름</label>
                <input type="text" id="inputname" class="form-control" disabled="disabled" placeholder="이름" v-model="state.form.name">
            </div>
            <div class="input-Box">
                <label for="inputId" class="form-label">ID</label>
                <input type="text" id="inputId" class="form-control" disabled="disabled" placeholder="ID를 입력해주세요" v-model="state.form.id" maxlength=16>
            </div>
            <div class="input-Box">
                <label for="inputInfo" class="form-label">자기소개</label>
                <textarea name="inputinfo" id="inputinfo" cols="30" rows="10" v-model="state.form.info" placeholder="자기소개글을 입력해주세요"></textarea>
            </div>
             <div class="input-Box">
                <label for="inputEmail" class="form-label">Email</label>
                <input type="email" class="form-control" id="inputEmail" v-model="state.form.email" @blur="emailValid">
                <div v-if="!state.emailValidFlag" class="form-text">이메일을 제대로 입력해주세요.</div>
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
              <input type="submit" value="변경하기">  
              <button @click="BackProfile">뒤로가기</button>
            </div>
        </form>  
    </div>
</template>

<script>
import { reactive,computed } from 'vue'
import { useStore } from 'vuex'
// import router from '../../router'
import { useRouter } from 'vue-router'
export default {
    setup() {
        const store = useStore()
        const router = useRouter()
        const state = reactive({
            form:{
                id : store.getters.loginUser.userId,
                name: store.getters.loginUser.name,
                info: store.getters.loginUser.info,
                email: store.getters.loginUser.email,
                interest: store.getters.loginUser.interest,
                photo: store.getters.loginUser.photo,
            }
        })
        const updateprofile = function() {
            state.form.info = store.getters.loginUser.info
            state.form.email = store.getters.loginUser.email
            state.form.interest = store.getters.loginUser.interest            
        }
        const updateForm = function() {
            store.dispatch('profileUpdate',{
                "name": state.form.name,
                "info": state.form.info,
                "email": state.form.email,
                "interest": state.form.interest,
                "photo" : state.form.photo
            })
            
            .then(() => {
                // console.log('then')
                updateprofile()
                alert('프로필이 변경되었습니다.')})
            console.log(store.getters.loginUser.photo)
            
            console.log(store.getters.loginUser.info)
            // updateprofile()

            // console.log(store.getters.loginUser.info)
        }
        
        const BackProfile = function() {
            console.log(store.getters.loginUser)
            router.push({name:"UserProfile"})
        }
        // const checkOnlyOne = function(element) {

        //     console.log(element)
        //     document.getElementsByName('profile')
        //         .forEach(cb=>cb.checked=false);
        //     element.checked = true;
        // }

        return {
            state,
            updateprofile,
            updateForm,
            BackProfile,
            // checkOnlyOne
        }
    }
}
</script>

<style scoped>
ul {
  list-style-type: none;
}

li {
  display: inline-block;
}

input[type="radio"][id^="../../"] {
  display: none;
}

label {
  border: 1px solid #fff;
  padding: 10px;
  display: block;
  position: relative;
  margin: 10px;
  cursor: pointer;
}

label:before {
  background-color: white;
  color: white;
  content: " ";
  display: block;
  border-radius: 50%;
  border: 1px solid grey;
  position: absolute;
  top: -5px;
  left: -5px;
  width: 25px;
  height: 25px;
  text-align: center;
  line-height: 28px;
  transition-duration: 0.4s;
  transform: scale(0);
}

label img {
  height: 250px;
  width: 250px;
  transition-duration: 0.2s;
  transform-origin: 50% 50%;
}

:checked + label {
  border-color: #ddd;
}

:checked + label:before {
  content: "✓";
  background-color: grey;
  transform: scale(1);
}

:checked + label img {
  transform: scale(0.9);
  /* box-shadow: 0 0 5px #333; */
  z-index: -1;
}
.input-Box button{
    background: #ffd700;
    color: #000000;
    cursor: pointer;
    width: 100px;
    margin-bottom: 20px;
    font-weight: 600;
    text-align: center;
    border-radius: 40px 80px;
    margin-right: 30px;
  }

  textarea {
    width: 100%;
    height: 200px;
    padding: 10px;
    box-sizing: border-box;
    border: solid 1px #000;
    border-radius: 5px;
    font-size: 16px;
    resize: none;
    }
  .input-Box:nth-child(7) {
    display: flex;
    justify-content: end;
    }
  .input-Box input[type="submit"] {
    margin-right:4px;
  }
  .form-select {
    width: 100%;
    height: 50px;
    margin-top: 3px;
    border: solid 1px #000;
}
</style>