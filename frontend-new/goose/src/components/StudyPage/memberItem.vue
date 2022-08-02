<template>
  <h1>구성원</h1>
  <p>이름 : {{ item.name }}</p>{{ item }}
  <button @click.prevent="kickOutmember(item.user_pk)" >강퇴</button>
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

<style>

</style>