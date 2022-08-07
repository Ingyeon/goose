<template>
<div class="container">
    <h1 class="text-center fw-bold my-3">게시판</h1>
  <router-link :to="{ name:'StudyHome', params: {studyPk:selectedStudy.id}}"><button id="title" class="btn btn-3 hover-border-3">{{selectedStudy.title}}로 이동</button></router-link>
  <div class="d-flex justify-content-between">
    <div>
      <button id="notice" class="btn btn-3 hover-border-3 active" @click="noticeClick">공지</button>
      <button id="free" class="btn btn-3 hover-border-3" @click="freeClick">자유</button>
    </div>
    <createStudyArticle @createArticle="btnActive" />
  </div>
  <!-- 테이블 -->
<div class="board-list">
  <div>
      <table class="table table-hover">
        <thead>
        <tr>
          <th>No</th>
          <th>제목</th>
          <th>작성자</th>
          <th>등록일시</th>
          <th>조회수</th>
        </tr>
        </thead>
        <tbody>
          <studyArticleItem v-for="item in studyArticleList" :key="item.id" :item="item" />
        </tbody>
      </table>
  </div>
</div>


  <!-- 테이블 끝 -->
  <!-- pagination -->
  <hr>
  <div class="d-flex justify-content-center">
    <nav aria-label="Page navigation example">
      <ul class="pagination">
        <li @click="pageDown" class="page-item"><a class="page-link" href="#">Previous</a></li>
        <li @click="pageUp" class="page-item"><a class="page-link" href="#">Next</a></li>
      </ul>
    </nav>
  </div>
  <div>
    <studyArticleDetail />
  </div>
</div>
</template>

<script>
import createStudyArticle from '@/components/StudyArticle/createStudyArticle.vue'
import studyArticleItem from '@/components/StudyArticle/studyArticleItem.vue'
import studyArticleDetail from '@/components/StudyArticle/studyArticleDetail.vue'
import { computed, reactive } from "vue"
import { useStore } from "vuex"
import _ from 'lodash'
export default {
  components:{createStudyArticle,studyArticleItem,studyArticleDetail
  },

  setup(){
    const store = useStore()
    // const studyArticles = computed(() => store.getters.studyArticles )
    const studyArticleList = computed(() => store.getters.studyArticleList)
    const selectedArticle = computed(() => store.getters.selectedArticle)
    // const isArticleList = computed(()=> !_.isEmpty(store.getters.studyArticleList))
    const selectedStudy = computed(()=> store.getters.selectedStudy)
    const data = reactive({
      page:1,
      isArticleList: !_.isEmpty(store.getters.studyArticleList),
      category:"notice"
    })

    const pageUp = ()=>{
      // if (!_.isEmpty(store.getters.studyArticleList)){
      if (store.getters.studyArticleList.length ==10){
        data.page++;
        fetchStudyArticleList();
      }
    }

    const pageDown = ()=>{
      if(data.page>1){
        data.page--;
        fetchStudyArticleList();
      }
    }

    const fetchStudyArticleList = () => {
      store.dispatch('getStudyArticleList',{category:data.category,page:data.page,title:null})
    }
    
    const noticeClick = ()=>{
      if (data.category != "notice"){
        data.category = "notice"
        data.page = 1
        fetchStudyArticleList()
        document.getElementById('notice').classList.add('active')
        document.getElementById('free').classList.remove('active')
      }
    }

    const freeClick = ()=>{
      if (data.category != "free"){
        data.category = "free"
        data.page = 1
        fetchStudyArticleList()
        document.getElementById('notice').classList.remove('active')
        document.getElementById('free').classList.add('active')
      }
    }

    fetchStudyArticleList()

    const btnActive = (category)=>{
      if( category =='free'){
        document.getElementById('notice').classList.remove('active')
        document.getElementById('free').classList.add('active')
      }
      else{
        document.getElementById('notice').classList.add('active')
        document.getElementById('free').classList.remove('active')
      }
    }
    return {studyArticleList,selectedArticle,pageUp,pageDown,data,selectedStudy,noticeClick,freeClick,btnActive}
  }
}
</script>

<style scoped>

:active, :hover, :focus {
    outline: 0!important;
    outline-offset: 0;
    }
    ::before,
    ::after {
      position: absolute;
      content: "";
    }
    .btn {
      position: relative;
      display: inline-block;
      width: auto; height: auto;
      background-color: transparent;
      border: none;
      cursor: pointer;
      margin: 0px 25px 15px;
      min-width: 150px;
    }
      .btn span {         
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
    .btn.hover-border-3::before,
    .btn.hover-border-3::after {
      width: 0%; height: 0%;
      opacity: 0;
      transition: width 0.2s 0.15s linear, height 0.15s linear, opacity 0s 0.35s;
    }
    .btn.hover-border-3::before {
      top: 0; right: 0;
      border-top: 1px solid rgb(28, 31, 30);
      border-left: 1px solid rgb(28, 31, 30);
    }
    .btn.hover-border-3::after {
      bottom: 0; left: 0;
      border-bottom: 1px solid rgb(28, 31, 30);
      border-right: 1px solid rgb(28, 31, 30);
    }
    .btn.hover-border-3:hover::before,
    .btn.hover-border-3:hover::after {
      width: 100%; height: 99%;
      opacity: 1;
      transition: width 0.2s linear, height 0.15s 0.2s linear, opacity 0s;   
    }
*{
  font-family: 'NanumSquare', sans-serif;
}
#title {
  font-size: 1.5rem;
  color:#000
}
.btn.hover-border-3.active{
  color: #000;
  border:none;
}
.btn.hover-border-3{
  color: #787c82
}
.page-link{
  color: #000
}
</style>