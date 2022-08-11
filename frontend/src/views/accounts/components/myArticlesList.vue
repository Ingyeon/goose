<template>
    <div class="frame">
        <div class="container mt-3">
          <myArticle v-for = 'article in myinfo' :key='article.id' :article='article'/>
        </div>
    </div>

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

<style scoped>

.frame {
    overflow-y: auto;
    /* border: 1px solid black; */
    height: 210px;
    width: 100%;
    line-height: 1em;
    border-radius: 10px;
    background-color: #f0f0f1;
}

.frame::-webkit-scrollbar {
    -webkit-appearance: none;
}

.frame::-webkit-scrollbar:vertical {
    width: 11px;
}

.frame::-webkit-scrollbar:horizontal {
    height: 11px;
}

.frame::-webkit-scrollbar-thumb {
    border-radius: 8px;
    border: 2px solid white; /* should match background, can't be transparent */
    background-color: rgba(0, 0, 0, .5);
}

.container {
    width:95%;
    height:95%
}

</style>