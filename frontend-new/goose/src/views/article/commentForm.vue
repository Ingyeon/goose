<template>
<div>
    <b-input-group>
    <b-input-group-text>댓글</b-input-group-text>
    <b-form-textarea @submit.prevent="onSubmit" id="textarea" v-model="content" placeholder="댓글을 입력해주세요." required></b-form-textarea>
    <b-input-group-append>
        <b-button variant="outline-secondary" @click="onSubmit">작성</b-button>
    </b-input-group-append>
    </b-input-group>
</div>
</template>

<script>
// import { useStore } from 'vuex'
import { reactive,computed } from 'vue'

export default {
    name: 'CommentForm',

    setup(){
        // const store = useStore
        const state = reactive({
            form: {
                content:''
            }
        })
        // 댓글 api 연결 후 아래 코드 수정
        // const onSubmit = function() {
        //     store.dispatch('경로',{content: state.form.content})  
        // }
        return {state}
    }
}
</script>