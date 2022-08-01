<template>
  <div class="card" style="width: 18rem;">
    <img src="http://encykorea.aks.ac.kr/Contents/GetImage?t=origin&id=da07ff58-06ea-4cb2-a957-0964d192cb14&w=878&h=582" alt="기본사진" class="card-img-top">
    <div class="card-body">
      <h5 class="card-title">보드 아이템</h5>
      <p class="card-text">{{ writing }}</p>
      <a href="#" class="btn btn-primary">Go somewhere</a>
    </div>
  </div>
</template>

<script>
export default {
  props: {
    writing : Object,
  },
}
</script>

<style>

</style>