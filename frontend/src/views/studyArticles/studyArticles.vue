<template>
<div v-if="selectedStudy.open == 1 && !passwordCheck && !isStudyMember">
  <div class="container">
    <div class="input-Box">
        <label for="inputcurrentPassword" class="form-label">비공개 스터디입니다 비밀번호를 입력해주세요</label>
        <input type="password" id="studyPassword" class="form-control" placeholder="비밀번호를 입력해주세요" v-model="data.inputPassword">
        <button @click.prevent="pwcheck" class="button">입장</button>
    </div>
  </div>
</div>
<!-- 본문 -->
<div v-if="selectedStudy.open==0 || passwordCheck || isStudyMember">
<div class="container">
    <h1 class="text-center fw-bold my-3">게시판</h1>
  <router-link :to="{ name:'StudyHome', params: {studyPk:selectedStudy.id}}"><button id="title" class="btn btn-3 hover-border-3">{{selectedStudy.title}}로 이동</button></router-link>
  <div class="d-flex justify-content-between">
    <div>
      <button id="all" class="btn btn-3 hover-border-3 active" @click="allClick">전체보기</button>
      <button id="notice" class="btn btn-3 hover-border-3 " @click="noticeClick">공지</button>
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
        <li @click="pageDown" class="page-item"><a class="page-link" href="#">다음</a></li>
        <li @click="pageUp" class="page-item"><a class="page-link" href="#">이전</a></li>
      </ul>
    </nav>
  </div>
  <div>
    <studyArticleDetail @refresh="refresh" />
  </div>
</div>
</div>
</template>

<script>
import createStudyArticle from '@/components/StudyArticle/createStudyArticle.vue'
import studyArticleItem from '@/components/StudyArticle/studyArticleItem.vue'
import studyArticleDetail from '@/components/StudyArticle/studyArticleDetail.vue'
import { computed, reactive } from "vue"
import { useStore } from "vuex"
import { useRoute, useRouter } from "vue-router";
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
      category: null,
      inputPassword : "",
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
    // 스터디 아트클 주소를 url창으로 입력해서 들어올 경우를 위한 코드
    const route = useRoute();
    store.dispatch('selectStudy',route.params.studyPk)

    
    const allClick = ()=>{
      if (data.category != "all"){
        data.category = null
        data.page = 1
        fetchStudyArticleList()
        document.getElementById('all').classList.add('active')
        document.getElementById('notice').classList.remove('active')
        document.getElementById('free').classList.remove('active')
      }
    }

    const noticeClick = ()=>{
      if (data.category != "notice"){
        data.category = "notice"
        data.page = 1
        fetchStudyArticleList()
        document.getElementById('notice').classList.add('active')
        document.getElementById('free').classList.remove('active')
        document.getElementById('all').classList.remove('active')
      }
    }

    const freeClick = ()=>{
      if (data.category != "free"){
        data.category = "free"
        data.page = 1
        fetchStudyArticleList()
        document.getElementById('notice').classList.remove('active')
        document.getElementById('free').classList.add('active')
        document.getElementById('all').classList.add('active')
      }
    }

    const btnActive = (category)=>{
      if( category =='free'){
        document.getElementById('all').classList.remove('active')
        document.getElementById('notice').classList.remove('active')
        document.getElementById('free').classList.add('active')
      }
      else if( category == 'notice'){
        document.getElementById('notice').classList.add('active')
        document.getElementById('free').classList.remove('active')
        document.getElementById('all').classList.remove('active')
      }
      else {
        document.getElementById('all').classList.add('active')
        document.getElementById('free').classList.remove('active')
        document.getElementById('notice').classList.remove('active')
      }
    }

    const refresh = ()=>{
      // all 클릭과 동일
      data.category = null
      data.page = 1
      fetchStudyArticleList()
      document.getElementById('all').classList.add('active')
      document.getElementById('notice').classList.remove('active')
      document.getElementById('free').classList.remove('active')
    }
    const passwordCheck = computed(()=>store.getters.passwordCheck)
    const pwcheck = ()=>{store.dispatch('passwordCheck',data.inputPassword)}
    const isStudyMember = computed(()=>store.getters.isStudyMember)
    return {studyArticleList,selectedArticle,pageUp,pageDown,data,selectedStudy,
    allClick,noticeClick,freeClick,btnActive,refresh,pwcheck,passwordCheck,isStudyMember,}
  },
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