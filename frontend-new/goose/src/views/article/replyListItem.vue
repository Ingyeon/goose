<template>
  <ol>
    <li class="list-group-item py-1">
        <!-- 라우터 링크 프로필 링크로 대체
<router-link class="fw-bold" :to="{  name: '#', parmas: {id: 'user_pk'}  }, ">
댓글 작성자 필드 어떻게 불러오는지 백엔드측에 물어봐야함
{{ reply.user }}
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
//  오류 발생중
//  원인: pk 값을 인식 못함 

import { mapGetters, mapActions } from 'vuex'

export default {
    props: {  reply: Object  },
    data(){
        return {
            isEditing: false,
            payload: {
                reply_pk: this.reply.pk,
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