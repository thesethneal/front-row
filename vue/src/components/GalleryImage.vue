<template>
  <div id="gallery">
    <div id="images-grid" v-if="images.length > 0">
      <img class="photos" v-for="image in images" :key="image.imageId" :image="image" :src="image.imageLink"/>
    </div>
    <div v-else>
      <p>No images in gallery</p>
    </div>
    
  </div>
</template>

<script>
import ImageService from '../services/ImageService';

export default {
  props: {
    bandName: {
      type: String,
      required: true
    }
  },
  data() {
    return {
      images: []
    };
  },
  created() {
    console.log(this.bandName);
    ImageService.getGallery(this.bandName)
      .then(response => {
        this.images = response.data;
      })
      .catch(error => {
        console.log(error);
      });
  }

}
</script>

<style scoped>

.photos {
  width: auto;
  height: 300px;
  margin: 1em;
  border-radius: 20px;
}

</style>