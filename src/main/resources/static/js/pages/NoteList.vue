<template>
  <div class="container" v-if="profile !== null">
    <note-form/>
    <note-card v-for="note in this.sortedPosts"
               :note="note"/>
  </div>
</template>

<script>
import NoteCard from "../notes/NoteCard.vue";
import {mapGetters, mapState} from "vuex";
import mainApi from "../api/mainApi";
import NoteForm from "../notes/NoteForm.vue";

export default {
  name: "NoteList",
  components: {NoteForm, NoteCard},
  computed: {
    ...mapGetters(['sortedPosts']),
    ...mapState(['profile', 'notes'])
  }
}
</script>

<style scoped>

</style>