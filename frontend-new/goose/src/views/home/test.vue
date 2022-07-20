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
<b-table striped hover :items="items"></b-table>

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
</template>

<script>
import {ref} from 'vue'


export default {
    setup(){
        // 추후 게시판 연결 후 ref값 변경 
        const currentPage = ref(1)
        const Rows = ref(10)
        const PerPage = ref(100)
    
    return {currentPage,Rows,PerPage}

    }
    
}
</script>
