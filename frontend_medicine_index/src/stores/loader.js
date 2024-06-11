import { defineStore } from 'pinia'
import { ref } from 'vue'

export const useLoaderStore = defineStore('loader', () => {
  const showLoader = ref(false)

  function setShowLoader(payload = null) {
    if (payload === showLoader.value) return
    showLoader.value = payload !== null ? payload : !showLoader.value
  }

  function turnLoaderOnFor5second() {
    showLoader.value = true
    setTimeout(() => {
      showLoader.value = false
    }, 5000)
  }

  return {
    showLoader,
    setShowLoader,
    turnLoaderOnFor5second
  }
})
