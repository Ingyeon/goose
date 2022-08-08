<template>
<div class="container">
    <h2 class="d-flex justify-content-center fw-bold">ID/PW찾기</h2>
    <div class="container mt-5 d-flex align-items-center justify-content-evenly">
        <router-link to="/find/id"><button>ID찾기</button></router-link>
        <router-link to="/refresh/password"><button>PW재발급받기</button></router-link>
    </div>
</div>
</template>

<script>
import { reactive,computed } from 'vue'
import { useStore } from 'vuex'

export default {
    setup() {
}}


</script>

<style scoped>
/* div{
    height: 100%
} */
.container{
    height:70%
}
*{
  margin: 0;
  padding: 0;
  box-sizing: border-box;
  font-family: "NanumSquare", sans-serif;
}
button {
    background: #ffd700;
    border-radius: 80px 40px;
    cursor: pointer;
    padding: 9px 20px;
    border: none;
    /* border-radius: 50px; */
    font-family: "NanumSquare", sans-serif;
    font-weight: bold;
    font-size: 1.5rem;
    transition: all 0.5s ease 0s;
    margin-left: 20px;
}

</style>