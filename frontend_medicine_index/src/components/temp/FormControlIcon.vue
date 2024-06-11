<script setup>

import BaseIcon from '@/components/temp/BaseIcon.vue'

defineProps({
  icon: {
    type: String,
    default: null
  },
  h: {
    type: String,
    default: null
  }
})
</script>

<template>
  <BaseIcon
    :h="h"
    :path="icon"
    class="absolute top-0 left-0 z-10 pointer-events-none text-gray-500 dark:text-slate-400"
    w="w-10"
  />
</template>
