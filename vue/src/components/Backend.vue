<template>
  <div class="main">
    <el-container>
      <UserLeftLine />
      <el-row style="width: 100%;float: left">
        <el-col :md="22" :xl="23" :xs="24">
          <router-view></router-view>
        </el-col>
      </el-row>
    </el-container>
  </div>
</template>

<script>
import UserLeftLine from "../views/user/UserLeftLine";
import router from "../router";


export default {
  name: "Backend",
  components: {UserLeftLine},
  data () {
    return{
      UserLeftLine,
      projectId : this.$route.query.user
    }
  },
  mounted() {
    this.mustLoginFirst()
  },
  methods: {
  getSes() {
      let res = JSON.parse(sessionStorage.getItem('userInfo'));
      console.log(res);
      return res;
    }
  },
  ifLogin(){
    return this.getSes() != null;
  },
  mustLoginFirst(){
    if (!this.ifLogin()){
      router.push({path:"/YangCraft/user/login"})
    }
  }
}
</script>

<style scoped>
.main{
  background: azure;
  /*background-image: url(https://gd-hbimg.huaban.com/4593fa59a02fc6cb69d18fdd66bd22b8b298aa6816255b-wqRBpK_fw1200)*/
}

</style>
