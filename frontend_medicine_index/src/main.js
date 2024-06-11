import 'vue3-toastify/dist/index.css'
import 'vue-sidebar-menu/dist/vue-sidebar-menu.css'
import 'vue-loading-overlay/dist/css/index.css'
import 'vue-sidebar-menu/dist/vue-sidebar-menu.css'
import '@vueup/vue-quill/dist/vue-quill.snow.css'

import './assets/main.css'
import { QuillEditor } from '@vueup/vue-quill'

import { createApp } from 'vue'
import { createPinia } from 'pinia'
import Vue3Toastify, { toast } from 'vue3-toastify'
import axios from 'axios'
import { FontAwesomeIcon } from '@fortawesome/vue-fontawesome'
import { fas } from '@fortawesome/free-solid-svg-icons'
import { fab } from '@fortawesome/free-brands-svg-icons'
import { far } from '@fortawesome/free-regular-svg-icons'
import App from './App.vue'
import router from './router'
import VueSidebarMenu from 'vue-sidebar-menu'
import { useLoaderStore } from '@/stores/loader.js'
import { dom, library } from '@fortawesome/fontawesome-svg-core'

// Init font awesome
library.add(fas)
library.add(fab)
library.add(far)
dom.watch()

const app = createApp(App)

app.use(createPinia())
  .use(router)
  .use(VueSidebarMenu)
  .use(Vue3Toastify, {
    theme: 'dark',
    autoClose: 2500, hideProgressBar: true, style: {
      width: '440px'
    }
  })

app.component('font-awesome-icon', FontAwesomeIcon)
  .component('QuillEditor', QuillEditor)


app.mount('#app')

// Default title tag
const defaultDocumentTitle = 'Medicine Index'
const axiosInstance = axios.create()

axiosInstance.defaults.headers.common['Access-Control-Allow-Origin'] = '*'
axiosInstance.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest'
app.config.globalProperties.$axios = axiosInstance

let userData = JSON.parse(localStorage.getItem('user'))

/*show loader on request, add token*/
const loaderStore = useLoaderStore()
axios.interceptors.request.use(
  async (config) => {
    const headerWhiteList = ['auth/login']
    if (!(config.params && config.params.dontLoad === true)) {
      loaderStore.setShowLoader(true)
    } else {
      config.params.dontLoad = null
    }
    if (userData == null) {
      userData = JSON.parse(localStorage.getItem('user'))
    }
    if (null != userData) {
      let addHeader = true
      headerWhiteList.forEach(url => {
        if (config.url.includes(url)) {
          addHeader = false
        }
      })
      if (addHeader) {
        config.headers.Authorization = `Bearer ${userData.token.token}`
      }
    } else {
      /*config.headers.Authorization = `Bearer ${tokenData}`*/
    }
    return config
  },
  (error) => {
    loaderStore.setShowLoader(false)
    return Promise.reject(error)
  }
)

// show error message on api response
axios.interceptors.response.use(
  (config) => {
    loaderStore.setShowLoader(false)
    if (config.data.status === false) {

      toast.error(config.data.message, {
        autoClose: 2500
      })
    }
    return config
  },
  (error) => {
    loaderStore.setShowLoader(false)
    if (error.response && error.response.data && error.response.data.message) {
      toast.error(error.response.data.message, {
        autoClose: 2500
      })
    } else {
      toast.error('Something went wrong!', {
        autoClose: 2500
      })
    }
    if (error.response.status === 401) {
      // authService.logout()
      // router.push('/')
    }
    return Promise.reject(error)
  }
)

router.afterEach((to) => {
  document.title = to.meta?.title
    ? `${to.meta.title} — ${defaultDocumentTitle}`
    : defaultDocumentTitle
})