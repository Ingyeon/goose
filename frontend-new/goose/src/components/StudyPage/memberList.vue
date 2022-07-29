<template>
    <h3>멤버리스트 뷰</h3>

    <memberItemVue v-for="item in memberList" :key="item.id" :item="item"/>
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

<style>

</style>