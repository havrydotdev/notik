<script lang="ts">
import {defineComponent} from "vue";
import axios from 'axios'
export default defineComponent({
  beforeCreate() {
     axios.get('http://localhost:8080/getUser')
        .then(response => {
          this.$store.commit('addProfileMutation', response.data)
          this.$store.state.isProfileFetched = true
        })
  },
  data() {
    return {
      dialog: false,
      valid: false,
      show2: false,
      password: 'Password',
      rules: {
        required: (value: string) => !!value || 'Password required.',
        min: (v: string) => (v.length >= 8 && v.length < 16) || 'Min 8 characters',
        isCorrect: (value: string) => /^(?=.*[0-9])(?=.*[!@#$%^&*])[a-zA-Z0-9!@#$%^&*]/.test(value) || 'Must contain at least one letter, one number and one special character'
      },
      email: '',
      emailRules: [
          (value: string) => {
          if (value) return true

          return 'E-mail is required.'
        },
        (value: string) => {
          if (/.+@.+\..+/.test(value)) return true

          return 'E-mail must be valid.'
        },
      ],
    }
  },
  methods: {
    sendInfo() {
      if (this.valid) {
        console.log('hello')
      }
    }
  }
})
</script>

<template>
    <v-app v-if="$store.state.isProfileFetched" class="bg-amber">
      <v-app-bar :elevation="5" class="bg-amber-lighten-4">
        <v-app-bar-title>Notik</v-app-bar-title>
        <template v-slot:append>

          <div v-if="$store.state.profile.isError !== true">
            <v-btn icon="mdi-heart"></v-btn>

            <v-btn icon="mdi-magnify"></v-btn>

            <v-btn icon="mdi-dots-vertical"></v-btn>
          </div>
          <div v-else>
            <v-dialog
                v-model="dialog"
                persistent
                width="500"
            >
              <template v-slot:activator="{ props }">
                <v-btn
                    class="bg-amber"
                    v-bind="props"
                >
                  Login
                </v-btn>
              </template>
              <v-card class="bg-amber-lighten-2" >
                <v-card-title style="display: flex; justify-content: center" class="mt-6">
                  <span class="text-h4">User Profile</span>
                </v-card-title>
                <v-card-text>
                  <v-container>
                    <v-form v-model="valid">
                      <v-text-field
                          v-model="email"
                          :rules="emailRules"
                          label="Email"
                          required
                      ></v-text-field>
                      <v-text-field
                          :append-inner-icon="show2 ? 'mdi-eye' : 'mdi-eye-off'"
                          :rules="[rules.required, rules.min, rules.isCorrect]"
                          :type="show2 ? 'text' : 'password'"
                          name="input-10-2"
                          label="Password"
                          hint="At least 8 characters"
                          class="input-group--focused"
                          @click:appendInner="show2 = !show2"
                          required
                      ></v-text-field>
                    </v-form>
                  </v-container>
                </v-card-text>
                <v-card-actions style="transform: translateX(-30px)" class="mb-5">
                  <v-spacer></v-spacer>
                  <v-btn
                      class="bg-amber-darken-1"
                      variant="text"
                      @click="dialog = false"
                  >
                    Close
                  </v-btn>
                  <v-btn
                      class="bg-amber-darken-2"
                      variant="text"
                      @click="sendInfo(); dialog = false"
                  >
                    Save
                  </v-btn>
                </v-card-actions>
              </v-card>
            </v-dialog>
            <v-btn class="bg-amber-darken-3 ml-1">Register</v-btn>
          </div>
        </template>
      </v-app-bar>
    </v-app>
</template>

<style scoped>

</style>
