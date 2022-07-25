<<<<<<< HEAD
<!-- <template>

    <h3>{{form.title}}</h3>
=======
<template>

    <h3>{{article.title}}</h3>
>>>>>>> 2e09ea8d (feat: 스터디 게시판 + 댓글)
    <CommentForm/>
</template>

<script>
import commentForm from "./commentForm.vue";
<<<<<<< HEAD
import {reactive} from 'vue'
import {useStore} from 'vuex'

export default{
    components: {commentForm},
    setup(){
        const store = useStore()
        const state = reactive({

            form: {
            recruitment: '',
            category: '',
            title: '',
            content: '',
            state: '모집중',
            }
        })
        const articleDetail = function(){
            store.dispatch(' ')
        }
    }
}
</script> -->
=======

export default{
    components: {commentForm}

}
</script>
>>>>>>> 2e09ea8d (feat: 스터디 게시판 + 댓글)
