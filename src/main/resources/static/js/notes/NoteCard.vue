<template>
  <v-card
      class="mx-auto"
      max-width="344"
  >
    <v-card-text>
      <div>Regular note</div>
      <p class="text-h4 text--primary">
        {{note.title}}
      </p>
      <p>{{systemNow()}}</p>
      <div class="text--primary">
        {{getBody()}}
      </div>
    </v-card-text>
    <v-card-actions>
      <v-btn
          text
          color="teal accent-4"
          @click="reveal = true"
      >
        Read more
      </v-btn>
    </v-card-actions>

    <v-expand-transition>
      <v-card
          v-if="reveal"
          class="transition-fast-in-fast-out v-card--reveal"
          style="height: 100%;"
      >
        <v-card-text class="pb-0">
          <p class="text-h4 text--primary">
            {{note.title}}
          </p>
          <p>{{note.body}}</p>
        </v-card-text>
        <v-card-actions class="pt-0">
          <v-btn
              text
              color="teal accent-4"
              @click="reveal = false"
          >
            Close
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-expand-transition>
  </v-card>
</template>

<script>
export default {
  name: "NoteCard",
  props: ["note"],
  data() {
    return {
      reveal: false
    }
  },
  methods: {
    systemNow() {
      const millis = (Date.now() - this.note.creationDate)
      let days = Math.round(millis / 86400000)
      let hours = Math.round(millis / 3600000)
      let minutes = Math.round(millis / 60000)
      if (days > 1) {
        return 'Created ' + (days === 1 ? ' day' : ' days') + " ago"
      } else if (hours > 1) {
        return 'Created ' + hours + (hours === 1 ? ' hour' : ' hours') + ' ago'
      } else if (minutes > 1) {
        return 'Created ' + minutes + (minutes === 1 ? ' minute' : ' minutes') + ' ago'
      } else {
        return "Created few seconds ago"
      }
    },
    getBody() {
      if (this.note.body.length > 40) {
        return this.note.body.substring(0, 39)
      } else {
        return this.note.body
      }
    }
  }
}
</script>

<style scoped>
.v-card--reveal {
  bottom: 0;
  opacity: 1 !important;
  position: absolute;
  width: 100%;
}
</style>