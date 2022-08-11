<template>
<div class="modal fade" id="updateStudyModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-fullscreen modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <!-- <h5 class="modal-title" id="exampleModalLabel">스터디 업데이트</h5> -->
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        <!-- 모달 바디 -->
        <div class="container">
          <div class="d-flex justify-content-center m-5">
            <h3>스터디 관리</h3>
          </div>
          <div class="container">
            <form>
              <!-- 공개스터디, 비공개 스터디 -->
              <div class="input-Box">
                <label class="form-label">공개 여부</label>
                <select v-model="state.credential.open" class="form-select" aria-label="Default select example">
                  <option value="0">공개</option>
                  <option value="1">비공개</option>
                </select>
                </div>
            <!-- 최대인원 -->
              <div class="input-Box">
                <label class="form-label">총원</label>
                <select v-model="state.credential.maxMember" class="form-select" aria-label="Default select example">
                  <!-- <option value="" selected>인원 수를 선택해주세요</option> -->
                  <option value="1">1명</option>
                  <option value="2">2명</option>
                  <option value="3">3명</option>
                  <option value="4">4명</option>
                  <option value="5">5명</option>
                  <option value="6">6명</option>
                </select>
              </div>

              <!-- 카테고리 -->
                <div class="input-Box">
                  <label for="selectcategory" class="form-label">사용언어</label>
                  <select v-model="state.credential.category" id="selectcategory" class="form-select" aria-label="Default select example">
                    <!-- <option selected>분류</option> -->
                    <option value="" selected>선택해주세요</option>
                    <option value="C">C</option>
                    <option value="C++">C++</option>
                    <option value="JAVA">JAVA</option>
                    <!-- <option value="JavaScript">JavaScript</option> -->
                    <option value="Python">Python</option>
                </select>
                <!-- <label for="selectcategory" class="custom-label">카테고리</label> -->
              </div>
              <!-- 스터디 이름 -->
              <div class="input-Box">
                <label for="studyname"  class="form-label">스터디 이름</label>
                <!-- <div class="form-floating input-Box"> -->
                <input type="text" id="studyname" class="form-control" placeholder="스터디 이름" v-model="state.credential.title" maxlength="30">
                  <!-- <label for="studyname"  class="form-label">스터디 이름</label> -->
                <!-- </div> -->
              </div>


            <!-- 비공개방일시 비밀번호 -->
            <div v-if="state.credential.open==1" class="input-Box">
              <label for="floatingPassword" class="form-label">스터디 비밀번호</label>
              <input v-model="state.credential.password" type="password" class="form-control" id="floatingPassword" placeholder="비밀번호">
            <!-- <label for="floatingPassword" class="form-label">Password</label> -->
            </div>


            <!-- 사진 url -->
            <selectImg/>

            <!-- 제출 -->
              <div class="input-Box d-flex justify-content-end">
                <input @click.prevent="updateStudy()" type="submit" value="수정" data-bs-dismiss="modal">  
              </div>
            </form>
          </div>
          </div>
            <!-- 업데이트에만 있는 목록들 -->
            <hr>
            <div class="container">
              <div class="input-Box">
                <label class="form-label">인원 관리</label>
                <memberListVue />
              </div>
            </div>
          <!-- 모달 바디 끝 -->
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">닫기</button>
      </div>
    </div>
  </div>
</div>





</template>

<script>
import { reactive } from '@vue/reactivity'
import { useStore } from "vuex"
import { computed, watch } from "vue"
import memberListVue from '@/components/StudyPage/memberList.vue'
import selectImg from "@/components/StudyPage/selectImg.vue"
export default {
    props: {
    },
    components: {
    memberListVue,selectImg
},
    setup(){
        const store = useStore()
        const studyId = computed(()=>store.getters.selectedStudy.id)
        const imgUrl = computed(()=>store.getters.choiceImg)
        const selectedStudy = computed(() => store.getters.selectedStudy);
        const state = reactive({
            credential:{
                category: store.getters.selectedStudy.category,
                id: store.getters.selectedStudy.id,
                image : imgUrl,
                member: store.getters.selectedStudy.member,
                maxmember:store.getters.selectedStudy.maxmember,
                open: store.getters.selectedStudy.open,
                password: "",
                title : store.getters.selectedStudy.title,
                url_conf:store.getters.selectedStudy.url_conf,
                url_page:store.getters.selectedStudy.url_page,
            }
        })
        const updateStudy = ()=>{
            store.dispatch('updateStudy',state.credential)
        }

        const patchCredential = ()=>{
          state.credential.category = store.getters.selectedStudy.category
          state.credential.id = store.getters.selectedStudy.id
          state.credential.image = imgUrl
          state.credential.member = store.getters.selectedStudy.member
          state.credential.maxmember = store.getters.selectedStudy.maxmember
          state.credential.open = store.getters.selectedStudy.open
          state.credential.title = store.getters.selectedStudy.title
          state.credential.url_conf = store.getters.selectedStudy.url_conf
          state.credential.url_page = store.getters.selectedStudy.url_page
        }

        return { updateStudy, state, selectedStudy,patchCredential}
    },

    watch:{
      selectedStudy: {
        handler(){
          this.patchCredential()
        }
      }
  }
}
</script>

<style scoped>
.form-select {
    width: 100%;
    height: 50px;
    margin-top: 3px;
    border: solid 1px #000;
}
h3 {
    font-family: 'NanumSquare', sans-serif;
    font-weight: bold;
  }

</style>