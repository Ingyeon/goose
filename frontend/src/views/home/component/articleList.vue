<template>
  <!-- <div class="container"> -->
    <b-card-group class="d-flex row">
    <!-- <div class="row"> -->
        <ArticleItem v-for="article in openstudyList" :key="article.id" :item="article"/>  
    <!-- </div> -->
    </b-card-group>
  <!-- </div> -->
</template>

<script>
import { useStore } from "vuex"
import { computed } from "vue"
import { useRouter } from "vue-router";
import study1 from "@/assets/study1.png"
import study2 from "@/assets/study2.png"
import study3 from "@/assets/study3.png"
import { reactive } from '@vue/reactivity';
import ArticleItem from './articleItem.vue'
export default {
  components: {
    ArticleItem
  },

  setup(){
    const store = useStore()
     const router = useRouter();
    const myStudyList = computed(()=> store.getters.myStudyList)
    // const openstudyList = computed(()=> store.getters.openstudyList)
    const openstudyList = computed(()=>store.getters.openstudyList);
    const fetchMyStudyList = function (studyId) {
      store.dispatch("myStudyList", studyId);
    };
    const open_set = function () {
      console.log("시작");
      console.log(openstudyList);
      store.dispatch("saveOpenList");
    };

    const fetchStudyHome = async function (studyId) {
      await store.dispatch("selectStudy", studyId)
    };

    const selectedStudy = computed(() => store.getters.selectedStudy);


    const clickbtn = async function (studyId) {
      // console.log(">mmm>>>>>>>>>", studyId)
      await fetchStudyHome(studyId);
      router.push({ name: "PublicStudyRoom" });
    };

    open_set()

    // const joinStudy = (studyId) => store.dispatch('joinStudy',studyId)
    return { myStudyList, fetchMyStudyList, openstudyList, open_set, clickbtn, selectedStudy  };
  },

  watch: {
    $route: {
      handler() {
        // this.open_set()
        this.fetchMyStudyList();
        this.open_set();
      },
      immediate: true,
    },
  },
};
</script>

<style scoped>
  .row {
    margin: 10px;
    height:100%;
  }

/* b-card-group{
  width: 100%
} */
</style>
