<template>
  <!-- <h1>Home</h1> -->
  <div>
    <div
      id="carouselExampleControlsNoTouching"
      class="carousel slide"
      data-bs-touch="false"
    >
      <div class="carousel-inner">
        <div class="carousel-item active notice-item">
          <div class="d-flex justify-content-center">
            <Notice />
          </div>
        </div>
        <!-- <div class="carousel-item notice-item">
          <Notice />
        </div>
        <div class="carousel-item notice-item">
          <Notice />
        </div> -->
      </div>
      <button
        class="carousel-control-prev"
        type="button"
        data-bs-target="#carouselExampleControlsNoTouching"
        data-bs-slide="prev"
      >
        <span class="carousel-control-prev-icon" aria-hidden="true"></span>
        <span class="visually-hidden">Previous</span>
      </button>
      <button
        class="carousel-control-next"
        type="button"
        data-bs-target="#carouselExampleControlsNoTouching"
        data-bs-slide="next"
      >
        <span class="carousel-control-next-icon" aria-hidden="true"></span>
        <span class="visually-hidden">Next</span>
      </button>
    </div>
    <div>
      <ul class="button-links">
        <li>
          <button
            id="open-study"
            class="btn btn-3 hover-border-3 active"
            @click.prevent="hire_study"
          >
            공개 스터디
          </button>
        </li>
        <li>
          <button
            id="me-study"
            class="btn btn-3 hover-border-3"
            @click.prevent="me_study"
          >
            참여중인 스터디
          </button>
        </li>
      </ul>
    </div>
    <!-- <div class="container"> -->
      <articleList v-if="state.toggle == 1" />
      <myStudyList v-if="state.toggle == 2" />
    <!-- </div> -->
  </div>
</template>

<script>
import Notice from "@/components/mainpage/notice";
import myStudyList from "@/components/mainpage/myStudyList.vue";
import articleList from "./component/articleList.vue";

import { useStore } from "vuex";
import { reactive } from "vue";

export default {
  components: {
    Notice,
    myStudyList,
    articleList,
  },
  setup() {
    const store = useStore();
    const state = reactive({
      toggle: 1,
    });

    const hire_study = function () {
      if (state.toggle != 1) {
        state.toggle = 1;
        document.getElementById("open-study").classList.add("active");
        document.getElementById("me-study").classList.remove("active");
      } else {
        state.toggle = 0;
          document.getElementById("open-study").classList.remove("active");
      }
    };

    const me_study = function () {
      if (state.toggle != 2) {
        state.toggle = 2;
        document.getElementById("open-study").classList.remove("active");
        document.getElementById("me-study").classList.add("active");
      } else {
        document.getElementById("me-study").classList.remove("active");
        state.toggle = 0;
      }
    };
    if (store.getters.isLoggedIn) {
      store.dispatch("myStudyList");
    }
    return { store, state, hire_study, me_study };
  },
};
</script>

<style scoped>
@media (min-width: 1400px) {
  .container-xxl,
  .container-xl,
  .container-lg,
  .container-md,
  .container-sm,
  .container {
    max-width: 1320px;
    margin-left:0px;
  }
}
.notice-item {
  width: 100%;
  height: 300px;
  background-color: #ffd700;
  border: 1px solid #ffd700;
  display: flex;
  justify-content: center;
}
ul[class="button-links"] {
  display: flex;
  margin-top: 50px;
}
.button-links {
  list-style: none;
}

li .button-study {
  display: inline-block;
  cursor: pointer;
  padding: 9px 20px;
  border: none;
  font-family: "NanumSquare", sans-serif;
  font-weight: bold;
  font-size: 1.25rem;
  transition: all 0.5s ease 0s;
  margin-left: 20px;
  background: #fff;
}

:active,
:hover,
:focus {
  outline: 0 !important;
  outline-offset: 0;
}
::before,
::after {
  position: absolute;
  content: "";
}
.btn {
  position: relative;
  display: inline-block;
  width: auto;
  height: auto;
  background-color: transparent;
  border: none;
  cursor: pointer;
  margin: 0px 25px 15px;
  min-width: 150px;
}
.btn span {
  position: relative;
  display: inline-block;
  font-size: 14px;
  font-weight: bold;
  letter-spacing: 2px;
  text-transform: uppercase;
  top: 0;
  left: 0;
  width: 100%;
  padding: 15px 20px;
  transition: 0.3s;
}
.btn.hover-border-3::before,
.btn.hover-border-3::after {
  width: 0%;
  height: 0%;
  opacity: 0;
  transition: width 0.2s 0.15s linear, height 0.15s linear, opacity 0s 0.35s;
}
.btn.hover-border-3::before {
  top: 0;
  right: 0;
  border-top: 1px solid rgb(28, 31, 30);
  border-left: 1px solid rgb(28, 31, 30);
}
.btn.hover-border-3::after {
  bottom: 0;
  left: 0;
  border-bottom: 1px solid rgb(28, 31, 30);
  border-right: 1px solid rgb(28, 31, 30);
}
.btn.hover-border-3:hover::before,
.btn.hover-border-3:hover::after {
  width: 100%;
  height: 99%;
  opacity: 1;
  transition: width 0.2s linear, height 0.15s 0.2s linear, opacity 0s;
}
.btn.hover-border-3.active {
  color: #000;
  border: none;
}
.btn.hover-border-3 {
  color: #a7aaad;
}
</style>
