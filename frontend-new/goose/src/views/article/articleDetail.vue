<template>

    <h3>{{article.title}}</h3>
    <CommentForm/>
</template>

<script>
import commentForm from "./commentForm.vue";

export default{
    components: {commentForm},
    setup(){
        
    }
}
</script>