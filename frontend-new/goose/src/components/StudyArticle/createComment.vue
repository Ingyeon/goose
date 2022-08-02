<template>
<div class="cus-blur">
    <form @submit.prevent="createComment">
    <div class="m-3">
    <label class="form-label fs-5" for="comment">댓글 작성</label>
    <input class="form-control" v-model="state.re_content" type="text" id="comment" required/>
    </div>
    <div>
    <button class="btn">작성 하기</button>
    </div>
</form>
</div>
</template>

<script>
import { reactive } from '@vue/reactivity'
import { useStore } from "vuex"
export default {
    setup(){
        const store = useStore()
        const state = reactive({
            article_pk: store.getters.selectedArticle.id,
            re_content: "",
            study_pk: store.getters.selectedStudy.id
        })
        const createComment = ()=>{
            store.dispatch('createComment',state)
        }
        return {createComment,state}
    }
}
</script>

<style>

</style>