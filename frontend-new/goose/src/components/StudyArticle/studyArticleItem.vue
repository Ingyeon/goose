<template>
<hr>
<div class="container d-flex">
  <h3 class="m-3"> 제목:{{ item.title }}</h3>
  <h3 class="m-3"> 글쓴이:{{ item.name }}</h3>
  <button @click="selectArticle" type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#studyArticleDetail">
  자세히 보기
</button>
</div>
</template>

<script>
import { useStore } from "vuex"
export default {
  props: {
    item:Object
  },

  setup(props){
    const store = useStore();
    const selectArticle = ()=>{
      console.log(props.item)
      store.dispatch('getStudyArticle',props.item.id)
    }
    
    return {selectArticle}
  }
}
</script>

<style>

</style>