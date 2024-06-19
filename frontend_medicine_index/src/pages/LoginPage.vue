<template>
  <div>
    <div class="grid grid-cols-1 md:grid-cols-2 w-full">
      <div class="flex flex-col p-5 justify-center items-center">
        <div class="text-3xl md:text-5xl font-bold mb-10">
          Hello Admin,<br />Welcome Back!
        </div>
        <div class="w-full md:w-1/2">
          <div class="w-full items-center mb-4">
            <Input id="username" v-model="form.userId" class="pr-10" placeholder="Email" type="text" />
          </div>
          <div class="relative w-full items-center mb-10 ">
            <Input id="password" v-model="form.password" :type="showPassword ? 'text' : 'password'" class="pr-10"
                   placeholder="Password" />
            <span class="absolute end-0 inset-y-0 flex items-center justify-center ">
            <Button v-if="!showPassword" size="icon" variant="transparent" @click="showPassword = !showPassword">
              <i class="fa-solid fa-eye"></i>
            </Button>
           <Button v-else size="icon" variant="transparent" @click="showPassword = !showPassword">
             <i class="fa-solid fa-eye-slash"></i>
            </Button>
          </span>
          </div>
          <Button class="self-start bg-purple-500 hover:bg-purple-700" @click="submit">
            Login
          </Button>
        </div>
      </div>
      <div class="order-first md:order-last">
        <img alt="" class="aspect-square" src="/src/assets/images/login.jpg" style="width: 100vw; object-fit: cover">
      </div>
    </div>
  </div>
</template>

<script setup>
import { Button } from '@/components/ui/button'
import { reactive, ref } from 'vue'
import { Input } from '@/components/ui/input'

const form = reactive({
  userId: '',
  password: ''
})
const showPassword = ref(false)

import { useRouter } from 'vue-router'
import authService from '@/services/auth.service'

const router = useRouter()

const submit = async () => {
  localStorage.clear()
  try {
    let response = await authService.login(form)
    if (response.status !== false) {
      localStorage.setItem('user', JSON.stringify(response.data))
      console.log('response.userInfo.roleNames', response.data.userInfo.roleNames)
      if (response.data.userInfo && response.data.userInfo.roleNames && response.data.userInfo.roleNames.includes('ADMIN')) {
        await router.push('/admin')
      } else {
        await router.push('/')
      }
    }
  } catch (e) {
    console.log('Error123', e)
  }
}
</script>
<style scoped>

</style>
