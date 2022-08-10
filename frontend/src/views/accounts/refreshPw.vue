<template>
<div class="container">
        <div class="input-Box">
            <label for="inputName" class="form-label">ID</label>
            <input type="text" id="inputName" class="form-control" placeholder="회원정보에 등록한 아이디를 입력해주세요" v-model="status.id">
        </div>
        <div class="input-Box">
            <label for="inputName" class="form-label">이메일</label>
            <input type="email" id="inputName1" class="form-control" placeholder="회원정보에 등록한 이메일을 입력해주세요" v-model="status.email">
        </div>
        
        <div class="input-Box">
            <button @click="FindForm">PW재발급받기</button>
        <router-link to="/idpasswordselect" class="button-link"> <button>이전 페이지</button> </router-link>
    </div>
</div>
</template>

<script>
import { reactive, computed } from 'vue'
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
           email : ''
        })
        
        // const CheckFindId = () => {
        //     if (findid === "Wrong Info") {
        //         findid = "조회되는 아이디가 없습니다."
        //     }
        // }
        const FindForm = async function(){
            try {
                let data = await axios.get(`http://localhost:8080/api/v1/users/findpw?email=${status.email}&userId=${status.id}`)
                console.log(data)
                router.push({name:{}})
            }
            catch(err){
                console.log(err)
                alert('ID와 이메일을 확인해주세요')
            }
        }
            
        return {
            status,
            router,
            store,
            FindForm,
            findid
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
    button:not(.btn-close) {
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
</style>