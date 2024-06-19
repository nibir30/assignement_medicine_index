<script setup>
import { Input } from '@/components/ui/input/index.js'
import { Button } from '@/components/ui/button/index.js'
</script>

<template>
  <div class="h-screen">
    <div class="h-screen">
      <div class="bg-search w-full h-1/3  flex justify-center items-center">
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