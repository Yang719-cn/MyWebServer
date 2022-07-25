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
        <p class="tip">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;为了避免你的<span class="warn">大工程修到别人家里</span>从而<span class="why">造成民事纠纷</span></p>
        <p class="tip">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;所以有啥要建的<span class="warn">大工程需上报</span>，进而等待<span class="why">管理员批准和协调</span></p>
      </div>
      <el-form class="add-form" ref="cityPlanningForm" :model="cityPlanningForm" label-width="80px">
        <el-form-item label="项目名称">
          <el-input
            style="width: 80%"
            v-model="cityPlanningForm.name"
            placeholder="城市规划项目名称"></el-input>
        </el-form-item>
        <el-form-item label="项目地址">
          <el-input
            style="width: 80%"
            v-model="cityPlanningForm.location"
            placeholder="城市规划项目地址"></el-input>
        </el-form-item>
        <el-form-item label="项目介绍">
          <el-input
            type="textarea"
            placeholder="你要建什么东西，不超过100字"
            v-model="cityPlanningForm.introduction"
            maxlength="100"
            style="width: 80%;"
            show-word-limit
          >
          </el-input>
        </el-form-item>
        <el-form-item label="规划图片或报告">
          <el-upload
            class="upload-demo"
            ref="upload"
            action="#"
            accept="image/jpeg, image/jpg, image/png"
            :before-upload="beforeUpload"
            :on-preview="handlePreview"
            :on-remove="handleRemove"
            :on-change="handleChange"
            :file-list="fileList"
            :auto-upload="false"
            :on-success="success"
            :multiple="false"
          >
            <el-button size="small" type="primary">点击上传</el-button>
            <div slot="tip" class="el-upload__tip">只能上传jpg/png文件，且不超过10Mb</div>
          </el-upload>
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
import axios from "axios";
import router from "../../../../router";

export default {
  name: "CityPlanning",
  data () {
    return {
      cityPlanningForm:[],
      fileList:[],
      rules: {
        name: [
          {required: true, message: '请输入用户名', trigger: 'blur'}
        ],
        introduction: [
          {required: true, message: '请输入密码', trigger: 'blur'}
        ]
      },
    }
  },
  methods:{
    handleChange(file, fileList) {
      if (fileList.length > 0) {
        this.fileList = [fileList[fileList.length - 1]]
      }
    },
    beforeUpload(file) {
      const acceptTypes = ["image/jpeg", "image/jpg", "image/png"];
      const isImg = acceptTypes.includes(file.type);
      const isLt10M = file.size / 1024 / 1024 < 10;
      if (!isImg) {
        console.log("文件必须图片格式");
        this.$message.error("文件必须图片格式");
      }
      if (!isLt10M) {
        console.log("图片大小不超过10M");
        this.$message.error("图片大小不超过10M！");
      }
      return isImg && isLt10M;
    },
    handleRemove(file, fileList) {
      console.log('图片移除咯');
      console.log(file, fileList);
    },
    handlePreview(file) {
      console.log('像放大图片看看');
      console.log(file);
    },
    //上传成功时弹框
    success(res) {

    },
    handleDownload(file) {
      if (acceptTypes.includes(file.raw.type)) {
        let link = document.createElement("a");
        link.download = file.name;
        link.href = file.url;
        link.click();
      } else {
        window.open(file.url);
      }
    },
    onSubmit() {
      //上传
      console.log('这是名称:'+this.fileList[0].name);
      let user = JSON.parse(sessionStorage.getItem('userInfo'));
      console.log(this.cityPlanningForm.name);
      console.log(user);

      //使用FormData传数据才能更好接收到
      let formData = new FormData();
      //this.fileList[i].raw:获取图片格式
      //这里的file必须和后端Controller中的requestparam参数一致，才能上传成功，否则会报400错误
      formData.append("file", this.fileList[0].raw,  this.cityPlanningForm.name);
      formData.append("uid", user.id);
      //这里创建了一个axios实例,这里我想要使用multipart/form-data
      const http1 = axios.create({
        headers: {
          // 'Content-Type': 'multipart/form-data'
          "Content-Type": "multipart/form-data"
        }
      })
      http1({
        url: 'http://localhost:8686/user/cityPlan',
        method: 'post',
        data: formData
      })
        .then((resp) => {
          if (resp.data.code > 0){
            console.log('图片保存成功了');
            console.log(resp.data);
            router.push({path: "/YangCraft/user/backend/userImgList"})
          }else {
            console.log('图片传送成功了，但保存失败了');
            console.log(resp.data);
            this.$alert('图片传送成功了，但保存失败了', '注意', {
              confirmButtonText: '确定',
              callback: action => {
                this.$message({
                  type: 'info',
                  message: '再试一次吧'
                });
              }
            });
          }
        })
        .catch((err) => {
          console.log('传给spring失败了');
          console.log(err);
          this.$alert('图片传送失败', '注意', {
            confirmButtonText: '确定',
            callback: action => {
              this.$message({
                type: 'info',
                message: '再试一次吧'
              });
            }
          });
        });
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
