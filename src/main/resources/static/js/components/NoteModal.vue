<template>
  <b-modal
      v-model="modal"
      has-modal-card
      trap-focus
      :destroy-on-hide="false"
      aria-role="dialog"
      aria-label="Login modal"
      close-button-aria-label="Close"
      aria-modal>
    <template #default="props">
      <full-note @close="props.close"></full-note>
    </template>
  </b-modal>
</template>

<script>
import FullNote from "../modal/FullNote.vue";

export default {
  name: "NoteModal",
  components: {FullNote},
  props: ['modal']
}
</script>

<style scoped>

</style>