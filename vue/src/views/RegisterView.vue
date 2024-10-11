<template>
  <div id="register" class="text-center">
    <div id="title">
      <h1 >Create an Account</h1>
    </div>
    <div id="register-form">
      <form v-on:submit.prevent="register">
        <div id="alert" role="alert" v-if="registrationErrors">
          {{ registrationErrorMsg }}
        </div>
        <div class="form-input-group">
          <label for="username">
            <p>Username</p>
          </label>
          <input type="email" id="username" v-model="user.username" required autofocus @blur="validateEmail" @input="validateEmail"/>
          <span class="register-alert" v-if="!isValidEmail">
            Invalid email format
          </span>
        </div>
        <div class="form-input-group">
          <label for="password">
            <p>Password</p>
          </label>
          <input type="password" id="password" v-model="user.password" @focus="validatePassword" @input="validatePassword" />
          <span class="register-alert" v-if="!isValidPassword">
            Password must contain: <br>
            - One capital letter <br>
            - One lowercase letter <br>
            - One number <br>
            - Minimum of 8 characters.
          </span>
        </div>
        <div class="form-input-group">
          <label for="confirmPassword">
            <p>Confirm Password</p>
          </label>
          <input type="password" id="confirmPassword" v-model="user.confirmPassword" required />
        </div>
        <div id="register-button">
          <button type="submit">
            <h2>Create Account</h2>
          </button>
        </div>
        <p>
          Already have an account?&nbsp;
          <router-link id="router-link" v-bind:to="{ name: 'login' }"> Log in.</router-link>
        </p>
      </form>
    </div>
  </div>
</template>

<script>
import authService from '../services/AuthService';

export default {
  data() {
    return {
      user: {
        username: '',
        password: '',
        confirmPassword: '',
        role: 'user',
      },
      isValidPassword: true,
      isValidEmail: true,
      registrationErrors: false,
      registrationErrorMsg: '⚠ There were problems registering this user.',
    };
  },
  methods: {
    validateEmail(){
      if(/^[^@]+@\w+(\.\w+)+\w$/.test(this.user.username)){
        console.log(this.isValidEmail)
        this.isValidEmail = true;
      }
      else this.isValidEmail = false;
    },
    register() {
      if (this.user.password != this.user.confirmPassword) {
        this.registrationErrors = true;
        this.registrationErrorMsg = '⚠ Password & Confirm Password do not match.';
      } else {
        authService
          .register(this.user)
          .then((response) => {
            if (response.status == 201) {
              this.$router.push({
                path: '/login',
                query: { registration: 'success' },
              });
            }
          })
          .catch((error) => {
            const response = error.response;
            this.registrationErrors = true;
            if (response.status === 400) {
              this.registrationErrorMsg = '⚠ A user with this email already exists.';
            }
          });
      }
    },
    validatePassword() {
      const hasUppercase = /[A-Z]/.test(this.user.password);
      const hasLowercase = /[a-z]/.test(this.user.password);
      const hasNumber = /\d/.test(this.user.password);
      const isValidLength = this.user.password.length >= 8;

      this.isValidPassword = hasUppercase && hasLowercase && hasNumber && isValidLength;
    },
    clearErrors() {
      this.registrationErrors = false;
      this.registrationErrorMsg = 'There were problems registering this user.';
    },
  },
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
  margin-bottom: 0.5rem;
  font-size: 18px;
  color: white;
}

h1 {
  font-size: 48px;
  color: white;
}


#register {
  background-color: rgb(22, 22, 22);
  z-index: 50;
  padding-bottom: 12vh;
  display: flex;
  flex-direction: column;
  align-items: center;
  min-height: 100vh;
  padding-top: 12vh;
}

#alert {
  display: flex;
  font-family: Montserrat;
  
  justify-content: center;

  background-color: red;
  color: white;
  margin: 20px;
  padding: 20px;
  border-radius: 10px;
}

#router-link {
  color: white;
}

#title{
  margin-bottom: 1em;
  text-align: center;
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

#register-form {
  width: 100%;
  max-width: 400px;
  background-color: rgb(22, 22, 22);
  padding: 20px;
  border-radius: 10px;
}

.register-alert {
  color: red;
  font-family: Montserrat;
  font-size: 16px;
  margin-top: 5px;
}

#register-button {
  display: flex;
  justify-content: center;
}

</style>
