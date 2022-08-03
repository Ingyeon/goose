<template>
  {{ item }}
  <div @click.prevent="selectStudy" class="d-flex container">
    <img :src="item.image" alt="기본사진" />
    <h3>스터디명: {{ item.title }}</h3>
  </div>
</template>

<script>
import { useStore } from "vuex";
import { useRouter } from "vue-router";
export default {
  props: {
    item: Object,
  },
  setup(props) {
    const store = useStore();
    const router = useRouter();

    const selectStudy = () => {
      store.dispatch("selectStudy", props.item.id);
      store.dispatch("compile");
      router.push({ name: "StudyHome", params: { studyPk: props.item.id } });
    };

    return { selectStudy };
  },
};
</script>

<style scoped></style>
