<template>
  <h1>최근 게시물 공지 (몇개만 출력하게 js 수정 필요)</h1>


  <!-- Modal 전체보기 누를경우 보이는 내용 -->
  <div class="modal fade" id="boardModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-fullscreen modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">게시글 및 공지</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        <h1>전체 게시글</h1>
        <h3>보드 : {{ board }}</h3>
        <boardItem v-for="writing in board" :key="writing" :writing="writing" />
        <h1>아이템 끝</h1>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
      </div>
    </div>
  </div>
</div>
</template>

<script>
import boardItem from "./boardItem.vue"
import { computed } from "vue"
import { useStore } from "vuex"
export default {
  components:{
    boardItem,
  },

  setup(){
    const store = useStore()
    const board = computed(() => store.getters.studyBoard )
    return { board,}
  }
}
</script>

<style>

</style>