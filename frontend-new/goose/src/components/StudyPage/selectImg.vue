<template>
    
<h3>대표 사진</h3>
<img @click.prevent="selectImg('https://upload.wikimedia.org/wikipedia/commons/thumb/1/15/Anser_cygnoides.jpg/1280px-Anser_cygnoides.jpg')"  src="https://upload.wikimedia.org/wikipedia/commons/thumb/1/15/Anser_cygnoides.jpg/1280px-Anser_cygnoides.jpg" alt="">
<img @click.prevent="selectImg('https://upload.wikimedia.org/wikipedia/commons/thumb/1/1a/Domestic_Greylag.jpg/1024px-Domestic_Greylag.jpg')" src="https://upload.wikimedia.org/wikipedia/commons/thumb/1/1a/Domestic_Greylag.jpg/1024px-Domestic_Greylag.jpg" alt="">
</template>

<script>
import { reactive } from '@vue/reactivity'
import { useStore } from "vuex"
export default {
    setup(){
        const store = useStore()
        const selectImg = (url)=>{
            store.dispatch('selectImg',url)
        }
        return {selectImg,}
    }
}
</script>

<style>

</style>