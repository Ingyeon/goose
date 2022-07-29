<template>
  <h1>구성원</h1>
  <p>이름 : {{ item.name }}</p>{{ item }}
</template>

<script>

export default {
    props: {
    item : Object,
  },
}
</script>

<style>

</style>