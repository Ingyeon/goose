<template>
  <div class="container">
    <div class="d-flex justify-content-center m-5">
      <h3>스터디 만들기</h3>
    </div>
    <div class="container">
      <form>
        <!-- 공개스터디, 비공개 스터디 -->
        <div class="input-Box">
          <label class="form-label">공개 여부</label>
          <select
            v-model="state.credential.open"
            class="form-select"
            aria-label="Default select example"
          >
            <option value="0">공개</option>
            <option value="1">비공개</option>
          </select>
        </div>
        <!-- 최대인원 -->
        <div class="input-Box">
          <label class="form-label">총원</label>
          <select
            v-model="state.credential.maxMember"
            class="form-select"
            aria-label="Default select example"
          >
            <!-- <option value="" selected>인원 수를 선택해주세요</option> -->
            <option value="1">1명</option>
            <option value="2">2명</option>
            <option value="3">3명</option>
            <option value="4">4명</option>
            <option value="5">5명</option>
            <option value="6">6명</option>
          </select>
        </div>

        <!-- 카테고리 -->
        <div class="input-Box">
          <label for="selectcategory" class="form-label">사용언어</label>
          <select
            v-model="state.credential.category"
            id="selectcategory"
            class="form-select"
            aria-label="Default select example"
          >
            <!-- <option selected>분류</option> -->
            <option value="" selected>선택해주세요</option>
            <option value="C">C</option>
            <option value="C++">C++</option>
            <option value="JAVA">JAVA</option>
            <!-- <option value="JavaScript">JavaScript</option> -->
            <option value="Python">Python</option>
          </select>
          <!-- <label for="selectcategory" class="form-label">카테고리</label> -->
        </div>
        <!-- 스터디 이름 -->
        <div class="input-Box">
          <label for="studyname" class="form-label">스터디 이름</label>
          <!-- <div class="form-floating input-Box"> -->
          <input
            type="text"
            id="studyname"
            class="form-control"
            placeholder="스터디 이름"
            v-model="state.credential.title"
            maxlength="30"
          />
          <!-- <label for="studyname"  class="form-label">스터디 이름</label> -->
          <!-- </div> -->
        </div>

        <!-- 비공개방일시 비밀번호 -->
        <div v-if="state.credential.open == 1" class="input-Box">
          <label for="floatingPassword" class="form-label">스터디 비밀번호</label>
          <input
            v-model="state.credential.password"
            type="password"
            class="form-control"
            id="floatingPassword"
            placeholder="비밀번호"
          />
          <!-- <label for="floatingPassword" class="form-label">Password</label> -->
        </div>
        <!-- 사진 url -->
        <selectImg />

        <!-- 제출 -->
        <div class="input-Box d-flex justify-content-end">
          <input @click.prevent="createStudy()" type="submit" value="만들기" v-if="state.credential.open == 1"/>
          <input @click.prevent="createStudy2()" type="submit" value="만들기" v-if="state.credential.open == 0"/>
        </div>
      </form>
    </div>
    <!-- {{ state.credential }} -->
  </div>
</template>

<script>
import { reactive } from "@vue/reactivity";
import { useStore } from "vuex";
import { computed } from "vue";
import { useRouter } from "vue-router";
import selectImg from "@/components/StudyPage/selectImg.vue";
export default {
  components: {
    selectImg,
  },

  props: {},
  setup() {
    const store = useStore();
    const router = useRouter();
    const loginUser = computed(() => store.getters.loginUser);
    const imgUrl = computed(() => store.getters.choiceImg);
    const state = reactive({
      credential: {
        category: "",

        image: imgUrl,
        maxmember: 1,
        member: 1,
        open: 0,
        password: "",
        title: "",
        url_conf: Math.random().toString(36).substr(2, 11),
        url_page: "",
      },
    });
    const fetchStudyHome = async function (studyId) {
      await store.dispatch("selectStudy", studyId)
    };
    const createStudy = async () => {
      await store.dispatch("createStudy", state.credential);
      await store.dispatch("authStudyList");
      await store.dispatch("saveOpenList");
    };
    const createStudy2 = async () => {
      await store.dispatch("createStudy", state.credential);
      await store.dispatch("authStudyList");
      await store.dispatch("saveOpenList");
      const openstudyList = await store.getters.openstudyList;
      var studyId = "";
      openstudyList.forEach(element => {
        if(element.url_conf == state.credential.url_conf){
          studyId = element.id;
        }
      });
      await fetchStudyHome(studyId);
      await router.push({ name: "PublicStudyRoom" });
    };
    return { createStudy, createStudy2, state };
  },
};
</script>

<style scoped>
.form-select {
  width: 100%;
  height: 50px;
  margin-top: 3px;
  border: solid 1px #000;
}
h3 {
  font-family: "NanumSquare", sans-serif;
  font-weight: bold;
}
.input-Box:nth-child(7) {
  display: flex;
  /* justify-content: space-evenly; */
  justify-content: end;
}
</style>
