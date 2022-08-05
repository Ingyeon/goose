<template>
<tr @click="selectArticle" data-bs-toggle="modal" data-bs-target="#studyArticleDetail">
  <td>{{ item.id }}</td>
  <td>{{ item.title }}</td>
  <td>{{ item.name }}</td>
  <td>{{ item.date }}</td>
  <td>{{ item.hit }}</td>
</tr>
</template>
<!-- <button @click="selectArticle" type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#studyArticleDetail"></button> -->
<script>
import { useStore } from "vuex"
export default {
  props: {
    item:Object
  },

  setup(props){
    const store = useStore();
    const selectArticle = ()=>{
      store.dispatch('getStudyArticle',props.item.id)
    }
    
    return {selectArticle}
  }
}
</script>

<style>

</style>