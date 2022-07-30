<template>
  <articleDetail :key='state.resetkey' />

  <button @click="reset">리셋</button>
</template>

<script>
import articleDetail from './articleDetail.vue'
import {  onMounted, reactive,  } from "vue"

export default {
    components: {articleDetail},

    setup(){
        const state = reactive({resetkey: 0})

        const reset = function(){
            state.resetkey += 1
            console.log(1)
        }
        onMounted(() => reset())
        return {state,reset}
    }
    
}
</script>

<style>

</style>