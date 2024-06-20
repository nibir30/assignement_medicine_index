<template>
  <div>
    <PageHeader class="mb-6">ALL MEDICINES</PageHeader>
    <div class="rounded-2xl border pt-2 pb-4">
      <div class="m-3 flex flex-row">
        <Select v-model="perPageTerm" @change="perPageUpdate">
          <SelectTrigger class="w-auto">
            <SelectValue placeholder="Select No of Rows" />
          </SelectTrigger>
          <SelectContent>
            <SelectItem v-for="pageValue in perPageOptions" :key="pageValue"
                        :value="pageValue">
              {{ pageValue }}
            </SelectItem>
          </SelectContent>
        </Select>
        <FormField :bold="false" autocomplete="off" class="ms-auto" is-required>
          <FormControl
            v-model.trim="searchTerm"
            autocomplete="off"
            placeholder="Search"
            type="text"
          />
        </FormField>
      </div>
      <Table>
        <TableCaption>A list of your recent invoices.</TableCaption>
        <TableHeader>
          <TableRow>
            <TableHead>Name</TableHead>
            <TableHead>Generic Name</TableHead>
            <TableHead>Price</TableHead>
            <TableHead>Batch No.</TableHead>
            <TableHead>Manufacturer</TableHead>
            <TableHead>Actions</TableHead>
          </TableRow>
        </TableHeader>
        <TableBody>
          <TableRow v-for="medicineInfo in medicineList" :key="medicineInfo.medicineId">
            <TableCell>{{ medicineInfo.name }}</TableCell>
            <TableCell>{{ medicineInfo.genericName }}</TableCell>
            <TableCell>{{ medicineInfo.price }} BDT</TableCell>
            <TableCell>{{ medicineInfo.batchNo }}</TableCell>
            <TableCell>{{ medicineInfo.manufacturer }}</TableCell>
            <TableCell>
              <router-link :to="{
                 path: '/medicine/edit',
                 params: medicineInfo.medicineId, // <-- changed 'props' to 'params'
                 query: { id: medicineInfo.medicineId },
               }">
                <Button class="me-2 bg-blue-600"><i class="fa-regular fa-pen-to-square"></i></Button>
              </router-link>
              <Button class="bg-red-500" @click="deleteMedicine(medicineInfo.medicineId)"><i
                class="fa-solid fa-trash"></i></Button>
            </TableCell>
          </TableRow>
        </TableBody>
      </Table>
      <div class="mt-10 flex justify-center">
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
<script setup>
import {
  Table,
  TableBody,
  TableCaption,
  TableCell,
  TableHead,
  TableHeader,
  TableRow
} from '@/components/ui/table'
import {
  Pagination,
  PaginationEllipsis,
  PaginationFirst,
  PaginationLast,
  PaginationList,
  PaginationListItem,
  PaginationNext,
  PaginationPrev
} from '@/components/ui/pagination'
import {
  Select,
  SelectContent,
  SelectItem,
  SelectTrigger,
  SelectValue
} from '@/components/ui/select'
import { Button } from '@/components/ui/button'

import { onBeforeMount, ref, watch } from 'vue'
import AdminMedicineService from '@/services/admin-medicine.service.js'
import FormControl from '@/components/temp/FormControl.vue'
import FormField from '@/components/temp/FormField.vue'
import { toast } from 'vue3-toastify'
import Swal from 'sweetalert2'
import PageHeader from '@/components/temp/PageHeader.vue'

onBeforeMount(async () => {
  await fetchMedicines()
})

function onUpdatePage(data) {
  console.log('onUpdatePage', data)
  medicineParams.value.currentPage = data
  fetchMedicines()
}

function perPageUpdate(data) {
  console.log(data)
}

const medicineParams = ref({
  perPage: 5,
  currentPage: 0,
  sortBy: null,
  sortType: null,
  search: null
})

const perPageOptions = [1, 5, 10, 100]

const medicineList = ref([])
const totalMedicinePages = ref(null)
const searchTerm = ref(null)
const perPageTerm = ref(5)

watch(perPageTerm, (newTerm, oldTerm) => {
  if (newTerm !== oldTerm) {
    medicineParams.value.perPage = newTerm
    medicineParams.value.currentPage = 0
    fetchMedicines()
  }
})
watch(searchTerm, (newTerm, oldTerm) => {
  if (newTerm !== oldTerm) {
    console.log(newTerm)
    if (newTerm.length > 2) {
      medicineParams.value.search = newTerm
      fetchMedicines()
    } else {
      if (medicineParams.value.search != null) {
        medicineParams.value.search = null
        fetchMedicines()
      }
    }
  }
})

async function fetchMedicines() {
  const response = await AdminMedicineService.getMedicinePaginated(medicineParams.value)
  if (response.success === true) {
    totalMedicinePages.value = response.data.data.totalItems
    medicineList.value = response.data.data.listResponse
  }
}

async function deleteMedicine(id) {
  const result = await Swal.fire({
    title: 'Are you sure?',
    icon: 'warning',
    showCancelButton: true,
    confirmButtonColor: '#3085d6',
    cancelButtonColor: '#d33',
    confirmButtonText: 'Yes!'
  })
  if (result.isConfirmed) {
    const response = await AdminMedicineService.deleteSingleMedicine(id)
    if (response.success === true) {
      toast.success(response.message, {
        autoClose: 2500,
        theme: 'dark'
      })
      await fetchMedicines()
    }
  }

}
</script>

<style scoped>

</style>