<template>
<div>
  <NavBar/>
  <router-view />
</div>

</template>

<script>
import NavBar from '@/components/NavBar'
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