<template>
  <div id="bands">
    <div id="followed-bands-container">
      <h2>Following</h2>

      <div id="followed-bands">
        <div id="add-band" @click="searchView">
          <img id="plus-sign" src="../assets/plus-sign-icon-2048x2048-mp0pz4g8.png" alt="Add Band" />
          <h4>FOLLOW A NEW BAND</h4>
        </div>
        <BandComponent v-for="band in followedBands" :key="band.bandId" :band="band"
          :hasMessage="checkIfBandHasUnreadMessage(band)" />
      </div>

    </div>
  </div>
</template>

<script>
import BandService from '../services/BandService';
import MessageService from '../services/MessageService';
import BandComponent from './BandComponent.vue';

export default {
  components: {
    BandComponent,
  },
  data() {
    return {
      followedBands: [],
      messages: []
    };
  },
  created() {
    this.fetchFollowedBands();
    this.fetchMessages();
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
    checkIfBandHasMessage(band) {
      return this.messages.some(message => message.message.messageSender === band.band.bandId);
    },
    checkIfBandHasUnreadMessage(band) {
      // const readMessages = JSON.parse(localStorage.getItem('readMessages')) || [];

      // return this.messages.some(message => message.message.messageSender === band.band.bandId && !readMessages.includes(message.message.messageId));

      return this.messages.some(message => message.message.messageSender === band.band.bandId && !message.isRead);

    }
  }

};
</script>

<style scoped>
#followed-bands {
  display: flex;
  flex-direction: row;
  flex-wrap: wrap;
  justify-content: center;
  width: 100%;
  max-width: 1200px;
  align-self: flex-start;

}

#followed-bands-container {
  display: flex;
  flex-direction: column;
  align-items: center;
  width: 100%;
}

#followed-bands-container h2 {
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
</style>