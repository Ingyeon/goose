<template>
<div class="container">
    <div class="container d-flex justify-content-between">
        <h4>최신 게시글</h4>
        <button @click.prevent="moveArticles" class="btn btn-3 hover-border-3">
            전체보기
        </button>
    </div>
    <div class="container box">
        <ul v-for="article in state.recentlyArticleList" :key="article.id">
            <ul class="d-flex justify-content-between" data-bs-toggle="modal" data-bs-target="#studyArticleDetail" @click="selectArticle">
                <li class="article" >{{article.title}}</li>
                <li class="article-author">{{article.name}}</li>
            </ul>
            <hr>
        </ul>
    </div>
</div>

<div>
    <studyArticleDetail />
</div>

</template>

<script>
import studyArticleDetail from '@/components/StudyArticle/studyArticleDetail.vue'
import { useStore } from "vuex"
import { reactive, watch,computed } from "vue"
import { useRouter } from "vue-router"
import axios from 'axios'
import rest from '@/api/rest'
export default {
    props: {
    item:Object
    },
    components:{
        studyArticleDetail
    },
    setup(props){
        const store = useStore()
        const router = useRouter();
        const state = reactive({
            recentlyArticleList : [],
        })

        const fetchRecentlyArticleList = () => {
            axios({
                url: rest.studyArticle.studyArticleList(),
                method:'get',
                headers: store.getters.authHeader,
                params: { "category":null, "page":1, "studyPk":store.getters.selectedStudy.id, "title":null}
            })
            .then((res)=>{
                const articleList = res.data.content.slice(0,5)
                state.recentlyArticleList = articleList
            })
        }
        // fetchRecentlyArticleList()

        const moveArticles = () => {
            router.push({
            name: "studyArticles",
            params: { studyPk: store.getters.selectedStudy.id },
            });
        };
        
        const selectArticle = ()=>{
            store.dispatch('getStudyArticle',props.item.id)
        }
        
        const selectedStudy = computed(()=> store.getters.selectedStudy)

        return {state,moveArticles,selectArticle,selectedStudy,fetchRecentlyArticleList}
    },

    watch:{
        selectedStudy:{
            handler(){
                console.log("최신 게시글 변경")
                this.fetchRecentlyArticleList();
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