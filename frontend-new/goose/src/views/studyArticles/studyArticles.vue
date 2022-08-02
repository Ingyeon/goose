<template>
  <h1>게시판</h1>
  <createStudyArticle />
  <studyArticleItem v-for="item in studyArticleList" :key="item.id" :item="item" />
  <hr>
  <div>
    <studyArticleDetail />
  </div>
  
</template>

<script>
import createStudyArticle from '@/components/StudyArticle/createStudyArticle.vue'
import studyArticleItem from '@/components/StudyArticle/studyArticleItem.vue'
import studyArticleDetail from '@/components/StudyArticle/studyArticleDetail.vue'
import { computed } from "vue"
import { useStore } from "vuex"
export default {
  components:{createStudyArticle,studyArticleItem,studyArticleDetail
  },

  setup(){
    const store = useStore()
    const studyArticles = computed(() => store.getters.studyArticles )
    const studyArticleList = computed(() => store.getters.studyArticleList)
    const selectedArticle = computed(() => store.getters.selectedArticle)
    const fetchStudyArticleList = () => {
      store.dispatch('getStudyArticleList',{category:null,page:1,title:null})
    }
    fetchStudyArticleList()
    return { studyArticles,studyArticleList,selectedArticle}
  }
}
</script>

<style>

</style>