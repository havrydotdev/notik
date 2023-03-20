import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import store from "@/store/store";
import "primevue/resources/themes/lara-light-indigo/theme.css";
//core
import 'vuetify/styles'
import { createVuetify } from 'vuetify'
import * as components from 'vuetify/components'
import * as directives from 'vuetify/directives'

const vuetify = createVuetify({
    components,
    directives,
})

const app = createApp(App)

app.use(store)
    .use(router)
    .use(vuetify)
    .mount('#app')
