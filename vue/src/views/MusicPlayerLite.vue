<template>
  <div id="aplayer"></div>
</template>

<script>
import Aplayer from 'Aplayer'
import 'Aplayer/dist/Aplayer.min.css'

export default {
  name: "MusicPlayerLite",
  data () {
    return {
      audio: [
        {
          name: 'One Last Kiss',
          artist: '宇多田ヒカル',
          url: 'https://m701.music.126.net/20220618210904/5c92ccab0b65198d36d0ad414f1e15bb/jdyyaac/obj/w5rDlsOJwrLDjj7CmsOj/14426990841/2779/4cf1/e2e8/523ebbf8e221f5ef2caed44aa7612777.m4a'
          cover: ''
        }
      ]
    }
  },
  mounted() {
    this.initAudio;
  },
  methods: {
    initAudio () {
      const ap = new Aplayer({
        container: document.getElementById("aplayer"),
        audio: this.audio,

      })
    }
  }
}
</script>

<style scoped>

</style>
