<template>
<div class="container">
    <div class="input-Box">
        <label class="form-label">멤버리스트</label>
        <ul> 
            <memberItemVue v-for="item in memberList" :key="item.id" :item="item"/>
        </ul>
    </div>
</div>
</template>

<script>
import { useStore } from "vuex"
import { computed } from "vue"
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