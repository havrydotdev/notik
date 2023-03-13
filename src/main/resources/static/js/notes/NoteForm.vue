<template>
  <section class="container mt-3 mb-3" style="width: 600px">
    <b-collapse
        aria-id="contentIdForA11y2"
        class="panel"
        animation="slide"
        v-model="isOpen">
      <template #trigger>
        <div
            class="panel-heading"
            role="button"
            aria-controls="contentIdForA11y2"
            :aria-expanded="isOpen">
          <strong>New note...</strong>
        </div>
      </template>
      <div class="box" style="width: 600px">
        <b-field label="Title" label-position="on-border">
          <b-input v-model="title"></b-input>
        </b-field>

        <b-field label="Text"
                 label-position="on-border">
          <b-input maxlength="300" type="textarea" v-model="text"></b-input>
        </b-field>
        <b-button class="is-primary" @click="save">
          Save
        </b-button>
      </div>
    </b-collapse>
  </section>
</template>

<script>
import {mapActions} from "vuex";
import Vue from "vue";
export default {
  name: "NoteForm",
  data() {
    return {
      text: '',
      title: '',
      isComponentModalActive: false,
      isOpen: false
    }
  },
  methods: {
    ...mapActions(['addNoteAction']),
    save() {
      if (this.text !== '' && this.title !== '') {
        this.addNoteAction({title: this.title, body: this.text})
        this.text = ''
        this.title = ''
        Vue.notify({
          title: 'Notik',
          text: 'New note have been successfully posted!'
        })
      } else {

      }
    }
  }
}
</script>

<style scoped>

</style>