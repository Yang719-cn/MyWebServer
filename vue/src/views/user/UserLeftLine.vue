<template>
  <div>
    <!--侧边栏-->
    <el-menu router
             default-active="onRoutes"
             class="el-menu-vertical-demo"
             @open="handleOpen"
             @close="handleClose"
             :collapse="isCollapse">
      <div class="title" @click="changeTabs">
        <div>
          <span v-if="!isCollapse">
            <el-image src="/static/img/title.png" style="height: 18px;width:73px;margin: 0;"></el-image>
          </span>
          <span v-if="isCollapse">
              <i class="el-icon-s-unfold"></i>
          </span>
        </div>
      </div>
      <el-menu-item index="0">
        <i class="el-icon-">
          <el-image src="user.imgUrl" height="24" width="24">
            <div slot="error" class="image-slot">
              <i class="el-icon-user"></i>
            </div>
          </el-image>
        </i>
        <span slot="title" style="word-break:normal;">
          个人中心
        </span>
      </el-menu-item>
      <el-menu-item index="/YangCraft/user/backend/mail">
        <i class='el-icon-message'></i>
        <span slot="title">
          我的消息
        </span>
      </el-menu-item>
      <!--第一栏-->
      <el-submenu index="1" v-show="ifPicUser">
        <template slot="title">
          <i class="el-icon-picture-outline-round"></i>
          <span slot="title">用户图片</span>
        </template>
        <el-menu-item-group>
          <el-menu-item index="/YangCraft/user/backend/userImgAdd">图片上传</el-menu-item>
          <el-menu-item route index="/YangCraft/user/backend/userImgList">图片浏览</el-menu-item>
        </el-menu-item-group>
      </el-submenu>
      <!--第二栏-->
      <el-submenu index="2">
        <template slot="title">
          <i class="el-icon-chat-line-round"></i>
          <span slot="title">游玩反馈</span>
        </template>
        <el-menu-item-group>
          <span slot="title">服务器环境</span>
          <el-menu-item index="2-1">玩家举报</el-menu-item>
          <el-menu-item index="2-2">错误报告</el-menu-item>
        </el-menu-item-group>
        <el-menu-item-group>
          <span slot="title">服务器建议</span>
          <el-menu-item index="2-3">插件建议</el-menu-item>
          <el-menu-item index="/YangCraft/user/backend/cityPlanning">城市规划</el-menu-item>
        </el-menu-item-group>
      </el-submenu>

      <el-menu-item index="/YangCraft/user/backend/mcMap">
        <i class="el-icon-map-location"></i>
        <span slot="title">
          网页地图
        </span>
      </el-menu-item>
      <!--用户栏-->
      <el-menu-item index="/YangCraft/user/backend/webPage">
        <i class="el-icon-paperclip"></i>
        <span slot="title">摸鱼网站</span>
      </el-menu-item>

      <el-menu-item index="/YangCraft/user/login" @click="loginOut()">
        <i class="el-icon-back"></i>
        <span slot="title">登出</span>
      </el-menu-item>

    </el-menu>
  </div>

</template>

<script>
import axios from "axios";

export default {
  name: "UserLeftLine",
  data() {
    return {
      isCollapse: true,
      ifPicUser: false
    };
  },
  mounted() {
    this.getIfPicUser()
  },
  methods: {
    loginOut(){
      sessionStorage.removeItem('userInfo');
    },
    getIfPicUser(){
      let _this = this;
      axios.get('http://yang719.cn:8086/user/ifPicUser?id='+JSON.parse(sessionStorage.getItem('userInfo')).id).then(resp => {
        console.log('阿这')
        _this.ifPicUser = resp.data.data
        console.log(_this.ifPicUser)
      })
    },
    changeTabs(){
      this.isCollapse = !this.isCollapse;
    },
    handleOpen(key, keyPath) {
      console.log(key, keyPath);
    },
    handleClose(key, keyPath) {
      console.log(key, keyPath);
    },
    getSes() {
      let res = JSON.parse(sessionStorage.getItem('userInfo'));
      return res;
    }
  },
  computed: {
    onRoutes() {
      const route = this.$route
      const { meta, path } = route
      // 在路由配置文件中设置自定义的路由路径到meta.activeMenu属性中，来控制菜单自定义高亮显示
      // meta中 有activeMenu 字段的子页面，都会显示高亮
      if (meta.activeMenu) {
        return meta.activeMenu
      }
      return path
    }
  }

}
</script>

<style scoped>
.el-menu-vertical-demo:not(.el-menu--collapse) {
  width: 160px;
  min-height: 100vh;
  font-size: 30px;
}

.el-menu-vertical-demo {
  font-size: 30px;
  min-height: 100vh;
  max-height: 100vh;
  position: relative;
}

.el-menu-vertical-demo .title{
  text-align: center;
  margin: 15px;
  -webkit-box-align: center;
  font-size: 20px;
}

.el-menu-vertical-demo .user{
  text-align: center;
  margin: 15px;
  -webkit-box-align: center;
  font-size: 20px;
  bottom: 0;

}

.main-active{
  background: azure;
}


</style>
