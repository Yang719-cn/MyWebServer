<template>
  <div class="main">
    <div class="top-breadcrumb">
      <el-breadcrumb separator-class="el-icon-arrow-right">
        <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
        <el-breadcrumb-item><a href="/YangCraft">YangCraft</a></el-breadcrumb-item>
        <el-breadcrumb-item><a href="/YangCraft/user/backend">用户后台</a></el-breadcrumb-item>
        <el-breadcrumb-item>用户图片</el-breadcrumb-item>
        <el-breadcrumb-item>图片添加</el-breadcrumb-item>
      </el-breadcrumb>
    </div>
    <el-card shadow="hover" class="img-add">
      <div class="tips">
        <p class="tip">Tip. 图片保存命名规格为：<span class="why">用户名+图片文件名+上传时间</span></p>
        <p class="tip">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;这样做是为了<span class="why">游戏内输指令自动补全时更方便</span></p>
        <p class="tip">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class="warn">请不要</span>为了一时爽快，而<span class="warn">精虫上脑直接传</span></p>
        <p class="tip">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;文件名中<span class="warn">请不要有美元符号“$”，不然</span>网页中<span class="warn">文件名不完全</span></p>
      </div>
      <el-form class="add-form" ref="imgAddForm" :model="imgAddForm" label-width="80px">
        <el-form-item label="图片名称">
          <el-input
            style="width: 80%"
            v-model="imgAddForm.name"
            placeholder="请务必填写此项以方便搜寻"></el-input>
        </el-form-item>
        <el-form-item>
          <el-upload
            class="upload-demo"
            ref="upload"
            action="#"
            accept="image/jpeg, image/jpg, image/png"
            list-type="picture-card"
            :before-upload="beforeUpload"
            :on-preview="handlePreview"
            :on-remove="handleRemove"
            :on-change="handleChange"
            :file-list="fileList"
            :auto-upload="false"
            :on-success="success"
            :multiple="false"
          >
            <i class="el-icon-plus"></i>
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
import router from "../../../router";
import moment from "moment";

export default {
  name: "UserImgAdd",
  data() {
    return {
      fileList: [],
      imgAddForm: {
        name: '',
      }
    }
  },
  methods: {
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
      console.log(this.imgAddForm.name);
      console.log(user);

      //警告你去填写图片名称
      if (this.imgAddForm.name == null || this.imgAddForm.name == '' ){
        this.$confirm('你未填写图片名称', '注意', {
          confirmButtonText: '这就去填写',
          cancelButtonText: '不填，我精虫上脑了',
          type: 'warning'
        }).then(() => {
          this.$message({
            type: 'success',
            message: '填写完后再上传你就看不到这条消息了'
          });
        }).catch(() => {
          this.$message({
            type: 'warning',
            message: '你是歌姬吧'
          });
        });
        return null;
      }

      //使用FormData传数据才能更好接收到
      let formData = new FormData();
      //this.fileList[i].raw:获取图片格式
      //这里的file必须和后端Controller中的requestparam参数一致，才能上传成功，否则会报400错误
      formData.append("file", this.fileList[0].raw,  this.imgAddForm.name);
      formData.append("uid", user.id);
      //这里创建了一个axios实例,这里我想要使用multipart/form-data
      const http1 = axios.create({
        headers: {
          // 'Content-Type': 'multipart/form-data'
          "Content-Type": "multipart/form-data"
        }
      })
      http1({
        url: 'http://yang719.cn:8086/user/addImg',
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
            if(resp.data.msg)
            this.$alert('图片传送成功了，但保存失败了', '注意', {
              confirmButtonText: '确定',
              callback: action => {
                this.$message({
                  type: 'info',
                  message: '换张图吧'
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
                message: '换张图吧'
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
