<template>
  <div  ref="scrollComponent">
    <li class="active">모집중인 스터디</li>
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
import { computed, reactive,ref,onMounted,onUnmounted } from "vue"
export default {

  setup(){
    const store = useStore()
    const scrollComponent = ref(null)
    const myStudyList = computed(()=> store.getters.myStudyList)
    const articleList = computed(()=> store.getters.articles)
    const fetchMyStudyList = function(studyId){
      store.dispatch('myStudyList',studyId)
    }
    const state = reactive({
        pushedArticles: [],
        index: 1
    })
    
    const listReset = async function(){
      await store.dispatch('fetchArticles',1)
      state.pushedArticles.push(store.getters.articles)
    }
    listReset()

    const ListScroll = async function() {
        state.index += 1
        let newArticles = store.dispatch('fetchArticles',state.index)
        state.pushedArticles.push(newArticles)
    }
    
        onMounted(() => {
        window.addEventListener("scroll", handleScroll)
    })
        onUnmounted(() => {
        window.removeEventListener("scroll", handleScroll)
    })

    const handleScroll = (e) => {
  let element = scrollComponent.value
  if (element.getBoundingClientRect().bottom < window.innerHeight) {
    ListScroll()
  }
}

    const joinStudy = (studyId) => store.dispatch('joinStudy',studyId)
    return {state, myStudyList,fetchMyStudyList,articleList,joinStudy,ListScroll}
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
