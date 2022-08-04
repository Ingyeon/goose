<template>
  <div class="container d-flex">
    <!-- {{ state.photo }} -->
    <img :src="state.photo" alt="기본사진" />
    <div>
      <div class="container d-flex ">
        <div class="m-3">스터디 이름 : {{ selectedStudy.title }}</div>
        <!-- <div class="m-3">
          인원 : {{ selectedStudy.member }}/ {{ selectedStudy.maxmember }}
        </div> -->
      </div>
      <div>스터디 소개글 : collume 추가되면 추가할 예정{{}}</div>
      <div>카페 매니저 : {{ manager.name }}</div>
    </div>
  </div>
  <div>
    <button
      v-if="isManager"
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
    <button
      type="button"
      class="btn btn-primary"
      data-bs-toggle="modal"
      data-bs-target="#exampleModal"
    >
      입장용 모달
    </button>

    <!-- 입장용 모달 시작 -->
    <div
      class="modal fade"
      id="exampleModal"
      tabindex="-1"
      aria-labelledby="exampleModalLabel"
      aria-hidden="true"
    >
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title" id="exampleModalLabel">Modal title</h5>
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
            <button
              type="button"
              class="btn btn-primary"
              data-bs-dismiss="modal"
              @click.prevent="clickbtn"
            >
              입장하기
            </button>
            <button
              type="button"
              class="btn btn-primary"
              data-bs-dismiss="modal"
              @click.prevent="clickbtn2"
            >
              입장하기2
            </button>
          </div>
        </div>
      </div>
    </div>
    <!-- 입장용 모달 끝 -->
  </div>
    <!-- 게시판 -->
    <miniArticles />
  <hr>
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
    <button v-if="isManager" @click.prevent="deleteStudy">
      스터디 터트리기
    </button>
    <button @click.prevent="dropOutStudy(loginUser.id)">스터디 탈퇴하기</button>
    <div>
      <button
        v-if="isManager"
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
import miniArticles from "@/components/StudyPage/miniArticles.vue"
// 사진
import study1 from "@/assets/study1.png"
import study2 from "@/assets/study2.png"
import study3 from "@/assets/study3.jpg"
import { useStore } from "vuex";
import { useRoute, useRouter } from "vue-router";
import { computed, watch, onMounted, reactive } from "vue";
// import router from "@/router";

export default {
  name: "StudyHome",
  components: {
    callender,
    studyUpdate,
    studyJoinList,
    miniArticles,
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

    const fetchStudyHome = () => {
      store.dispatch("selectStudy", route.params.studyPk);
    };
    onMounted(() => {
      fetchStudyHome();
    });

    const loginUser = computed(() => store.getters.loginUser);
    const dropOutStudy = (user_pk) => {
      store.dispatch("dropOutStudy", user_pk);
      router.push({ name: "Home" });
    };
    const manager = computed(() => store.getters.studyManager);
    const isManager = computed(() => store.getters.isStudyManager);
    const clickbtn = function () {
      router.push({ name: "PrivateStudyRoom" });
    };
    const clickbtn2 = function () {
      router.push({ name: "PublicStudyRoom" });
    };
    onMounted(() => {
      fetchStudyHome();
    });
    const state = reactive({
      photo : store.getters.selectedStudy.image
    })

    const changePhoto = ()=>{
      if (store.getters.selectedStudy.image==='study1'){state.photo = study1}
      else if (store.getters.selectedStudy.image==='study2'){state.photo = study2}
      else if (store.getters.selectedStudy.image==='study3'){state.photo = study3}
    }
    changePhoto
    
    
    return {
      selectedStudy,
      deleteStudy,
      pageUpdate,
      fetchStudyHome,
      dropOutStudy,
      loginUser,
      manager,
      isManager,
      clickbtn,
      clickbtn2,
      changePhoto,
      state
    };
  },

  watch: {
    selectedStudy: {
      handler(){
        this.changePhoto();
      }
    }
  }
};


</script>

<style></style>
