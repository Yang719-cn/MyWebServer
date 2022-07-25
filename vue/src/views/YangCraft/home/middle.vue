<template>
  <div class="home">
    <!-- 轮播 start -->
    <el-carousel :interval="5000" height="94vh">
      <el-carousel-item v-for="item in banners " :key="item._id">
        <img
          :src="item.product_pic_url"
          width="100%"
          alt="">
      </el-carousel-item>
    </el-carousel>
  </div>
</template>

<script>

export default {
  name: "middle",
  data(){
    return {
      banners:[
        {
          title:"干锅滋小鱼",
          product_pic_url: "/static/img/YangCraft/home/spawn.png",
          _id:1
        },
        {
          title:"海岸",
          _id:2,
          product_pic_url: "/static/img/YangCraft/home/coast.png",
        },
        {
          title:"G41",
          _id:3,
          product_pic_url: "/static/img/YangCraft/home/G41.png",
        },
        {
          title:"高山",
          product_pic_url: "/static/img/YangCraft/home/mountain.png",
          _id:4
        },
        {
          title:"大巴",
          _id:5,
          product_pic_url:  "/static/img/YangCraft/home/15and45.png",
        },
      ],
    }
  }
}

</script>

<style scoped>

</style>
