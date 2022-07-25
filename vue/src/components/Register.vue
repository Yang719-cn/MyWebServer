<template>
  <div id="bg">
    <el-image :src="bgUrl" class="bg"></el-image>
    <RegisterBox id="box"/>
    <iframe id="player" frameborder="no" border="0" marginwidth="0" marginheight="0" width=330 height=86 src="https://music.163.com/outchain/player?type=2&id=1329665666&auto=1&height=66"></iframe>
  </div>
</template>

<script>
import RegisterBox from "../views/RegisterBox";

export default {
  name: "Register",
  components: {RegisterBox},
  data () {
    return {
      bgUrl:"https://w.wallhaven.cc/full/rd/wallhaven-rdwjj7.jpg",
      RegisterBox,
    }
  },
  mounted() {
    this.bgChange()
  },
  methods:{
    bgChange(){
      console.log("准备换壁纸")
      let bgList = new Array("https://w.wallhaven.cc/full/rd/wallhaven-rdwjj7.jpg",
        "https://w.wallhaven.cc/full/z8/wallhaven-z8dg9y.png",
        "https://images6.alphacoders.com/730/thumb-1920-730763.png",
        "https://w.wallhaven.cc/full/y8/wallhaven-y8lqo7.jpg",
        "https://w.wallhaven.cc/full/g7/wallhaven-g7kwk3.jpg",
        "https://w.wallhaven.cc/full/r2/wallhaven-r21e31.jpg",
        "https://w.wallhaven.cc/full/r2/wallhaven-r2wr3m.png",
        "https://get.wallhere.com/photo/1920x1200-px-adventure-astronaut-fi-interstellar-Mystery-sci-1883593.jpg");
      let bgListLen = bgList.length
      let num = Math.floor(Math.random()*bgListLen)
      this.$data.bgUrl = bgList[num];
    }
  }
}
</script>

<style scoped>
#bg {
  position: relative;
}

#bg .bg{
  position: absolute;
  width: 100%;
  height: 100vh;
  margin: 0;
  padding: 0;
  float: top;
}

#box{
  height: 100vh;
}

#player {
  position: absolute;
  transform: translateY(-100%);
}
</style>
