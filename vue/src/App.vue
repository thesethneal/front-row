<template>
  <div id="capstone-app">
    <div id="nav">
      <div id="left-nav">
        <div class="nav-item">
          <img src="src/assets/frlogo.png" alt="Front Row Logo" @click="this.$router.push('/')">
          <router-link v-bind:to="{ name: 'home' }" id="home-button">Home</router-link>
        </div>
        <div class="nav-item">
            <div id="searchbox-wrapper">&nbsp;🔎 <input id="searchbox" type="text" placeholder="SEARCH" @focus="startSearch" v-model="searchQuery" /></div>
        </div>
      </div>
      <div id="right-nav">
        <div class="nav-item">
          <router-link v-bind:to="{ name: 'profile' }" v-if="$store.state.token != ''" id="profile-button">Profile</router-link>
        </div>
        <div class="nav-item">
          <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''" id="logout-button">Logout</router-link>
        </div>
      </div>
    </div>
    <router-view :searchQuery="searchQuery"/>
  </div>
</template>

<script>
import { useRouter } from 'vue-router';

export default {
  components: {

  },
  data() {
    const router = useRouter();
    return {
      router,
      searchQuery: ''
    };
  },
  methods: {
    startSearch() {
      if(this.$store.state.token != ''){
        this.router.push('/search')
      }
      else this.router.push('/discover')
    },
    // NEW METHOD: Clear search query
    clearSearchQuery() {
      this.searchQuery = '';
    }
  },
  created() {
    // NEW: Add navigation guard to clear search query before each route change
    this.router.beforeEach((to, from, next) => {
      this.clearSearchQuery();
      next();
    });
  }
};


</script>

<style scoped>
  #capstone-app{
    background-color: rgb(22, 22, 22);
    min-height: 100vh;
    display: flex;
    flex-direction: column;
  }

  #nav{
    position: fixed;
    
    display: flex;
    justify-content: space-evenly;
    align-items: center;

    background-color: white;
    width: 100%;
    height: 6vh;
    z-index: 1000;
  }

  .nav-item {
    display: flex;
    align-items: center;
    padding: 20px;
    box-sizing: border-box;
  }

  #left-nav {
    width: 50%;
    display: flex;
    justify-content: flex-start;
  }

  #right-nav {
    width: 50%;
    display: flex;
    justify-content: flex-end;
  }

  #home-button{
    color: black;
    font-family: Montserrat;
    font-size: 20px;
    font-weight: 600;
    text-decoration-line: none;
  }

  #logout-button {
    color: black;
    font-family: Montserrat;
    font-size: 20px;
    font-weight: 600;
    text-decoration-line: none;
  }

  #searchbox {
    outline: none;  
    border: none;
    width: 100%; 
    background: transparent; 
  }

  #searchbox-wrapper {
    display: flex;
    width: 20vw;
    height: 2em;
    border: 2px solid black;
    border-radius: 16px;
    padding: 10px; 
    align-items: center; 
    box-sizing: border-box; 
  }

  #searchbox::placeholder {
    color: #999; 
    font-size: 14px; 
  }

  #profile-button {
    color: black;
    font-family: Montserrat;
    font-size: 20px;
    font-weight: 600;
    align-content: center;
    text-decoration-line: none;
  }

  img {
    height: 3vh;
    object-fit: fill;
    padding-right: 12px;
  }
  

</style>
