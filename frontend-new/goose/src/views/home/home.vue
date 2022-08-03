<template>
  <h1>Home</h1>
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
  <button @click.prevent="hire_study" class="btnn btnn-5 hover-border-11">모집중인 스터디</button>
<articleList v-if='state.toggle==1'/>
<button @click.prevent="me_study" class="btnn btnn-5 hover-border-11">참여중인 스터디</button>
<myStudyList v-if="state.toggle==2" />


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
  return {store,state,hire_study,me_study}

}}
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


  * {
  box-sizing: border-box;
  margin: 0; padding: 0;
}

:active, :hover, :focus {
  outline: 0!important;
  outline-offset: 0;
}
::before,
::after {
  position: absolute;
  content: "";
}

.btnn-holder {
  display: flex;
  flex-wrap: wrap;
  justify-content: center;
  max-width: 1000px;
  margin: 10px auto 35px;
}
.btnn {
  position: relative;
  display: inline-block;
  width: auto; height: auto;
  background-color: transparent;
  border: none;
  cursor: pointer;
  margin: 0px 25px 15px;
  min-width: 150px;
}
  .btnn span {         
    position: relative;
    display: inline-block;
    font-size: 14px;
    font-weight: bold;
    letter-spacing: 2px;
    text-transform: uppercase;
    top: 0; left: 0;
    width: 100%;
    padding: 15px 20px;
    transition: 0.3s;
  }


/*--- btnn-5 ---*/
.btnn-5 span {
  color: rgb(28, 31, 30);
  border: 2px solid rgb(249, 211, 27);
  transition: 0.2s;
}
.btnn-5 span:hover {
  background-color: rgb(245,245,245);
}

/* 21. hover-border-11 */
.btnn.hover-border-11::before,
.btnn.hover-border-11::after {
  width: 100%; height: 2px;
  background-color: rgb(54, 56, 55);
  z-index: 2;
  transition: 0.35s; 
}
.btnn.hover-border-11::before {
  top: 0; right: 0;
}
.btnn.hover-border-11::after {
  bottom: 0; left: 0;
}
.btnn.hover-border-11:hover::before,
.btnn.hover-border-11:hover::after {
  width: 0%;
  transition: 0.2s 0.2s ease-out; 
}

.btnn.hover-border-11 span::before,
.btnn.hover-border-11 span::after {
  width: 2px; height: 100%;
  background-color: rgb(54, 56, 55);
  z-index: 2;
  transition: 0.25s; 
}
.btnn.hover-border-11 span::before {
  bottom: 0; right: -2px;
}
.btnn.hover-border-11 span::after {
  top: 0; left: -2px;
}
.btnn.hover-border-11 span:hover::before,
.btnn.hover-border-11 span:hover::after {
  height: 0%;
}
</style>