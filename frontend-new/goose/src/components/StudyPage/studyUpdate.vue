<template>
<div class="modal fade" id="updateStudyModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-fullscreen modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">스터디 만들기</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        <!-- 모달 바디 -->
        <div class="container">
          <div class="d-flex justify-content-center m-5">
            <h3>스터디 만들기</h3>
          </div>
          <div class="container">
          <form>
            <!-- 공개스터디, 비공개 스터디 -->
            <div class="d-flex">
              <p class="text-capitalize custom-label">공개 여부</p>
              <div class="m-3">
                <select v-model="state.credential.open" class="form-select" aria-label="Default select example">
                  <option value="0">공개</option>
                  <option value="1">비공개</option>
                </select>
              </div>
            </div>
          <!-- 최대인원 -->
            <div class="d-flex">
              <p class="text-capitalize custom-label">총원</p>
              <div class="m-3">
                <select v-model="state.credential.maxMember" class="form-select" aria-label="Default select example">
                  <option value="1">1명</option>
                  <option value="2">2명</option>
                  <option value="3">3명</option>
                  <option value="4">4명</option>
                  <option value="5">5명</option>
                  <option value="6">6명</option>
              </select>
              </div>
            </div>

            <!-- 카테고리 -->
              <label for="selectcategory" class="custom-label">카테고리</label>
              <div class="form-floating custom-label">
              <select v-model="state.credential.category" id="selectcategory" class="form-select" aria-label="Default select example">
                <!-- <option selected>분류</option> -->
                <option value="C">C</option>
                <option value="C++">C++</option>
                <option value="JAVA">JAVA</option>
                <option value="JavaScript">JavaScript</option>
                <option value="Python">Python</option>
              </select>
              <label for="selectcategory" class="custom-label">카테고리</label>
            </div>
            <!-- 스터디 이름 -->
            <div>
              <label for="studyname"  class="custom-label">스터디 이름</label>
              <div class="form-floating input-Box">
                <input type="text" id="studyname" class="form-control" placeholder="스터디 이름" v-model="state.credential.title">
                <label for="studyname"  class="form-label">스터디 이름</label>
              </div>
            </div>


          <!-- 비공개방일시 비밀번호 -->
          <label for="floatingPassword" class="custom-label">Password</label>
          <div class="form-floating input-Box">
            <input v-model="state.credential.password" type="password" class="form-control" id="floatingPassword" placeholder="Password">
            <label for="floatingPassword" class="form-label">Password</label>
          </div>

          <!-- 사진 url -->
          <selectImg/>

          <!-- 제출 -->
            <div class="input-Box">
              <input @click.prevent="createStudy()" type="submit" value="Submit">  
            </div>
          </form>
          </div>
          {{ state.credential }}
          </div>
            <!-- 업데이트에만 있는 목록들 -->
            <hr>
            <div class="container">
              <p>인원 관리</p>
              <memberListVue />
            </div>
          <!-- 모달 바디 끝 -->
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
      </div>
    </div>
  </div>
</div>





</template>

<script>
import { reactive } from '@vue/reactivity'
import { useStore } from "vuex"
import { computed } from "vue"
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

        const state = reactive({
            credential:{
                category: store.getters.selectedStudy.category,
                id: store.getters.selectedStudy.id,
                image : store.getters.selectedStudy.image,
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
        return { updateStudy, state}
    }
}
</script>

<style>

</style>