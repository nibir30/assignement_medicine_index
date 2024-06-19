import { createRouter, createWebHistory } from 'vue-router'
import AdminHomePage from '@/pages/AdminHomePage.vue'
import AddMedicinePage from '@/pages/medicine/AddMedicinePage.vue'
import AllMedicinePage from '@/pages/medicine/AllMedicinePage.vue'
import LayoutAuthenticatedComponent from '@/layouts/LayoutAuthenticatedComponent.vue'
import AddManufacturerPage from '@/pages/manufacturer/AddManufacturerPage.vue'
import AllManufacturerPage from '@/pages/manufacturer/AllManufacturerPage.vue'
import LoginPage from '@/pages/LoginPage.vue'
import HomePage from '@/pages/user/HomePage.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'userHome',
      component: HomePage
    },
    {
      path: '/admin',
      name: 'home',
      component: LayoutAuthenticatedComponent,
      children: [
        {
          title: 'Dashboard',
          path: '',
          name: 'Dashboard',
          component: AdminHomePage
        }
      ]
    },
    {
      path: '/login',
      name: 'login',
      component: LoginPage
    },
    {
      meta: {
        title: 'Medicine'
      },
      path: '/medicine',
      component: LayoutAuthenticatedComponent,
      children: [
        {
          title: 'Add Medicine',
          path: 'add',
          name: 'Add Medicine',
          component: AddMedicinePage
        },
        {
          title: 'All Medicines',
          path: 'all',
          name: 'All Medicines',
          component: AllMedicinePage
        }
      ]
    }, {
      meta: {
        title: 'Manufacturer'
      },
      path: '/manufacturer',
      component: LayoutAuthenticatedComponent,
      children: [
        {
          title: 'Add Manufacturer',
          path: 'add',
          name: 'Add Manufacturer',
          component: AddManufacturerPage
        },
        {
          title: 'All Manufacturer',
          path: 'all',
          name: 'All Manufacturer',
          component: AllManufacturerPage
        }
      ]
    }
  ]
})

export default router
