<template>
  <div>
    <button v-if="admin" @click="openWidget">Upload Image to Gallery</button>
    <button v-else @click="setUrl">Upload Banner Image</button>
  </div>
</template>

<script>
import ImageService from '../services/ImageService';

export default {
  props: {
    admin: {
      type: Boolean,
      required: true
    },
    modelValue: {
      type: String,
      default: ''
    },
    bandName: {
      type: String,
      required: false
    }
  },
  data() {
    return {
      imageUrl: ''
    }
  },
  methods: {
    openWidget() {
      const widget = window.cloudinary.createUploadWidget(
        {
          cloudName: 'dfxvcpj23',
          uploadPreset: 'system_uploader_1e2ddab171f769b9_c929acf69d8900b2aaba20b2a4ea4d9459'
        },
        (error, result) => {
          if (!error && result && result.event === 'success') {
            console.log("Done uploading", result.info.secure_url)
            console.log(this.bandName)
            this.sendImageToServer(result.info.secure_url);
          }
          if (!error && result && result.event === 'close') {
          console.log("Widget closed");
          window.location.reload();  // Refresh the page when the widget closes
        }
        }
      );
      widget.open();
    },
    setUrl() {
      const widget = window.cloudinary.createUploadWidget(
        {
          cloudName: 'dfxvcpj23',
          uploadPreset: 'system_uploader_1e2ddab171f769b9_c929acf69d8900b2aaba20b2a4ea4d9459'
        },
        (error, result) => {
          if (!error && result && result.event === 'success') {
            this.imageUrl = result.info.secure_url;
            this.$emit('update:modelValue', this.imageUrl);
          }
        }
      );
      widget.open();
    },
    sendImageToServer(imageUrl) {
      ImageService.uploadImage(imageUrl, this.bandName)
        .then(response => {
          console.log(response.data);
        })
        .catch(error => {
          console.error(error.response.data);
        });
    }
  }
}
</script>

<style scoped>
button {
  padding: 10px 20px;
  background-color: #999999;  /* Light grey */
  color: white;               /* White text */
  border: none;               /* Remove default border */
  border-radius: 5px;         /* Rounded corners */
  font-size: 16px;            /* Font size */
  cursor: pointer;            /* Pointer on hover */
  transition: background-color 0.3s ease; /* Smooth transition */
  margin-bottom: 1em;
}

button:hover {
  background-color: #808080;  /* Darker light grey on hover */
}
</style>