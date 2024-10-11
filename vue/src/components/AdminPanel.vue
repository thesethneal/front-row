<template>
  <div id="admin">
    <h2>Admin Panel</h2>

    <div style="display: flex; justify-content: center; margin: 5vw; gap: 10vw;">
        <div>
            <h3>Update Usable Genres</h3>
            <form @submit.prevent="updateGenres">
                <div class="genre-list">
                    <label class="genre-item" v-for="genre in genres" :key="genre">
                      <br/>
                      <input
                        type="checkbox"
                        v-model="genre.visible"
                      /> <p style="text-transform: capitalize; color: white;">{{ genre.genreName }}</p>
                    </label>
                </div>
                <button type="submit" @click="updateGenres">Update Genres</button>
            </form>
        </div>
        <div id="add-genre">
            <h3>Add New Genre</h3>
            <form @submit.prevent="">
                <input type="text" v-model="genreToAdd.name">
                <button type="submit" @click="addGenre">Add Genre</button>
            </form>
        </div>
    </div>
  </div>
</template>

<script>
import GenreService from '../services/GenreService';

export default {
    data(){
        return {
            genres: [], 
            genreToAdd: {
                name: ''
            }
        }
    },
    created() {
        GenreService.getGenresToManage().then(response => {
            this.genres = response.data 
        })
        .catch(error => {
            console.error(error);
        });
    },
    methods: {
        updateGenres(){
            GenreService.updateGenres(this.genres)
            .then(response => {
                console.log
            })
        },
        addGenre(){
            GenreService.addGenre(this.genreToAdd)
            .then(response => {
                location.reload();
            })
            .catch(error => {
                console.error(error.response.data);
            });
        }
    }
}
</script>

<style scoped>
    #admin {
        margin-block: 12vh;
    }


    .genre-item {
        display: flex;
        padding: 0.5em;
        align-items: center;

    }

    input {
        margin: 4px;
    }

    button {
  margin-top: 1em;
  color: black;
  border-radius: 20px;
  justify-content: center;
  width: fit-content;
  min-width: 120px;
  height: fit-content;
  min-height: 30px;
}

</style>