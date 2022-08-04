<template>
<div class="container">
    <div class="d-flex my-3">
        <h1>최신 게시글</h1>
        <button @click.prevent="moveArticles" class="btn btn-primary mx-3">
          게시판 전체보기
        </button>
    </div>
    <li data-bs-toggle="modal" data-bs-target="#studyArticleDetail" @click="selectArticle(article.id)" v-for="article in state.halfArticleList" :key="article.id">{{article.title}}</li>
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
export default {
    components:{
        studyArticleDetail
    },
    setup(){
        const store = useStore()
        const router = useRouter();
        const state = reactive({
            halfArticleList : [],
        })

        const fetchStudyArticleList = () => {
            store.dispatch('getStudyArticleList',{category:null,page:1,title:null})
            .then(()=>{
                state.halfArticleList = store.getters.studyArticleList
                state.halfArticleList.splice(5)
            })
        }
        fetchStudyArticleList

        
        const moveArticles = () => {
            router.push({
            name: "studyArticles",
            params: { studyPk: store.getters.selectedStudy.id },
            });
        };
        
        const selectArticle = (articleId)=>{
            store.dispatch('getStudyArticle',articleId)
        }

        const selectedStudy = computed(()=> store.getters.selectedStudy)

        return {state,moveArticles,selectArticle,fetchStudyArticleList,selectedStudy}
    },

    watch:{
        selectedStudy:{
            handler(){
                this.fetchStudyArticleList();
            }
        }
    }
}
</script>

<style>

</style>