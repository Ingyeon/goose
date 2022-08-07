<template>
  <div class="container">
    <!-- <li class="active">오픈 스터디</li> -->
    <b-card-group class="d-flex justify-content-start">
      <div class="row" v-for="article in openstudyList" :key="article.id">
        <div class="col-12 col-md-4 col-lg-3">        
        <div class="card">
          <img :src="state.photo" class="card-img-top" alt="alt">
          <div class="card-body">
            <h5 class="card-title">{{article.title}}</h5>
            <p class="card-text">사용 언어: {{}}</p>
            <div class="d-flex justify-content-end">
              <button href="#" class="button">스터디 입장하기</button>
            </div>
          </div>
            <!-- {{article}} -->
      <!-- <p class="card-text">{{article.content}}</p> -->
      <!-- <button @click.prevent="joinStudy(article)" type="button" class="btn btn-primary" >가입신청</button> -->
          </div>
        </div>
      </div>
    </b-card-group>
  </div>  
</template>

<script>
import { useStore } from "vuex"
import { computed } from "vue"
import study1 from "@/assets/study1.png"
import study2 from "@/assets/study2.png"
import study3 from "@/assets/study3.jpg"
import { reactive } from '@vue/reactivity';
export default {
  props: {
    item:Object,
  },

  setup(props){
    const store = useStore()
    const myStudyList = computed(()=> store.getters.myStudyList)
    // const openstudyList = computed(()=> store.getters.openstudyList)
    const openstudyList = store.getters.openstudyList
    
    const state = reactive({
      photo : ""
    })
    
    const fetchMyStudyList = function(studyId){
      store.dispatch('myStudyList',studyId)
    }
    
    const changePhoto = ()=>{
      if (props.item.image==='study1'){state.photo = study1}
      else if (props.item.image==='study2'){state.photo = study2}
      else if (props.item.image==="study3"){state.photo = study3}
    }
    changePhoto()
    const open_set = function(){
      console.log('시작')
      console.log(openstudyList)
      store.dispatch('saveOpenList')
    }
    
    // open_set()

    // const joinStudy = (studyId) => store.dispatch('joinStudy',studyId)
    return {myStudyList,fetchMyStudyList,openstudyList,open_set, changePhoto,state}
  },

  watch: {
    $route: {
      handler() {
        // this.open_set()
        this.fetchMyStudyList()
        this.open_set()
      },
      immediate: true
    }
  }
}
</script>

<style>
  .row {
    margin: 10px;
  }
  .card {
    width: 18rem;
    margin-left:20px;
  }
  .notice-item {
    width : 100%;
    height: 300px;
    background-color: #f5d682;
    border: 1px solid red;
    display: flex;
    justify-content: center;
  }
  .button{
    background: #ffd700;
    color: #000000;
    cursor: pointer;
    width: 80%;
    margin-bottom: 20px;
    font-weight: 600;
    text-align: center;
    border-radius: 40px 80px;
  }
</style>
