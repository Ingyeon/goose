<template>
<h1>스터디 참여하기</h1>
<!-- 검색창 -->
<div class="input-group mb-3">
<select class="form-select-line-height" v-model="state.search.status">
  <option selected>상태</option>
  <option value="1">모집중</option>
  <option value="2">진행중</option>
  <option value="3">모집완료</option>
</select>
<select class="form-select-line-height" v-model="state.search.category">
  <option selected>분류</option>
  <option value="1">토익</option>
  <option value="2">면접</option>
  <option value="3">자유</option>
</select>
  <input type="text" class="form-control" v-model="state.search.text">
    <button class="btn btn-outline-secondary" type="button">검색</button>
</div>

<!-- 게시판 -->
<div class="board-list">
<div v-if="isList">
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
      <tr v-for="row in articles" :key="row">
        <td>{{ row.id }}</td>
        <td><button @click="onclick(row.id)">{{row.title}}</button></td>
        <td>{{ row.name }}</td>
        <td>{{ row.date }}</td>
        <td>{{ row.hit  }}</td>
      </tr>
      </tbody>
    </table>
</div>
</div>
<!-- 무한 스크롤이랑 카드형 게시글? -->
  <router-link to="/newarticle" class="btn btn-primary">글 작성</router-link>

</template>

<script>
import { reactive,computed } from 'vue'
import { onMounted } from 'vue'
import {useStore} from 'vuex'
import _ from 'lodash'
import { useRouter } from 'vue-router'

export default {
    setup(){
        // 추후 게시판 연결 후 ref값 변경 - 페이지네이션 관련 코드
        const Router = useRouter()

        const store = useStore()
        // 게시판
        const state = reactive({
            article_list: [
            ],
            headers: ['글번호','상태','분류','제목','작성자','등록일','조회수'],
            articles: [],
            search: {
              status: '',
              category: '',
              text: '',
            }
        })
        

        const articles_set = function(){
          store.dispatch('fetchArticles',1) 
          state.articles = store.getters.articles
        }
        articles_set()

        const isList = computed(() => !_.isEmpty(state.articles))

        const onclick = function(item){
        store.dispatch('fetchArticle', item)
        Router.push({name: 'ArticleDetail', params: {id:item}})
        }
        
        const articles = computed(() => store.getters.articles)

    
    return {state,onMounted,articles_set,isList,onclick,articles}


    }
    
}
</script>

<style>


</style>