<template>
<div class="container">
        <div class="input-Box">
            <label for="inputName" class="form-label">이름</label>
            <input type="text" id="inputName" class="form-control" placeholder="회원정보에 등록한 이름을 입력해주세요" v-model="status.name">
        </div>
        <div class="input-Box">
            <label for="inputName" class="form-label">이메일</label>
            <input type="email" id="inputName1" class="form-control" placeholder="회원정보에 등록한 이메일을 입력해주세요" v-model="status.email">
        </div>
        
        <div class="input-Box">
            <button @click="FindForm">ID찾기</button>
            <!-- <div v-if="!status.validcheck" class="modal fade" id="idModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">{{status.name}}님의 아이디</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <div class="container">
                            <div class="input-Box">
                                <label for="IdInput" class="form-label">ID</label> 
                                <div class="form-lable">{{findid}}</div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div> -->
        <router-link to="/idpasswordselect" class="button-link"> <button>이전 페이지</button> </router-link>
    </div>
</div>
</template>

<script>
import { reactive, computed } from 'vue'
import {useStore} from 'vuex'

export default {
     setup() {
        const store = useStore()
        // const token = computed(() => store.getters.isLoggedIn)
        const findid = computed(()=>store.getters.findId)
        const status = reactive({
            validcheck: 0,
           name : '',
           email : ''
        })
        
        // const CheckFindId = () => {
        //     if (findid === "Wrong Info") {
        //         findid = "조회되는 아이디가 없습니다."
        //     }
        // }
        const FindForm = () => {
            store.dispatch("findId", {"email": status.email, "name": status.name})
            .then(() =>{
                status.validcheck = 1
        })
        }
        return {
            status,
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