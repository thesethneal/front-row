<template>
  <div id="profile">
    <h2 id="header">Profile</h2>  
    <div id="profile-section">
      <h3>Email:</h3>
      <h4 id="username">Email:</h4>{{ user.username }}
      <h4 id="phone-number">Phone number:</h4>{{ user.phoneNumber }}
      <form id="update-form" v-if="showUpdateForm" @submit.prevent="updatePhoneNumber">
        <input type="text" pattern="\d{3}-\d{3}-\d{4}" title="Phone number format: 123-456-7890" v-model.lazy="phoneNumber" placeholder="000-000-0000">
        <button id="submit-button" type="submit">Update</button>
      </form>
      <button id="update-button" @click="toggleUpdateForm">{{ showUpdateForm ? 'Cancel' : 'Update Phone Number' }}</button>
    </div>

    <div id="stats-section">
      <h3>Following: {{ followedBands.length }} bands</h3>
      <p v-for="(count, genre) in followedByGenre" :key="genre" style="text-transform: capitalize; margin-top: 0.5em;">{{ genre }}: {{ count }}</p>
    </div>
    <div id="admin">
      <button v-if="isAdmin" @click="this.$router.push('/admin')">Admin Panel</button>
    </div>
  </div>
</template>

<script>
import BandService from '../services/BandService';
import UserService from '../services/UserService';

export default {
  data() {
    return {
      followedBands: [],
      user: {},
      genres: [],
      followedByGenre: {},
      showUpdateForm: false,
      isAdmin: '',
      phoneNumber: ''
    }
  },
  mounted() {
    this.fetchFollowedBands();
    this.fetchUser();
    this.fetchGenres();
    this.checkRole();
  },
  methods: {
    fetchFollowedBands() {
      BandService.getFollowedBands().then(response => {
        this.followedBands = response.data;
        this.getFollowedByGenre();
      })
        .catch(error => {
          console.error(error);
        });
    },

    fetchUser() {
      UserService.getUser().then(response => {
        this.user = response.data;
      })
        .catch(error => {
          console.error(error);
        });
    },

    fetchGenres() {
      BandService.getGenres().then(response => {
        this.genres = response.data;
      });
    },

    getFollowedByGenre() {
      let count = {};
      this.followedBands.forEach(band => {
        let genres = band.genreNames;

        genres.forEach(genre => {
          genre = genre.trim();

          if (count[genre]) {
            count[genre]++;
          } else {
            count[genre] = 1;
          }

        });
      });

      this.followedByGenre = count;
    },

    updatePhoneNumber() {
      this.user.phoneNumber = this.phoneNumber;
      this.phoneNumber = '';
      this.showUpdateForm = false;
      
      UserService.updateUserPhoneNumber(this.user.phoneNumber).then(response => {
        console.log(response);
      })
      .catch(error => {
        console.error(error);
      });
    },

    toggleUpdateForm() {
      this.showUpdateForm = !this.showUpdateForm;
    },

    checkRole() {
      UserService.getRole().then(response => {
        if (response.data.role == 'ROLE_ADMIN') {
          this.isAdmin = true
        }
      })
    }

  }
}
</script>

<style scoped>
#profile {
  margin: 4rem;
  margin-top: 6vh;
  color: white;
  display: grid;
  grid-template-columns: 3fr 1fr 3fr;
  grid-template-areas:
    "header header header"
    "profile-section . stats-section"
    "admin admin admin";
}

#header {
  grid-area: header;
  margin: 1em;
  justify-content: center;
}

#profile-section {
  grid-area: profile-section;
  display: flex;
  flex-direction: column;
  align-items:flex-start;
  margin-left: 50%;
}

#username {
  margin-top: 1em;
}

#phone-number {
  margin-top: 1em;
}

#update-form {
  margin: 1em;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}

#submit-button {
  margin: 1em;
  color: black;
  border-radius: 20px;
  align-content: center;
  justify-content: center;
}

button {
  margin-top: 1em;
  color: black;
  border-radius: 20px;
  justify-content: center;
  width: fit-content;
  min-width: 120px;
  height: fit-content;
  min-height: 30px;
}

#stats-section {
  grid-area: stats-section;
  display: flex;
  flex-direction: column;
  align-items:flex-start;
}

#admin {
  grid-area: admin;
  margin-top: 0;
  margin-left: 10vw;
}


</style>