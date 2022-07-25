<template>
  <div>
    <div class="top-breadcrumb">
      <el-breadcrumb separator-class="el-icon-arrow-right">
        <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
        <el-breadcrumb-item><a href="/YangCraft">YangCraft</a></el-breadcrumb-item>
        <el-breadcrumb-item><a href="/YangCraft/user/backend">用户后台</a></el-breadcrumb-item>
        <el-breadcrumb-item>网页</el-breadcrumb-item>
      </el-breadcrumb>
    </div>
    <el-card class="card">
      <iframe id="web" src="https://bilibili.com/audio/home"  border="0" > </iframe>
    </el-card>
  </div>
</template>

<script>
export default {
  name: "Music163"
}
</script>

<style scoped>
.top-breadcrumb{
  margin: 16px;
  font-size: 30px;
  padding: 5px;
}

.card{
  margin: 10px;
}

.card #web{
  height: 80vh;
  width: 100%;
}

</style>
