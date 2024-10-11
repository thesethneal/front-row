<template>
  <div id="bands">
    <div id="managed-bands-container">
      <h2>My Bands</h2>

      <div id="managed-bands">
        <div id="add-band" @click="showCreateBand = true">
          <img id="plus-sign" src="../assets/plus-sign-icon-2048x2048-mp0pz4g8.png" alt="Add Band" />
          <h4>MANAGE A NEW BAND</h4>
        </div>
        <BandComponent v-for="band in managedBands" :key="band.bandId" :band="band" />
      </div>

    </div>

    <div v-if="showCreateBand" id="modal-overlay">
      <div id="modal-content">
        <CreateBand id="create-band" @close="closeModal" @band-created="bandCreated" />
      </div>
    </div>

  </div>
</template>

<script>
import BandService from '../services/BandService';
import MessageService from '../services/MessageService';
import BandComponent from './BandComponent.vue';
import CreateBand from './CreateBand.vue';

export default {
  components: {
    BandComponent,
    CreateBand
  },
  data() {
    return {
      followedBands: [],
      messages: [],
      showCreateBand: false,
      managedBands: []
    };
  },
  created() {
    this.fetchFollowedBands();
    this.fetchMessages();
    this.fetchManagedBands();
  },
  methods: {
    fetchFollowedBands() {
      BandService.getFollowedBands().then(response => {
        this.followedBands = response.data;
        console.log(this.followedBands);
      })
        .catch(error => {
          console.error(error);
        });
    },
    fetchManagedBands() {
      BandService.getManagedBands().then(response => {
        this.managedBands = response.data;
      })
        .catch(error => {
          console.error(error);
        });
    },
    searchView() {
      this.$router.push('/search')
    },
    fetchMessages() {
      MessageService.getUserInbox().then(response => {
        this.messages = response.data;
        console.log(this.messages);
      })
        .catch(error => {
          console.error(error);
        });
    },
    closeModal() {
      this.showCreateBand = false;
    },
    bandCreated() {
      this.showCreateBand = false;
      this.fetchManagedBands();
    },
  }

};
</script>

<style scoped>
#managed-bands {
  display: flex;
  flex-direction: row;
  flex-wrap: wrap;
  justify-content: center;
  width: 100%;
  max-width: 1200px;
  align-self: flex-start;
}

#managed-bands-container {
  display: flex;
  flex-direction: column;
  align-items: center;
  width: 100%;
}

#managed-bands-container h2 {
  text-align: center;
  width: 100%;
  margin-bottom: 20px;
}

#add-band {
  width: 200px;
  height: 200px;
  position: relative;
  overflow: hidden;
  margin: 1em;
  border-radius: 20px;
  z-index: 1;
  background-color: darkgray;
  display: flex;
  flex-direction: column;
  justify-content: space-evenly;
  align-items: center;
  font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
  font-size: 1em;
}

#plus-sign {
  width: 50%;
  height: 50%;
}

#modal-overlay {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.5);
  backdrop-filter: blur(5px);
  display: flex;
  justify-content: center;
  align-items: center;
  z-index: 10;
}

#modal-content {
  background-color: rgba(240, 34, 27, 0.925);
  padding: 20px;
  border-radius: 5px;
  width: 50%;
  height: 70%;
  overflow: scroll;
}
</style>