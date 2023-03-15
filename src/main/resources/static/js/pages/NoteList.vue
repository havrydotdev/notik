<template>
  <div class="container" v-if="profile !== null">
    <note-form/>
    <div style="display: flex; flex-direction: row; flex-wrap: wrap; transform: translateX(37px)">
      <note-card v-for="note in this.allNotes.filter(it => it.isImportant || it.important)"
                 :note="note"/>
    </div>
    <div class="text-divider" v-if="notes.length !== 0"/>
    <div style="display: flex; flex-direction: row; flex-wrap: wrap; transform: translateX(37px)">
      <note-card v-for="note in this.allNotes.filter(it => !it.isImportant && !it.important)"
                 :note="note"/>
    </div>
  </div>

  <div class="container" v-else style="margin-top: 200px">
    <img
        src="https://i.im.ge/2023/03/15/DqvHnq.noteik-logo.webp"
        alt="Notik"
        class="mt-6"
        style="margin: 0 auto; display: block"
    >
    <div style="display: flex; justify-content: center; margin-top: 70px;">
      <login-button/>
      <register-button/>
      <a class="button" href="https://github.com/gavrylenkoIvan" target="_blank" style="margin-left: 10px">
        <b-icon icon="github"></b-icon>
      </a>
    </div>
  </div>
</template>

<script>
import NoteCard from "../notes/NoteCard.vue";
import {mapGetters, mapState} from "vuex";
import mainApi from "../api/mainApi";
import NoteForm from "../notes/NoteForm.vue";
import LoginButton from "../components/LoginButton.vue";
import RegisterButton from "../components/RegisterButton.vue";
import NoteModal from "../components/NoteModal.vue";
export default {
  name: "NoteList",
  components: {RegisterButton, LoginButton, NoteForm, NoteCard},
  computed: {
    ...mapGetters(['allNotes']),
    ...mapState(['profile', 'notes'])
  }
}
</script>

<style scoped>
.text-divider {
  content: '';
  height: 1px;
  background-color: silver;
  flex-grow: 1;
  margin-bottom: 35px;
  margin-top: 15px;
}
</style>