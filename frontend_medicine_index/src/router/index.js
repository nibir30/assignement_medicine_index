import { createRouter, createWebHistory } from 'vue-router'
import AdminHomePage from '@/pages/AdminHomePage.vue'
import AddMedicinePage from '@/pages/medicine/AddMedicinePage.vue'
import AllMedicinePage from '@/pages/medicine/AllMedicinePage.vue'
import LayoutAuthenticatedComponent from '@/layouts/LayoutAuthenticatedComponent.vue'
import AddManufacturerPage from '@/pages/manufacturer/AddManufacturerPage.vue'
import AllManufacturerPage from '@/pages/manufacturer/AllManufacturerPage.vue'
import LoginPage from '@/pages/LoginPage.vue'
import HomePage from '@/pages/user/HomePage.vue'
import EditMedicinePage from '@/pages/medicine/EditMedicinePage.vue'
import LogoutPage from '@/pages/LogoutPage.vue'

const routes = [
  {
    path: '/',
    name: 'userHome',
    component: HomePage
  },
  {
    path: '/admin',
    name: 'adminHome',
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
    path: '/logout',
    name: 'logout',
    component: LogoutPage
  },
  {
    meta: {
      title: 'Medicine'
    },
    path: '/medicine',
    name: 'adminMedicine',
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
      },
      {
        title: 'Edit Medicine',
        path: 'edit',
        name: 'Edit Medicine',
        component: EditMedicinePage,
        props: true
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

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes,
  linkActiveClass: '', // active class for non-exact links.
  linkExactActiveClass: 'active' // active class for *exact* links.
})

router.beforeEach((to, from, next) => {
  let hasAdminAccess = false

  const user = JSON.parse(localStorage.getItem('user'))
  if (user && user.userInfo.roleNames.includes('ADMIN')) {
    hasAdminAccess = true
  }
  console.log('to', to)
  const adminWhiteList = ['Dashboard', 'All Medicines', 'Edit Medicine', 'Add Medicine']
  const whiteList = ['userHome', 'logout']
  if (whiteList.includes(to.name)) {
    next()
  } else if (to.name === 'login') {
    if (hasAdminAccess) {
      next('/medicine/all')
    } else {
      next()
    }
  } else if (adminWhiteList.includes(to.name)) {
    if (hasAdminAccess) {
      next()
    } else {
      next('/')
    }
  } else {
    next('/')
  }
})
export default router
