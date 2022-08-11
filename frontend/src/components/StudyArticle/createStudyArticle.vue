<template>
  <!-- Button trigger modal -->
  <button
    v-if="isStudyMember"
    type="button"
    class="cus-btn-yellow"
    data-bs-toggle="modal"
    data-bs-target="#createStudyArticle"
  >
    글 작성
  </button>

  <!-- Modal -->
  <div
    class="modal fade"
    id="createStudyArticle"
    data-bs-backdrop="static"
    data-bs-keyboard="false"
    tabindex="-1"
    aria-labelledby="staticBackdropLabel"
    aria-hidden="true"
  >
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="staticBackdropLabel">글 작성</h5>
          <button
            type="button"
            class="btn-close"
            data-bs-dismiss="modal"
            aria-label="Close"
          ></button>
        </div>
        <!-- Modal Body -->
        <div class="modal-body">
          <!-- form -->
          <form>
            <!-- 카테고리 -->
            <div v-if="isManager" class="input-Box">
              <label class="form-label">카테고리</label>
              <!-- <p class="text-capitalize custom-label">카테고리</p> -->
              <!-- <div class="m-3"> -->
              <select
                v-model="credential.category"
                class="form-select"
                aria-label="Default select example"
              >
                <!-- <select class="form-select" aria-label="Default select example"> -->
                <option value="notice">공지</option>
                <option value="free">자유</option>
                <!-- <option value="code">코드</option> -->
              </select>
            </div>
            <!-- 스터디 이름 -->
            <div class="input-Box">
              <label for="studyArticleName" class="form-label">글 제목</label>
              <input
                type="text"
                id="studyArticleName"
                class="form-control"
                placeholder="글 제목"
                v-model="credential.title"
                maxlength="30"
              />
              <!-- <label for="studyArticleName"  class="form-label">글 제목</label> -->
            </div>
            <!-- 글 내용 -->
            <div class="input-Box">
              <label for="articleContent" class="form-label">내용</label>
              <textarea
                type="textarea"
                class="form-control"
                id="articleContent"
                v-model="credential.content"
                required
              ></textarea>
              <!-- <textarea type="textarea" class="form-control" id="articleContent" required></textarea> -->
            </div>

            <!-- 제출 -->
            <div class="input-Box">
              <input
                @click.prevent="
                  [createArticle(), $emit('createArticle', credential.category)]
                "
                type="submit"
                value="작성"
                data-bs-dismiss="modal"
              />
            </div>
          </form>

          <!-- form 끝 -->
        </div>
        <!-- <div class="modal-footer">
          <button
            type="button"
            class="btn btn-secondary"
            data-bs-dismiss="modal"
          >
            Close
          </button>
        </div> -->
      </div>
    </div>
  </div>
</template>

<script>
import { reactive } from "@vue/reactivity";
import { useStore } from "vuex";
import { computed } from "vue";
export default {
  setup() {
    const store = useStore();

    // const selectedStudy = computed(() => store.getters.selectedStudy);
    const credential = reactive({
      category: "free",
      content: "",
      image: "",
      study_pk: store.getters.selectedStudy.id,
      title: "",
    });

    const createArticle = () => {
      // console.log(credential)
      store.dispatch("createStudyArticle", credential);
      credential.title = "";
      credential.content = "";
    };

    const isManager = computed(()=> store.getters.isStudyManager);
    const isStudyMember = computed(()=>store.getters.isStudyMember);
    return { createArticle, credential, isManager,isStudyMember };
  },
};
</script>

<style scoped>
.cus-btn-yellow {
  background: #ffd700;
  color: #000000;
  cursor: pointer;
  width: 100px;
  margin-bottom: 20px;
  font-weight: 600;
  text-align: center;
  border-radius: 80px 40px;
}

.form-select {
  width: 100%;
  height: 50px;
  margin-top: 3px;
  border: solid 1px #000;
}

textarea {
  width: 100%;
  height: 200px;
  padding: 10px;
  box-sizing: border-box;
  border: solid 1px #000;
  border-radius: 5px;
  font-size: 16px;
  resize: none;
}
.input-Box:nth-child(4) {
  display: flex;
  justify-content: end;
}
.input-Box input[type="submit"] {
  margin-right: 4px;
}
</style>
