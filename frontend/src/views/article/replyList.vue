<template>
 <h4>댓글 목록</h4>
 <replyListItem/>
 <reply-form/> 
</template>

<script>
import ReplyNation from './pagenation/replyNation.vue'
import replyForm from './replyForm.vue'
import replyListItem from './replyListItem.vue'
import {  useStore  } from 'vuex'
import {  computed  } from 'vue'

export default {
    components: {
        replyForm,
        replyListItem,
        ReplyNation,
    },
    setup(){
        }
    }
</script>

<style>

</style>