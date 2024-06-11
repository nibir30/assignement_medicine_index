<script setup>
import { Button } from '@/components/ui/button'
import LayoutAuthenticated from '@/layouts/LayoutAuthenticated.vue'
</script>

<template>
  <layout-authenticated>
    <div class="grid grid-cols-2 gap-y-4 gap-x-2">
      <div>Hello world</div>
      <div>Hello world</div>
      <Button>Click me</Button>
      <Button>Click me</Button>
    </div>
  </layout-authenticated>
</template>

<style scoped>

</style>