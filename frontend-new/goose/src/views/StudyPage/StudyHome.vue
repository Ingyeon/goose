<template>
<div class="container d-flex">
  <img src="http://encykorea.aks.ac.kr/Contents/GetImage?t=origin&id=da07ff58-06ea-4cb2-a957-0964d192cb14&w=878&h=582" alt="기본사진">
  <div>
    <div class="container d-flex">
      <div class="m-3">스터디 이름 : {{ studyName }}</div>
      <div class="m-3">인원 : {{ NOP }} </div>
    </div>
    <div>가입 요청 확인</div>
    <div>스터디 소개글 : {{ notice }}</div>
  </div>
</div>
<div class="container d-flex">
  <div>스터디 주소 : {{ webURI }}</div>
  <router-link :to="{ path: '/login' +''}" class="navbar-brand">입장하기</router-link>
</div>
<div>
  <button @click.prevent="">전체보기</button>
  <Board />
</div>
<div>
  <button @click.prevent="">전체보기</button>
  <Callender />
</div>
<div class="container d-flex">
  <button @click.prevent="" >스터디 터트리기</button>
  <button @click.prevent="" >스터디 탈퇴하기</button>
  <div>
    <button @click.prevent="" >스터디 수정</button>
    <StudyUpdate />
    <!-- https://bootstrap-vue.org/docs/components/modal -->
  </div>
</div>
</template>

<script>
import Board from '@/components/StudyPage/board.vue'
import Callender from '@/components/StudyPage/Callender'
import StudyUpdate from '@/components/StudyPage/studyUpdate.vue'
import { useStore } from "vuex"
import { computed } from "vue"

export default {
  components:{
      Board,  // 항목을 몇개만 보여주는건 Board에서 props를 이용하여 처리할 예정(전체보기와 구분)
      // 그전에 b-table 관련해서 찾아보자
      Callender,StudyUpdate,
  },

  setup() {
    const store = useStore()
    const studyName = computed(() => store.getters.studyName )
    const NOP = computed(() => store.getters.numOfPeople)
    const notice = computed(() => store.getters.notice)
    const webURI = computed(() => store.getters.webURI)
    return { studyName,NOP,notice,webURI }
  }
}
</script>

<style>

</style>
