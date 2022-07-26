<template>
  <h3>
    {{ item }}
    <button type="button" class="btn btn-primary">승인</button>
    <button type="button" class="btn btn-primary">거절</button>
  </h3>
</template>

<script>
export default {
  props: {
    item : Object
  }
}
</script>

<style>

</style>