<template>
<div class="modal fade" id="studyArticleDetail" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="staticBackdropLabel">{{ article.title }}</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        <!-- 디테일 -->
        <div v-if="state.type==0">
            내용:{{article.content}}
            <button @click="deleteArticle">삭제하기</button>
        </div>
        <!-- 업데이트 -->
        <updateStudyArticle v-if="state.type==1" />


      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
        <button v-if="state.type==0" @click.prevent="typeChange" type="button" class="btn btn-primary">수정하기</button>
        <button v-if="state.type!=0" @click.prevent="typeChange" type="button" class="btn btn-primary">돌아가기</button>
      </div>
    </div>
  </div>
</div>
</template>

<script>
import updateStudyArticle from "@/components/StudyArticle/updateStudyArticle.vue"
import { computed, reactive } from '@vue/runtime-core';
import { useStore } from "vuex"
export default {
    components:{
        updateStudyArticle,
    },

    setup(){
        const store = useStore();
        const article = computed(()=> store.getters.selectedArticle)
        const state = reactive({
            type: 0
        })
        const typeChange = ()=>{
            state.type = (state.type+1)%2
        }
        const deleteArticle = ()=>{
          store.dispatch("deleteStudyArticle")
        }

        return {article,state,typeChange,deleteArticle}
    }

}
</script>

<style>

</style>