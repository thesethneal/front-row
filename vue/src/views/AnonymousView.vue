<template>
  <div id="bg-image">
    <div id="anonymous-home">
      <div style="display: flex; margin-top: 6vh; align-items: center;">
        <div style="margin-right: 2vw; width: 10vw;">
          <img src="src/assets/logo-white.png" alt="Logo" style="width: 100%; height: auto;">
        </div>
        <div style="display: flex; flex-direction: column; align-items: flex-start;">
          <h1>FRONT ROW</h1>
          <h2 style="margin-top: -0.5em; ">IT'S WHERE YOU WANT TO BE</h2>
        </div>
      </div>
      <div id="description">
        <p>Tailored to strengthen the engagement between bands and their fans.<br>Stay up to date on all your favorites.</p>
        <div id="buttons">
          <button @click="$router.push('/login')">
              <h2 class="router-button" >Login</h2>
          </button>
          <button @click="$router.push('/register')">
              <h2 class="router-button" >Create Account</h2>
          </button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>

export default {
  data() {
    return {
    }
  },
  components: {
  }
};
</script>

<style scoped>
  #bg-image {
    background-image: linear-gradient(to bottom, #000000, #ffffff00), url('src/assets/BR.png');
    background-position: center;

    display: flex; 
    flex-direction: column;
    align-items: center;

    height: 100vh;
  }

  #buttons{
    margin: auto;
  }

  button {
    color: white;
    border-radius: 24px;
    border: none;
    margin: 20px;
  }

  #description {
    display: flex;
    flex-direction: column;
    padding: 20px;
  }

  #anonymous-home {
    padding-top: 5vh;
    display: flex;
    flex-direction: column;
    height: 100vh;
    justify-content: space-between;
  }

  .router-button {
    color: black;
    text-decoration: none;
    
    font-family: Montserrat;
    font-size: 28px;
    font-style: normal;
    font-weight: 600;
    line-height: normal;
  }

  #buttons button h2 {
    margin: 10px;
  }

</style>
