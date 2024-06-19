<script setup>
import { Input } from '@/components/ui/input/index.js'
import { Button } from '@/components/ui/button/index.js'
import { onBeforeMount, ref } from 'vue'
import AdminMedicineService from '@/services/admin-medicine.service.js'

onBeforeMount(async () => {
  await fetchMedicines()
})

function onUpdatePage(data) {
  console.log('onUpdatePage', data)
  medicineParams.value.currentPage = data
  fetchMedicines()
}

const medicineParams = ref({
  perPage: 5,
  currentPage: 0,
  sortBy: null,
  sortType: null,
  search: null
})

const medicineList = ref([])
const totalMedicinePages = ref(null)
const searchTerm = ref(null)

async function fetchMedicines() {
  const response = await AdminMedicineService.getMedicinePaginated(medicineParams.value)
  if (response.success === true) {
    totalMedicinePages.value = response.data.data.totalPages
    medicineList.value = response.data.data.listResponse
  }
}
</script>

<template>
  <div>
    <div>
      <div class="bg-search w-full  flex justify-center items-center" style="height: 33vh">
        <div class="relative w-1/2 items-center">
          <Input id="password" class="pr-10" placeholder="Search Medicines"
                 type="text" />
          <span class="absolute end-0 inset-y-0 flex items-center justify-center ">
            <Button size="icon" variant="transparent">
              <i class="fa-solid fa-magnifying-glass"></i>
            </Button>
          </span>
        </div>
      </div>
    </div>
    <div class="mt-8 grid grid-cols-4 gap-4 mx-4">
      <div v-for="medicineInfo in medicineList" :key="medicineInfo" class="rounded-xl border">
        <div class="aspect-video">
          <img :src="'http://localhost:8051/medicine-backend/api/v1/public/media/' + medicineInfo.imageId"
               class="h-1/2 object-cover w-full rounded-t-xl">
          <div class="p-3">
            <div>
              {{ medicineInfo.name }}
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>
.bg-search {
  background-image: url("/src/assets/images/search_bg.jpg");
  background-size: cover;
}

.with-blur-backdrop {
  background-color: rgba(255, 255, 255, 0.4);
  -webkit-backdrop-filter: blur(5px);
  backdrop-filter: blur(5px);
  height: inherit;
}
</style>