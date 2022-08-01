<template>
  <ol>
    <li class="list-group-item py-1">
            <ol v-for = "reply in replies.content" :key="reply">
    <ul>{{reply.name}}:{{reply.re_content}}</ul></ol>
    </li>

    <!-- <li class="list-group-item flex-fill" v-if="!isEditing">{{  reply.re_content  }}</li> -->
    <!-- 수정할 때 나오는 버튼 -->
    <!-- <li v-if="isEditing">  
        <input type="text" v-model="payload.re_content">
        <button @click="update_reply">확인</button>
        <button @click="switchEditing">취소</button>
    </li> -->
    <!-- 수정 하기 전 나오는 버튼 -->
    <!-- <span v-if="currentUser.username === comment.user.username && !isEditing" class="px-1">
    <button @click="switchEditing">수정</button>
    <button @click="deleteReply(payload)">삭제</button>
    </span> -->
  </ol>
</template>

<script>

import { computed } from 'vue'
import {useStore} from 'vuex'
export default {
    
    setup(){
        const store = useStore()


        const reply_list = function(){
            // reply_page 값은 임의로 1 부여
            store.dispatch('fetchReplies', {article_pk:store.getters.article.id,reply_page:1} )
        }
        reply_list()

        const replies = computed(() => store.getters.replies)

    return {store,reply_list,replies}}}

</script>

<style>

</style>