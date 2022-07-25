<template>
  <div id="main">
    <div class="top-breadcrumb">
      <el-breadcrumb separator-class="el-icon-arrow-right">
        <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
        <el-breadcrumb-item><a href="/YangCraft">YangCraft</a></el-breadcrumb-item>
        <el-breadcrumb-item><a href="/YangCraft/user/backend">用户后台</a></el-breadcrumb-item>
        <el-breadcrumb-item>用户图片</el-breadcrumb-item>
        <el-breadcrumb-item>图片列表</el-breadcrumb-item>
      </el-breadcrumb>
    </div>
    <el-card shadow="hover" v-show="tableData.length<1" style="margin: 16px;padding: 10px">
      <p>看来你还没有添加过图片了，添加一点图片吧</p>
    </el-card>
    <el-card class="img-list" shadow="hover" v-show="tableData.length > 0">
      <el-table
        :data="tableData.filter(data => !search || data.name.toLowerCase().includes(search.toLowerCase()))"
        max-height="80vh"
        height="80vh"
        stripe="true"
        default-sort="prop: 'date', order: 'descending'">
        <el-table-column
          fixed
          prop="picture.picurl"
          label="图片"
          width="180">
          <template slot-scope="scope">
            <el-image :src="getImgUrl(scope.row.picture.picurl)">
              <div slot="error" style="font-size: 50px">
                <i class="el-icon-loading"></i>
              </div>
            </el-image>
          </template>
        </el-table-column>
        <el-table-column
          sortable
          prop="time"
          :formatter="formatDate"
          label="上传时间"
          width="160">
        </el-table-column>
        <el-table-column
          prop="name"
          label="图片名称"
          width="140">
          <template slot-scope="scope">
            <p>{{getImgName(scope.row.picture.picurl)}}</p>
          </template>
        </el-table-column>
        <el-table-column
          sortable
          prop="picture.picheight"
          label="图高"
          width="80">
        </el-table-column>
        <el-table-column
          sortable
          prop="picture.picwidth"
          label="图宽"
          width="80">
        </el-table-column>
        <el-table-column
          sortable
          prop="picture.filesize"
          label="文件大小"
          width="100">
        </el-table-column>
        <el-table-column
          fixed="right"
          align="right"
          width="240px">
          <template slot="header" slot-scope="scope">
            <el-input
              v-model="search"
              size="mini"
              placeholder="输入关键字搜索"/>
          </template>
          <template slot-scope="scope">
            <el-button
              size="mini"
              @click="handleCopyInstructions(scope.row)">生成游戏内代码</el-button>
            <el-button
              size="mini"
              type="danger"
              @click="handleDelete(scope.$index, scope.row)">删除图片</el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-card>
  </div>
</template>

<script>
import axios from "axios";
import router from "../../../router";

export default {
  name: "UserImgList",
  mounted:function () {
    this.loadList();
  },
  methods: {
    //通过地址获取base64码
    getImgBase64(){

    },
    //获取图片相对
    getImg(path){
      let iii = path.split("/");
      return iii[iii.length-1];
    },
    //获取图片代理地址
    getImgUrl(path){
      return "http://yang719.cn:8086/" + this.getImg(path);
    },
    //获取图片名字
    getImgName(path){
      return  this.getImg(path).split("$")[1]
    },
    //栏删除函数部位
    deleteRow(index, rows) {
      console.log(index+" "+rows);
      rows.splice(index, 1);
    },
    //格式化时间
    formatDate(row, column) {
      // 获取单元格数据
      let data = row[column.property]
      if(data == null) {
        return null
      }
      let dt = new Date(data)
      return dt.getFullYear() + '-' + (dt.getMonth() + 1) + '-' + dt.getDate() + ' ' + dt.getHours() + ':' + dt.getMinutes() + ':' + dt.getSeconds()
    },
    //获取表信息
    loadList() {
      var _this = this
      axios.get('http://yang719.cn:8086/user/imgList?id='+JSON.parse(sessionStorage.getItem('userInfo')).id).then(resp => {
        if (resp.data.code > 0) {
          console.log('阿这')
          _this.tableData = resp.data.data
          console.log(_this.tableData)
        }
      })
    },
    //生成游戏内代码
    handleCopyInstructions(data){
      console.log(data)
      // 创建元素用于复制
      const aux = document.createElement('input')
      // 获取复制内容
      const content = "/img create "+ this.getImg(data.picture.picurl);
      // 设置元素内容
      aux.setAttribute('value', content)
      // 将元素插入页面进行调用
      document.body.appendChild(aux)
      // 复制内容
      aux.select()
      // 将内容复制到剪贴板
      document.execCommand('copy')
      // 删除创建元素
      document.body.removeChild(aux)
      //提示信息
      this.$message({
        message: '复制成功,直接复制到游戏中去吧!',
        type: 'success'
      })
    },
    //删除图片
    handleDelete(lineId,data){
      let that = this;
      this.$confirm('此操作将永久删除该文件, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        axios.post("http://yang719.cn:8086/user/deleteImg", data.picture).then(function (resp){
          console.log(resp.data);
          if (resp.data.code>0){
            console.log("删除成功");
            that.$message({
              type: 'success',
              message: '删除成功!'
            });
            console.log("刷新界面咯");
            that.$router.go(0);
          }else {
            that.$message.error('删除失败');
            console.log("删除失败");
          }
        }).catch(function (error){
          console.info(error);
          console.log("删除请求发送失败");
        });
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        });
      });
    }
  },
  data() {
    return {
      tableData: []
    }
  }
}
</script>

<style scoped>

.top-breadcrumb{
  margin: 16px;
  font-size: 30px;
  padding: 5px;
}

.img-list{
  margin: 16px;
  width: 100%;
}

.img-list el-table{

}

</style>
