<template>
  <div class="container d-flex">
    <img :src="selectedStudy.image" alt="기본사진" />
    <div>
      <div class="container d-flex">
        <div class="m-3">스터디 이름 : {{ selectedStudy.title }}</div>
        <div class="m-3">
          인원 : {{ selectedStudy.member }}/ {{ selectedStudy.maxmember }}
        </div>
      </div>
      <div>스터디 소개글 : collume 추가되면 추가할 예정{{}}</div>
    </div>
  </div>
  <div>
    <button
      type="button"
      class="btn btn-primary"
      data-bs-toggle="modal"
      data-bs-target="#studyJoinListModal"
    >
      참가 신청 리스트
    </button>
    <studyJoinList />
  </div>
  <div class="container d-flex">
    <div>스터디 주소 : {{ selectedStudy.url_conf }}</div>
    <router-link :to="{ path: '/' + '' }" class="navbar-brand"
      >입장하기</router-link
    >
  </div>
  <div>
    <!-- 게시판 -->
    <button @click.prevent="moveArticles" class="btn btn-primary">게시판</button>
  </div>
  <div>
    <button
      type="button"
      class="btn btn-primary"
      data-bs-toggle="modal"
      data-bs-target="#callenderModal"
    >
      전체보기
    </button>
    <callender />
  </div>
  <div class="container d-flex">
    <button @click.prevent="deleteStudy">스터디 터트리기</button>
    {{ loginUser.id }}
    <button @click.prevent="dropOutStudy(loginUser.id)">스터디 탈퇴하기</button>
    <div>
      <button
        type="button"
        class="btn btn-primary"
        data-bs-toggle="modal"
        data-bs-target="#updateStudyModal"
      >
        스터디 수정
      </button>
      <studyUpdate />
    </div>
  </div>

  <!-- Button trigger modal -->
  <button
    type="button"
    class="btn btn-primary"
    data-bs-toggle="modal"
    data-bs-target="#staticBackdrop1"
  >
    Launch static backdrop modal
  </button>

  <!-- Modal -->
  <div
    class="modal fade"
    id="staticBackdrop1"
    data-bs-backdrop="static"
    data-bs-keyboard="false"
    tabindex="-1"
    aria-labelledby="staticBackdropLabel"
    aria-hidden="true"
  >
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="staticBackdropLabel">Modal title</h5>
          <button
            type="button"
            class="btn-close"
            data-bs-dismiss="modal"
            aria-label="Close"
          ></button>
        </div>
        <div class="modal-body">...</div>
        <div class="modal-footer">
          <button
            type="button"
            class="btn btn-secondary"
            data-bs-dismiss="modal"
          >
            Close
          </button>
          <button type="button" class="btn btn-primary">Understood</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import callender from "@/components/StudyPage/callender";
import studyUpdate from "@/components/StudyPage/studyUpdate.vue";
import studyJoinList from "@/components/StudyPage/studyJoinList.vue";
import { useStore } from "vuex";
import { computed, watch,onMounted } from "vue";
import { useRoute,useRouter } from "vue-router";

export default {
  
  name: "StudyHome",
  components: {
    
    callender,
    studyUpdate,
    studyJoinList,
  },

  setup() {
    const store = useStore();
    const route = useRoute();
    const router = useRouter();
    const selectedStudy = computed(() => store.getters.selectedStudy);
    const deleteStudy = () =>
      store.dispatch("deleteStudy", store.getters.studyId);
    const pageUpdate = () =>
      store.dispatch("selectStudy", store.getters.selectedStudy.id);

    const fetchStudyHome = () =>{
      console.log("패치 스터디 홈 작동")
      store.dispatch("selectStudy",route.params.studyPk)
    };
    onMounted(()=>{
      fetchStudyHome()
    })

    const moveArticles = ()=>{
      router.push({name: 'studyArticles', params: {studyPk:store.getters.selectedStudy.id}})
    }
    const loginUser = computed(()=> store.getters.loginUser)
    const dropOutStudy = (user_pk)=> {
      store.dispatch("dropOutStudy",user_pk)
      router.push({ name: "Home" })
    }
    return { selectedStudy, deleteStudy, pageUpdate,fetchStudyHome,moveArticles,dropOutStudy,loginUser };
  },
};
</script>

<style></style>
