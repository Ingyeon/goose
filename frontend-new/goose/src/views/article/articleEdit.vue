<template>
<div class="container">
<h3 class="d-flex justify-content-center display-4">게시글 수정</h3>
<div>
<form>

    <label class="form-label mx-0 px-0">모집 여부</label>
    <select class="form-select" v-model="state.form.state">
  <option value='모집중'>모집 중</option>
  <option value='모집완료'>모집완료</option>
</select>

    <label class="form-label mx-0 px-0">인원 선택</label>
    <select class="form-select" v-model="state.form.recruitment">
  <option value=1>1</option>
  <option value=2>2</option>
  <option value=3>3</option>
    <option value=4>4</option>
    <option value=5>5</option>
    <option value=6>6</option>
</select>

  <div class="mb-3">
    <label for="titleInput" class="form-label mx-0 px-0">제목</label>
    <input type="text" class="form-control border border-dark" id="titleInput" v-model="state.form.title">
  </div>
  <div class="mb-3">
    <label for="textarea" class="form-label mx-0 px-0">내용</label>
    <textarea type="textarea" class="form-control" id="textarea" v-model="state.form.content"></textarea>
  </div>
  <div class="input-Box">
    <button @click.prevent="BackDetail">뒤로 가기</button>
    <button type="submit" @click.prevent="clickSet">작성</button>
  </div>
</form>
</div>
</div>
<div class="p-3"></div>

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
        category:store.getters.article.category, 
        content: state.form.content,
        recruitment: Number(state.form.recruitment),
        state: state.form.state,
        title: state.form.title,
        })
        
    }
    study_info()

    return {state,BackDetail,clickSet,store,study_info}
    }
}
</script>

<style scoped>
ul {
  list-style-type: none;
}

li {
  display: inline-block;
}

input[type="radio"][id^="../../"] {
  display: none;
}

label {
  border: 1px solid #fff;
  padding: 10px;
  display: block;
  position: relative;
  margin: 10px;
  cursor: pointer;
}

label:before {
  background-color: white;
  color: white;
  content: " ";
  display: block;
  border-radius: 50%;
  border: 1px solid grey;
  position: absolute;
  top: -5px;
  left: -5px;
  width: 25px;
  height: 25px;
  text-align: center;
  line-height: 28px;
  transition-duration: 0.4s;
  transform: scale(0);
}

label img {
  height: 250px;
  width: 250px;
  transition-duration: 0.2s;
  transform-origin: 50% 50%;
}

:checked + label {
  border-color: #ddd;
}

:checked + label:before {
  content: "✓";
  background-color: grey;
  transform: scale(1);
}

:checked + label img {
  transform: scale(0.9);
  /* box-shadow: 0 0 5px #333; */
  z-index: -1;
}
.input-Box button{
    background: #ffd700;
    color: #000000;
    cursor: pointer;
    width: 100px;
    margin-bottom: 20px;
    font-weight: 600;
    text-align: center;
    border-radius: 40px 80px;
    margin-right: 30px;
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
  .input-Box:nth-child(7) {
    display: flex;
    justify-content: end;
    }
  .input-Box input[type="submit"] {
    margin-right:4px;
  }
  .form-select {
    width: 100%;
    height: 50px;
    margin-top: 3px;
    border: solid 1px #000;
}
</style>