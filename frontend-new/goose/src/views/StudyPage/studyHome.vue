<template>
<div class="container d-flex">
  <img :src=selectedStudy.image alt="기본사진">
  <div>
    <div class="container d-flex">
      <div class="m-3">스터디 이름 : {{ selectedStudy.title }}</div>
      <div class="m-3">인원 : {{ selectedStudy.member }}/ {{ selectedStudy.maxmember }} </div>
    </div>
    <div>스터디 소개글 : collume 추가되면 추가할 예정{{ }}</div>
  </div>
</div>
<div>
  <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#studyJoinListModal">참가 신청 리스트</button>
  <studyJoinList />
</div>
<div class="container d-flex">
  <div>스터디 주소 : {{ selectedStudy.url_conf }}</div>
  <router-link :to="{ path: '/' +''}" class="navbar-brand">입장하기</router-link>
</div>
<div>
  <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#boardModal">전체보기</button>
  <board />
</div>
<div>
  <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#callenderModal">전체보기</button>
  <callender />
</div>
<div class="container d-flex">
  <button @click.prevent="deleteStudy" >스터디 터트리기</button>
  <button @click.prevent="" >스터디 탈퇴하기</button>
  <div>
    <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#updateStudyModal">스터디 수정</button>
    <studyUpdate />
  </div>
</div>
</template>

<script>
import board from '@/components/StudyPage/board.vue'
import callender from '@/components/StudyPage/callender'
import studyUpdate from '@/components/StudyPage/studyUpdate.vue'
import studyJoinList from '@/components/StudyPage/studyJoinList.vue'
import { useStore } from "vuex"
import { computed, watch } from "vue"

export default {
  name: "StudyHome",
  components:{
      board,callender,studyUpdate,studyJoinList,
  },

  setup() {
    const store = useStore()
    const selectedStudy = computed(() => store.getters.selectedStudy )
    const deleteStudy = () => store.dispatch('deleteStudy',store.getters.studyId)
    const pageUpdate = () => store.dispatch('selectStudy',store.getters.selectedStudy.id)

    return { selectedStudy,deleteStudy, pageUpdate,}
  },

  watch: {
    selectedStudy (){
      console.log("빅브라더")
      this.pageUpdate()
    }
  }
}
</script>

<style>

</style>
