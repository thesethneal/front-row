<template>
  <div class="search">
    <div id="search-results"> 
      <BandSearch class="anonymous" id="bands" :searchQuery="searchQuery" :bands="bands" :selectedGenres="selectedGenres"/>
    </div>
  </div>
</template>

<script>
import BandSearch from '../components/BandSearch.vue';

export default {
  components: {
    BandSearch,
  },
  props: ['searchQuery'],
  data() {
    return {
      bands: [], 
      filteredBands: []
    };
  }
};
</script>


<style scoped>
.search {
  padding-top: 12vh;
  padding-right: 2rem;
  padding-left: 2rem;
  background-color: rgb(22, 22, 22);
  
}

#bands {
    justify-content: center;
}

#search-results {
  margin: 0.5em;
  display: flex;
  flex-wrap: wrap;
  justify-content: center;
  align-items: flex-start;
}

#genre-heading {
  text-align: center;
  margin-bottom: 1rem;
}

h3 {
  color: #FFF;
  text-align: start;
  font-family: Montserrat;
  font-size: 28px;
  font-style: normal;
  font-weight: 500;
  line-height: normal;
}

</style>

