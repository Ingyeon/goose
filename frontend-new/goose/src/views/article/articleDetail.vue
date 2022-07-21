<template>
<!-- 작성한 게시글 상세보기는 필드와 연관이 크기 때문에 작업 미뤄둠 -->
    <h1>스터디 모집 게시판</h1>
    <div>
        <!-- <h3>{{article.title}}</h3> -->
    </div>
    <CommentForm/>
</template>

<script>
import CommentForm from "./commentForm.vue";

export default{
    components: CommentForm
}
</script>