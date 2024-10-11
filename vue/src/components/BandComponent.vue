<template>
    <div :class="{'bandItem':true, 'highlighted':hasMessage}" @click="toBandPage">
        <div class="image-container">
            <img :src="band.band.bandHeroImage" alt="band image"/>
            <div class="artistGenre">
                <h4>{{ band.band.bandName }}</h4>
                <div class="genreList">
                    <p v-for="genre in band.genreNames" :key="genre">{{ genre }}</p>
                </div>  
            </div>
        </div>
        <div v-if="hasMessage" class="notification"></div>
    </div>
</template>

<script>
export default {
    props: {
        band: {
            type: Object,
            required: true
        },
        hasMessage: {
            type: Boolean,
            required: true
        }
    },
    methods: {
        toBandPage () {
            if(this.$store.state.token != ''){
                this.$router.push({ path: `/${this.band.band.bandName}` })
            }
            else this.$router.push('/register')
        }
    }
}
</script>

<style scoped>

.bandItem {
    width: 200px;
    height: 200px;
    position: relative;
    overflow: visible;
    margin: 1em;
    border-radius: 20px;
    z-index: 1;
}

.notification {
    position: absolute;
    top: -0.5em;
    right: -0.5em;
    width: 2em;
    height: 2em;
    background-color: gold;
    border-radius: 50%;
    z-index: 3;
}

.highlighted {
    border: 3px solid gold;
    background-color: rgba(255, 215, 0, 0.4);
    z-index: 2;
}

.image-container {
    width: 100%;
    height: 100%;
    position: relative;
    overflow: hidden;
    border-radius: 20px;
}

.image-container img {
    width: 100%;
    height: 100%;
    object-fit: cover;
    border-radius: 20px;
}

.artistGenre {
    position: absolute;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    color: white;
    background-color: rgba(0, 0, 0, 0.4);
    text-align: center;
    font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
}

.artistGenre h4 {
    font-size: 1.6em;
    width: 90%;
    line-height: 1.1;
}

.genreList {
    display: flex;
    width: 90%;
    justify-content: center;
    align-items: center;
}

.genreList p {
    font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
    font-size: 1em;
    text-align: center;
    justify-content: center;
    margin: 0.2em;
}

p {
    display: flex;
    justify-content: center;
    align-items: center;
    padding: 5px;
}

</style>