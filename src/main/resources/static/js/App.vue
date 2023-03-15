<template>
  <div>
    <b-navbar wrapper-class="container" shadow>
      <template #brand>
        <b-navbar-item tag="router-link" :to="{ path: '/' }">
          <img
              src="https://i.im.ge/2023/03/13/DFDkxP.helloworld.webp"
              alt="Notik"
          >
        </b-navbar-item>
      </template>
      <template #start>
        <b-navbar-item href="#">
          Home
        </b-navbar-item>
        <b-navbar-item href="https://github.com/gavrylenkoIvan" target="_blank">
          <b-icon icon="github" class="mr-1"></b-icon> Github
        </b-navbar-item>
      </template>

      <template #end v-if="profile == null">
        <b-navbar-item tag="div">
          <div class="buttons">
            <login-button/>
            <register-button/>
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
import LoginButton from "./components/LoginButton.vue";
import RegisterButton from "./components/RegisterButton.vue";
export default {
  name: "App",
  components: {RegisterButton, LoginButton, LoginForm, RegisterForm},
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