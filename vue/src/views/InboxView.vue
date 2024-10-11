<template>
  <div class="inbox">
    <inbox ref="inboxComponent" />
  </div>
</template>

<script>
import Inbox from '../components/Inbox.vue';

export default {
  components: {
    Inbox
  },
  beforeRouteLeave(to, from, next) {
    if (this.$refs.inboxComponent) {
      this.$refs.inboxComponent.markAllAsRead()
        .then(() => {
          console.log('All messages marked as read');
          next();
        })
        .catch(error => {
          console.error('Failed to mark all messages as read', error);
          next();
        });
    } else {
      next();
    }
  },
  mounted() {
    console.log('InboxView mounted');
  }
};
</script>

<style scoped>
.inbox {
  padding-top: 12vh;
  background-color: rgb(22, 22, 22);
}
</style>
