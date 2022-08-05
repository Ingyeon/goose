<template>
<div class="container">
    <h1 class="text-center my-3">게시판</h1>
  <router-link :to="{ name:'StudyHome', params: {studyPk:selectedStudy.id}}"><button class="cus-btn-black">Study home</button></router-link>
  <div class="d-flex justify-content-between">
    <div>
      <button class="cus-btn-sky" @click="noticeClick">공지</button>
      <button class="cus-btn-sky" @click="freeClick">자유</button>
    </div>
    <createStudyArticle />
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
  <nav aria-label="Page navigation example">
    <ul class="pagination">
      <li @click="pageDown" class="page-item"><a class="page-link" href="#">Previous</a></li>
      <li @click="pageUp" class="page-item"><a class="page-link" href="#">Next</a></li>
    </ul>
  </nav>
  
  <hr>
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
      }
    }

    const freeClick = ()=>{
      if (data.category != "free"){
        data.category = "free"
        data.page = 1
        fetchStudyArticleList()
      }
    }

    fetchStudyArticleList()
    return {studyArticleList,selectedArticle,pageUp,pageDown,data,selectedStudy,noticeClick,freeClick}
  }
}
</script>

<style scoped>
.cus-btn-blue {
background: #0078AA;
color: #000000;
cursor: pointer;
width: 100px;
margin-bottom: 20px;
font-weight: 600;
text-align: center;
border-radius: 40px 80px;
}

.cus-btn-sky {
background: #3AB4F2;
color: #000000;
cursor: pointer;
width: 100px;
margin-bottom: 20px;
font-weight: 600;
text-align: center;
border-radius: 40px 80px;
}

.cus-btn-black {
background: #81CACF;
color: black;
cursor: pointer;
width: 100px;
margin-bottom: 20px;
font-weight: 600;
text-align: center;
border-radius: 40px 80px;
}
</style>