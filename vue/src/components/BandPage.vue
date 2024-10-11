<template>
  <div class="band-view">
    <div class="hero-section" v-if="band && roleChecked">
      <img id="heroImage" :src="band?.band?.bandHeroImage" alt="Band Hero Image">
      <div class="overlay">
        <h2 id="name">{{ bandName }}</h2>
        <section class="genres">
          <p>{{ band?.genreNames?.join(' • ') }}</p>
        </section>
        <button :class="{ 'follow-button': true, 'unfollow': isFollowing }" v-if="isFollowing !== null"
          @click="toggleFollow">
          {{ isFollowing ? 'Unfollow' : 'Follow' }}
        </button>
        <button class="edit-button" v-if="canEdit" @click="$router.push(`${bandName}/edit`)">
          Edit Page
        </button>
      </div>
    </div>

    <section class="content">
      <div v-if="band && roleChecked">
        <p id="description">{{ band?.band?.bandDescription }}</p>

        <div id="events-section">
          <button class="add-event-button" v-if="canEdit" @click="toggleCreateEvent">{{ showCreateEvent ? 'Cancel' :
      'Add Event' }}</button>
          <div v-if="showCreateEvent" id="new-event-form">
            <CreateEvent @event-created="handleEventCreated"></CreateEvent>
          </div>
          <div id="events" v-if="bandEvents.length > 0">
            <h3>Upcoming Events</h3>
            <div id="event-component-list">
              <EventComponent v-for="event in bandEvents" :key="event.event.eventId" :event="event"></EventComponent>
            </div>
          </div>
        </div>

        <section id="gallery">
          <h3 id="gallery-header">Gallery</h3>
          <image-upload v-if="canEdit === true" :admin="true" :bandName="bandName"></image-upload>
          <gallery-image :bandName="bandName" />
        </section>
      </div>
      <div v-else>
        Loading...
      </div>
    </section>
  </div>
</template>



<!-- <template>
  <div class="band-view">

    <section class="content">
      <div v-if="band && roleChecked">
        <h2 id="name">{{ bandName }}</h2>
        <button :class="{ 'follow-button': true, 'unfollow': isFollowing }" v-if="band && isFollowing !== null"
          @click="toggleFollow">
          {{ isFollowing ? 'Unfollow' : 'Follow' }}
        </button>
        <button class="edit-button" v-if="canEdit" @click="$router.push(`${bandName}/edit`)">
          Edit Page
        </button>
        <img id="heroImage" :src="band.band.bandHeroImage" alt="Band Hero Image">
        <section class="genres">
          <p>{{ band?.genreNames?.join(' • ') }}</p>
        </section>
        <br>
        <p id="description">{{ band?.band?.bandDescription }}</p>

        <div id="events-section">
          <button class="add-event-button" v-if="canEdit" @click="toggleCreateEvent">{{ showCreateEvent ? 'Cancel' :
        'Add Event' }}</button>
          <div v-if="showCreateEvent" id="new-event-form">
            <CreateEvent @event-created="handleEventCreated"></CreateEvent>
          </div>
          <div id="events" v-if="bandEvents.length > 0">
            <h3>Upcoming Events</h3>
            <div id="event-component-list">
              <EventComponent v-for="event in bandEvents" :key="event.event.eventId" :event="event"></EventComponent>
            </div>
          </div>
        </div>

        <br><br>
        <section id="gallery">
          <h3 id="gallery-header">Gallery</h3>
          <image-upload v-if="canEdit === true" :admin="true" :bandName="bandName"></image-upload>
          <gallery-image :bandName="bandName" />
        </section>
      </div>
      <div v-else>
        Loading...
      </div>
    </section>
  </div>
</template> -->

<script>
import BandService from '../services/BandService';
import GalleryImage from './GalleryImage.vue';
import UserService from '../services/UserService';
import ImageUpload from './ImageUpload.vue';
import EventComponent from './EventComponent.vue';
import CreateEvent from './CreateEvent.vue';

export default {
  components: {
    GalleryImage,
    ImageUpload,
    EventComponent,
    CreateEvent
  },
  data() {
    return {
      band: '',
      bandName: this.$route.params.bandName,
      isFollowing: null,
      BandService,
      canEdit: false,
      roleChecked: false,
      bandEvents: [],
      showCreateEvent: false
    }
  },
  mounted() {
    this.getBand();
    this.checkRole();
  },
  methods: {
    getBand() {
      this.BandService.getBand(this.bandName)
        .then(response => {
          this.band = response.data;
          console.log(this.band.band.bandDescription);
          if (this.band && this.band?.band?.bandId) {
            this.getIsFollowing(this.band.band.bandId);
            this.fetchBandEvents();
          } else {
            console.error("Band not found or missing bandId");
          }
          this.checkRole()
        })
        .catch(error => {
          console.error(error);
        });
    },
    getIsFollowing(bandId) {
      this.BandService.getIsFollowing(bandId)
        .then(response => {
          this.isFollowing = response.data;
        })
        .catch(error => {
          console.error(error);
        });
    },
    toggleFollow() {
      if (this.isFollowing) {
        this.unfollowBand();
      } else {
        this.followBand();
      }
    },
    followBand() {
      this.BandService.followBand(this.band.band.bandId)
        .then(response => {
          console.log(response);
          this.isFollowing = true;
        })
        .catch(error => {
          console.error(error);
        });
    },
    unfollowBand() {
      this.BandService.unfollowBand(this.band.band.bandId)
        .then(response => {
          console.log(response);
          this.isFollowing = false;
        })
        .catch(error => {
          console.error(error);
        });
    },
    checkRole() {
      UserService.getRole().then(response => {
        console.log(response.data);
        if (response.data.role == 'ROLE_BAND' && response.data.managedBands.some(band => band.bandId === this.band?.band?.bandId)) {
          this.canEdit = true
        }
        this.roleChecked = true;
      })
    },
    fetchBandEvents() {
      BandService.getBandEvents(this.band.band.bandId)
        .then(response => {
          const now = new Date();
          this.bandEvents = response.data.filter(event => {
            const eventDate = new Date(event.event.eventTime);
            return eventDate > now;
          })
            .sort((a, b) => {
              const dateA = new Date(a.event.eventTime);
              const dateB = new Date(b.event.eventTime);
              return dateA - dateB;
            });
        })
        .catch(error => {
          console.error(error);
        });
    },
    toggleCreateEvent() {
      this.showCreateEvent = !this.showCreateEvent;
    },
    handleEventCreated(newEvent) {
      this.fetchBandEvents();
      this.showCreateEvent = false;
    }

  }
}
</script>

