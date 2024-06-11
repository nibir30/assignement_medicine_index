<script setup>
import { QuillEditor } from '@vueup/vue-quill'
import { onMounted, ref, watch } from 'vue'

const emit = defineEmits(['getHtmlOutput'])
const editorContent = ref('')
const props = defineProps({
  htmlData: {
    type: String,
    default: ''
  }
})
onMounted(() => {
  editorContent.value = props.htmlData
})
watch(props, async () => {
  editorContent.value = props.htmlData
})

function sendToPage(data) {
  emit('getHtmlOutput', data)
}

const contentType = 'html'
</script>

<template>
  <div class="flex flex-col">
    <quill-editor v-model:content="editorContent" v-model:content-type="contentType"
                  style="min-height: 120px " theme="snow"
                  @update:content="sendToPage"
    ></quill-editor>
  </div>
</template>

<style scoped>

</style>
