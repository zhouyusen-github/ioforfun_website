import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import ElementPlus from 'element-plus';
import 'element-plus/lib/theme-chalk/index.css';

// createApp(App).use(router).mount('#app')
const app = createApp(App)
app.use(router)
app.use(ElementPlus)
app.mount('#app')
