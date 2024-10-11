<template>
  <div class="results">
    <div :class="['band-results-grid', customClass]" v-if="filteredBands.length > 0">
      <BandComponent 
        v-for="band in filteredBands" 
        :key="band.bandId" 
        :band="band" 
        :hasMessage="false" 
      />
    </div>
    <div v-else>
      <br><br><br>
      <p id="no-bands">No Bands Found</p>
    </div>
  </div>
</template>

<script>
import BandService from '../services/BandService';
import BandComponent from './BandComponent.vue';

export default {
  props: {
    searchQuery: {
      type: String,
      required: false
    },
    selectedGenres: {
      type: Array,
      required: false
    },
    customClass: {
      type: String,
      default: ''
    }
  },
  components: {
    BandComponent
  },
  data() {
    return {
      bands: [],
      filteredBands: [],
      BandService
    }
  },
  created() {
    this.fetchBands();
  },
  methods: {
    fetchBands() {
      BandService.getBands()
        .then(response => {
          this.bands = response.data;
          this.filterBands();
        })
        .catch(error => {
          console.error(error);
        });
    },
    filterBands() {
      let filteredBands = this.bands || [];

      if (this.searchQuery) {
        const search = this.searchQuery.toLowerCase();

        filteredBands = this.bands.filter(band => {
          const bandName = band.band.bandName || '';
        
          return (
            bandName.toLowerCase().includes(search)
          );
        });
      }

      if (this.selectedGenres && this.selectedGenres.length > 0) {
        filteredBands = filteredBands.filter(band => {
          
          return this.selectedGenres.some(genre => {
            return band.genreNames.includes(genre)
          });
          
        });
      } else if (this.selectedGenres && this.selectedGenres.length === 0) {
        filteredBands = [];
      }

      filteredBands.sort((a, b) => {
        const stripThe = name => name.toLowerCase().replace(/^the\s+/i, '');
        const nameA = stripThe(a.band.bandName);
        const nameB = stripThe(b.band.bandName);
        if (nameA < nameB) return -1;
        if (nameA > nameB) return 1;
        return 0;
      });

      this.filteredBands = filteredBands;
    }
  },
  watch: {
    searchQuery() {
      this.filterBands();
    },
    selectedGenres: {
      handler() {
        this.filterBands();
      },
      deep: true
    }
  }
};
</script>

<style scoped>
.anonymous .band-results-grid {
  margin: 10px;
  margin-top: 2rem;
  border-radius: 20px;
  display: flex;
  flex-wrap: wrap;
  justify-content: center;
  align-items: flex-start;
  z-index: 1;
}

.known .band-results-grid {
  margin: 5px;
  margin-top: 2rem;
  border-radius: 20px;
  display: flex;
  flex-wrap: wrap;
  align-items: flex-start;
  z-index: 1;
}

#no-bands {
  font-family: Montserrat, sans-serif;
  color: #1a1a1a; /* Darker grey for text */
  background-color: #e6e6e6; /* Very light grey background */
  padding: 25px;
  border-radius: 10px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1); /* Soft shadow for depth */
  max-width: 420px;
  margin: 0 auto;
  justify-content: center;
  align-items: flex-start;
  font-weight: bold;
}


</style>