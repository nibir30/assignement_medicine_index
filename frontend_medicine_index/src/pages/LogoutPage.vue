<script setup>
import { onBeforeMount } from 'vue'
import { useRouter } from 'vue-router'

const router = useRouter()

onBeforeMount(async () => {
  localStorage.clear()
  await router.push('/login')
})
</script>

<template>
  <div></div>
</template>

<style scoped>

</style>