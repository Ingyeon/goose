<template>
<div class="container">
<h3 class="d-flex justify-content-center m-5">게시글 수정</h3>
<div>
<form>

    <label class="form-label">모집 여부</label>
    <select class="form-select" v-model="state.form.state">
  <option value='모집중'>모집 중</option>
  <option value=모집완료>모집완료</option>
</select>

    <label class="form-label">인원 선택</label>
    <select class="form-select" v-model="state.form.recruitment">
  <option value=1>1</option>
  <option value=2>2</option>
  <option value=3>3</option>
    <option value=4>4</option>
    <option value=5>5</option>
    <option value=6>6</option>
</select>

      <label class="form-label">모집할 내 스터디 선택</label>
    <select class="form-select" v-model="state.form.study_pk">
  <!-- 함수로 for문 돌려서 셀렉트 박스에 넣기 -->
  
  <option v-for="item in state.my_study"  :value="item.id">{{  item.title }}</option> 
<!-- 게시글 작성자가 운영중인 스터디의 이름들을 목록에 뿌려줘야함 -->

</select>

    <label class="form-label">분류 선택</label>
    <select class="form-select" v-model="state.form.category">
  <option value="면접">면접</option>
  <option value="토익">토익</option>
  <option value="알고리즘">알고리즘</option>
    <option value="개인공부">개인공부</option>
    <option value="기타">기타</option>
</select>
  <div class="mb-3">
    <label for="titleInput" class="form-label">제목</label>
    <input type="text" class="form-control" id="titleInput" v-model="state.form.title">
  </div>
  <div class="mb-3">
    <label for="textarea" class="form-label">내용</label>
    <textarea type="textarea" class="form-control" id="textarea" v-model="state.form.content"></textarea>
  </div>
  <div class="input-Box">
    <button @click.prevent="BackDetail">뒤로 가기</button>
    <button type="submit" @click.prevent="clickSet" class="btn btn-primary">작성</button>
  </div>
</form>
</div>
</div>

</template>

<script>
import { reactive } from 'vue'
import {useStore} from 'vuex'
import { useRouter } from 'vue-router'

export default {
    name: 'newArticle',
    
    setup(){
    const store = useStore()
    const router = useRouter()
    const state = reactive({
        id: store.getters.article.id,
        form:{
            recruitment: store.getters.article.recruitment,
            category: store.getters.article.category,
            title: store.getters.article.title,
            content: store.getters.article.content,
            state: store.getters.article.state,

        },
        // 현재 내가 운영중인 스터디의 정보
        my_study:[]
    })

    store.dispatch('authStudyList')
// 내가 운영하는 스터디를 state.study에 넣는 함수
    const study_info = function(){
    for (let study of store.getters.authStudyList)
      state.my_study.push({id: study.id,title:study.title})
    }
    const BackDetail = function(){
      router.push({name: 'ArticleDetail' ,params: {id:state.id}})
    }
    const clickSet = function(){   // 게시글 수정
      store.dispatch('updateArticle',{
        category:state.form.category, 
        content: state.form.content,
        recruitment: Number(state.form.recruitment),
        state: state.form.state,
        title: state.form.title,
        })
        console.log(state.form.category)
    }
    study_info()

    return {state,BackDetail,clickSet,store,study_info}
    }
}
</script>

<style scoped>
  h3 {
    font-family: 'NanumSquare', sans-serif;
    font-weight: bold;
  }
  .input-Box{
    display: flex;
    /* justify-content: space-evenly; */
    justify-content: end;

    }
    .input-Box button{
    background: #ffd700;
    color: #000000;
    cursor: pointer;
    width: 100px;
    margin-bottom: 20px;
    font-weight: 600;
    text-align: center;
    border-radius: 80px 40px;
    margin-right: 30px;
    border:none;
  }
  label[class="form-label"] {
    font-family: 'NanumSquare', sans-serif;
    font-weight: bold;
    font-size: 18px;
    margin-top: 3px;
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
</style>