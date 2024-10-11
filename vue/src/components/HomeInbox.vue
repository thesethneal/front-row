<template>
  <div id="inbox">
    <div id="header">
      <h2 id="title">Inbox</h2>
    </div>
    <div id="message-card" v-for="message in filteredMessages" :key="message.message.messageId">
      <MiniMessageComponent :message="message" :isRead="checkIfMessageIsRead(message)" />
    </div>
  </div>
</template>

<script>
import MessageService from "../services/MessageService.js";
import BandService from "../services/BandService.js";
import MiniMessageComponent from "../components/MiniMessageComponent.vue";

export default {
  components: {
    MiniMessageComponent
  },
  data() {
    return {
      messages: [],
      followedBands: [],
      selectedBand: 'all',
      selectedSort: 'newest',
      pollInterval: null
    };
  },
  computed: {
    filteredMessages() {

      if (!this.followedBands.length || !this.messages.length) {
        return [];
      }

      const currentTime = new Date();

      return this.messages.filter(message => {
        const expirationTime = new Date(message.message.messageTimeExpiration);
        const notExpired = expirationTime > currentTime;
        const isBandFollowed = this.followedBands.some(band => band.band.bandName === message.bandName);
        const bandMatches = this.selectedBand === 'all' || message.bandName === this.selectedBand;

        return notExpired && bandMatches && isBandFollowed;
      });
    },
  },
  created() {
    this.fetchFollowedBands().then(() => {
      return this.fetchInboxMessages();
    }).catch(error => {
      console.error(error);
    });
  },
  methods: {
    handleMarkAsRead(messageId) {
      const messageIndex = this.messages.findIndex(message => message.message.messageId === messageId);
      if (messageIndex !== -1) {
        this.$set(this.messages, messageIndex, { ...this.messages[messageIndex], isRead: true });
      }
      MessageService.markAsRead(messageId).then(() => {
        this.fetchInboxMessages();
        this.$emit('messages-updated');
      })
        .catch(error => {
          console.error(error);
        });
    },

    fetchInboxMessages() {
      return MessageService.getUserInbox().then(response => {
        this.messages = response.data;
        console.log(response.data);

      })
        .catch(error => {
          console.error(error);
        });
    },

    fetchFollowedBands() {
      return BandService.getFollowedBands().then(response => {
        this.followedBands = response.data;
      })
        .catch(error => {
          console.error(error);
        });
    },

    handleMessageReceived() {
      this.fetchInboxMessages();
    },

    checkIfMessageIsRead(targetMessage) {
      return this.messages.some(message => message.message.messageId === targetMessage.message.messageId && message.isRead);
    }
  }
}
</script>

<style scoped>
#inbox {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  width: 100%;
  font-family: 'Trebuchet MS', 'Lucida Sans Unicode', 'Lucida Grande', 'Lucida Sans', Arial, sans-serif;
  padding: 5px;
  align-self: flex-start;
}

#message-card {
  width: 100%;
  padding: 5px;
}

#header {
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  align-items: center;
  width: 100%;
  padding: 5px;
}

#title {
  align-items: flex-start;
  justify-content: flex-start;

}
</style>