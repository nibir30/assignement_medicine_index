<script setup>
import { Input } from '@/components/ui/input/index.js'
import { Button } from '@/components/ui/button/index.js'
import { onBeforeMount, ref, watch } from 'vue'
import AdminMedicineService from '@/services/admin-medicine.service.js'
import {
  Dialog,
  DialogContent,
  DialogDescription,
  DialogHeader,
  DialogTitle,
  DialogTrigger
} from '@/components/ui/dialog'
import {
  Pagination,
  PaginationEllipsis,
  PaginationFirst,
  PaginationLast, PaginationList, PaginationListItem,
  PaginationNext, PaginationPrev
} from '@/components/ui/pagination/index.js'

let baseUrl = null

onBeforeMount(async () => {
  baseUrl = import.meta.env.VITE_API_URL

  await fetchMedicines()
  firstTimeLoading.value = false
})

function onUpdatePage(data) {
  console.log('onUpdatePage', data)
  medicineParams.value.currentPage = data
  fetchMedicines()
}

const firstTimeLoading = ref(true)
const medicineParams = ref({
  perPage: 6,
  currentPage: 0,
  sortBy: null,
  sortType: null,
  search: null
})

const medicineList = ref([])
const totalMedicinePages = ref(null)
const searchTerm = ref(null)

watch(searchTerm, (newTerm, oldTerm) => {
  if (searchTerm.value === '') {
    fetchMedicines()
  }
})

async function fetchMedicines() {
  if (searchTerm.value != null && searchTerm.value != '') {
    medicineParams.value.search = searchTerm.value
  } else {
    medicineParams.value.search = null
  }
  const response = await AdminMedicineService.getMedicinePaginated(medicineParams.value)
  if (response.success === true) {
    totalMedicinePages.value = response.data.data.totalItems
    medicineList.value = response.data.data.listResponse
  }
}
</script>

<template>
  <div class="">
    <div>
      <div class="bg-search w-full  flex justify-center items-center" style="height: 10vh">
        <div class="relative w-1/2 items-center">
          <Input v-model="searchTerm" class="pr-10" placeholder="Search Medicines" type="text"
                 @keyup.enter="fetchMedicines" />
          <span class="absolute end-0 inset-y-0 flex items-center justify-center ">
                <Button size="icon" variant="transparent" @click="fetchMedicines">
                  <i class="fa-solid fa-magnifying-glass"></i>
                </Button>
              </span>
        </div>
      </div>
    </div>
    <div class="mx-10 md:mx-32 flex-col flex">
      <div v-if="medicineParams.search == null || medicineParams.search === ''" class="mt-8 text-3xl font-bold">All
        Medicines
      </div>
      <div v-else class="mt-8 text-3xl font-bold">Searched Medicines</div>
      <div v-if="medicineList.length === 0 && !firstTimeLoading" class="mt-2 text-red-500">
        Sorry, no medicine found searching for '{{ medicineParams.search }}'
      </div>
      <div class="mt-4 mb-8 grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6 ">
        <div v-for="medicineInfo in medicineList" :key="medicineInfo"
             class="rounded-xl border hover:border-amber-600 hover:border-2 hover:scale-105 cursor-pointer">
          <Dialog>
            <DialogTrigger>
              <div
                class="grid grid-cols-2 break-words">
                <div class="flex justify-center">
                  <img :alt="medicineInfo.name"
                       :src="baseUrl +'/public/media/' + medicineInfo.imageId"
                       class="p-2 object-contain " style="height: 150px;width: 150px">
                </div>
                <div class="p-3 text flex flex-col items-start text-start">
                  <div class="text-2xl font-bold capitalize text-black">
                    {{ medicineInfo.name }}
                  </div>
                  <div class="">
                    Generic Name: {{ medicineInfo.genericName }}
                  </div>
                  <div>
                    Price: {{ medicineInfo.price }} BDT
                  </div>
                </div>
              </div>
            </DialogTrigger>
            <DialogContent>
              <DialogHeader>
                <DialogTitle class="capitalize">Medicine Name: {{ medicineInfo.name }}</DialogTitle>
                <DialogDescription>
                  <div
                    class="mt-2 flex flex-col">
                    <img :alt="medicineInfo.name"
                         :src="baseUrl+'/public/media/' + medicineInfo.imageId"
                         class="object-contain rounded-xl border p-2"
                         style="max-height: 40vh">
                    <div class="p-3 text">
                      <div class="text-2xl font-bold capitalize text-black">
                        {{ medicineInfo.name }}
                      </div>
                      <div>
                        Generic Name: {{ medicineInfo.genericName }}
                      </div>
                      <div>
                        Price: {{ medicineInfo.price }} BDT
                      </div>
                      <div>
                        Manufacturer: {{ medicineInfo.manufacturer }}
                      </div>
                      <div>
                        Batch No: {{ medicineInfo.batchNo }}
                      </div>
                      <div v-if="medicineInfo.otherDetails != null && medicineInfo.otherDetails !== ''" class="mt-4">
                        <div class="text-black">Description</div>
                        <div v-html="medicineInfo.otherDetails">
                        </div>
                      </div>

                    </div>
                  </div>
                </DialogDescription>
              </DialogHeader>
            </DialogContent>
          </Dialog>

        </div>
      </div>
      <div class="text-center self-center mb-10">
        <Pagination v-slot="{ page }" :default-page="0" :items-per-page="medicineParams.perPage"
                    :on-update:page="onUpdatePage" :page="medicineParams.currentPage + 1"
                    :sibling-count="1"
                    :total="totalMedicinePages" show-edges>
          <PaginationList v-slot="{ items }" class="flex items-center gap-1">
            <PaginationFirst @click="onUpdatePage(0)" />
            <PaginationPrev @click="onUpdatePage(medicineParams.currentPage -1  )" />

            <template v-for="(item, index) in items">
              <PaginationListItem v-if="item.type === 'page'" :key="index" :value="item.value" as-child>
                <Button :variant="item.value === page ? 'default' : 'outline'" class="w-10 h-10 p-0"
                        @click="onUpdatePage(item.value -1)">
                  {{ item.value }}
                </Button>
              </PaginationListItem>
              <PaginationEllipsis v-else :key="item.type" :index="index" />
            </template>

            <PaginationNext @click="onUpdatePage(medicineParams.currentPage + 1 )" />
            <PaginationLast @click="onUpdatePage(totalMedicinePages -1 )" />
          </PaginationList>
        </Pagination>
      </div>
    </div>

  </div>
</template>

<style scoped>
.bg-search {
  background-image: url("/src/assets/images/search_bg.jpg");
  background-size: cover;
}

.text {
  color: #6a6a6a;
}


.background-image {
  position: fixed;
  left: 0;
  right: 0;
  z-index: 1;
  display: block;
  background-size: cover;
  background-image: url('/src/assets/images/search_bg.jpg');
  height: 30vh;
  -webkit-filter: blur(2px);
  -moz-filter: blur(2px);
  -o-filter: blur(2px);
  -ms-filter: blur(2px);
  filter: blur(2px);
}

.content {
  position: fixed;
  left: 0;
  right: 0;
  z-index: 9999;
  margin-left: 20px;
  margin-right: 20px;
}
</style>