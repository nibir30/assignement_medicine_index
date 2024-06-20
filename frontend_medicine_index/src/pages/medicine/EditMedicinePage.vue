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
            <img v-else-if="medicineInfo.imageId" :src="baseUrl + '/public/media/' + medicineInfo.imageId"
                 class="rounded-2xl object-contain" />
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
import { onBeforeMount, reactive, ref } from 'vue'
import { Button } from '@/components/ui/button'
import { Input } from '@/components/ui/input'
import AdminMedicineService from '@/services/admin-medicine.service.js'
import { toast } from 'vue3-toastify'
import { useRoute } from 'vue-router'

const route = useRoute()

let medicineId = null

async function fetchMedicine() {
  const response = await AdminMedicineService.getSingleMedicine(medicineId)
  console.log('response', response)
  if (response.success === true) {
    medicineInfo.value = response.data
    form.name = response.data.name
    form.genericName = response.data.genericName
    form.manufacturer = response.data.manufacturer
    form.description = response.data.description
    form.price = response.data.price
    form.batchNo = response.data.batchNo
    otherDetails.value = response.data.otherDetails
  }
}

let baseUrl = null

onBeforeMount(async () => {
  baseUrl = import.meta.env.VITE_API_URL
  console.log('route', route)
  medicineId = route.query.id
  await fetchMedicine()
})

const otherDetails = ref('')
const medicineInfo = ref({})
const form = reactive({
  name: '',
  genericName: '',
  manufacturer: '',
  description: '',
  price: '',
  batchNo: ''
})

function clearForm() {
  form.name = ''
  form.genericName = ''
  form.manufacturer = ''
  form.description = ''
  form.price = ''
  form.batchNo = ''
  otherDetails.value = ''
  imageFileName.value = null
  medicineImage.value = null
}

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
    id: medicineInfo.value.medicineId,
    name: form.name,
    genericName: form.genericName,
    manufacturer: form.manufacturer,
    description: form.description,
    price: form.price,
    batchNo: form.batchNo,
    otherDetails: otherDetails.value
  }
  if (imageFileName.value) {
    payload.medicine_image = imageFileName.value
  }
  const response = await AdminMedicineService.saveMedicine(payload)
  console.log('save response', response)
  if (response.success === true) {
    toast.success(response.message, {
      autoClose: 2500,
      theme: 'dark'
    })
    clearForm()
  }
}
</script>

<style scoped>

</style>