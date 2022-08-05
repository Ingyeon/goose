<template>
    <div class="container">
        <div class="mx-5">
        <h3 class="d-flex justify-content-center my-2">{{ article_log.title}}</h3>
        <div class="d-flex  justify-content-between">
            <p class="mx-2 my-1">스터디명: {{  study_log.title  }}</p>
            <p class="mx-2 my-1">분류: {{ article_log.category  }}</p>
            <p class="mx-2 my-1">모집 여부: {{ article_log.state  }}</p>
            <p class="mx-2 my-1">작성일자: {{  article_log.date  }}</p>
        </div>
        <div class="content-detail">{{  article_log.content  }}</div>
        <div>
            <!--스터디 페이지와 연결 // 스터디 페이지 완성 되었을 때 연결 -->

        <!-- 가입 신청 했을 경우 if문으로 신청 취소 구현 (api 미구현)-->
        <div class="d-flex justify-content-between">
            <router-link to="/articles">   <button class="btn btn-secondary my-2">게시글 목록</button>  </router-link>

        <div>
        <button @click="article_edit" v-if="state.check == true" class="btn btn-primary mx-2 my-2">수정</button>

        <button @click="article_delete" v-if="state.check == true" class="btn btn-danger my-2">삭제</button> 
        </div>
        </div>
        <div class="d-flex justify-content-center">
            <!-- 스터디 이미 참여중인 인원은 참여 신청 안보이게 설정 -->
        <button @click.prevent="joinStudy(article_log.study_pk)" v-if="article_log.state == '모집중'" class="w-btn w-btn-yellow">참여 신청</button>
        </div>
        </div>
        <reply-list/>
    </div>
    </div>



</template>

<script>

import { reactive,computed } from "vue";
import {  useStore  } from 'vuex'
import { useRouter } from 'vue-router';
import replyList from "./replyList.vue";
import { useRoute } from "vue-router";

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
            check: false
        })
        // const article_start = function(){
        // store.dispatch('fetchArticle',route.params.id)
        // }
        // article_start()


        const joinStudy = (studyId) => store.dispatch('joinStudy',studyId)
      
        const article_info = function(){
            state.form.id = store.getters.article.id,
            state.form.title = store.getters.article.title,
            state.form.category = store.getters.article.category,
            state.form.state = store.getters.article.state,
            state.form.date = store.getters.article.date,
            state.form.content = store.getters.article.content
            // 
        }

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
        const owner_check = function(){
            if (store.getters.article.user_pk == store.getters.loginUser.id){
                state.check = true
            }
        }
        owner_check()
        const study_log = computed(() => store.getters.selectedStudy)
        const article_log = computed(() => store.getters.article)

        return {article_info,store,state,article_delete,article_edit,article_log,joinStudy,study_log}
    },
}
</script>

<style>
.content-detail {
  border: 1px solid wheat;
  margin-top: 1rem;
  padding-top: 1rem;
  min-height: 360px;
}


.w-btn {
    position: relative;
    border: none;
    display: inline-block;
    padding: 15px 30px;
    border-radius: 15px;
    font-family: "paybooc-Light", sans-serif;
    box-shadow: 0 15px 35px rgba(0, 0, 0, 0.2);
    text-decoration: none;
    font-weight: 600;
    transition: 0.25s;
}

.w-btn-yellow {
    background-color: #fce205;
    color: #6e6e6e;
}

.w-btn:hover {
    letter-spacing: 2px;
    transform: scale(1.2);
    cursor: pointer;
}
</style>