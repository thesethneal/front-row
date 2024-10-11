<template>
  <div class="profile">
    <Profile></Profile>
  </div>
</template>

<script>
import Profile from '../components/Profile.vue'

export default {
  components: {
    Profile
  }
};
</script>

<style scoped>
  .profile {
    font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
    display: grid;
    grid-template-columns: 
      1fr;
  }
</style>
