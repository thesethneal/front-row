import axios from 'axios';

export default {
  
  getGallery(bandName){
    return axios.get(`/${bandName}/gallery`)
  },

  uploadImage(url, bandName) {
    return axios.post(`/band/gallery-upload`, {
      url: url,
      bandName: bandName
  });
  }

}
