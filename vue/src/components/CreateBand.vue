<template>
  <main class="main-container">
    <header class="header">
        <div>
            <h1 id="title">Create a Band</h1>
        </div>
    </header>
    <form class="content" @submit.prevent="submitForm">
        <div id="cover-image" >
            <ImageUpload :admin="false" v-model="band.band.bandHeroImage" required ></ImageUpload>
        </div>
        <!-- :imageUrl="band.band.bandHeroImage" -->
        <div class="input">
            <input 
            v-model="band.band.bandName"
            class="text-field"
            type="text"
            placeholder="Band Name"
            required
            >
        </div>
        <div class="input">
            <textarea
            v-model="band.band.bandDescription"
            class="text-field"
            placeholder="Band Description"
            required
            ></textarea>
        </div>
        <div>
            <h3 id="genre-heading">Add genres</h3>
            <div class="genre-list"> 
                <GenreSearch class="create-view" 
                @update:selectedGenres="updateSelectedGenres" 
                required
                :initialSelectAll="false"
                />
            </div>
        </div>
        <div v-if="errors.length" class="error-messages">
            <ul>
            <li v-for="error in errors" :key="error">{{ error }}</li>
            </ul>
        </div>
        <div class="button-container">
            <button type="submit" id="create-button">CREATE BAND</button>
            <button @keyup.esc="cancel()" @click="cancel()" id="cancel-button">Cancel</button>
        </div>
    </form>
    
  </main>
</template>

<script>
import BandService from '../services/BandService';
import ImageUpload from '../components/ImageUpload.vue';
import GenreSearch from './GenreSearch.vue';
import { watch } from 'vue';

export default {
    data() {
        return {
            errors: [],
            band: {
                band: {
                    bandName: '',
                    bandDescription: '',
                    bandHeroImage: '',  
                },
                genreNames: []
            }
        }
    },
    components: {
            ImageUpload,
            GenreSearch
        },
    props: {
        showCreateBand: {
            type: Boolean,
            required: true
        }
    },
    methods: {
        createBand() {
            BandService.create(this.band).then(response => {
            if (response.status === 201) {
                BandService.updateRole('ROLE_BAND'); 
                this.$emit('band-created');
                this.$router.push('/' + this.band.band.bandName);
            }
            })
            .catch(error => {
                console.error("Error creating band:", error);
                this.errors.push("An error occurred while creating the band. Please try again.");
            });
        },
        updateSelectedGenres(genres) {
            this.band.genreNames = genres;
        },
        cancel() {
        this.$emit('close');
        },
        mounted() {
            this.debugger();
        },
        checkForm() {
            this.errors = [];

            if (!this.band.band.bandName.trim()) {
                this.errors.push('Name required.');
            }
            if (!this.band.band.bandDescription.trim()) {
                this.errors.push('Description required.');
            }
            if (!this.band.band.bandHeroImage) {
                this.errors.push('Image required.');
            }
            if (this.band.genreNames.length === 0) {
                this.errors.push('At least one genre is required.');
            }

            return this.errors.length === 0;
        },
        submitForm() {
            console.log("Form submitted");
            if (this.checkForm()) {
                console.log("Form is valid, creating band");
                this.createBand();
            } else {
                console.log("Form is invalid, errors:", this.errors);
            }
        }
    },
    watch: {
        'band.band.bandHeroImage'(newValue) {
            console.log("Band Hero Image updated: ", newValue);
        }
    },
}
</script>


<style scoped>
#title {
    text-align: center;
    margin-top: 1rem; /* Reduced margin for better spacing */
    background-color: rgba(240, 34, 27, 0.925);
    color: white; /* Better contrast for the title */
    padding: 1rem;
    border-radius: 5px; /* Rounded corners */
    box-shadow: 0 2px 5px rgba(0, 0, 0, 0.3); /* Subtle shadow for depth */
    font-size: 5rem;
}

.main-container {
    display: flex;
    flex-direction: column;
    height: auto; /* Allow height to adjust based on content */
    background-color: #f9f9f9; /* Light background */
    width: auto;
}

.header {
    padding: 1rem;
}

