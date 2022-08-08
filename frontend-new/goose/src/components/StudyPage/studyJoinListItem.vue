<template>
  <h3>
    <li>이름: {{ item.name }},  id : {{ item.user_id}}</li>
    <button @click="joinAccept" type="button" class="btn btn-primary">승인</button>
    <button @click="joinRefuse" type="button" class="btn btn-primary">거절</button>
  </h3>
</template>

<script>
import { reactive } from '@vue/reactivity'
import { useStore } from "vuex"

export default {
  props: {
    item : Object
  },
  setup(props){
    const store = useStore()
    const credential = reactive({
      id: props.item.id,
      study_pk:props.item.study_pk,
      user_pk: props.item.user_pk,
      name: props.item.name
    })

    const joinAccept = ()=>{
      store.dispatch('joinAgree',credential)
    }
    const joinRefuse = ()=>{
      store.dispatch('joinRefuse',credential.id)
    }
    return {joinAccept,joinRefuse}
  }
}
</script>

<style>

</style>