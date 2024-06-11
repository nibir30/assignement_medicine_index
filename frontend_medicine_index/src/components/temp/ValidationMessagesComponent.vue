<script setup>
const props = defineProps({
  errors: {
    type: Array,
    default: []
  }
})
</script>

<template>
  <div>
    <div v-for="(error, index) of errors"
         :key="index" class="text-red-800">
      {{ error.$message }}
    </div>
  </div>
</template>

<style scoped>

</style>
