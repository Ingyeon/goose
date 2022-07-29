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
  <div>
    <li class="active">모집중인 스터디</li>
    <li class="disabled">참여중인 스터디</li>
    <myStudyList />
  </div>
</template>

<script>
import Notice from '@/components/mainpage/notice'
import myStudyList from '@/components/mainpage/myStudyList.vue'
import { useStore } from "vuex"
import { computed } from "vue"
export default {
  components:{
    Notice,
    myStudyList
  },
  setup(){
    const store = useStore()
    const myStudyList = computed(()=> store.getters.myStudyList)

    const fetchMyStudyList = () =>{
      store.dispatch('myStudyList')
    }

    return {myStudyList,fetchMyStudyList}
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