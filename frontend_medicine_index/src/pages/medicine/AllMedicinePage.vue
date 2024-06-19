<template>
  <div>
    <div class="font-bold text-3xl mb-5">All Medicines</div>
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
            <TableHead>price</TableHead>
            <TableHead>Batch No.</TableHead>
            <TableHead>Manufacturer</TableHead>
          </TableRow>
        </TableHeader>
        <TableBody>
          <TableRow v-for="medicineInfo in medicineList" :key="medicineInfo.medicineId">
            <TableCell>{{ medicineInfo.name }}</TableCell>
            <TableCell>{{ medicineInfo.genericName }}</TableCell>
            <TableCell>{{ medicineInfo.price }}</TableCell>
            <TableCell>{{ medicineInfo.batchNo }}</TableCell>
            <TableCell>{{ medicineInfo.manufacturer }}</TableCell>
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
const perPageTerm = ref(1)

watch(perPageTerm, (newTerm, oldTerm) => {
  if (newTerm !== oldTerm) {
    medicineParams.value.perPage = newTerm
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
    totalMedicinePages.value = response.data.data.totalPages
    medicineList.value = response.data.data.listResponse
  }
}
</script>

<style scoped>

</style>