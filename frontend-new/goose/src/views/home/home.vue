<template>
  <!-- <h1>Home</h1> -->
  <div>
    <div id="carouselExampleControlsNoTouching" class="carousel slide" data-bs-touch="false">
      <div class="carousel-inner">
        <div class="carousel-item active notice-item">
          <Notice/>
        </div>
        <div class="carousel-item notice-item">
          <Notice/>
        </div>
        <div class="carousel-item notice-item">
          <Notice/>
        </div>
      </div>
      <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleControlsNoTouching" data-bs-slide="prev">
        <span class="carousel-control-prev-icon" aria-hidden="true"></span>
        <span class="visually-hidden">Previous</span>
      </button>
      <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleControlsNoTouching" data-bs-slide="next">
        <span class="carousel-control-next-icon" aria-hidden="true"></span>
        <span class="visually-hidden">Next</span>
      </button>
    </div>
    <div>
      <ul class="button-links">
        <li><button @click.prevent="hire_study" >모집중인 스터디</button></li>
        <articleList v-if='state.toggle==1'/>

        <li><button @click.prevent="me_study">참여중인 스터디</button></li>
        <myStudyList v-if="state.toggle==2" />
      </ul>
    </div>
  </div>

</template>

<script>
import Notice from '@/components/mainpage/notice'
import myStudyList from '@/components/mainpage/myStudyList.vue'
import articleList from './component/articleList.vue'

import { useStore } from "vuex"
import { reactive } from "vue"

export default {
  components:{
    Notice,
    myStudyList,
    articleList
  },
  setup(){
    const store = useStore()
    const state = reactive({
      toggle: 0
    })

    const hire_study = function(){
      if (state.toggle != 1){
        state.toggle = 1
      }
      else {state.toggle = 0}}

    const me_study = function(){
      if (state.toggle != 2){
        state.toggle = 2
      }
      else(state.toggle = 0)}

    store.dispatch('myStudyList')
  return {store,state,hire_study,me_study}

}}
</script>

<style scoped>
</style>