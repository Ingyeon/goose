<template>
  <!-- <div class="input-Box"> -->
    <!-- <table class="table table-hover"> -->
      <tbody>
        <tr>
          <td>{{item.id}}</td>
          <td>{{item.name}}</td>
          <td>{{item.user_id}}</td>
          <td>{{item.study_time}}</td>
          <td><button class="button-danger" @click.prevent="kickOutmember(item.user_pk)" >강퇴</button></td>
        </tr>
      </tbody>
    <!-- </table> -->
    
      <!-- <p>{{item}}</p> -->
    <!-- <div class="d-flex">
      <p>{{ item.id }}</p>
      <p>이름 : {{ item.name }}</p>
      <p>{{ item.user_id }}</p>
      <button class="button-danger" @click.prevent="kickOutmember(item.user_pk)" >강퇴</button>
    </div> -->
  <!-- </div> -->
</template>

<script>
import { useStore } from "vuex";
export default {
  props: {
    item : Object,
  },

  setup(){
    const store = useStore();

    const kickOutmember = (user_pk)=> store.dispatch("dropOutStudy",user_pk)
    return {kickOutmember}
  }
}
</script>

<style scoped>
* {
  font-family: 'NanumSquare', sans-serif;
}
.button-danger{
  margin-left: 10px;
  background: #ec610b;
  color: #fff;
  cursor: pointer;
  width: 70px;
  height:25px;
  font-weight: bold;
  text-align: center;
  border-radius: 80px 40px;
  font-size:18px;
  border:none;
}
</style>