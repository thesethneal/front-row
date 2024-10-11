<template>
  <div id="manager-view">
    <outbox id="outbox"></outbox>
    <ManagedBands id="managed-bands"></ManagedBands>
  </div>
    
</template>

<script>
import Outbox from '../components/Outbox.vue';
import BandService from '../services/BandService';
import ManagedBands from '../components/ManagedBands.vue';

export default {
  components: {
      Outbox,
      ManagedBands
  },
  data() {
    return {
      showCreateBand: false,
      managedBands: []
    }
  },
  created() {
    this.fetchManagedBands();
  },
  methods: {
    fetchManagedBands() {
      BandService.getManagedBands().then(response => {
        this.managedBands = response.data;
      })
      .catch(error => {
        console.error(error);
      });
    },
  }
}
</script>

<style scoped>
#manager-view {
  display: grid;
  grid-template-columns: 1fr 2fr;
  grid-template-areas: 
    "outbox managed-bands";
  padding: 20px;
}

#managed-bands {
  grid-area: managed-bands;
  margin: 5px;
  border-radius: 20px;
  display: flex;
  flex-wrap: wrap;
  justify-content: center;
  align-items: flex-start;
}

#outbox {
  grid-area: outbox;
}

</style>