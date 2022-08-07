<template>
  <div>
    <div class="container d-flex mt-3">
      <div class="row">
        <div class="col-lg-4 col-md-6 col-12">
          <img class="container-fluid" :src="state.photo" alt="기본사진" />
          <div class="d-flex align-items-center">
            <div id="study-url" class="fw-bold">
              스터디 주소 : <span>{{ selectedStudy.url_conf }}</span>
            </div>
            <button
              type="modal-button"
              class="modal-button"
              data-bs-toggle="modal"
              data-bs-target="#exampleModal"
            >
              스터디 입장하기
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
                    <h5 class="modal-title" id="exampleModalLabel">
                      {{ selectedStudy.title }}
                    </h5>
                    <button
                      type="button"
                      class="btn-close"
                      data-bs-dismiss="modal"
                      aria-label="Close"
                    ></button>
                  </div>
                  <div class="modal-body">
                    컴파일러 선택
                    <select v-model="compiler" @change="selectLanguage">
                      <option value="C">C</option>
                      <option value="C++">C++</option>
                      <option value="JAVA">JAVA</option>
                      <option value="Python">Python</option>
                    </select>
                    {{ compiler }} {{ selectedStudy.category }}
                  </div>
                  <div class="modal-footer">
                    <button
                      type="button"
                      class="btn btn-warning"
                      data-bs-dismiss="modal"
                      @click.prevent="clickbtn"
                      @click="selectLanguage"
                    >
                      입장하기
                    </button>
                    <button
                      type="button"
                      class="btn btn-secondary"
                      data-bs-dismiss="modal"
                    >
                      닫기
                    </button>
                    <!-- <button type="button" class="btn btn-warning" data-bs-dismiss="modal" @click.prevent="clickbtn2">
              입장하기2
            </button> -->
                  </div>
                </div>
              </div>
            </div>
            <!-- 입장용 모달 끝 -->
          </div>
        </div>
        <div class="col-lg-8 col-md-6 col-12">
          <div class="container d-flex justify-content-center">
            <h3 class="m-3 fw-bold">{{ selectedStudy.title }}</h3>
          </div>
          <div class="d-flex justify-content-end">
            <span class="accept-list">
              <button
                id="listbutton"
                v-if="isManager"
                type="button"
                class="button"
                data-bs-toggle="modal"
                data-bs-target="#studyJoinListModal"
              >
                참가 신청 리스트
              </button>
              <studyJoinList />
            </span>
            <span class="editbutton">
              <button
                id="editbutton"
                v-if="isManager"
                type="button"
                class="button"
                data-bs-toggle="modal"
                data-bs-target="#updateStudyModal"
              >
                스터디 수정
              </button>
              <studyUpdate />
            </span>
            <!-- <div class="m-3">
            인원 : {{ selectedStudy.member }}/ {{ selectedStudy.maxmember }}
          </div> -->
          </div>

          <div class="d-flex justify-content-end">
            스터디 관리자 : {{ manager.name }}
          </div>
          <h3>공지사항</h3>
          <div class="study-content">
            <div class="container">
              <notice />
            </div>
          </div>
        </div>
      </div>
    </div>
    <br />
    <br />
    <div class="box">
      <!-- 게시판 -->
      <miniArticles />
    </div>

    <div class="callender-box">
      <callender />
    </div>
    <div class="container d-flex justify-content-evenly">
      <button
        class="button-danger"
        v-if="isManager"
        @click.prevent="deleteStudy"
      >
        스터디 터트리기
      </button>
      <button class="button-danger" @click.prevent="dropOutStudy(loginUser.id)">
        스터디 탈퇴하기
      </button>
    </div>
    <div style="height: 50px"></div>
  </div>
</template>

<style scoped>
* {
  font-family: "NanumSquare", sans-serif;
}
.row {
  display: flex;
  display: -webkit-flex;
  flex-wrap: wrap;
  width: 100%;
  height: 100%;
}

