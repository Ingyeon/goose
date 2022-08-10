<template>
  <ul class="pagination justify-content-center">

    <li v-for="index in state.endPageIndex-state.startPageIndex + 1 " :key="index" class="page-item" :class="{active:( (state.startPageIndex + index - 1) == state.currentPageIndex)}">
      <button class=" pagenation"  @click.native="movePage(state.startPageIndex + index - 1)">{{ state.startPageIndex + index - 1 }}</button>
      <!-- <a class="page-link" href="javascript:movePage(' + i + ')">' + i + '</a> -->
    </li>
  </ul>
</template>


<script>
import { reactive } from "vue";
import {  useStore  } from 'vuex'

// 필터링 하고 페이지네이션 쓰면 풀리는 문제 해결해야함
export default{
  props: {
    search: Object,
  },
    setup(props){
        const store = useStore()
        const state = reactive({
            totalListItemCount: 0,
            listRowCount: 10,
            pageLinkCount: 10,
            currentPageIndex: 1,

            pageCount: 0,
            startPageIndex: 1,
            endPageIndex: store.getters.total,
        })

        const movePage = function(param){
            state.currentPageIndex = param
            store.dispatch('fetchArticles',param)
            console.log(props.search)
        }

        const initUi = function()
        {

      state.pageCount = Math.ceil(state.totalListItemCount/state.listRowCount);

      if( (state.currentPageIndex % state.pageLinkCount) == 0 ){
        state.startPageIndex = ((state.currentPageIndex / state.pageLinkCount)-1)*state.pageLinkCount + 1
      }else{
        state.startPageIndex = Math.floor(state.currentPageIndex / state.pageLinkCount)*state.pageLinkCount + 1
      }

      if( (state.currentPageIndex % state.pageLinkCount) == 0 ){ //10, 20...맨마지막
        state.endPageIndex = ((state.currentPageIndex / state.pageLinkCount)-1)*state.pageLinkCount + state.pageLinkCount
      }else{
        state.endPageIndex =  Math.floor(state.currentPageIndex / state.pageLinkCount)*state.pageLinkCount + state.pageLinkCount;
      }

      if(state.endPageIndex > state.pageCount){
        state.endPageIndex = state.pageCount
      }
    }

return {state,movePage,initUi}}}
</script>

<style scoped>
.pagenation{
  color: black;
  background-color: #ffd700;
  font-family: "NanumSquare", sans-serif;
  font-weight: bold;
  font-size: 1rem;
  transition: all 0.5s ease 0s;
  font-family: "NanumSquare", sans-serif;
  font-weight: bold;
  padding: 9px 20px;
  border-radius: 80px 40px;
}

.pagenation:hover{
  background-color: white;
}
.pagenation:focus{
  background-color: wheat;
}
</style>