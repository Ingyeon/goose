<template>
    <form>
        <!-- 카테고리 -->
          <div v-if="isManager" class="d-flex">
            <p class="text-capitalize custom-label">카테고리</p>
            <div class="m-3">
              <select v-model="credential.category" class="form-select" aria-label="Default select example">
              <!-- <select class="form-select" aria-label="Default select example"> -->
                <option value="notice">공지</option>
                <option value="free">자유</option>
                <!-- <option value="code">코드</option> -->
            </select>
            </div>
          </div>
          <!-- 스터디 이름 -->
          <div>
            <label for="studyArticleName"  class="custom-label">글 제목</label>
            <div class="form-floating input-Box">
              <input type="text" id="studyArticleName" class="form-control" placeholder="글 제목" v-model="credential.title" maxlength="30">
              <!-- <input type="text" id="studyArticleName" class="form-control" placeholder="글 제목" > -->
              <label for="studyArticleName"  class="form-label">글 제목</label>
            </div>
          </div>
          <!-- 글 내용 -->
          <div class="mb-3 input-Box">
            <label for="articleContent" class="form-label">내용</label>
            <textarea type="textarea" class="form-control" id="articleContent" v-model="credential.content" required></textarea>
            <!-- <textarea type="textarea" class="form-control" id="articleContent" required></textarea> -->
          </div>

        <!-- 제출 -->
          <div class="input-Box">
            <input @click.prevent="updateArticle" type="submit" value="Submit">  
          </div>
        </form>
</template>

<script>
import { reactive } from '@vue/reactivity';
import { useStore } from "vuex";
import { computed } from "vue";
export default {
  setup(){
    const store = useStore();

    const credential = reactive({
      "category":store.getters.selectedArticle.category,
      "content":store.getters.selectedArticle.content,
      "image":store.getters.selectedArticle.image,
      "title":store.getters.selectedArticle.title,
    })

    const updateArticle = () =>{
      store.dispatch("updateStudyArticle",credential)
    }
    const isManager = store.getters.isStudyManager
    return {updateArticle,credential,isManager} 
  }
}
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
  margin-right:4px;
}
</style>