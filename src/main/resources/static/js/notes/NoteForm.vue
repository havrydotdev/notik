<template>
  <v-layout row>
    <v-text-field
        label="Note title"
        placeholder="Title"
        v-model="title"
        @keyup.enter="save"
    />
    <v-text-field
        label="Note text"
        placeholder="Text"
        v-model="text"
        @keyup.enter="save"
    />
    <v-btn @click="save">
      Save
    </v-btn>
  </v-layout>
</template>

<script>
import {mapActions} from "vuex";

export default {
  name: "NoteForm",
  data() {
    return {
      text: '',
      title: ''
    }
  },
  methods: {
    ...mapActions(['addNoteAction']),
    save() {
      if (this.text !== '' && this.title !== '') {
        this.addNoteAction({title: this.title, body: this.text})
        this.text = ''
        this.title = ''
      }
    }
  }
}
</script>

<style scoped>

</style>