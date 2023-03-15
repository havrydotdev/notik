import Vue from "vue";
import VueResource from "vue-resource";

Vue.use(VueResource)

const notes = Vue.resource('/notes{/id}')

export default {
    add: note => notes.save({}, note),
    remove: id => notes.remove({id: id}),
    edit: note => notes.update({id: note.id}, note)
}
