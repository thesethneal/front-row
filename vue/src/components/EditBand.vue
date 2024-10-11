<template>
  <div class="band-view">
    <div v-if="band" class="page-setup">
      <button @click="cancel()" id="cancel-button">Back</button>
      <h2>Edit {{ bandName }} Fan Page</h2>
      <form @submit.prevent="updateItem" class="edit-form">
        <label for="bandName" class="form-label">Band Name</label>
        <input 
          type="text" 
          id="bandName"
          class="input-field" 
          :placeholder="band?.band.bandName" 
          v-model="editedBand.band.bandName" 
        />
        
        <label for="bandDescription" class="form-label">Band Description</label>
        <textarea 
          id="bandDescription"
          class="textarea-field"
          :placeholder="band.band.bandDescription" 
          v-model="editedBand.band.bandDescription">
        </textarea>
        
        <div class="image-section">
          <label for="heroImage" class="form-label">Image</label>
          <img :src="band.band.bandHeroImage" alt="Band Hero Image" class="hero-image" id="heroImage">
          <image-upload-vue 
            :admin="false" 
            v-model="editedBand.band.bandHeroImage">
          </image-upload-vue>
        </div>
        
        <button id="show-genres" @click="toggleGenres">Edit Genres</button> <!-- Add button to toggle genre list/selection -->
        <div class="genre-list" v-if="editGenres">
          <label class="form-label">Genres</label>
          <GenreSearch 
            class="edit" 
            @update:selectedGenres="updateSelectedGenres"
            :initialSelectAll="false"
          />
        </div>
        <div v-else>
          <br><br>
        </div>
        
        <button type="submit" class="save-button" @click="updateBand">Save</button>
        <button @click="cancel()" id="cancel-button">Cancel</button>
      </form>
    </div>
    <div v-else>
      <p>Loading...</p>
    </div>
  </div>
</template>

<script>
import BandService from '../services/BandService';
import ImageUploadVue from './ImageUpload.vue';
import GenreSearch from './GenreSearch.vue';

export default {
  components: {
      ImageUploadVue,
      GenreSearch
  },
  data() {
    return {
      band: '',
      editedBand: '', 
      bandName: this.$route.params.bandName,
      BandService,
      newImageUrl: '',
      editGenres: false
    }
  },
  created() {
    this.getBand();
  },
  methods: {
    getBand() {
      console.log(this.band);
      console.log(this.editedBand);
      this.BandService.getBand(this.bandName)
        .then(response => {
          this.band = response.data;
          this.editedBand = JSON.parse(JSON.stringify(this.band));

        }).catch(error => {
          console.error(error);
        });
    
    },
    setImage(data){
      this.editedBand.band.bandHeroImage = data;
    },
    updateSelectedGenres(genres) {
      this.editedBand.genreNames = genres;
    },
    updateBand(){
      console.log(this.editedBand);
      console.log(this.band);
      this.band = this.editedBand;
      BandService.updateBand(this.band.band.bandId, this.band)
        .then(response => {
          this.$router.push(`/${this.band.band.bandName}`)
        })
        .catch(error => {
          console.error(error);
        });
    },
    cancel() {
      console.log(this.band);
      console.log(this.editedBand);
      this.$router.push(`/${this.bandName}`)
    },
    toggleGenres() {
      this.editGenres = !this.editGenres;
    }
  }
}
</script>

<style scoped>
.edit-form {
  padding: 30px;
}

body {
  font-family: 'Montserrat', sans-serif;
  background-color: rgb(43, 43, 43);
  color: white;
  filter: grayscale(100%);
}

.band-view {
  display: flex;
  justify-content: center;
  align-items: center;
  padding: 40px;
}

.page-setup {
  width: 90%;
  max-width: 1200px;
  background-color: #2c2c2c;
  padding: 30px;
  border-radius: 8px;
  box-shadow: 0px 4px 10px rgba(0, 0, 0, 0.4);
}

h2 {
  color: white;
  margin-bottom: 20px;
  font-size: 28px;
}

.form-label {
  font-weight: bold;
  margin-bottom: 5px;
  display: block;
  color: #e0e0e0;
}

.input-field,
.textarea-field {
  width: 97%;
  padding: 12px;
  margin-bottom: 20px;
  border: 1px solid #777;
  background-color: #444;
  color: white;
  border-radius: 4px;
}

.textarea-field {
  height: 120px;
}

.image-section {
  margin-bottom: 30px;
}

.hero-image {
  width: 100%;
  height: 500px;
  object-fit: cover;
  justify-content: center;
  align-items: center;
  margin-bottom: 10px;
  border-radius: 4px;
}

.genre-list {
  margin-bottom: 30px;
}

.save-button, #cancel-button {
  background-color: #5a5a5a;
  color: white;
  padding: 12px 24px;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  font-size: 18px;
  transition: background-color 0.3s;
}

#show-genres {
  padding: 10px 20px;
  background-color: #999999;  /* Light grey */
  color: white;               /* White text */
  border: none;               /* Remove default border */
  border-radius: 5px;         /* Rounded corners */
  font-size: 16px;            /* Font size */
  cursor: pointer;            /* Pointer on hover */
  transition: background-color 0.3s ease; /* Smooth transition */
  margin-bottom: 1em;
  margin-left: 1rem;
}

#show-genres:hover {
  background-color: #808080;  /* Darker light grey on hover */
}

.save-button:hover, #cancel-button:hover {
  background-color: #6f6f6f;
}

#cancel-button {
  margin-left: 10px;
}

.loading {
  font-size: 20px;
  color: #bbb;
}

input, textarea {
  font-family: 'Montserrat', sans-serif;
}

</style>