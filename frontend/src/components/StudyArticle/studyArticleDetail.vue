<template>
<div class="modal fade" id="studyArticleDetail" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h3 v-if="state.type==0" class="modal-title fw-bold" id="staticBackdropLabel">{{ article.title }}</h3>
        <h3 v-if="state.type!=0" class="modal-title" id="staticBackdropLabel">글 수정</h3>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        <!-- 디테일 -->
        <div class="box" v-if="state.type==0">
          <h4 class="fw-bold">내용</h4>
          <hr>
          <div class="container">
            <div class="content">
              {{article.content}}
            </div>
            <!-- 댓글 -->
          </div>
            <hr>
            <h4 class="fw-bold">댓글</h4>
            <comment/>
        </div>
        <!-- 업데이트 -->
        <updateStudyArticle v-if="state.type==1" />


      </div>
      <div class="modal-footer">
        <!-- <button @click="typeReset" type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button> -->
        <button v-if="isWriter && state.type==0" @click.prevent="[deleteArticle(),$emit('refresh')]" class="btn btn-warning" data-bs-dismiss="modal">삭제하기</button>
        <button v-if="state.type==0 && isWriter" @click.prevent="typeChange" type="button" class="btn btn-primary">수정하기</button>
        <button v-if="state.type!=0" @click.prevent="typeChange" type="button" class="btn btn-primary">돌아가기</button>
      </div>
    </div>
  </div>
</div>

</template>

<script>
import updateStudyArticle from "@/components/StudyArticle/updateStudyArticle.vue"
import comment from "./comment.vue";
import { computed, reactive } from '@vue/runtime-core';
import { useStore } from "vuex"
export default {
    components:{
        updateStudyArticle,comment,
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
          store.dispatch('refresh')
        }
        const typeReset = ()=>{
          setTimeout(()=>{
            state.type = 0
          },1000)
        }
        
        
        const isWriter = computed(()=> store.getters.isArticleWriter)
        return {article,state,typeChange,deleteArticle,isWriter,typeReset}
    }

}
</script>

<style scoped>
.modal-body{
  height:100%
}
.box{
  height:100%
}
 .container {
  height: 150px;
 }
 .content{
  font-weight: 500;
  font-size: 1.2rem
 }
</style>