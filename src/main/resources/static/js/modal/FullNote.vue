<template>
  <div class="modal-card">
    <header class="modal-card-head">
      <p class="modal-card-title">Login</p>
      <button
          type="button"
          class="delete"
          @click="$emit('close')"/>
    </header>
    <section class="modal-card-body">
      <b-field label="Email">
      </b-field>

      <b-field label="Password">
      </b-field>
    </section>
    <footer class="modal-card-foot">
      <b-button
          label="Close"
          @click="$emit('close')" />
    </footer>
  </div>
</template>

<script>
export default {
  name: "FullNote"
}
</script>

<style scoped>

</style>