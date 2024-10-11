<template>
    <div id="outbox">
        <div id="header">
            <h2 id="title">Sent Messages</h2>
            <div id="buttons">
                <button id="new-message" @click="toggleNewMessage">{{ showNewMessage ? 'CANCEL' : 'NEW MESSAGE'
                    }}</button>
            </div>
        </div>
        <div v-if="showNewMessage" id="new-message-form">
            <CreateMessage @message-sent="handleMessageSent" @close="toggleNewMessage"></CreateMessage>
        </div>
        <div id="message-card" v-for="message in limitedMessages" :key="message.messageId" @click="outboxView">
            <mini-message-component :message="message" :isRead="true" />
        </div>
        <button id="to-outbox" @click="outboxView">SEE ALL SENT MESSAGES</button>

    </div>
</template>

<script>
import CreateMessage from "../components/CreateMessage.vue";
import MessageService from "../services/MessageService.js";
import MiniMessageComponent from './MiniMessageComponent.vue';


export default {
    components: {
        CreateMessage,
        MiniMessageComponent
    },
    data() {
        return {
            messages: [],
            managedBands: [],
            showNewMessage: false,
            sentMessages: [],
            messageLimit: 3
        };
    },
    created() {
        this.fetchSentMessages();
    },
    computed: {
        limitedMessages() {
            return this.sentMessages.slice(0, this.messageLimit);
        }
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
        },
        outboxView() {
            this.$router.push('/outbox');
        },
    }
}

</script>

<style scoped>
#message-card {
    width: 100%;
    padding: 5px;
}

#new-message-form {
    display: flex;
    flex-direction: row;
    align-content: center;
    justify-content: center;
    width: 100%;
    padding: 5px;
}

#outbox {
    display: flex;
    flex-direction: column;
    align-content: center;
    align-items: center;
    width: 100%;
    font-family: 'Trebuchet MS', 'Lucida Sans Unicode', 'Lucida Grande', 'Lucida Sans', Arial, sans-serif;
    padding: 5px;
    align-self: flex-start;
}

#new-message,
#to-outbox {
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

#new-message:hover,
#to-outbox:hover {
    background-color: #aaa;
    /* Lighter shade on hover */
    transform: translateY(-1px);
    /* Lift effect on hover */
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