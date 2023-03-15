<template>
  <div class="mt-2 mb-5">
    <div class="box mr-2 ml-2 red" style="height: 250px; width: 300px; cursor: pointer;" v-if="note.isImportant || note.important">
      <h3 class="title is-4 mb-5">{{note.title}}</h3>
      <h5 class="subtitle is-6">{{systemNow()}}</h5>
      <div class="mt-5" style="min-height: 90px">
        {{getBody()}}
      </div>
      <div style="margin-left: 160px">
        <b-button type="is-link"
                  icon-right="file-edit"
                  @click="prompt()"/>
        <b-button type="is-danger"
                  icon-right="delete"
                  class="ml-1"
                  @click="deleteCurrent()"/>
      </div>
    </div>

    <div class="box mr-2 ml-2" style="height: 250px; width: 300px; cursor: pointer;" v-else>
      <h3 class="title is-4 mb-5">{{note.title}}</h3>
      <h5 class="subtitle is-6">{{systemNow()}}</h5>
      <div class="mt-5" style="min-height: 90px">
        {{getBody()}}
      </div>
      <div style="margin-left: 160px">
        <b-button type="is-link"
                  icon-right="file-edit"
                  @click="prompt()"/>
        <b-button type="is-danger"
                  icon-right="delete"
                  class="ml-1"
                  @click="deleteCurrent()"/>
      </div>
    </div>
  </div>
</template>

<script>
import {mapActions} from "vuex";
import FullNote from "../modal/FullNote.vue";

export default {
  name: "NoteCard",
  components: {FullNote},
  props: ["note"],
  data() {
    return {
      reveal: false,
      modal: false
    }
  },
  methods: {
    ...mapActions(['deleteNoteAction', 'editNoteAction']),
    systemNow() {
      const millis = (Date.now() - this.note.creationDate)
      let days = Math.round(millis / 86400000)
      let hours = Math.round(millis / 3600000)
      let minutes = Math.round(millis / 60000)
      if (days >= 1) {
        return 'Created ' + days + (days === 1 ? ' day' : ' days') + " ago"
      } else if (hours >= 1) {
        return 'Created ' + hours + (hours === 1 ? ' hour' : ' hours') + ' ago'
      } else if (minutes >= 1) {
        return 'Created ' + minutes + (minutes === 1 ? ' minute' : ' minutes') + ' ago'
      } else {
        return "Created few seconds ago"
      }
    },
    getBody() {
      if (this.note.body.length > 40) {
        return this.note.body.substring(0, 39) + '...'
      } else {
        return this.note.body
      }
    },
    deleteCurrent() {
      this.deleteNoteAction(this.note)
    },
    prompt() {
      this.$buefy.dialog.prompt({
        message: `Edit note`,
        inputAttrs: {
          placeholder: 'Enter here...',
          maxlength: 300,
          value: this.note.body
        },
        trapFocus: true,
        onConfirm: (value) => this.editCurrent(value)
      })
    },
    editCurrent(str) {
      this.editNoteAction({id: this.note.id, title: this.note.title, body: str, userId: this.note.userId, isImportant: `${(this.note.important === undefined || this.note.important === null ? this.note.isImportant : this.note.important)}`, creationDate: this.note.creationDate})
    }
  }
}
</script>

<style scoped>
.red {
  background-color: lightgreen;
}
</style>