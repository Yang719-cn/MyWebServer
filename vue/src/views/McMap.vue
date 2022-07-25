<template>
  <div>
    <div class="top-breadcrumb">
      <el-breadcrumb separator-class="el-icon-arrow-right">
        <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
        <el-breadcrumb-item><a href="/YangCraft">YangCraft</a></el-breadcrumb-item>
        <el-breadcrumb-item><a href="/YangCraft/user/backend">用户后台</a></el-breadcrumb-item>
        <el-breadcrumb-item>网页地图</el-breadcrumb-item>
      </el-breadcrumb>
    </div>
    <el-card class="map">
      <iframe id="mcMap" border="0" src="http://yang719.cn:8123/"></iframe>
    </el-card>
  </div>
</template>

<script>
export default {
  name: "McMap"
}
</script>

<style scoped>

.top-breadcrumb{
  margin: 16px;
  font-size: 30px;
  padding: 5px;
}

.map{
  margin: 10px;
}

.map #mcMap{
  height: 80vh;
  width: 100%;
}

</style>
