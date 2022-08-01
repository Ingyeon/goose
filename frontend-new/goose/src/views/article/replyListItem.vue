<template>
  <ol>
    <li class="list-group-item py-1">
            <ol v-for = "reply in replies.content" :key="reply">
            <!-- 댓글 api에 댓글 작성자도 받게 부탁? -->
    <ul>{{'작성자명'}}:{{reply.re_content}}</ul></ol>
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

import {reactive, computed} from 'vue'
import {useStore} from 'vuex'
import { useRoute } from 'vue-router'
export default {
    
    setup(){
        const store = useStore()
        const route =useRoute()
        const state = reactive({
            form: {
                re_content: '',
            },
            Name:[]
        })

        const reply_list = function(){
            // reply_page 값은 임의로 1 부여
            store.dispatch('fetchReplies', {article_pk:store.getters.article.id,reply_page:1} )
        }

        // 전체 리스트에서 받아오기 때문에 개별 pk에 따른 사용자명 불러오기 힘듬
        const name_search = function(){
            for (var reply in store.getters.replies){
                store.dispatch('fetchUserInfo',reply.user_pk)
                state.Name.push(store.getters.targetUser.name)
                
            }
            
        }

        reply_list()
        // name_search()
        const name = computed(() => store.getters.article.name)

        const replies = computed(() => store.getters.replies)

    return {store,state,reply_list,name,replies}}}

</script>

<style>

</style>