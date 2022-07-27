<template>

    <h3>{{form.title}}</h3>
    <div>
        <p>분류: {{ form.category  }}</p>
        <p>모집 여부 {{ form.state  }}</p>
        <p>작성일자: {{  form.date  }}</p>
    </div>
    <div>{{form.content}}</div>
    <div>
        <!--스터디 페이지와 연결 // 스터디 페이지 완성 되었을 때 연결 -->
        <button>참여 신청</button>
        <router-link :to="{  name: ArticleList  }">   <button>게시글 목록</button>  </router-link>
        <button @click="updateArticle">수정</button>
        <button @click="deleteArticle">삭제</button>

    </div>

    <!-- 댓글 목록 / 게시글에 속한 댓글 어떻게 가져오는게 맞는지? -->
    <!-- <reply-list :replies:""></reply-list> -->

</template>

<script>

import { mapGetters, mapActions } from "vuex";
// import replyList from "./replyList.vue";

export default{
    // components: {  replyList },
    data(){
        return{
            id: this.$route.paprams.id,
        }
    },
    computed: {
        ...mapGetters(['isAuthor', 'article'])
    },
    methods: {
        ...mapActions([
            'fetchArticle',
            'deleteArticle',
            'updateArticle',
        ])
    },
    created(){
        this.fetchArticle(this.id)
    }
}
</script>
