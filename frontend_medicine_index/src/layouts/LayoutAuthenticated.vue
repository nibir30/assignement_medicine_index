<template>
  <sidebar-menu
    v-model:collapsed="collapsed"
    :menu="menu"
    :show-one-child="true"
    :theme="selectedTheme"
    @update:collapsed="onToggleCollapse"
    @item-click="onItemClick"
  />
  <div
    v-if="isOnMobile && !collapsed"
    class="sidebar-overlay"
    @click="collapsed = true"
  />
  <div id="demo" :class="[{ collapsed: collapsed }, { onmobile: isOnMobile }]">
    <div class="demo">
      <div class="container">
        <slot />
      </div>
    </div>
  </div>
</template>

<script setup>
import { onMounted, ref } from 'vue'
import { h } from 'vue'
import { FontAwesomeIcon } from '@fortawesome/vue-fontawesome'

onMounted(() => {
  onResize()
  window.addEventListener('resize', onResize)
})

const selectedTheme = 'dark-theme'
const isOnMobile = ref(false)
const collapsed = ref(false)

function onToggleCollapse(collapsed) {
  console.log('onToggleCollapse', collapsed)
}

function onItemClick(event, item) {
  console.log('onItemClick', event)
  console.log('onItemClick - item', item)
}

function onResize() {
  if (window.innerWidth <= 767) {
    isOnMobile.value = true
    collapsed.value = true
  } else {
    isOnMobile.value = false
    collapsed.value = false
  }
}

const separator = h('hr', {
  style: {
    borderColor: 'rgba(0, 0, 0, 0.1)',
    margin: '6px'
  }
})

const faIcon = (props) => {
  return {
    element: h('div', [h(FontAwesomeIcon, { size: 'lg', ...props })])
  }
}
const menu = [
  {
    header: 'Medicine Index Admin',
    hiddenOnCollapse: true
  },
  {
    href: '/',
    title: 'Dashboard',
    icon: faIcon({ icon: 'fa-solid fa-chart-line' })
  },
  {
    component: separator
  },
  {
    header: 'Medicines',
    hiddenOnCollapse: true
  },
  {
    title: 'Medicine',
    icon: faIcon({ icon: 'fa-solid fa-capsules' }),
    child: [
      {
        href: '/medicine/all',
        title: 'All Medicines',
        icon: faIcon({ icon: 'fa-solid  fa-border-all', size: 'sm' })
      },
      {
        href: '/medicine/add',
        title: 'Add New Medicine',
        icon: faIcon({ icon: 'fa-solid fa-plus', size: 'sm' })
      }
    ]
  },
  {
    title: 'Manufacturer',
    icon: faIcon({ icon: 'fa-solid fa-warehouse' }),
    child: [
      {
        href: '/manufacturer/all',
        title: 'All Manufacturers',
        icon: faIcon({ icon: 'fa-solid fa-border-all', size: 'sm' })
      },
      {
        href: '/manufacturer/add',
        title: 'Add New Manufacturer',
        icon: faIcon({ icon: 'fa-solid fa-plus', size: 'sm' })
      }
    ]
  }
]
</script>
<style>

body,
html {
  margin: 0;
  padding: 0;
}

body {
  font-family: 'Source Sans Pro', sans-serif;
  font-size: 18px;
  color: #262626;
}

#demo {
  padding-left: 290px;
  transition: 0.3s ease;
}

#demo.collapsed {
  padding-left: 65px;
}

#demo.onmobile {
  padding-left: 65px;
}

.sidebar-overlay {
  position: fixed;
  width: 100%;
  height: 100%;
  top: 0;
  left: 0;
  background-color: #000;
  opacity: 0.5;
  z-index: 900;
}

.demo {
  padding: 20px;
}

</style>
