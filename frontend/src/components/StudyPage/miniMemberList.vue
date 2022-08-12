<template>
<div class="modal fade" id="miniMemberList" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">스터디 구성원</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        <table class="table table-hover">
            <thead>
                <tr>
                    <th>이름</th>
                    <th>아이디</th>
                    <th>자기소개</th>
                    <th v-if="isStudyManager">강퇴</th>
                </tr>
            </thead>
            <tbody v-for="member in studyMemberList" :key="member.id">
                <tr>
                    <td>{{ member.name }}</td>
                    <td>{{ member.user_id }}</td>
                    <td>{{ member.info }}</td>
                    <td v-if="isStudyManager"><button v-if="member.user_id !=loginUser.userId" class="button-danger" @click.prevent="kickOutmember({id:member.user_pk,name:member.name})" >강퇴</button></td>
                </tr>
            </tbody>
        </table>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">닫기</button>
      </div>
    </div>
  </div>
</div>
</template>

<script>
import { computed } from '@vue/runtime-core'
import { useStore } from 'vuex'
export default {
    setup(){
        const store = useStore()
        const loginUser = computed(()=>store.getters.loginUser)
        const studyMemberList = computed(()=>store.getters.studyMemberList)
        const isStudyManager = computed(()=>store.getters.isStudyManager)

        const kickOutmember = (credential)=> {
          const check = confirm(
            `${credential.name}을(를) 추방 하시겠습니까?`
          );
          if(check){
            store.dispatch("dropOutStudy",credential.id)}
        }
        
        return {studyMemberList,isStudyManager,loginUser,kickOutmember}
    },

}
</script>

<style scoped>
.button-danger{
  margin-left: 10px;
  background: #ec610b;
  color: #fff;
  cursor: pointer;
  width: 70px;
  height:25px;
  font-weight: bold;
  text-align: center;
  border-radius: 80px 40px;
  font-size:18px;
  border:none;
}

</style>