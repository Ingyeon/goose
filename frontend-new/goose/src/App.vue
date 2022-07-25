<template>
<div>
  <NavBar/>
  <router-view />
</div>

</template>

<script>
import NavBar from '@/components/navBar'
export default {
  name: "App",
  
  props: {
    msg: String,
  },

  components:{
    NavBar,
}
};
</script>