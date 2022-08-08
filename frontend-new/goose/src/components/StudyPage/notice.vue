<template>
    <ul v-for="article in state.noticeList" :key="article.id">
        <div class="d-flex justify-content-between" data-bs-toggle="modal" data-bs-target="#studyArticleDetail" @click="selectArticle" :key="article.id">
            <li class="article" >{{article.title}}</li>
            <li class="article-author">{{article.name}}</li>
        </div>
    </ul>
</template>

<script>
import axios from 'axios'
import rest from '@/api/rest'
import { useStore } from "vuex"
import { reactive, computed } from "vue"
export default {
    setup(){
        const store = useStore();
        const state = reactive({
            noticeList : [],
        })
        const fetchNoticeList = () => {
            axios({
                url: rest.studyArticle.studyArticleList(),
                method:'get',
                headers: store.getters.authHeader,
                params: { "category":"notice", "page":1, "studyPk":store.getters.selectedStudy.id, "title":null}
            })
            .then((res)=>{
                const articleList = res.data.content.slice(0,5)
                state.noticeList = articleList
            })
        }
        const selectedStudy = computed(()=> store.getters.selectedStudy)
        return {state,selectedStudy,fetchNoticeList}
    },

    watch:{
        selectedStudy:{
            handler(){
                this.fetchNoticeList();
            }
        },
    }
}
</script>

<style scoped>
h4 {
    font-weight:bold;
}
* {
    font-family: "NanumSquare", sans-serif;
}
ul li {
    list-style: none;
}

li.article {
    font-size: 1.2rem;
    font-weight: bold;
    cursor: pointer;

}
li.article-author{
    margin-right:15px;
}
.container {
    height:100%;
}
.container.box{
    background-color: #f0f0f1;
    margin-top: 1rem;
    padding-top: 1rem;
    height:83%;
    border-radius: 30px;
    }

:active, :hover, :focus {
outline: 0!important;
outline-offset: 0;
}
::before,
::after {
  position: absolute;
  content: "";
}
.btn {
  position: relative;
  display: inline-block;
  width: auto; height: auto;
  background-color: transparent;
  border: none;
  cursor: pointer;
  margin: 0px 25px 15px;
  min-width: 150px;
}
  .btn span {         
    position: relative;
    display: inline-block;
    font-size: 14px;
    font-weight: bold;
    letter-spacing: 2px;
    text-transform: uppercase;
    top: 0; left: 0;
    width: 100%;
    padding: 15px 20px;
    transition: 0.3s;
  }
.btn.hover-border-3::before,
.btn.hover-border-3::after {
  width: 0%; height: 0%;
  opacity: 0;
  transition: width 0.2s 0.15s linear, height 0.15s linear, opacity 0s 0.35s;
}
.btn.hover-border-3::before {
  top: 0; right: 0;
  border-top: 1px solid rgb(28, 31, 30);
  border-left: 1px solid rgb(28, 31, 30);
}
.btn.hover-border-3::after {
  bottom: 0; left: 0;
  border-bottom: 1px solid rgb(28, 31, 30);
  border-right: 1px solid rgb(28, 31, 30);
}
.btn.hover-border-3:hover::before,
.btn.hover-border-3:hover::after {
  width: 100%; height: 99%;
  opacity: 1;
  transition: width 0.2s linear, height 0.15s 0.2s linear, opacity 0s;   
}
</style>