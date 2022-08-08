<template>
    <!-- <h3 class="d-flex">스터디 리스트</h3> -->
    <div class="container">
        <b-card-group class="d-flex">
            <div class="row">
                <MyStudyItem v-for="item in myStudyList" :key="item.id" :item="item"/>
            </div>
        </b-card-group>
    </div>
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
    const patchMyStudyList = ()=>{store.dispatch('myStudyList')}
    patchMyStudyList
    return {myStudyList}
  }
}
</script>

<style scoped>

</style>