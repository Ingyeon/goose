<template>
  <div class="card">
    <img :src="item.image" alt="@/src/assets/study1.png" class='card-img-top'/>
    <div class="card-body">
      <h3 class="card-title">{{ item.title }}</h3>
      <a @click.prevent="selectStudy" class="btn btn-warning">스터디 홈</a>
    </div>
    
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
