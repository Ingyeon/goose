<template>
    <p>{{state.title}}</p>
</template>

<script>
import { useStore } from "vuex";
import { reactive } from '@vue/reactivity';
export default {
  props: {
    article: Object,
  },
  setup(props) {
    const state = reactive({
        title: props.article.title
    })
    

    return {state };
  },
};
</script>

<style scoped></style>
