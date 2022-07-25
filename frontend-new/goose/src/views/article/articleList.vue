<template>
<h1>스터디 참여하기</h1>
<!-- 검색창 -->
<div class="input-group mb-3">
<select class="form-select-line-height">
  <option selected>상태</option>
  <option value="1">모집중</option>
  <option value="2">진행중</option>
  <option value="3">모집완료</option>
</select>
<select class="form-select-line-height">
  <option selected>분류</option>
  <option value="1">토익</option>
  <option value="2">면접</option>
  <option value="3">자유</option>
</select>
  <input type="text" class="form-control">
    <button class="btn btn-outline-secondary" type="button">검색</button>
</div>

<!-- 게시판 -->
<b-table striped hover :items="state.article_list" :fields="state.headers" show-empty>
</b-table>

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
import { ref, reactive} from 'vue'
<<<<<<< HEAD
import { onMounted } from 'vue'
import {useStore} from 'vuex'
=======
// import { onMounted } from 'vue'
// import {useStore} from 'vuex'
>>>>>>> 2e09ea8d (feat: 스터디 게시판 + 댓글)

export default {
    setup(){
        // 추후 게시판 연결 후 ref값 변경 - 페이지네이션 관련 코드
        const currentPage = ref(1)
        const Rows = ref(10)
        const PerPage = ref(100)

<<<<<<< HEAD
        const store = useStore()
        const articles = store.getters.articles

        // 게시판
        const state = reactive({
            article_list: [
            ],
            headers: ['글번호','상태','분류','제목','작성자','등록일','조회수']
        })

        const makeLists = function(){
            for(let articleItem of articles) {
                state.article_list.push({
                    '글번호': articleItem.id,
                    '상태': articleItem.state,
                    '분류': articleItem.category,
                    '제목': articleItem.title,
                    '작성자': articleItem.user_pk, // user_pk값으로 작성자 이름 가져오는 작업 필요
                    '등록일': articleItem.date,
                    '조회수': articleItem.hit // 임의값 넣음, 추후 조회수 관련 로직 작성 필요
                })
            }
        }
        onMounted(() => {
        makeLists()})
    
    return {currentPage,Rows,PerPage,state,makeLists,onMounted}
=======
        // const store = useStore()

        // 게시판
        const state = reactive({
            article_list: [],
            headers: ['글번호','상태','분류','제목','작성자','등록일','조회수']
        })

        // 게시판 db의 필드값 확인 후 아래 코드에서 수정 후 사용할 예정
        // const makeLists = function(){
        //     for(let articleItem of store.articles) {
        //         state.article_list.push({
        //             글번호: articleItem.??,
        //         })
        //     }
        // }
        // onMounted(() => {
        // makeLists()})
    
    return {currentPage,Rows,PerPage,state}
>>>>>>> 2e09ea8d (feat: 스터디 게시판 + 댓글)


    }
    
}
</script>
