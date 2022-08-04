<template>
<h1 class="d-flex justify-content-center">스터디 모집 게시판</h1>
<div class="container">
<!-- 검색창 -->
<div class="input-group mb-3">
<select v-model="state.search.state">
  <option value=null>상태</option>
  <option value="모집중">모집중</option>
  <option value="진행중">진행중</option>
  <option value="모집완료">모집완료</option>
</select>
<select v-model="state.search.category">
  <option value=null>분류</option>
          <option value="C">C</option>
          <option value="C++">C++</option>
          <option value="JAVA">JAVA</option>
          <option value="JavaScripts">JavaScripts</option>
          <option value="Python">Python</option>
</select>
  <input type="text" class="form-control" v-model="state.search.title">
    <button class="btn btn-outline-secondary" type="button" @click="filterArticles()">검색</button>
</div>

<div>
  <button @click="category_sort()">전체</button>
  <button @click="category_sort('C')">C</button>
  <button @click="category_sort('C++')">C++</button>
  <button @click="category_sort('JAVA')">JAVA</button>
  <button @click="category_sort('Python')">Python</button>
  <button @click="category_sort('JavaScripts')">JavaScripts</button>

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
        <td><div @click="onclick(row.id)" class="btn">{{row.title}}</div></td>
        <td>{{ row.name }}</td>
        <td>{{ row.date }}</td>
        <td>{{ row.hit  }}</td>
      </tr>
      </tbody>
    </table>
</div>
</div>
<pageLink :search = 'state.search'/>
<!-- 무한 스크롤이랑 카드형 게시글? -->
  <router-link to="/newarticle" class="btn btn-primary">글 작성</router-link>
</div>
</template>

<script>
import { reactive,computed } from 'vue'
import { onMounted } from 'vue'
import {useStore} from 'vuex'
import _ from 'lodash'
import { useRouter } from 'vue-router'
import pageLink from './pagenation/pageLink.vue'

export default {

  components: {pageLink},
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
              state: null,
              category: null,
              title: null,
            },
        })
        
        const filterArticles = function(){
          store.dispatch('fetchArticles',1)
          store.dispatch('filterArticles',state.search)
        }

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

        const category_sort = function(item){
          store.dispatch('sortedArticles',item)
        }

    
    return {state,filterArticles,onMounted,articles_set,isList,onclick,articles,category_sort}


    }
    
}
</script>

<style scoped>
  .input-Box:nth-child(3) {
    display: flex;
    /* justify-content: space-evenly; */
    justify-content: end;

    }
    .input-Box button{
    background: #ffd700;
    color: #000000;
    cursor: pointer;
    width: 100px;
    margin-bottom: 20px;
    font-weight: 600;
    text-align: center;
    border-radius: 80px 40px;
    margin-right: 30px;
  }

@import url(https://fonts.googleapis.com/css?family=Open+Sans:300,400,700);

body {
  font-family: 'Open Sans', sans-serif;
  font-weight: 300;
  line-height: 1.42em;
  color:#A7A1AE;
  background-color:#ffd700;
}

h1 {
  font-size:3em; 
  font-weight: 300;
  line-height:1em;
  text-align: center;
}

h2 {
  font-size:1em; 
  font-weight: 300;
  text-align: center;
  display: block;
  line-height:1em;
  padding-bottom: 2em;
  color: #FB667A;
}

h2 a {
  font-weight: 700;
  text-transform: uppercase;
  color: #FB667A;
  text-decoration: none;
}

.blue { color: #185875; }
.yellow { color: #FFF842; }

.container th h1 {
	  font-weight: bold;
	  font-size: 1em;
  text-align: left;
  color: #185875;
}

.container td {
	  font-weight: normal;
	  font-size: 1em;
  -webkit-box-shadow: 0 2px 2px -2px #0E1119;
	   -moz-box-shadow: 0 2px 2px -2px #0E1119;
	        box-shadow: 0 2px 2px -2px #0E1119;
}

.container {
	  text-align: left;
	  overflow: hidden;
	  width: 80%;
	  margin: 0 auto;
  display: table;
  padding: 0 0 8em 0;
}

.container td, .container th {
	  padding-bottom: 2%;
	  padding-top: 2%;
  padding-left:2%;  
}

/* Background-color of the odd rows */

.container th {
	  background-color: #ffd700;
}

.container td:first-child { color: #0E1119; }

.container tr:hover {
-webkit-box-shadow: 0 6px 6px -6px #0E1119;
	   -moz-box-shadow: 0 6px 6px -6px #0E1119;
	        box-shadow: 0 6px 6px -6px #0E1119;
}

.container td:hover {
  background-color: #FFF842;
  color: #403E10;
  font-weight: bold;
  
  box-shadow: #7F7C21 -1px 1px, #7F7C21 -2px 2px, #7F7C21 -3px 3px, #7F7C21 -4px 4px, #7F7C21 -5px 5px, #7F7C21 -6px 6px;
  transform: translate3d(6px, -6px, 0);
  
  transition-delay: 0s;
	  transition-duration: 0.4s;
	  transition-property: all;
  transition-timing-function: line;
}

@media (max-width: 800px) {
.container td:nth-child(4),
.container th:nth-child(4) { display: none; }
}
</style>
