<template>
  <div class="card">
    {{ item.image }}
    <img :src="state.photo" alt="사진" class='card-img-top'/>
    <div class="card-body">
      <h3 class="card-title">{{ item.title }}</h3>
      <a @click.prevent="selectStudy" class="btn btn-warning">스터디 홈</a>
    </div>
  </div>
</template>

<script>
import study1 from "@/assets/study1.png"
import study2 from "@/assets/study2.png"
import study3 from "@/assets/study3.jpg"
import { useStore } from "vuex";
import { useRouter } from "vue-router";
import { reactive } from '@vue/reactivity';
export default {
  props: {
    item: Object,
  },
  setup(props) {
    const store = useStore();
    const router = useRouter();

    const selectStudy = () => {
      store.dispatch("selectStudy", props.item.id);
      router.push({ name: "StudyHome", params: { studyPk: props.item.id } });
    };

    const state = reactive({
      photo : ""
    })

    const changePhoto = ()=>{
      if (props.item.image==='study1'){state.photo = study1}
      else if (props.item.image==='study2'){state.photo = study2}
      else if (props.item.image==="study3"){state.photo = study3}
    }
    changePhoto()

    return { selectStudy,state,changePhoto };
  },

  // watch:{
  //   item: {
  //     handler(){
  //       this.changePhoto
  //     }
  //   }
  // }
};
</script>

<style scoped></style>
