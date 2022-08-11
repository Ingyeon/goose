<template>
    <!-- id 대신 선호 언어? -->
    <div class="d-flex justify-content-between">
    <li class="fw-bold">이름: {{ item.name }},  id : {{ item.user_id }}</li>
    <div> 
      <button class="accept" @click.prevent="joinAccept" type="button" >승인</button>
      <button class="reject" @click.prevent="joinRefuse" type="button" >거절</button>
    </div>  
    </div>
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
      // router.go()
    }
    const joinRefuse = ()=>{
      store.dispatch('joinRefuse',credential.id)
      // router.go()
    }
    return {joinAccept,joinRefuse}
  }
}
</script>

<style scoped>
* {
  font-family: 'Josefin Sans', sans-serif;
}
li {
  list-style: none;
}
button[class="accept"] {
  background: #ffd700;
  color: #000000;
  cursor: pointer;
  font-weight: bold;
  text-align: center;
  height:30px;
  width :50px;
  border-radius: 80px 40px;
  font-size: 13px;
  border: none;
}
button[class="reject"] {
  background: #ee5c26;
  color: #000000;
  cursor: pointer;
  font-weight: bold;
  text-align: center;
  height:30px;
  width :50px;
  border-radius: 80px 40px;
  font-size: 13px;
  border: none;
}
</style>