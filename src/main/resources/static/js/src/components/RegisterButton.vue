<template>
  <v-dialog
      v-model="dialog"
      persistent
      width="500">

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
</template>

<script lang="ts">
import {defineComponent} from "vue";
export default defineComponent({
  name: "Register",
  data() {
    return {
      dialog: false,
      show2: false,
      valid: false,
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

<style scoped>

</style>