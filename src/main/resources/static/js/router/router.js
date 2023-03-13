import Vue from 'vue'
import VueRouter from 'vue-router'
import NoteList from "../pages/NoteList.vue";
import Register from "../modal/Register.vue";

Vue.use(VueRouter)

const routes = [
    { path: '/', component: NoteList },
    { path: '/register', component: Register},
    { path: '*', component: NoteList}
]

export default new VueRouter({
    mode: 'history',
    routes
})
