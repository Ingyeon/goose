<template>
  <h3>
    <!-- id 대신 선호 언어? -->
    <li>이름: {{ item.name }},  id : {{ item.user_id }}</li>
    <button @click="joinAccept" type="button" class="btn btn-primary">승인</button>
    <button @click="joinRefuse" type="button" class="btn btn-primary">거절</button>
  </h3>
</template>

<script>
import { reactive } from '@vue/reactivity'
import { useStore } from "vuex"
import { useRouter } from 'vue-router'
export default {
  props: {
    item : Object
  },
  setup(props){
    const router = useRouter()
    const store = useStore()
    const credential = reactive({
      id: props.item.id,
      study_pk:props.item.study_pk,
      user_pk: props.item.user_pk,
      name: props.item.name
    })

    const joinAccept = ()=>{
      store.dispatch('joinAgree',credential)
      // 신청 확인 후 화면 그대로 유지되는걸해결 위해 임시로 새로고침 넣음
      router.go()
    }
    const joinRefuse = ()=>{
      store.dispatch('joinRefuse',credential.id)
      router.go()
    }
    return {joinAccept,joinRefuse}
  }
}
</script>

<style>

</style>