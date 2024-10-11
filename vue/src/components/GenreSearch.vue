<template>
    <div :class="['genre-search', customClass]">
      <div class="genres">
        <label>
          <input 
            type="checkbox" 
            v-model="selectAll" 
            @change="toggleSelectAll"
          /> Select All
        </label>
      </div>
      <div class="genre-list"> 
        <label v-for="genre in visibleGenres" :key="genre">
          <br />
          <input 
            type="checkbox" 
            v-model="selectedGenres" 
            :value="genre.genreName" 
            @change="updateSelectedGenres" 
          /> <p style="text-transform: capitalize; color: black;">{{ genre.genreName }}</p>
        </label>
      </div>
    </div>
  </template>
  
<script>
import BandService from '../services/BandService';
import GenreService from '../services/GenreService';

export default {
  props: {
    customClass: {
      type: String,
      default: ''
    },
    initialSelectAll: {
      type: Boolean,
      default: true
    }
  },
  data() {
    return {
      genres: [], 
      visibleGenres: [],
      selectedGenres: [],
      selectAll: this.initialSelectAll 
    };
  },
  methods: {
    fetchGenres() {
      BandService.getGenres().then(response => {
        this.genres = response.data;
        if (this.initialSelectAll) {
          this.selectedGenres = this.genres.map(genre => genre);
        }
        this.updateSelectedGenres();
      });
      GenreService.getGenresToManage().then(response => {
        this.visibleGenres = response.data
        this.visibleGenres = this.visibleGenres.filter(item => item.visible)
      }
      )
    },
    toggleSelectAll() {
      this.selectedGenres = this.selectAll ? this.genres.map(genre => genre) : [];
      this.updateSelectedGenres();
    },
    updateSelectedGenres() {
      this.$emit('update:selectedGenres', this.selectedGenres);
    }
  },
  watch: {
    selectedGenres(newVal) {
      this.selectAll = newVal.length === this.genres.length;
    }
  },
  created() {
    this.fetchGenres();
    BandService.getGenres().then(response => {
          this.genres = response.data 
      })
      .catch(error => {
          console.error(error);
      });
  }
}
</script>

<style scoped>
.genre-search {
    font-family: Montserrat, sans-serif;
    color: #1a1a1a; /* Darker grey for text */
    background-color: #e6e6e6; /* Very light grey background */
    padding: 25px;
    border-radius: 10px;
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1); /* Soft shadow for depth */
    margin: 0 auto;
}

.genres label {
    font-size: 18px;
    color: #1a1a1a; /* Darker grey for "Select All" */
    font-weight: 600;
    display: flex;
    align-items: center;
    margin-bottom: 12px;
}

input[type="checkbox"] {
    accent-color: #666; /* Medium grey for checkbox */
    margin-right: 12px;
    transform: scale(1.2); /* Slightly larger checkbox */
}

input[type="checkbox"]:hover {
    accent-color: #333; /* Darker grey on hover */
    cursor: pointer;
}

.search-view .genre-list {
    border-top: 1px solid #b3b3b3; /* Medium grey border to separate sections */
    padding-top: 15px;
    margin-top: 15px;
}

.search-view .genre-list label {
    display: flex;
    align-items: center;
    font-size: 16px;
    color: #333;
    padding: 8px 0;
    border-bottom: 1px solid #ccc; /* Light grey separator between genres */
}

.search-view .genre-list label:last-of-type {
    border-bottom: none; /* Remove bottom border for the last item */
}

.create-view .genre-list {
  display: flex;
  flex-wrap: wrap;
  gap: 8px; /* Adjusted spacing between genre items */
  margin-top: 10px;
  justify-content: center; /* Center align genres */
}

.create-view .genre-list label {
  display: flex;
  align-items: center;
  justify-content: center;
  background-color: #f0f0f0; /* Light background */
  border: 1px solid #d3d3d3; /* Subtle border */
  border-radius: 8px; /* Rounded corners */
  padding: 6px 10px; /* Spacing within each genre item */
  cursor: pointer;
  transition: background-color 0.3s, transform 0.2s;
  box-sizing: border-box;
}

.create-view .genre-list label:hover {
  background-color: #e0e0e0; /* Slightly darker on hover */
  transform: scale(1.02); /* Small scaling effect */
}

.create-view .genre-list input[type="checkbox"] {
  margin-right: 8px; /* Space between checkbox and label */
}

/* Media query for smaller screens (mobile) */
@media (max-width: 768px) {
  .create-view .genre-list label {
    flex: 0 1 calc(100% - 8px); /* Single-column layout */
    max-width: calc(100% - 8px);
  }
}

/* Media query for mid-sized screens (tablet) */
@media (max-width: 1140px) and (min-width: 768px) {
  .create-view .genre-list label {
    flex: 0 1 calc(50% - 8px); /* Two-column layout */
    max-width: calc(50% - 8px);
  }
}

/* Media query for larger screens (desktop) */
@media (max-width: 1470px) and (min-width: 1140px) {
  .create-view .genre-list label {
    flex: 0 1 calc(33% - 8px); /* Three-column layout */
    max-width: calc(33% - 8px);
  }
}

/* Media query for extra-large screens (desktop widescreen) */
@media (min-width: 1470px) {
  .create-view .genre-list label {
    flex: 0 1 calc(25% - 8px); /* Four-column layout */
    max-width: calc(25% - 8px); /* Adjust max-width for four columns */
  }
}


.edit .genre-list {
  display: flex;
  flex-wrap: wrap;
  gap: 8px; /* Adjusted spacing between genre items */
  margin-top: 10px;
  justify-content: center; /* Center align genres */
}

.edit .genre-list label {
  display: flex;
  align-items: center;
  justify-content: center;
  background-color: #f0f0f0; /* Light background */
  border: 1px solid #d3d3d3; /* Subtle border */
  border-radius: 8px; /* Rounded corners */
  padding: 6px 10px; /* Spacing within each genre item */
  cursor: pointer;
  transition: background-color 0.3s, transform 0.2s;
  flex: 0 1 calc(25% - 8px); /* Responsive four-column layout */
  max-width: calc(25% - 8px); /* Adjust max-width for four columns */
  box-sizing: border-box;
}

.edit .genre-list label:hover {
  background-color: #e0e0e0; /* Slightly darker on hover */
  transform: scale(1.02); /* Small scaling effect */
}

.edit .genre-list input[type="checkbox"] {
  margin-right: 8px; /* Space between checkbox and label */
}

/* Media query for smaller screens */
@media (max-width: 768px) {
  .edit .genre-list label {
    flex: 0 1 calc(100% - 8px); /* Single-column layout on smaller screens */
    max-width: calc(100% - 8px);
  }
}

</style>