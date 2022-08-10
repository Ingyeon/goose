<template>
    <!-- <h3 class="d-flex">스터디 리스트</h3> -->
    <div class="container">
        <b-card-group class="d-flex row">
            <!-- <div class="row"> -->
                <MyStudyItem v-for="item in myStudyList" :key="item.id" :item="item"/>
            <!-- </div> -->
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
    patchMyStudyList()
    console.log("dddddddddddd")
    console.log(myStudyList)
    return {myStudyList}
  }
}
</script>

<style scoped>
  .row {
    margin: 10px;
    height:100%;
    width: 100%;
  }
</style>