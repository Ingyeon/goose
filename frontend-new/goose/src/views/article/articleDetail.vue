<template>

    <h3>{{ article_log.title}}</h3>
    <div>
        <p>스터디 명: {{  study_name  }}</p>
        <p>분류: {{ article_log.category  }}</p>
        <p>모집 여부: {{ article_log.state  }}</p>
        <p>작성일자: {{  article_log.date  }}</p>
    </div>
    <div>{{  article_log.content  }}</div>
    <div>
        <!--스터디 페이지와 연결 // 스터디 페이지 완성 되었을 때 연결 -->

        <!-- 가입 신청 했을 경우 if문으로 신청 취소 구현(api 아직 미구현) -->
        <button @click.prevent="joinStudy(article_log.study_pk)">참여 신청</button>
        <router-link to="/articles">   <button>게시글 목록</button>  </router-link>
        <button @click="article_edit">수정</button>
        <!-- 삭제 백엔드에 url 물어보기 -->
        <button @click="article_delete">삭제</button> 

    </div>
    <reply-list/>
<p>{{ $route.params.id }}</p>
</template>

<script>

import { reactive,computed } from "vue";
import {  useStore  } from 'vuex'
import { useRouter } from 'vue-router';
import replyList from "./replyList.vue";
import { useRoute } from "vue-router";
// import replyList from "./replyList.vue";

export default{
    components: {replyList},
    setup(){
        const route = useRoute()
        const store = useStore()
        const router = useRouter()

        const state = reactive({
            form: {
                id: '',
                title: '',
                category: '',
                state: '',
                date: '',
                content: '',
            },
            componentkey: 0
        })

        const joinStudy = (studyId) => store.dispatch('joinStudy',studyId)
      
        const article_info = function(){
            state.form.id = store.getters.article.id,
            state.form.title = store.getters.article.title,
            state.form.category = store.getters.article.category,
            state.form.state = store.getters.article.state,
            state.form.date = store.getters.article.date,
            state.form.content = store.getters.article.content
            console.log(state.form)
            // 
        }
        
        const article_start = function(){
            store.dispatch('fetchArticle',route.params.id)
        }
        article_start()
        const article_delete = function(){
        store.dispatch('deleteArticle',store.getters.article.id)
        router.push({name: 'ArticleList'})
        }

        const study_name = function(){
            store.dispatch('selectStudy',store.getters.article.study_pk)
        }
        const article_edit = function(){
            router.push({name: 'article_edit'})
        }
        study_name()
        const study_log = computed(() => store.getters.selectedStudy)
        const article_log = computed(() => store.getters.article)

        return {article_info,store,state,article_delete,article_edit,article_log,joinStudy,study_log,article_start}
    },
    
}
</script>
