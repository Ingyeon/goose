<template>

    <h3>{{ state.form.title}}</h3>
    <div>
        <p>분류: {{ state.form.category  }}</p>
        <p>모집 여부: {{ state.form.state  }}</p>
        <p>작성일자: {{  state.form.date  }}</p>
    </div>
    <div>{{  state.form.content  }}</div>
    <div>
        <!--스터디 페이지와 연결 // 스터디 페이지 완성 되었을 때 연결 -->
        <button @click.prevent="joinStudy(article_log.study_pk)">참여 신청</button>
        <router-link to="/articles">   <button>게시글 목록</button>  </router-link>
        <button @click="article_edit">수정</button>
        <!-- 삭제 백엔드에 url 물어보기 -->
        <button @click="article_delete">삭제</button> 

    </div>

</template>

<script>

import { reactive, watch } from "vue";
import {  useStore  } from 'vuex'
import { useRoute } from 'vue-router';
import { useRouter } from 'vue-router';
// import replyList from "./replyList.vue";

export default{
    setup(){
        const store = useStore()
        const route = useRoute()
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

        const article_delete = function(){
        store.dispatch('deleteArticle',store.getters.article.id)
        }

        const article_edit = function(){
            router.push({name: 'article_edit'})
        }
        article_info()

        return {article_info,store,state,article_delete,article_edit,article_log,joinStudy}
    },

    watch: {
        article_info(){
            console.log('실행됨')
            router.go()
        }
    }
    
}
</script>
