<template>
  <ol class="m-0 p-0">
    <li class="list-group py-1">
            <ol v-for = "reply in replies" :key="reply.id">

      <div class="bg-warning">{{reply.name}}</div>
    <ul class="list-group-item d-flex justify-content-between">
    {{reply.re_content}}
        <div v-if="state.isEditing">
    <input type="text" v-model="state.form.re_content">
    <button @click.prevent="update_reply(reply.id)" class="mx-2">완료</button>
    <button @click.prevent="switchEditing()" class="mx-2">취소</button>
    </div>
        <div v-if="loginUser.id === reply.user_pk && state.isEditing == false" class="px-1">
    <button @click.prevent="switchEditing()" class="mx-2">수정</button>
    <button @click.prevent="deleteReply(reply.id)" class="mx-2">삭제</button>
    </div></ul></ol>
    </li>
  </ol>
</template>

<script>

import { useRouter } from 'vue-router'
import { computed, reactive } from 'vue'
import {useStore} from 'vuex'
export default {
    
    setup(){
        const store = useStore()
        const router =useRouter()
        const state = reactive({
          isEditing: false,
          form: {re_content:''}
        })

        const update_reply = function(reply_id){
        console.log(reply_id)
        store.dispatch('updateReply',{id: reply_id,re_content: state.form.re_content})
        }

        const deleteReply = function(reply_id){
          store.dispatch('deleteReply', reply_id)
        }

        const switchEditing = function(){
          state.isEditing = !state.isEditing
        }
        

        const reply_list = function(){
            // reply_page 값은 임의로 1 부여
            store.dispatch('fetchReplies', {article_pk:store.getters.article.id,reply_page:1} )
        }
        reply_list()

        const loginUser = computed(() => store.getters.loginUser)

        const replies = computed(() => store.getters.replies)

    return {store,reply_list,replies,update_reply,state,deleteReply,loginUser,switchEditing}}}

</script>

<style>

</style>