.study-content {
  /* border: 4px solid #000; */
  background-color: #f0f0f1;
  margin-top: 1rem;
  padding-top: 1rem;
  height: 70%;
  border-radius: 30px;
}
img {
  margin-top: 50px;
  background-size: cover;
  /* width:100%;*/
  height: 90%;
}

/* .editbutton {

  position: relative;
  bottom: 0px;
  left: 65%;
}
.accept-list  {
  position: relative;
  bottom:0px;
  left:60%
} */
.button {
  background: #ffd700;
  color: #000000;
  cursor: pointer;
  width: 150px;
  height: 30px;
  margin-bottom: 20px;
  font-weight: bold;
  text-align: center;
  border-radius: 80px 40px;
  font-size: 16px;
  border: none;
}
.modal-button {
  margin-left: 10px;
  background: #ffd700;
  color: #000000;
  cursor: pointer;
  width: 150px;
  height: 40px;
  font-weight: bold;
  text-align: center;
  border-radius: 80px 40px;
  font-size: 18px;
  border: none;
}
.button-danger {
  margin-left: 10px;
  background: #ec610b;
  color: #fff;
  cursor: pointer;
  width: 150px;
  height: 40px;
  font-weight: bold;
  text-align: center;
  border-radius: 80px 40px;
  font-size: 18px;
  border: none;
}
.box {
  height: 50%;
  margin-top: 15px;
}
.callender-box {
  height: 70%;
  margin-top: 15px;
}

@media screen and (max-width: 768px) {
  /* .box{
    position:relative;
    top:50px;
  } */
  #study-url {
    display: none;
  }
}

@media screen and (max-width: 1048px) {
  .box {
    position: relative;
    top: 50px;
  }
}
</style>

<script>
import callender from "@/components/StudyPage/callender";
import studyUpdate from "@/components/StudyPage/studyUpdate.vue";
import studyJoinList from "@/components/StudyPage/studyJoinList.vue";
import miniArticles from "@/components/StudyPage/miniArticles.vue";
import notice from "@/components/StudyPage/notice.vue";
// 사진
import study1 from "@/assets/study1.png";
import study2 from "@/assets/study2.png";
import study3 from "@/assets/study3.jpg";
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
    notice,
  },

  setup() {
    const store = useStore();
    const route = useRoute();
    const router = useRouter();
    const selectedStudy = computed(() => store.getters.selectedStudy);
    const deleteStudy = () =>
      store.dispatch("deleteStudy", store.getters.studyId);
    // const pageUpdate = () =>
    //   store.dispatch("selectStudy", store.getters.selectedStudy.id);

    const fetchStudyHome = () => {
      store.dispatch("selectStudy", route.params.studyPk);
    };
    fetchStudyHome();

    const loginUser = computed(() => store.getters.loginUser);
    const dropOutStudy = (user_pk) => {
      store.dispatch("dropOutStudy", user_pk);
    };
    const manager = computed(() => store.getters.studyManager);
    const isManager = computed(() => store.getters.isStudyManager);
    const clickbtn = function () {
      router.push({ name: "PrivateStudyRoom" });
    };
    const clickbtn2 = function () {
      router.push({ name: "PublicStudyRoom" });
    };

    const state = reactive({
      photo: store.getters.selectedStudy.image,
    });

    const changePhoto = () => {
      if (store.getters.selectedStudy.image === "study1") {
        state.photo = study1;
      } else if (store.getters.selectedStudy.image === "study2") {
        state.photo = study2;
      } else if (store.getters.selectedStudy.image === "study3") {
        state.photo = study3;
      }
    };
    changePhoto();

    return {
      selectedStudy,
      deleteStudy,
      // pageUpdate,
      // fetchStudyHome,
      dropOutStudy,
      loginUser,
      manager,
      isManager,
      clickbtn,
      clickbtn2,
      changePhoto,
      state,
    };
  },

  watch: {
    selectedStudy: {
      handler() {
        this.changePhoto();
      },
    },
  },
};
</script>

<style></style>
