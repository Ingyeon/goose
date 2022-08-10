<template>
    <div class="container">
        <div class="mx-5">
        <div class="d-flex justify-content-center my-2 header">
            <h2 class="fw-bold">{{ article_log.title}}</h2>
            <div class="state">
                <h5 class="button-on" v-if="article_log.state=='모집중'">{{ article_log.state  }}</h5>
                <h5 class="button-off" v-if="article_log.state=='모집완료'">{{ article_log.state  }}</h5>
            </div>
        </div>
        <div v-if="state.check == true && state.toggle==false" class="d-flex justify-content-end">
            <button class="btn" @click="article_edit" v-if="state.check == true" style="cursor: pointer">수정</button>
            <button class="btn" @click="article_delete" v-if="state.check == true" style="cursor: pointer">삭제</button>
        </div>
        <!-- <h5 class="article-param mx-2 my-1">모집여부: {{ article_log.state  }}</h5> -->
        <!-- {{article_log}} -->
        <hr>
        <div class="d-flex justify-content-evenly mx-5">
            <div class="container">
                <h5 class="fw-bold article-param mx-5 my-3">스터디명: {{  study_log.title  }}</h5>
                <h5 class="fw-bold article-param mx-5 my-3">사용언어: {{ article_log.category  }}</h5>
            </div>
            <div class="container">
                <h5 class="fw-bold article-param mx-5 my-3">작성자: {{article_log.name}}</h5>
                <h5 class="fw-bold article-param mx-5 my-3">모집인원: {{  article_log.recruitment  }}명</h5>
            </div>
        </div>
            
        <div class="content-detail">
            <div class="container">
                <div class="detail">
                    {{  article_log.content  }}
                </div>
            </div>
        </div>
        <div>
            <!--스터디 페이지와 연결 // 스터디 페이지 완성 되었을 때 연결 -->

        <!-- 가입 신청 했을 경우 if문으로 신청 취소 구현 (api 미구현)-->
        <div class="d-flex justify-content-center">
            <router-link to="/articles">   <button class="btn btn-secondary my-2">게시글 목록</button>  </router-link>

        </div>
        <div class="d-flex justify-content-center">
            <!-- 스터디 이미 참여중인 인원은 참여 신청 안보이게 설정 -->
        <button @click.prevent="joinStudy(article_log.study_pk)" v-if="article_log.state == '모집중' & state.check == false" class="w-btn w-btn-yellow">참여 신청</button>
        </div>
        </div>

        <reply-list class='py-6'/>
    </div>
    </div>
    <div class="py-5"></div>



</template>

<script>

import { reactive,computed } from "vue";
import {  useStore  } from 'vuex'
import { useRouter } from 'vue-router';
import replyList from "./replyList.vue";
import { useRoute } from "vue-router";

// 참여신청 이상하게 뜨는 버그
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
            check: false,
            toggle: false
        })
        // const article_start = function(){
        // store.dispatch('fetchArticle',route.params.id)
        // }
        // article_start()

        const switching = function(){
            if (state.toggle == false){state.toggle = true}
            else {state.toggle=false}
        }

        const joinStudy = function(studyId) { 
        if (state.check == false){
        store.dispatch('joinStudy',studyId)}}
      
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

        return {article_info,store,state,article_delete,article_edit,article_log,joinStudy,study_log,switching}
    },
}
</script>

<style>
.header{
    position:relative;
    left:2%
}
.state{
    position: relative;
    top:11px;
    left:2%
}

.button-on{
    display: flex;
    align-items: center;
    justify-content: center;
    font-weight: bold;
    color: #fff;
    width:80px;
    height:30px;
    border-radius: 40px;
    background-color: #13df24;

}
.button-off{
     display: flex;
    align-items: center;
    justify-content: center;
    font-weight: bold;
    color: #fff;
    width:100px;
    height:30px;
    border-radius: 40px;
    background-color: #6e6e6e;
}
.content-detail {
  border: 5px solid wheat;
  border-radius: 30px;
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

.detail {
    font-weight: 600;
    font-size: 1.25rem
}
</style>