<template>
  <div class="col-12 col-md-6 col-lg-4 col-xl-3">
    <div class="card">
      <img :src="state.photo" class="card-img-top" alt="alt" />
      <div class="card-body">
        <h5 class="card-title fw-bold">{{item.title}}</h5>
        <p class="card-text">사용 언어:{{item.category}}</p>
        <div class="d-flex justify-content-end">
          <button @click.prevent="selectStudy" class="button">스터디 홈</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import study1 from "@/assets/study1.png";
import study2 from "@/assets/study2.png";
import study3 from "@/assets/study3.png";
import { useStore } from "vuex";
import { useRouter } from "vue-router";
import { reactive } from "@vue/reactivity";
export default {
  props: {
    item: Object,
  },
  setup(props) {
    const store = useStore();
    const router = useRouter();

    const fetchStudyHome = async function (studyId) {
      await store.dispatch("selectStudy", studyId);
    };
    const selectStudy = async () => {
      await fetchStudyHome(props.item.id);
      router.push({ name: "StudyHome", params: { studyPk: props.item.id } });
    };

    const state = reactive({
      photo: "",
    });

    const changePhoto = () => {
      if (props.item.image === "study1") {
        state.photo = study1;
      } else if (props.item.image === "study2") {
        state.photo = study2;
      } else if (props.item.image === "study3") {
        state.photo = study3;
      }
    };
    changePhoto();

    return { selectStudy, state, changePhoto };
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

<style scoped>
.row{
   margin: 10px;
}
* {
  font-family: "NanumSquare", sans-serif;
}

/* #study-category {
  background-color: #f0f0f1;
  border-radius: 20px
} */
.card {
  width: 18rem;
  height: 100%;
  margin-left: 20px;
  margin-bottom: 20px;
}
.card-img-top {
  height: 15rem;
  object-fit: cover;
}
.button {
  background: #ffd700;
  color: #000000;
  cursor: pointer;
  width: 150px;
  height: 30px;
  margin-bottom: 20px;
  font-weight: bold;
  text-align: center;
  border-radius: 80px 40px;
  font-size: 16px;
  border: none;
}
</style>
