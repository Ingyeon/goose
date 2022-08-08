<template>
  <ol class="m-0 p-0">
    <li class="list-group py-1">
            <ol v-for = "reply in replies" :key="reply.id">
            
      <div class="bg-warning">{{reply.name}}</div>
    <ul class="list-group-item d-flex justify-content-between">
    {{reply.re_content}}
        <!-- <div v-if="state.isEditing">
    <input type="text" v-model="state.form.re_content">
    <button @click.prevent="update_reply(reply.id)" class="mx-2 check-button"></button>
    <button @click.prevent="switchEditing()" class="mx-2 btn-close p-0"></button>
    </div> -->
        <div v-if="loginUser.id === reply.user_pk && state.isEditing == false" class="px-1">
    <!-- <button @click.prevent="switchEditing()" class="img-button"></button> -->
    <button @click.prevent="deleteReply(reply.id)" class="mx-2 p-0 btn-close size"></button>
    </div></ul></ol>
    </li>
  </ol>
</template>

<script>

import { computed, reactive } from 'vue'
import {useStore} from 'vuex'
export default {
    
    setup(){
        const store = useStore()
        const state = reactive({
          isEditing: false,
          form: {re_content:''}
        })

        // const update_reply = function(reply_id){
        // console.log(reply_id)
        // store.dispatch('updateReply',{id: reply_id,re_content: state.form.re_content})
        // }

        const deleteReply = function(reply_id){
          store.dispatch('deleteReply', reply_id)
        }

        const switchEditing = function(){
          state.isEditing = !state.isEditing
        }
        
        const reply_list = async function(){
            // reply_page 값은 임의로 1 부여
            await store.dispatch('fetchReplies', {article_pk:store.getters.article.id,reply_page:1} )
        }
        reply_list()

        const loginUser = computed(() => store.getters.loginUser)

        const replies = computed(() => store.getters.replies)

    return {store,reply_list,replies,state,deleteReply,loginUser,switchEditing}}}

</script>

<style scoped >

button.img-button {
        background: url( "@/assets/수정버튼.png" ) no-repeat;
        border: none;
        width: 24px;
        height: 24px;
        cursor: pointer;
      }

.check-button {
          background: url( "@/assets/체크.png" ) no-repeat;
        border: none;
        width: 24px;
        height: 24px;
        cursor: pointer;
}

.size {
          width: 24px;
        height: 24px;
}
</style>