<template>
<h1>스터디 참여하기</h1>
<div>
<form>
        <label class="form-label">인원 선택</label>
    <select class="form-select" v-model="state.form.recruit">
  <option selected>인원</option>
  <option value="1">1</option>
  <option value="2">2</option>
  <option value="3">3</option>
    <option value="4">4</option>
    <option value="5">5</option>
    <option value="6">6</option>
</select>
    <label class="form-label">스터디 선택</label>
    <select class="form-select" v-model="state.form.chooseStudy">
  <option selected>선택</option>
  <option value="면접">면접</option>
  <option value="토익">토익</option>
  <option value="알고리즘">알고리즘</option>
    <option value="개인공부">개인공부</option>
    <option value="기타">기타</option>
</select>
    <label class="form-label">분류</label>
    <select class="form-select" v-model="state.form.classification">
  <option selected>선택</option>
  <option value="모집중">모집중</option>
  <option value="모집완료">모집완료</option>
</select>
  <div class="mb-3">
    <label for="titleInput" class="form-label">제목</label>
    <input type="text" class="form-control" id="titleInput" v-model="state.form.title">
  </div>
  <div class="mb-3">
    <label for="textarea" class="form-label">내용</label>
    <textarea type="textarea" class="form-control" id="textarea" v-model="state.form.textarea"></textarea>
  </div>
  <button type="submit" class="btn btn-primary">작성</button>
    
</form>
</div>

<!-- 확인용 -->
{{state.form}}
</template>

<script>
import {reactive} from 'vue'
// import {useStore} from 'vuex'

export default {
    name: 'newArticle',
    
    setup(){
    // const store = useStore()

    const state = reactive({
        form:{
            // 임의 필드명, 추후 템플릿과 같이 수정
            recruit: null,
            chooseStudy: null,
            classification: null,
            title: '',
            textarea: '',

        }
    })
    // 클릭 이벤트에 db로 폼 보내기 + 게시판 라우터 이동 구현 필요
    const clickSet = function(event){
        event.preventDefault()
        alert(JSON.stringify(state.form))

    }
    return {state,clickSet}
    }
}
</script>