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

import {  mapGetters,mapActions } from 'vuex'


// vue3에서는 vuex의 패턴들을 쪼개서 관리 하지 않으면 불러오기 까다로워짐 -> vue2 방식으로 처리 
export default {
    name: 'replyForm',

    data(){
        return {
            re_content: ''
        }},
    computed: {
        ...mapGetters(['article'])   // article_pk를 가져오기 위해 불러옴
    },
    methods: {
        ...mapActions(['createReply']),
        onSubmit(){
            this.createReply({ article_pk: this.article.pk, re_content: this.re_content})
            this.re_content = ''
        }
    }
    }

</script>