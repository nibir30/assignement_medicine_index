<template>
  <div>
    <PageHeader class="mb-6">ADD MEDICINE</PageHeader>
    <div class="border rounded-2xl p-4 grid grid-cols-1 md:grid-cols-3 gap-x-4">
      <div class="col-span-1 md:col-span-2">
        <div class=" grid grid-cols-1 md:grid-cols-2 gap-x-2 gap-y-2">
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
          <FormField :bold="false" autocomplete="off" class="col-span-1 md:col-span-2" label="Other details">
            <EditorVueQuillComponent :html-data="otherDetails" class=""
                                     @get-html-output="getDetailsHtml"></EditorVueQuillComponent>
          </FormField>
        </div>
        <div class="flex flex-row mt-4 gap-x-2">
          <Button @click="submit">Add Medicine</Button>
          <Button class="bg-red-500 hover:bg-red-800">Cancel</Button>
        </div>
      </div>
      <div class="col-span-1 order-first sm:order-last">
        <div class="grid w-full max-w-sm items-center gap-1.5">
          <Label for="picture">Picture</Label>
          <div class="aspect-video rounded-2xl border w-full p-3">
            <img v-if="medicineImage" :src="medicineImage" class="rounded-2xl object-contain" />
            <img v-else class="rounded-2xl object-contain" src="/src/assets/images/upload.jpg" />
          </div>
          <Input id="picture" type="file" v-on:change="uploadFile" />
        </div>
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
import { Input } from '@/components/ui/input'
import AdminMedicineService from '@/services/admin-medicine.service.js'
import { toast } from 'vue3-toastify'

const otherDetails = ref('')
const form = reactive({
  name: '',
  genericName: '',
  manufacturer: '',
  description: '',
  price: '',
  batchNo: ''
})

const imageFileName = ref(null)
const medicineImage = ref(null)

function uploadFile(e) {
  console.log(e)
  imageFileName.value = e.target.files[0]
  medicineImage.value = URL.createObjectURL(imageFileName.value)
  console.log('medicineImage.value', medicineImage.value)
  console.log('imageFileName.value', imageFileName.value)

  // this.upavader = e.target.files[0];
}

function getDetailsHtml(data) {
  otherDetails.value = data
}

async function submit() {
  let payload = {
    medicine_image: imageFileName.value,
    name: form.name,
    genericName: form.genericName,
    manufacturer: form.manufacturer,
    description: form.description,
    price: form.price,
    batchNo: form.batchNo,
    otherDetails: otherDetails.value
  }
  console.log('payload', payload)
  const response = await AdminMedicineService.saveMedicine(payload)
  console.log('save response', response)
  if (response.success === true) {
    toast.success(response.message, {
      autoClose: 2500,
      theme: 'dark'
    })
  }
}
</script>

<style scoped>

</style>