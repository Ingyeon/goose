<template>
    <h1>업데이트!</h1>
    <form>
        <!-- 카테고리 -->
          <div class="d-flex">
            <p class="text-capitalize custom-label">카테고리</p>
            <div class="m-3">
              <select v-model="credential.category" class="form-select" aria-label="Default select example">
              <!-- <select class="form-select" aria-label="Default select example"> -->
                <option value="notice">공지</option>
                <option value="free">자유</option>
                <option value="code">코드</option>
            </select>
            </div>
          </div>
          <!-- 스터디 이름 -->
          <div>
            <label for="studyArticleName"  class="custom-label">글 제목</label>
            <div class="form-floating input-Box">
              <input type="text" id="studyArticleName" class="form-control" placeholder="글 제목" v-model="credential.title">
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
      console.log(credential)
      store.dispatch("updateStudyArticle",credential)
    }
    return {updateArticle,credential} 
  }
}
</script>

<style>

</style>