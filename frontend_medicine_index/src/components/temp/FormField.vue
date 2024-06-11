<script setup>
import { computed, useSlots } from 'vue'

const props = defineProps({
  label: {
    type: String,
    default: null
  },
  applyGrid: {
    type: Boolean,
    default: true
  },
  labelFor: {
    type: String,
    default: null
  },
  help: {
    type: String,
    default: null
  },
  bold: {
    type: Boolean,
    default: true
  },
  isRequired: {
    type: Boolean,
    default: false
  }
})

const slots = useSlots()

const wrapperClass = computed(() => {
  const base = []
  const slotsLength = slots.default().length
  if (props.applyGrid) {
    if (slotsLength > 1) {
      base.push('grid grid-cols-1 gap-3')
    }

    if (slotsLength === 1) {
      base.push('grid grid-cols-1 gap-3')
    }

    if (slotsLength === 2) {
      base.push('md:grid-cols-2')
    }
  }
  // console.log(props.label, slotsLength)

  return base
})
</script>

<template>
  <div class="mb-2 last:mb-0">
    <label v-if="label" :for="labelFor" class="block  mb-2 flex flex-row" style="font-family: Nunito"
           v-bind:class="bold ? 'font-semibold' : 'font-medium'">
      {{ label }}
      <div v-if="isRequired" class="ms-1 text-red-800 font-bold"> *</div>
    </label>
    <div :class="wrapperClass">
      <slot />
    </div>
    <div v-if="help" class="text-xs text-gray-500 dark:text-slate-400 mt-1">
      {{ help }}
    </div>
  </div>
</template>
