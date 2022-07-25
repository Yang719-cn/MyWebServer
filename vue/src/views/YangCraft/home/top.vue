<template>
  <div class="main">
  <div id="main">
    <div id="title">
      <i class='bx bx-joystick-alt'></i><h1>YangCraft</h1>
    </div>
    <div class="link">
      <el-button type="primary" round><router-link to="YangCraft/user/login" class="btnText"><i class="el-icon-user"/>&nbsp;&nbsp;用户登录</router-link></el-button>
      <el-button type="primary" round><router-link to="YangCraft/admin/login" class="btnText"><i class="el-icon-user"/>&nbsp;&nbsp;管理登录</router-link></el-button>
      <a href="yang719.cn:23333" class="btnText"><el-button type="primary" round><i class="el-icon-cpu"/>&nbsp;&nbsp;服务器后台</el-button></a>
    </div>
  </div>
  </div>
</template>

<script>
export default {
  name: "top",
  data() {
      return {
        activeIndex: '1',
        activeIndex2: '1'
      };
    },
    methods: {
      handleSelect(key, keyPath) {
        console.log(key, keyPath);
      }
    }
}
</script>

<style scoped>

#title{
  float: left;
  
}

#title i{
  font-size:36px; 
  float:left
}

#title h1{
  font-size:30px; 
  float:left
}

.main {
  height: 6vh;
}

.link {
  float: right;
}

.btnText {
  color: white;
  text-decoration:none;
}

</style>
