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


<!-- 프로님한테 물어보기? 방법 아예 갈아엎기? -->
<!-- 게시판 -->
<div v-if="isList">
  <b-table striped hover :items="state.article_list" @row-clicked="rowClick" :fields="state.headers" show-empty>
  </b-table>
</div>

<!-- 페이지네이션 -->
    <b-pagination
    v-model="currentPage"
    :total-rows="Rows"
    :per-page="PerPage"
    first-text="First"
    prev-text="Prev"
    next-text="Next"
    last-text="Last"
    ></b-pagination>
  <router-link to="/newarticle" class="btn btn-primary">글 작성</router-link>
</template>

<script>
import { reactive,computed } from 'vue'
import { onMounted } from 'vue'
import {useStore} from 'vuex'
import _ from 'lodash'
import { useRouter } from 'vue-router';
export default {
    setup(){
        // 추후 게시판 연결 후 ref값 변경 - 페이지네이션 관련 코드
        const currentPage = 1
        const Rows = 10
        const PerPage = 10
        const router = useRouter()

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
        
        const makeLists = function(){
            for(let articleItem of state.articles) {
                state.article_list.push({
                    '글번호': articleItem.id,
                    '상태': articleItem.state,
                    '분류': articleItem.category,
                    '제목': articleItem.title,
                    '작성자': articleItem.name,
                    '등록일': articleItem.date,
                    '조회수': articleItem.hit,
                    'id': articleItem.id
                })
            }
        }

        const isList = computed(() => !_.isEmpty(state.articles))

        const rowClick = function(item){
          console.log(item)
          store.dispatch('fetchArticle',item.id)
          router.push({
          path: `/article/${item.id}`
          })
        }

        onMounted(() => {
        makeLists()})
        
    
    return {store,currentPage,Rows,PerPage,state,makeLists,onMounted,rowClick,articles_set,isList}


    }
    
}
</script>
