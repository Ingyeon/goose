<template>
<div>
<hr>
<ul class="d-flex justify-content-between">
    <h6 v-if="state.toggle==0">{{ item.re_content }}</h6> 
    <div class="d-flex">
        <h6 v-if="state.toggle==0">글쓴이: {{ item.name}}</h6>    
        <button v-if="state.toggle==0" @click.prevent="updateToggle" class="btn btn-edit"><img src="@/assets/수정버튼.png" alt=""></button>
        <button v-if="isCommentWriter & state.toggle==0" @click.prevent="deleteComment" class="btn-close"></button>
    </div>
</ul>
<div v-if="state.toggle==1" class="cus-blur">
    <form @submit.prevent="patchComment">
        <div class="m-3 d-flex">
            <!-- <label class="form-label fs-5" for="pComment">댓글 수정</label> -->
            <input class="form-control" v-model="state.re_content" type="text" id="pComment" required/>
            <button id="submit-btn" class="btn">제출</button>
            <button v-if="state.toggle==1" @click.prevent="updateToggle" id="submit-btn" class="btn">취소</button>
        </div>
    </form>
</div>
</div>
</template>

<script>
import { reactive } from '@vue/reactivity'
import { useStore } from "vuex"
import { computed } from '@vue/runtime-core'
export default {
    props:{
        item:Object
    },
    setup(props){
        const store = useStore()
        const state = reactive({
            toggle:0,
            re_content: props.item.re_content
        })
        const deleteComment = ()=>{
            store.dispatch("deleteComment",props.item.id)
        }
        const updateToggle = ()=>{
            state.toggle = (state.toggle+1)%2
        }
        
        const patchComment = ()=>{
            store.dispatch("patchComment",{re_content:state.re_content, id:props.item.id})
            state.re_content = props.item.re_content
            state.toggle = 0
        }

        const isCommentWriter = computed(()=> props.item.user_pk == store.getters.loginUser.id)

        return {deleteComment,updateToggle,state,patchComment,isCommentWriter}
    }
    
}
</script>

<style scoped>
    .btn-edit {
        position:relative;
        bottom:10px;
    }
    .btn-close {
        position:relative;
        bottom:4px;
    }
    #submit-btn {
        width:70px;
    }
</style>