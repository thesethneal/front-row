import axios from 'axios';

export default {
  
    getGenresToManage(){
        return axios.get('/all-genres')
    },
    
    addGenre(genre){
        return axios.post('/add-genre', genre)
    },

    updateGenres(genres){
        return axios.put('/update-genres', genres)
    }

}
