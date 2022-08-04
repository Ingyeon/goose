<template>
  <h1>게시판</h1>
  <createStudyArticle />
  <studyArticleItem v-for="item in studyArticleList" :key="item.id" :item="item" />
  <!-- pagination -->
  <hr>
  <nav aria-label="Page navigation example">
  <ul class="pagination">
    <li @click="pageDown" class="page-item"><a class="page-link" href="#">Previous</a></li>
    <li @click="pageUp" class="page-item"><a class="page-link" href="#">Next</a></li>
  </ul>
</nav>
  
  <hr>
  <div>
    <studyArticleDetail />
  </div>
  
</template>

<script>
import createStudyArticle from '@/components/StudyArticle/createStudyArticle.vue'
import studyArticleItem from '@/components/StudyArticle/studyArticleItem.vue'
import studyArticleDetail from '@/components/StudyArticle/studyArticleDetail.vue'
import { computed, reactive } from "vue"
import { useStore } from "vuex"
import _ from 'lodash'
export default {
  components:{createStudyArticle,studyArticleItem,studyArticleDetail
  },

  setup(){
    const store = useStore()
    const studyArticles = computed(() => store.getters.studyArticles )
    const studyArticleList = computed(() => store.getters.studyArticleList)
    const selectedArticle = computed(() => store.getters.selectedArticle)
    // const isArticleList = computed(()=> !_.isEmpty(store.getters.studyArticleList))

    const data = reactive({
      page:1,
      isArticleList: !_.isEmpty(store.getters.studyArticleList)
    })

    const pageUp = ()=>{
      // if (!_.isEmpty(store.getters.studyArticleList)){
      if (store.getters.studyArticleList.length ==10){
        data.page++;
        fetchStudyArticleList();
      }
    }

    const pageDown = ()=>{
      if(data.page>1){
        data.page--;
        fetchStudyArticleList();
      }
    }

    const fetchStudyArticleList = () => {
      store.dispatch('getStudyArticleList',{category:null,page:data.page,title:null})
    }
    fetchStudyArticleList()
    return { studyArticles,studyArticleList,selectedArticle,pageUp,pageDown,data}
  }
}
</script>

<style>

</style>