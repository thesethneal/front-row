<template>
    <div id="outbox">
        <div id="header">
            <h2 id="title">Sent Messages</h2>
            <button id="new-message" @click="toggleNewMessage">{{ showNewMessage ? 'CANCEL' : 'NEW MESSAGE' }}</button>
        </div>
        <div v-if="showNewMessage" id="new-message-form">
            <CreateMessage id="create-message" @message-sent="handleMessageSent" @close="toggleNewMessage"></CreateMessage>
        </div>
        <div id="message-card" v-for="message in sentMessages" :key="message.messageId">
            <MessageComponent :message="message" :isRead="true"/>
        </div>
    </div>
</template>

<script>
import MessageComponent from "../components/MessageComponent.vue";
import CreateMessage from "../components/CreateMessage.vue";
import MessageService from "../services/MessageService.js";


export default {
    components: {
        MessageComponent,
        CreateMessage
    },
    data() {
        return {
            messages: [],
            managedBands: [],
            showNewMessage: false,
            sentMessages: []
        };
    },
    created() {
        this.fetchSentMessages();
    },
    methods: {
        toggleNewMessage() {
            this.showNewMessage = !this.showNewMessage;
        },

        handleMessageSent() {
            this.toggleNewMessage();
            this.fetchSentMessages();
        },

        fetchSentMessages() {
            MessageService.getUserOutbox().then(response => {
                this.sentMessages = response.data;
            })
        }
    }
}

</script>

<style scoped>
#message-card {
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    flex-basis: 100%;
    width: 100%;
    color: rgba(255, 255, 255, 0.743);
    font-family: 'Trebuchet MS', 'Lucida Sans Unicode', 'Lucida Grande', 'Lucida Sans', Arial, sans-serif;
}

#new-message-form {
    display: flex;
    flex-direction: row;
    align-content: center;
    justify-content: center;
    width: 100%;
    padding: 5px;
}

#create-message {
    display: flex;
    flex-direction: column;
    align-content: center;
    justify-content: center;
    width: 50%;
}

#outbox {
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    width: 100%;
    padding: 5px;
}

#new-message {
    background-color: white;
    color: rgb(27, 27, 27);
    padding: 10px 20px;
    /* Consistent padding */
    margin: 0.5em;
    /* Spacing between buttons */
    border: none;
    /* No border */
    border-radius: 10px;
    /* Rounded corners */
    cursor: pointer;
    /* Pointer on hover */
    font-size: 1rem;
    /* Standard font size */
    transition: background-color 0.3s, transform 0.2s;
    /* Smooth transition */
}

#new-message:hover {
    background-color: #aaa;
    /* Lighter shade on hover */
    transform: translateY(-1px);
    /* Lift effect on hover */
}

#header {
    margin-top: 10vh;
    display: flex;
    flex-direction: column;
    justify-content: space-between;
    align-items: center;
    width: 100%;
    padding: 5px;
}
</style>