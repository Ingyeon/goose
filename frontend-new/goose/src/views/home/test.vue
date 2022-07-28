<template>
<div class="container d-flex">
  <img src="http://encykorea.aks.ac.kr/Contents/GetImage?t=origin&id=da07ff58-06ea-4cb2-a957-0964d192cb14&w=878&h=582" alt="기본사진">
  <div>
    <div class="container d-flex">
      <div class="m-3">스터디 이름 : {{ studyName }}</div>
      <div class="m-3">인원 : {{ member }}/ {{ maxMember }} </div>
    </div>
    <div>스터디 소개글 : collume 추가되면 추가할 예정{{ }}</div>
  </div>
</div>
<div>
  <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#studyJoinListModal">참가 신청 리스트</button>
  <studyJoinList />
</div>
<div class="container d-flex">
  <div>스터디 주소 : {{ camURL }}</div>
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
<h1 v-if="is_list"> 리스트 잇데</h1>
<h1 v-if="!is_list"> 리스트 없데</h1>
</template>

<script>
import board from '@/components/StudyPage/board.vue'
import callender from '@/components/StudyPage/callender'
import studyUpdate from '@/components/StudyPage/studyUpdate.vue'
import studyJoinList from '@/components/StudyPage/studyJoinList.vue'
import { useStore } from "vuex"
import { computed } from "vue"
import _ from 'lodash';
export default {
  components:{
      board,callender,studyUpdate,studyJoinList,
  },

  setup() {
    const store = useStore()
    const studyName = computed(() => store.getters.studyName )
    const member = computed(() => store.getters.member)
    const maxMember = computed(() => store.getters.maxMember)
    // const notice = computed(() => store.getters.notice)
    const camURL = computed(() => store.getters.camURL)

    const deleteStudy = () =>{
      store.dispatch('deleteStudy',store.getters.studyId)
    }

    const is_list = computed(()=> _.isEmpty(store.getters.loginUser))
    return { studyName,member,maxMember,camURL,deleteStudy,is_list }
  }
}
</script>

<style>

</style>
