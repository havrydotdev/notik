import Vue from 'vue'
import App from "./App.vue";
import '@babel/polyfill'
import router from "./router/router";
import VueResource from "vue-resource";
import store from "./store/store";
import Notifications from 'vue-notification'
import Buefy from 'buefy'
import 'buefy/dist/buefy.css'

Vue.use(Buefy)
Vue.use(VueResource)
Vue.use(Notifications)

window.addEventListener('load', function () {
    new Vue({
        el: '#app',
        router,
        store,
        render: a => a(App)
    })
})
