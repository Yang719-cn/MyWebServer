<template>
  <div class="main">
    <div class="top-breadcrumb">
      <el-breadcrumb separator-class="el-icon-arrow-right">
        <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
        <el-breadcrumb-item><a href="#/YangCraft">YangCraft</a></el-breadcrumb-item>
        <el-breadcrumb-item>用户后台</el-breadcrumb-item>
      </el-breadcrumb>
      <el-card id="hello">
        <h5>欢迎来到YangCraft用户后端</h5>
      </el-card>
    </div>
  </div>
</template>

<script>
export default {
  name: "UserBackendHome"
}
</script>

<style scoped>

.top-breadcrumb{
  margin: 16px;
  font-size: 30px;
  padding: 5px;
}

.main{
}

#hello{
  margin-top: 16px;
}

</style>
