<template>
    <!-- <li class="active">오픈 스터디</li> -->     
      <div class="mt-3 col-12 col-md-6 col-lg-4 col-xl-3">
        <div class="card">
          <img :src="state.photo" class="card-img-top" alt="alt">
          <div class="card-body">
            <h5 class="card-title fw-bold">{{item.title}}</h5>
            <p class="card-text">사용 언어: {{item.category}}</p>
            <div v-if="isLoggedIn" class="d-flex justify-content-end">
              <button @click="clickbtn(item.id)" class="button" >스터디 입장하기</button>
            </div>
          </div>
            <!-- {{article}} -->
      <!-- <p class="card-text">{{article.content}}</p> -->
      <!-- <button @click.prevent="joinStudy(article)" type="button" class="btn btn-primary" >가입신청</button> -->
        </div>
      </div>
</template>

<script>
import { useStore } from "vuex"
import { computed } from "vue"
import { useRouter } from "vue-router";
import study1 from "@/assets/study1.png"
import study2 from "@/assets/study2.png"
import study3 from "@/assets/study3.png"
import { reactive } from '@vue/reactivity';
// import ArticleItem from './articleItem.vue'
export default {
  props: {
    item:Object,
  },

  setup(props){
    const store = useStore()
    const myStudyList = computed(()=> store.getters.myStudyList)
    const router = useRouter();
    const isLoggedIn = computed(()=> store.getters.isLoggedIn)
      
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
      store.dispatch('saveOpenList')
    }
    
    const fetchStudyHome = async function (studyId) {
      await store.dispatch("selectStudy", studyId)
    };

     const clickbtn = async function (studyId) {
      await fetchStudyHome(studyId);
      router.push({ name: "PublicStudyRoom" });
    };

    // open_set()

    // const joinStudy = (studyId) => store.dispatch('joinStudy',studyId)
    return {isLoggedIn,myStudyList, router, store, fetchMyStudyList,openstudyList,open_set, changePhoto,state,clickbtn}
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

<style scoped>
  .row {
    margin: 10px;
  }
  * {
  font-family: 'NanumSquare', sans-serif;
}
.card-body{
  /* border-top: 0.7px solid black; */
  background-color: #f6f6f7;
}
  .card {
    width: 18rem;
    height: 100%;
    margin-left:20px;
    margin-bottom:20px;
  }
  .card-img-top {
    height: 15rem;
    object-fit: cover;
  }
  .button{
    background: #ffd700;
    color: #000000;
    cursor: pointer;
    width: 80%;
    margin-bottom: 20px;
    font-weight: 600;
    text-align: center;
    border-radius: 80px 40px;
    border: none;
  }
</style>
