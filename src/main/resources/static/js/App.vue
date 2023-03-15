<template>
  <div class="element">
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
        <b-dropdown
            v-model="navigation"
            position="is-bottom-left"
            append-to-body
            aria-role="menu">
          <template #trigger>
            <a
                class="navbar-item"
                role="button">
              <b-icon icon="menu"></b-icon>
            </a>
          </template>

          <b-dropdown-item custom aria-role="menuitem">
            Logged as <b>{{profile.userName}}</b>
          </b-dropdown-item>
          <hr class="dropdown-divider">
          <b-dropdown-item has-link value="home" aria-role="menuitem">
            <a href="/">
              <b-icon icon="home"></b-icon>
              Home
            </a>
          </b-dropdown-item>
          <hr class="dropdown-divider" aria-role="menuitem">
          <b-dropdown-item value="settings">
            <b-icon icon="account"></b-icon>
            Profile
          </b-dropdown-item>
          <b-dropdown-item has-link value="logout" aria-role="menuitem">
            <a href="/logout">
              <b-icon icon="logout"></b-icon>
              Logout
            </a>
          </b-dropdown-item>
        </b-dropdown>
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
      register: false,
      navigation: "home"
    }
  }
}
</script>

<style scoped>
.element::-webkit-scrollbar { width: 0; }
</style>