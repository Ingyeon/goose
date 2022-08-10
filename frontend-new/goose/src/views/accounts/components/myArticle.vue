<template>
<div class="d-block justify-content-between">
  <p @click="articleMove()" class="articlepush bg-warning p-1">{{state.title}}</p>
    </div>
</template>

<script>
import { useStore } from "vuex";
import { reactive } from '@vue/reactivity';
import { useRouter } from "vue-router";
export default {
  props: {
    article: Object,
  },
  setup(props) {
    const router = useRouter()
    const store = useStore()
    const state = reactive({
        title: props.article.title,
        article_pk: props.article.id,
        date: props.article.date
    })
    const articleMove = async function(){
        await store.dispatch('fetchArticle',state.article_pk)
        router.push({name:'ArticleDetail',
        params: state.article_pk})
        window.scrollTo(0,0)
    }

    return {state, articleMove };
  },
};
</script>

<style scoped>

.articlepush {
  cursor: pointer;
}

</style>
