<template>
    <div @click.prevent="selectStudy" class="d-flex container">
        <img src="http://encykorea.aks.ac.kr/Contents/GetImage?t=origin&id=da07ff58-06ea-4cb2-a957-0964d192cb14&w=878&h=582" alt="기본사진">
        <h3>스터디명: {{ item.title }}</h3>

    </div>

</template>

<script>
import { useStore } from "vuex"
export default {
    props: {
        item : Object
    },
    setup(props){
        
        const store = useStore()
        const selectStudy = () => {
            store.dispatch('selectStudy', props.item.id)
        }

        return {selectStudy}
    }
}
</script>

<style>

</style>