<style scoped>
/* #description {
  grid-area: content;
  color: white;
  background-color: rgb(43, 43, 43);
  font-size: 1.2em;
  line-height: 1.6;
  padding: 15px 20px;
  border-radius: 8px;
  border: 1px solid #ddd;
  max-width: 90%;
  margin: 1em auto;
  text-align: justify;
} */

#description {
  color: white;
  background-color: rgb(43, 43, 43);
  font-size: 1.2em;
  line-height: 1.6;
  padding: 15px 20px;
  border-radius: 8px;
  border: 1px solid #ddd;
  margin: 1em 0;
  text-align: justify;
  white-space: pre-wrap;
}

.band-view {
  /* margin: 10vh; */
  /* display: grid;
  grid-template-columns: 1fr 5fr 1fr;
  grid-template-areas:
    ". name ."
    ". content ."
    ". content ."
    ". content ."
    ". content ."
    ". events ."
    "gallery gallery gallery"
    "gallery gallery gallery"
    "gallery gallery gallery"; */
  display: flex;
  flex-direction: column;
  align-items: center;
  width: 100%;
}

.hero-section {
  position: relative;
  width: 100%;
  height: 80vh;
  display: flex;
  justify-content: center;
  align-items: center;
  overflow: hidden; /* Ensures the image doesn't overflow */
}

#name {
  grid-area: name;
  font-size: 5em;
  font-weight: bold;
  margin-top: 1em;
  text-align: left;
}

/* #events-section {
  grid-area: events;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  align-content: center;
} */

#new-event-form {
  width: 75%;
  display: flex;
  align-content: center;
  align-items: center;
  justify-content: center;
  margin: 1em;
}

#description {
  font-size: 1.2em;
  margin-top: 1em;
}

#heroImage {
  width: 100%;
  /* height: 500px; */
  height: 100%;
  object-fit: cover;
  justify-content: center;
  align-items: center;
}

.overlay {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background: rgba(0, 0, 0, 0.5); /* Darker overlay for better text contrast */
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  color: white;
  text-align: center;
   /* Padding for better spacing around text */
}

/* .genres {
  display: flex;
  flex-direction: row;
  margin-top: 1em;
} */

.genres {
  display: flex;
  justify-content: center;
  margin: 1em;
}

.genres p {
  font-size: 1.6em;
}

/* .content {
  grid-area: content;
  display: flex;
  flex-direction: column;
  align-items: center;
} */

.content {
  max-width: 1200px;
  width: 100%;
  padding: 20px;
  box-sizing: border-box;
  /* Center the content below the hero image */
}

/* #gallery {
  grid-area: gallery;
} */

#events-section,
#gallery {
  margin: 2em 0;
  width: 100%;
  display: flex;
  flex-direction: column;
  align-items: center;
}

/* #gallery-header {
  font-size: 1.5em;
  margin-bottom: 1em;
  justify-content: center;
} */

#gallery-header {
  font-size: 1.5em;
  margin-bottom: 1em;
}

section h3 {
  color: #FFF;
  text-align: start;
  font-family: Montserrat;
  font-size: 28px;
  font-style: normal;
  font-weight: 500;
  line-height: normal;
}

.follow-button, .edit-button {
  padding: 10px 20px;
  background-color: #999999;
  color: white;
  border: none;
  border-radius: 5px;
  font-size: 1.5em;
  cursor: pointer;
  transition: background-color 0.3s ease;
  margin-top: 1em;
  font-family: Montserrat;
}

/* .edit-button {
  padding: 10px 20px;
  background-color: #999999;
  color: white;
  border: none;
  border-radius: 5px;
  font-size: 16px;
  cursor: pointer;
  transition: background-color 0.3s ease;
  margin-bottom: 1em;
  margin-left: 1rem;
} */

.add-event-button {
  padding: 10px 20px;
  background-color: #999999;
  /* Light grey */
  color: white;
  /* White text */
  border: none;
  /* Remove default border */
  border-radius: 5px;
  /* Rounded corners */
  font-size: 16px;
  /* Font size */
  cursor: pointer;
  /* Pointer on hover */
  transition: background-color 0.3s ease;
  /* Smooth transition */
}

.edit-button:hover,
.add-event-button:hover,
.follow-button:hover {
  background-color: #808080;
  /* Darker light grey on hover */
}

.follow-button:active {
  background-color: #333333;
  /* Even darker grey when clicked */
}

.follow-button:focus {
  outline: none;
  /* Remove focus outline */
}

.follow-button.unfollow {
  background-color: #666666;
  /* Darker grey for unfollow */
}

.follow-button.unfollow:hover {
  background-color: #4d4d4d;
  /* Darker grey on hover */
}

#event-component-list {
  display: flex;
  flex-direction: row;
  flex-wrap: wrap;
  justify-content: center;
}

#events {
  margin-top: 2em;
  width: 100%;
}
</style>