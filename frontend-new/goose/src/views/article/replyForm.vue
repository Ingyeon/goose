<template>
<div>
    <b-input-group>
    <b-input-group-text>댓글</b-input-group-text>
    <b-form-textarea @submit.prevent="onSubmit" id="textarea" v-model="re_content" placeholder="댓글을 입력해주세요." required></b-form-textarea>
    <b-input-group-append>
        <b-button variant="outline-secondary" @click="onSubmit">작성</b-button>
    </b-input-group-append>
    </b-input-group>
</div>
</template>

<script>
import {reactive} from 'vue'
import {useStore} from 'vuex'

export default {
    name: 'newArticle',
    
    setup(){
        const store = useStore()
        const state = reactive({
            form: {
                re_content: ''
            },
            article: store.getters.article
        })

        const onSubmit = function(){
            store.dispatch('createReply',{article_pk: state.article.id, re_content: state.form.re_content})
            state.form.re_content = ''
        }

    return {onSubmit,store,state}}}

</script>