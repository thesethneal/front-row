<template>
  <div id="login">
    <div id="title">
      <h1 >Log in to Front Row</h1>
    </div>
    <div id="login-form">
      <form v-on:submit.prevent="login">
        <div id="alert" role="alert" v-if="invalidCredentials">
          ⚠ Incorrect username and password!
        </div>
        <div role="alert" v-if="this.$route.query.registration">
          <p>Thank you for registering, please log in.</p>
        </div>
        <div class="form-input-group">
          <label for="username">
            <p>Username</p>
          </label>
          <input type="username" v-model="user.username" required autofocus/>
        </div>
        <div class="form-input-group">
          <label for="password">
            <p>Password</p>
          </label>
          <input type="password" id="password" v-model="user.password" required />
        </div>
        <div id="login-button">
          <button id="submit" type="submit">
            <h2>Log in</h2>
          </button>
        </div>
        <p>
          Don't have an account yet?&nbsp;
          <router-link id="router-link" v-bind:to="{ name: 'register' }">Sign up.</router-link>
        </p>
      </form>
    </div>
  </div>
</template>

<script>
import authService from "../services/AuthService";

export default {
  components: {},
  data() {
    return {
      user: {
        username: "",
        password: ""
      },
      invalidCredentials: false
    };
  },
  methods: {
    login() {
      authService
        .login(this.user)
        .then(response => {
          if (response.status == 200) {
            this.$store.commit("SET_AUTH_TOKEN", response.data.token);
            this.$store.commit("SET_USER", response.data.user);
            this.$router.push("/");
          }
        })
        .catch(error => {
          const response = error.response;

          if (response.status === 401) {
            this.invalidCredentials = true;
          }
        });
    }
  }
};
</script>

<style scoped>
.form-input-group {
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  margin-bottom: 1.5rem;
  width: 100%;
  
}

label {
  margin-right: 0.5rem;
}

h1 {
  font-size: 48px;
}

#login {
  margin-top: 12vh;
  display: flex;
  flex-direction: column;
  align-items: center;
  z-index: 50;
  background-color: #161616;
}

#login-form {
  width: 100%;
  max-width: 400px;
  background-color: rgb(22, 22, 22);
  padding: 20px;
  border-radius: 10px;
}

#alert {
  display: flex;
  font-family: Montserrat;
  
  justify-content: center;

  background-color: red;
  color: white;
  margin: 20px;
  padding: 20px;
}

#router-link {
  color: white;
}

#title{
  margin-bottom: 40px;
}

input {
  width: 100%;
  padding: 12px 15px;
  margin-bottom: 8px;
  border: 1px solid #ccc;
  box-sizing: border-box;
  border-radius: 10px;
  font-size: 16px;
  box-sizing: border-box;
}

button h2 {
  color: black;
  
  font-family: Montserrat;
  font-size: 28px;
  font-style: normal;
  font-weight: 600;
  line-height: normal;

  margin: 12px;
}

button {
  color: white;
  border-radius: 24px;
  border: none;
  margin: 20px;

  width: 100%;
}

#login-button {
  display: flex;
  justify-content: center;
}

</style>