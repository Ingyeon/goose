<template>
<h1>스터디 참여하기</h1>
<div>
<form>
        <label class="form-label">인원 선택</label>
    <select class="form-select" v-model="state.form.recruitment">
  <option selected>인원</option>
  <option value=1>1</option>
  <option value=2>2</option>
  <option value=3>3</option>
    <option value=4>4</option>
    <option value=5>5</option>
    <option value=6>6</option>
</select>

      <label class="form-label">모집할 내 스터디 선택</label>
    <select class="form-select" v-model="state.form.study_pk">
  <option selected>선택</option>
  <!-- 함수로 for문 돌려서 셀렉트 박스에 넣기 -->
  
  <option v-for="item in study_list"  :value="item.id">{{  item.title }}</option> 
<!-- 게시글 작성자가 운영중인 스터디의 이름들을 목록에 뿌려줘야함 -->

</select>

    <label class="form-label">분류 선택</label>
    <select class="form-select" v-model="state.form.category">
  <option selected>선택</option>
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
  <button type="submit" @click.prevent="clickSet" class="btn btn-primary">작성</button>
    
</form>
<button @click="test">123</button>
</div>

<!-- 확인용 -->
{{state.form}}
</template>

<script>
import {computed, onMounted, reactive} from 'vue'
import {useStore} from 'vuex'
import {  useRouter  } from 'vue-router'

export default {
    name: 'newArticle',
    
    setup(){
    const Router = useRouter()
    const store = useStore()
    const state = reactive({
        form:{
            recruitment: '',
            category: '',
            title: '',
            content: '',
            state: '모집중',
            study_pk: null,
        },
        // 현재 내가 운영중인 스터디의 정보
        my_study:[]
    })

    // 현재 구현해야 하는 것
    // 1. 게시글 작성자의 운영 스터디를 목록으로 보여주기
    // 2. 선택한 스터디를 state.study에 할당하기

    store.dispatch('authStudyList')
// 내가 운영하는 스터디를 state.study에 넣는 함수
    const study_info = function(){
    for (let study of store.getters.authStudyList)
      state.my_study.push({id: study.id,title:study.title})
    }

    const clickSet = function(){   // 게시글 생성
      store.dispatch('fetchLoginUser')
      store.dispatch('createArticle',{
        category:state.form.category, 
        recruitment: Number(state.form.recruitment),
        state: state.form.state,
        title: state.form.title,
        content: state.form.content,
        study_pk: state.form.study_pk,
        user_pk: store.getters.loginUser.id,
        })
        Router.push({path:`/article/${store.getters.article.id}`})

    }
    const study_list = computed(() => store.getters.authStudyList)
    onMounted(() =>study_info())

    return {state,clickSet,store,study_info,study_list}
    }
}
</script>