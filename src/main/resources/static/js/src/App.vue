<script lang="ts">
import {defineComponent} from "vue";
import axios from 'axios'
import RegisterButton from "@/components/RegisterButton.vue";
export default defineComponent({
  components: {RegisterButton},
  beforeCreate() {
     axios.get('http://localhost:8080/getUser')
        .then(response => {
          this.$store.commit('addProfileMutation', response.data)
          this.$store.state.isProfileFetched = true
        })
  }
})
</script>

<template>
    <v-app v-if="$store.state.isProfileFetched" class="bg-amber-lighten-4">
      <v-app-bar :elevation="5" class="bg-amber-accent-3">
        <v-app-bar-title>Notik</v-app-bar-title>
        <template v-slot:append>

          <div v-if="$store.state.profile.isError !== true">
            <v-btn icon="mdi-heart"></v-btn>

            <v-btn icon="mdi-magnify"></v-btn>

            <v-btn icon="mdi-dots-vertical"></v-btn>
          </div>
          <div v-else>
            <register-button></register-button>
            <v-btn class="bg-amber-darken-3 ml-1">Register</v-btn>
          </div>
        </template>
      </v-app-bar>
    </v-app>
</template>

<style scoped>

</style>
