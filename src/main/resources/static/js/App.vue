<template>
  <div>
    <b-navbar wrapper-class="container" shadow>
      <template #brand>
        <b-navbar-item tag="router-link" :to="{ path: '/' }">
          <img
              src="https://i.im.ge/2023/03/13/DFDkxP.helloworld.webp"
              alt="Lightweight UI components for Vue.js based on Bulma"
          >
        </b-navbar-item>
      </template>
      <template #start>
        <b-navbar-item href="#">
          <b-icon icon="home-circle" class="mr-1"></b-icon> Home
        </b-navbar-item>
        <b-navbar-item href="https://github.com/gavrylenkoIvan">
          <b-icon icon="github" class="mr-1"></b-icon> Github
        </b-navbar-item>
      </template>

      <template #end v-if="profile == null && notes == null">
        <b-navbar-item tag="div">
          <div class="buttons">
            <b-button
                label="Log in"
                type="is-primary"
                @click="login = true" />
            <b-modal
                v-model="login"
                has-modal-card
                trap-focus
                :destroy-on-hide="false"
                aria-role="dialog"
                aria-label="Login modal"
                close-button-aria-label="Close"
                aria-modal>
              <template #default="props">
                <login-form @close="props.close"></login-form>
              </template>
            </b-modal>
            <b-button
                label="Register"
                type="is-grey"
                @click="register = true" />
            <b-modal
                v-model="register"
                has-modal-card
                trap-focus
                :destroy-on-hide="false"
                aria-role="dialog"
                aria-label="Register modal"
                close-button-aria-label="Close"
                aria-modal>
              <template #default="props">
                <register-form @close="props.close"></register-form>
              </template>
            </b-modal>
          </div>
        </b-navbar-item>
      </template>

      <template v-else #end>
        <b-navbar-item tag="div">
          <div class="buttons">
            <a class="button is-primary" style="width: 26px; height: 30px" href="/logout">
              <b-icon icon="exit-to-app"></b-icon>
            </a>
          </div>
        </b-navbar-item>
      </template>
    </b-navbar>
    <notifications position="bottom right"/>
    <router-view></router-view>
  </div>
</template>
<script>
import {mapState} from "vuex";
import LoginForm from "./modal/Login.vue";
import RegisterForm from "./modal/Register.vue";
export default {
  name: "App",
  components: {LoginForm, RegisterForm},
  computed: mapState(['profile', 'notes']),
  data() {
    return {
      login: false,
      register: false
    }
  }
}
</script>

<style scoped>

</style>