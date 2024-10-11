import axios from 'axios';

export default {
  getUserInbox() {
    return axios.get('/inbox');
  },

  markAsRead(messageId) {
    return axios.put(`/inbox/${messageId}/read`);
  },

  markAllAsRead() {
    return axios.put('/inbox/read-all');
  },

  sendMessage(message) {
    return axios.post('/send-message', message);
  },

  getUserOutbox() {
    return axios.get('/outbox');
  }

}
