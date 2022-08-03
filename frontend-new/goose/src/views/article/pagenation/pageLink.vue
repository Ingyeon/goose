<template>
  <ul class="pagination justify-content-center">

    <li v-for="index in state.endPageIndex-state.startPageIndex + 1 " :key="index" class="page-item" :class="{active:( (state.startPageIndex + index - 1) == state.currentPageIndex)}">
      <button class="page-link"  @click.native="movePage(state.startPageIndex + index - 1)">{{ state.startPageIndex + index - 1 }}</button>
      <!-- <a class="page-link" href="javascript:movePage(' + i + ')">' + i + '</a> -->
    </li>
  </ul>
</template>

<script>
import { reactive,computed } from "vue";
import {  useStore  } from 'vuex'


export default{
    setup(){
        const store = useStore()
        const state = reactive({
            totalListItemCount: 0,
            listRowCount: 10,
            pageLinkCount: 10,
            currentPageIndex: 1,

            pageCount: 0,
            startPageIndex: 1,
            endPageIndex: 10,
        })

        const movePage = function(param){
            state.currentPageIndex = param
            store.dispatch('fetchArticles',param)
            
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