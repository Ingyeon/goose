<template>
    <h3>스터디 리스트</h3>
    <MyStudyItem v-for="item in myStudyList" :key="item.id" :item="item"/>
</template>

<script>
import { useStore } from "vuex"
import { computed } from "vue"
import MyStudyItem from "./myStudyItem.vue"
export default {
    components:{
    MyStudyItem
},
    setup(){
    const store = useStore()
    const myStudyList = computed(()=> store.getters.myStudyList)
    return {myStudyList}
  }
}
</script>

<style scoped>

</style>