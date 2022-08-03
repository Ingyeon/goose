<template>
<div class=""></div>
<h6>댓글 내용: {{ item.re_content }}, 글쓴이: {{ item.name}}</h6>
<button v-if="isCommentWriter" @click.prevent="deleteComment" class="btn">댓글 삭제</button>
<!-- <button @click.prevent="updateToggle" class="btn">댓글 수정</button>
<div v-if="state.toggle==1" class="cus-blur">
    <form @submit.prevent="patchComment">
    <div class="m-3">
    <label class="form-label fs-5" for="pComment">댓글 수정</label>
    <input class="form-control" v-model="state.re_content" type="text" id="pComment" required/>
    </div>
    <div>
    <button class="btn">댓글 수정</button>
    </div>
    </form>
</div> -->
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
            re_content:""
        })
        const deleteComment = ()=>{
            store.dispatch("deleteComment",props.item.id)
        }
        const updateToggle = ()=>{
            state.toggle = (state.toggle%2)+1
        }
        
        const patchComment = ()=>{
            store.dispatch("patchComment",{re_content:state.re_content, id:props.item.id})
        }

        const isCommentWriter = computed(()=> props.item.user_pk == store.getters.loginUser.id)

        return {deleteComment,updateToggle,state,patchComment,isCommentWriter}
    }
    
}
</script>

<style>

</style>