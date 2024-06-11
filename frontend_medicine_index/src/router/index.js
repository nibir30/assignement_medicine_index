import { createRouter, createWebHistory } from 'vue-router'
import HomePage from '@/pages/HomePage.vue'
import AddMedicinePage from '@/pages/medicine/AddMedicinePage.vue'
import AllMedicinePage from '@/pages/medicine/AllMedicinePage.vue'
import LayoutAuthenticatedComponent from '@/layouts/LayoutAuthenticatedComponent.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: LayoutAuthenticatedComponent,
      children: [
        {
          title: 'Dashboard',
          path: '',
          name: 'Dashboard',
          component: HomePage
        }
      ]
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
    }
  ]
})

export default router
