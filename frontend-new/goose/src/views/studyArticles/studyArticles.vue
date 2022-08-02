<template>
  <h1>게시판</h1>
  <createStudyArticle />

  
</template>

<script>
import studyArticle from '@/components/StudyArticle/studyArticle.vue'
import createStudyArticle from '@/components/StudyArticle/createStudyArticle.vue'
import { computed } from "vue"
import { useStore } from "vuex"
export default {
  components:{
    studyArticle,createStudyArticle,
  },

  setup(){
    const store = useStore()
    const studyArticles = computed(() => store.getters.studyArticles )
    
    return { studyArticles,}
  }
}
</script>

<style>

</style>