import Vue from "vue";
import VueResource from "vue-resource";

Vue.use(VueResource)

export default {
    addNote(title, text) {
        Vue.http.post('/notes', {title: title, body: text})
    }
}
