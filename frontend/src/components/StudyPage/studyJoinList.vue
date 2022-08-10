<template>
<div class="modal fade" id="studyJoinListModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">스터디 참가 신청 리스트</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        <studyJoinListItem v-for="item in studyJoinList" :key="item.id" :item="item"/>
        <div v-if="!studyJoinList">참가 신청이 없습니다</div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
      </div>
    </div>
  </div>
</div>
</template>

<script>
import studyJoinListItem from "@/components/StudyPage/studyJoinListItem.vue"
import { useStore } from "vuex"
import { computed } from "vue"
export default {
  components:{
    studyJoinListItem,
  },
  setup(){
    const store = useStore()
    
    const studyJoinList = computed(() => store.getters.studyJoinList)
    
    return{studyJoinList}
  }
}
</script>

<style>

</style>