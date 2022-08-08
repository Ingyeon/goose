<template>
  <div style="height:100%">
    <NavBar
      v-if="
        $route.name !== 'PrivateStudyRoom' && $route.name !== 'PublicStudyRoom'
      "
    />
    <router-view />
  </div>
</template>

<script>
import NavBar from '@/components/navBar-new'
export default {
  name: "App",

  props: {
    msg: String,
  },

  components: {
    NavBar,
  },
};
</script>
