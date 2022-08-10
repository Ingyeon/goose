<template>
<div v-if="isStudyMember" class="cus-blur">
    <form @submit.prevent="createComment">
    
    <div class="m-3">
        <label class="form-label fs-5" for="comment">댓글 작성</label>
        <div class="d-flex justify-content-between">
            <input class="form-control" v-model="state.re_content" type="text" id="comment" required/>
            <button class="button">작성하기</button>
        </div>
    </div>
</form>
</div>
</template>

<script>
import { reactive } from '@vue/reactivity'
import { useStore } from "vuex"
import { computed } from "vue";
export default {
    setup(){
        const store = useStore()
        const state = reactive({
            re_content: "",
        })
        const createComment = ()=>{
            store.dispatch('createComment',state.re_content)
            state.re_content = ""
        }
        const isStudyMember = computed(()=> store.getters.isStudyMember)
        return {createComment,state,isStudyMember}
    }
}
</script>

<style scoped>
    .button {
        background: #ffd700;
        color: #000000;
        cursor: pointer;
        width:100px;
        margin-left:5px;
        /* width: 110px;
        height: 40px; */
        /* margin-bottom: 20px;
        font-size: 1.25rem;*/
        font-weight: bold; 
        text-align: center;
        border-radius: 80px 40px;
        border: none;
    }
</style>