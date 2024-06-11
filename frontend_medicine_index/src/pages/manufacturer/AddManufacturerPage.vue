<template>
  <div>
    <PageHeader class="mb-6">ADD MEDICINE</PageHeader>
    <div class="border rounded-2xl p-4">
      <div class="grid grid-cols-3 gap-x-2 gap-y-2">
        <FormField :bold="false" autocomplete="off" is-required label="Medicine name">
          <FormControl
            v-model.trim="form.name"
            autocomplete="off"
            placeholder="Enter name"
            required
            type="email"
          />
        </FormField>
        <FormField :bold="false" autocomplete="off" is-required label="Medicine Generic name">
          <FormControl
            v-model.trim="form.genericName"
            autocomplete="off"
            placeholder="Enter generic name"
            required
            type="email"
          />
        </FormField>
        <FormField :bold="false" autocomplete="off" is-required label="Manufacturer (mfd) ">
          <FormControl
            v-model.trim="form.manufacturer"
            autocomplete="off"
            placeholder="Enter manufacturer"
            required
            type="email"
          />
        </FormField>
        <FormField :bold="false" autocomplete="off" is-required label="Description">
          <FormControl
            v-model.trim="form.description"
            autocomplete="off"
            placeholder="Enter description"
            required
            type="email"
          />
        </FormField>
        <FormField :bold="false" autocomplete="off" is-required label="Price">
          <FormControl
            v-model.trim="form.price"
            autocomplete="off"
            placeholder="Enter price"
            required
            type="email"
          />
        </FormField>
        <FormField :bold="false" autocomplete="off" is-required label="Batch Number">
          <FormControl
            v-model.trim="form.batchNo"
            autocomplete="off"
            placeholder="Enter batch number"
            required
            type="email"
          />
        </FormField>
        <FormField :bold="false" autocomplete="off" class="col-span-3" label="Other details">
          <EditorVueQuillComponent :html-data="otherDetails" class=""
                                   @get-html-output="getDetailsHtml"></EditorVueQuillComponent>
        </FormField>
      </div>
      <div class="flex flex-row mt-4 gap-x-2">
        <Button>Add Medicine</Button>
        <Button class="bg-red-500 hover:bg-red-800">Cancel</Button>
      </div>
    </div>
  </div>
</template>


<script setup>
import PageHeader from '@/components/temp/PageHeader.vue'
import FormControl from '@/components/temp/FormControl.vue'
import FormField from '@/components/temp/FormField.vue'
import EditorVueQuillComponent from '@/components/RichtextEditor/EditorVueQuillComponent.vue'
import { reactive, ref } from 'vue'
import { Button } from '@/components/ui/button'

const otherDetails = ref('')
const form = reactive({
  'name': '',
  'genericName': '',
  'manufacturer': '',
  'description': '',
  'price': '',
  'batchNo': '',
  'otherDetails': ''
})

function getDetailsHtml(data) {
  otherDetails.value = data
}
</script>

<style scoped>

</style>