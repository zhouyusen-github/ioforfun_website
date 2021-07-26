import { createRouter, createWebHistory } from 'vue-router'
import Index from '../views/Index.vue'

const routes = [
  {
    path: '/',
    name: 'Index',
    component: Index
  },
  {
    path: '/index',
    name: 'Index2',
    component: Index
  },
  {
    path: '/index.html',
    name: 'Index3',
    component: Index
  },
  {
    path: '/activation',
    name: 'activation',
    // lazy-loaded when the route is visited.
    component: () => import('../views/activation.vue')
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
