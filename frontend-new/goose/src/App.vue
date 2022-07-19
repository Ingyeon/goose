<template>
<div>
  <NavBar/>
  <router-view />
</div>
<loginview></loginview>

</template>

<script>
import NavBar from '@/components/NavBar'
import loginview from './components/loginview.vue'
export default {
  name: "App",
  
  props: {
    msg: String,
  },

  components:{
    NavBar,
    loginview,
}
};
</script>