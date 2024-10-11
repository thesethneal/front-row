<template>
    <main class="main-container">
        <header>
            <h2 id="title">Create Event</h2>
        </header>
        <div class="content">
            <label for="event-date">Event Date</label>
            <input type="date" id="event-date" name="event-date" v-model="eventDate">

            <label for="event-time">Event Time</label>
            <input type="time" id="event-time" name="event-time" v-model="eventTime">

            <label for="event-venue">Event Venue</label>
            <input type="text" id="event-venue" name="event-venue" v-model="event.eventVenue">

            <label for="event-address">Event Address</label>
            <input type="text" id="event-address" name="event-address" v-model="event.eventAddress">

            <label for="event-name">Event Name (Optional)</label>
            <input type="text" id="event-name" name="event-name" v-model="event.eventName">

            <button @click="addEvent()" id="create-event-button">CREATE EVENT</button>
            
        </div>
    </main>
</template>

<script>
import BandService from '../services/BandService';

export default {
    data() {
        return {
            eventDate: '',
            eventTime: '',
            event: {
                eventTime: '',
                eventVenue: '',
                eventAddress: '',
                eventHost: '',
                eventName: '',
            },
            bandName: this.$route.params.bandName,
            bandId: null,
        }
    },
    created() {
        this.fetchBand();
    },
    methods: {
        fetchBand() {
            BandService.getBand(this.bandName).then(response => {
                this.bandId = response.data.band.bandId;
            })
                .catch(error => {
                    console.error(error);
                });
        },
        addEvent() {
            const formattedEventTime = `${this.eventDate}T${this.eventTime}:00`;
            this.event.eventTime = formattedEventTime;
            this.event.eventHost = this.bandId;

            BandService.addEvent({
                event: this.event,
                bandName: this.bandName,
            }).then(response => {
                console.log(response.data);
                this.$emit('event-created', response.data);
            })
                .catch(error => {
                    console.error(error);
                });
        },
    }
}
</script>

<style scoped>
.main-container {
    display: flex;
    flex-direction: column;
    background-color: rgb(27, 27, 27);
    /* Light background */
    justify-content: center;
    border: 1px solid white;
    border-radius: 20px;
    width: 90%;
    margin-top: 1em;
    margin-bottom: 1em;
    color: white;
}

.content {
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    margin: 1em;
}

label {
    color: white;
    font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
    width: 90%;
    margin: 0.2em;
}

input {
    width: 90%;
    padding: 10px;
    border: 1px solid #ccc;
    /* Light border */
    border-radius: 10px;
    /* Rounded corners */
    background-color: #f4f4f4;
    /* Light grey background for inputs */
    transition: border-color 0.3s;
    /* Smooth transition */
    font-size: 1rem;
}

button {
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

button:hover {
    background-color: #aaa;
    /* Lighter shade on hover */
    transform: translateY(-1px);
}

</style>