.content {
    flex-grow: 1;
    overflow-y: auto;
    padding: 1rem;
    background-color: #fff; /* Clean white background */
    border-radius: 8px; /* Rounded corners */
    box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1); /* Subtle shadow */
    margin-top: 1rem; /* Add margin top to separate from header */
}

.input {
    display: flex;
    flex-direction: column;
    margin-bottom: 1rem; /* Spacing between inputs */
    width: 100%;
}

textarea {
    width: auto;
    white-space: pre-wrap; /* Preserve line breaks and spaces */
    word-wrap: break-word; /* Ensure long words break and wrap correctly */
}

input, textarea {
    padding: 10px;
    border: 1px solid #ccc; /* Light border */
    border-radius: 4px; /* Rounded corners */
    background-color: #f4f4f4; /* Light grey background for inputs */
    transition: border-color 0.3s; /* Smooth transition */
    font-size: 1rem; /* Standard font size */
    font-family: Montserrat, sans-serif;
}

input:focus, textarea:focus {
    border-color: #555; /* Darker border on focus */
    outline: none; /* Remove default outline */
}

#genre-heading {
    text-align: center;
    font-weight: bold;
    margin: 1rem 0; /* Space above and below */
    color: #474747;
}

.genre-list {
    text-align: center;
    margin-bottom: 1rem;
    width: 100%; /* Set to 100% to use full available width */
    max-width: 100%; /* Set a maximum width if desired */
    margin: 0 auto; /* Center the genre list */
}

.button-container {
    display: flex;
    justify-content: center; /* Center buttons */
    margin-top: 1rem; /* Space above buttons */
}

button {
    padding: 10px 20px; /* Consistent padding */
    margin: 0 5px; /* Spacing between buttons */
    border: none; /* No border */
    border-radius: 4px; /* Rounded corners */
    cursor: pointer; /* Pointer on hover */
    font-size: 1rem; /* Standard font size */
    transition: background-color 0.3s, transform 0.2s; /* Smooth transition */
}

#create-button {
    background-color: #333; /* Dark background */
    color: white; /* White text */
}

#create-button:hover {
    background-color: #555; /* Lighter shade on hover */
    transform: translateY(-1px); /* Lift effect on hover */
}

#cancel-button {
    background-color: #ccc; /* Light grey */
    color: #333; /* Dark text */
}

#cancel-button:hover {
    background-color: #aaa; /* Darker grey on hover */
    transform: translateY(-1px); /* Lift effect on hover */
}

.error-messages {
  color: red;
  margin: 20px;
}

.genre-search {
    font-family: Montserrat, sans-serif;
    color: #1a1a1a; /* Darker grey for text */
    background-color: #e6e6e6; /* Very light grey background */
    padding: 25px;
    border-radius: 10px; /* Rounded corners */
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1); /* Soft shadow for depth */
    margin: 0 auto; /* Center the component */
}

.genres label {
    font-size: 18px; /* Maintain readability */
    color: #1a1a1a; /* Darker grey for "Select All" */
    font-weight: 600; /* Bold for emphasis */
    display: flex;
    align-items: center;
    margin-bottom: 12px; /* Space below label */
}

.genre-list {
    border-top: 1px solid #b3b3b3; /* Medium grey border */
    padding-top: 15px; /* Space above genres */
    margin-top: 15px; /* Space above the genre list */
}

.genre-list label {
    display: flex;
    align-items: center;
    font-size: 16px; /* Consistent font size */
    color: #333; /* Slightly darker text for better contrast */
    padding: 10px 0; /* Increased padding for better touch targets */
    border-bottom: 1px solid #ccc; /* Light grey separator */
}

.genre-list label:last-of-type {
    border-bottom: none; /* Remove border for the last item */
}

input[type="checkbox"] {
    accent-color: #666; /* Medium grey for checkbox */
    margin-right: 12px; /* Space between checkbox and label */
    transform: scale(1.2); /* Slightly larger checkbox for easier clicking */
}

input[type="checkbox"]:hover {
    accent-color: #333; /* Darker grey on hover */
    cursor: pointer; /* Pointer on hover */
}

/* Additional styling for responsive design */
@media (max-width: 480px) {
    .genre-search {
        padding: 15px; /* Reduced padding for smaller screens */
        max-width: 90%; /* Responsive width */
    }
}
</style>





