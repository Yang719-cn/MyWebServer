<template>
  <div class="main">
    <div class="top-breadcrumb">
      <el-breadcrumb separator-class="el-icon-arrow-right">
        <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
        <el-breadcrumb-item><a href="/YangCraft">YangCraft</a></el-breadcrumb-item>
        <el-breadcrumb-item><a href="/YangCraft/user/backend">用户后台</a></el-breadcrumb-item>
        <el-breadcrumb-item><a href="">游玩反馈</a> </el-breadcrumb-item>
        <el-breadcrumb-item>城市规划</el-breadcrumb-item>
      </el-breadcrumb>
    </div>
    <el-card shadow="hover" class="img-add">
      <div class="tips">
        <p class="tip">Tip. 这里是城市规划建议提交出</p>
        <p class="tip">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;为了避免你的<span class="why">大工程修到别人家里</span>从而<span class="why">造成民事纠纷</span></p>
        <p class="tip">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;所以有啥要建的<span class="warn">大工程需上报</span>，进而等待<span class="warn">管理员批准和协调</span></p>
      </div>
      <el-form class="add-form" ref="pluginSuggestForm" :model="pluginSuggestForm" label-width="80px">
        <el-form-item label="图片名称">
          <el-input
            style="width: 80%"
            v-model="pluginSuggestForm.name"
            placeholder="请务必填写此项以方便搜寻"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="onSubmit">立即上传</el-button>
          <el-button>取消</el-button>
        </el-form-item>
      </el-form>
    </el-card>
  </div>
</template>

<script>
export default {
  name: "PluginSuggest",
  data () {
    return {
      pluginSuggestForm:{},
    }
  },
  methods:{
    onSubmit() {
    }
  }
}
</script>

<style scoped>
.main{
  padding: 16px;
}

.tips{
  margin: 7px 7px 20px 7px;
}

.tip{
  font-size: 16px;
  color: #404244;
  padding: 2px;
  margin-bottom: 10px;
}

.tip .why{
  color: deepskyblue;
}

.tip .warn{
  color: red;
}

.top-breadcrumb{
  font-size: 30px;
  padding: 5px;
  margin-bottom: 16px;
}

.img-add{
  width: 80%;
}
</style>

