<template>
  <div>
    <li class="active">오픈 스터디</li>
    <b-card-group class="d-flex justify-content-start">
    <div v-for="article in articleList" :key="article.id">
    <div class="card mcs mx-2 my-2 row row-col-4" style="width: 18rem;" >
  <img src="#" class="card-img-top" alt="alt">
  <div class="card-body">
    <h5 class="card-title">{{article.title}}</h5>
    <p class="card-text">{{article.content}}</p>
    <button @click.prevent="joinStudy(article)" type="button" class="btn btn-primary" >가입신청</button>
  </div>
  </div>
  </div>
  </b-card-group>
  </div>  
</template>

<script>
import { useStore } from "vuex"
import { computed } from "vue"
export default {

  setup(){
    const store = useStore()
    const myStudyList = computed(()=> store.getters.myStudyList)
    const articleList = computed(()=> store.getters.articles)
    const fetchMyStudyList = function(studyId){
      store.dispatch('myStudyList',studyId)
    }
    const joinStudy = (studyId) => store.dispatch('joinStudy',studyId)
    return {myStudyList,fetchMyStudyList,articleList,joinStudy}
  },

  watch: {
    $route: {
      handler() {
        this.fetchMyStudyList()
      },
      immediate: true
    }
  }
}
</script>

<style>
  .notice-item {
    width : 100%;
    height: 300px;
    background-color: #f5d682;
    border: 1px solid red;
    display: flex;
    justify-content: center;
  }
</style>
