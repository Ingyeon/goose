<template>
<div class="modal fade" id="studyArticleDetail" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
  <div class="modal-dialog modal-lg">
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
          <div class="d-flex justify-content-end">
            <button v-if="state.type==0 && isWriter" @click.prevent="typeChange" type="button" class="cus-btn-yellow mx-2">수정하기</button>
            <button v-if="(isWriter || isStudyManager) && state.type==0" @click.prevent="[deleteArticle(),$emit('refresh')]" class="cus-btn-delete mx-2" data-bs-dismiss="modal">삭제하기</button>
            
          </div>

            <hr>
            <h4 class="fw-bold">댓글</h4>
            <comment/>
        </div>
        <!-- 업데이트 -->
        <div>
          <updateStudyArticle v-if="state.type==1" @typeChange="typeChange()" />
        </div>


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
        
        const isStudyManager = computed(()=>store.getters.isStudyManager)
        const isWriter = computed(()=> store.getters.isArticleWriter)
        return {article,state,typeChange,deleteArticle,isWriter,typeReset,isStudyManager}
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

  .cus-btn-delete {
  background: #E64848;
  color: whitesmoke;
  cursor: pointer;
  width: 100px;
  margin-bottom: 20px;
  font-weight: 600;
  text-align: center;
  border-radius: 80px 40px;
}
</style>