<template>
<div class="container">
    <div class="input-Box">
        <label class="form-label">멤버리스트</label>
            <label class="form-label">구성원</label>
        <table class="table table-hover">
        <thead>
            <tr>
                <th>No</th>
                <th>이름</th>
                <th>아이디</th>
                <th>자기소개</th>
                <th>스터디 가입 날짜</th>
            </tr>
        </thead>
        <memberItemVue v-for="item in memberList" :key="item.id" :item="item"/>
        </table>
    </div>
</div>
</template>

<script>
import { useStore } from "vuex"
import { computed,reactive } from "vue"
import memberItemVue from './memberItem.vue'
export default {
    components:{
        memberItemVue,
    },
    setup(){
        const store = useStore()
        const memberList = computed(()=> store.getters.studyMemberList)

        return {memberList}
    }
}
</script>

<style scoped>
* {
  font-family: 'NanumSquare', sans-serif;
}

</style>