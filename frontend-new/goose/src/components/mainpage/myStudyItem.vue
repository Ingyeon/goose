<template>
  <div class="container d-flex">
    <div class="row mt-3">
      <div class="col-lg-4 col-md-6 col-12">
        <img :src="state.photo" alt="사진" class='container-fluid'/>
      </div>
      <div id="study-category" class="col-lg-8 col-md-6 col-12 d-flex">
        <div class="container d-flex justify-content-center">
          <h3 class="fw-bold mt-3">{{item.title}}</h3>
          <!-- {{item}} -->
          <!-- {{item.category}} -->
        </div>
        <div class="d-flex justify-content-end align-items-end">
          <button @click.prevent="selectStudy" class="button">스터디 홈</button>
        </div>
      </div>
    </div>
  </div>
  <!-- <div class="card mcs mx-2 my-2 row row-col-4"> -->
    <!-- {{ item.image }} -->
    <!-- <div class="card-body">
      <h3 class="card-title">{{ item.title }}</h3>
      <a @click.prevent="selectStudy" class="btn btn-warning">스터디 홈</a>
    </div> -->
  <!-- </div> -->

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
    // const store = useStore();
    const router = useRouter();

    const selectStudy = () => {
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

<style scoped>
.row{
  width:100%;
  height:100%
}
* {
  font-family: 'NanumSquare', sans-serif;
}

#study-category {
  /* border: 2px solid ; */
  background-color: #f0f0f1;
  border-radius: 20px
}

.button {
  background: #ffd700;
  color: #000000;
  cursor: pointer;
  width: 150px;
  height:30px;
  margin-bottom: 20px;
  font-weight: bold;
  text-align: center;
  border-radius: 80px 40px;
  font-size:16px;
  border:none;
}

</style>
