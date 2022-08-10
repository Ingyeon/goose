<template>
<div class="modal fade" id="miniMemberList" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">스터디 구성원</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        {{studyMemberList}}
        <table class="table table-hover">
            <thead>
                <tr>
                    <th>이름</th>
                    <th>아이디</th>
                    <th>자기소개</th>
                </tr>
            </thead>
            <tbody v-for="member in studyMemberList" :key="member.id">
                <tr>
                    <td>{{member.name}}</td>
                    <td>{{member.user_id}}</td>
                    <td>추가중</td>
                </tr>
            </tbody>
        </table>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
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
        const studyMemberList = computed(()=>store.getters.studyMemberList)

        return {studyMemberList}
    }
}
</script>

<style>

</style>