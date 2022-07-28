<template>
  <ol>
    <li class="list-group-item py-1">
        <!-- 작성자 누르면 작성자의 프로필 주소로 이동, user_pk로 댓글 작성자 불러오는 작업 필요 -->
<!-- <router-link class="fw-bold" :to="{  name: '#', parmas: {id: 'user_pk'}  }, ">


</router-link> -->
    </li>
    <li class="list-group-item flex-fill" v-if="!isEditing">{{  payload.re_content  }}</li>
    <!-- 수정할 때 나오는 버튼 -->
    <li v-if="isEditing">  
        <input type="text" v-model="payload.re_content">
        <button @click="update_reply">확인</button>
        <button @click="switchEditing">취소</button>
    </li>
    <!-- 수정 하기 전 나오는 버튼 -->
    <span v-if="currentUser.username === comment.user.username && !isEditing" class="px-1">
    <button @click="switchEditing">수정</button>
    <button @click="deleteReply(payload)">삭제</button>
    </span>
  </ol>
</template>

<script>

import { mapGetters, mapActions } from 'vuex'

export default {
    props: {  reply: Object  },
    data(){
        return {
            isEditing: false,
            payload: {
                id: this.reply.id,
                re_content: this.reply.re_content
            },
        }
    },
    computed: {
        ...mapGetters(['fetchLoginUser']),
    },
    metohds: {
        ...mapActions(['updateReply', 'deleteReply']),
        switchEditing(){
            this.isEditing = !this.isEditing
        },
        onUpdate(){
            this.updateReply(this.payload)
            this.isEditing = false
        }
    }
}
</script>

<style>

</style>