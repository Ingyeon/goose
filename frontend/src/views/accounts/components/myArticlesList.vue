<template>
  <myArticle v-for = 'article in myinfo' :key='article.id' :article='article'/>
</template>

<script>
import myArticle from './myArticle.vue'
import {reactive,computed} from 'vue'
import {useStore} from 'vuex'

export default {
    components: {myArticle},
    setup(){
    const store = useStore()
    const state = reactive({
        form: store.getters.loginUser
    })
    const myArticles = function(){
        store.dispatch('filterArticles',state.form)
    }
    myArticles()

    const myinfo = computed(() => store.getters.articles)

    return {store,myinfo}
    }
}
</script>

<style>

</style>