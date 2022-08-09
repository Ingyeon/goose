<template>
<div class="container">
  <div class="d-flex justify-content-around">
    <div>
      <img class="goose" src="@/assets/Goose-1.png" alt="구스">
    </div>
    <div>
      <div class="container d-flex justify-content-center">
        <h4 id="notice" class="mt-4 text-center">NOTICE</h4>
      </div>
      <div class="mt-5">
        <h1><span>코딩스터디 플랫폼 Goose!</span>를 소개합니다.</h1>
        <h3 class="mt-4">어디서든 마음껏 다양한 사람들과 함께 코딩해보세요!</h3>
        <img class="notice" src="@/assets/study1.png" alt="">
      </div>
    </div>
  </div>
</div>
</template>

<script>
export default {

}
</script>

<style scoped>
* {
  font-family: "NanumSquare", sans-serif;
  }
  img:not(.notice) {
    width: 300px;
    height: 300px
    /* height: 7rem; */
  }
  #notice {
    font-weight: bold;
    color: #fff;
    width:100px;
    height:30px;
    border-radius: 40px;
    background-color: #ec610b;
  }
  span {
    font-weight: 900;
  }
  h2 {
    font-weight: 500;
  }
  .notice {
    position: relative;
    bottom:150px;
    left:97%;
    width:220px;
    height:220px;
  }
</style>

