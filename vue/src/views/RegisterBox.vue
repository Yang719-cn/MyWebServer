<template>
  <div id="LoginBox">
    <el-row type="flex" justify="center" align="middle" style="height: 100%">
      <el-col align="middle" :xs="22" :sm="16" :md="8" class="login-box">
        <div id="title">
          <el-divider content-position="left"><h3>注册</h3></el-divider>
        </div>
        <el-form ref="regForm" :model="regForm" :rules="rules">
          <el-form-item prop="email" >
            <el-input class="input" prefix-icon="el-icon-message" v-model="regForm.email" clearable placeholder="请输入邮箱"></el-input>
          </el-form-item>
          <el-form-item prop="name" >
            <el-input class="input" prefix-icon="el-icon-user-solid" v-model="regForm.name" clearable placeholder="请输入用户名"></el-input>
          </el-form-item>
          <el-form-item prop="password">
            <el-input class="input" prefix-icon="el-icon-lock" v-model="regForm.password" type="password" placeholder="请输入密码" show-password></el-input>
          </el-form-item>
          <el-form-item style="padding:0;">
            <el-col :span="16">
              <el-input class="input" placeholder="请输入邮箱验证码" v-model="regForm.emailCode" prefix-icon="el-icon-chat-dot-round" style="width: 100%;margin: 0"></el-input>
            </el-col>
            <el-col :span="8">
              <el-button type="primary" @click="getEmailCode()" plain :disabled ="btnGetCode.canGet">{{btnGetCode.txt}}</el-button>
            </el-col>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="submitForm('regForm')" plain  class="logOrReg">注册</el-button>
          </el-form-item>
        </el-form>
        <div class="copyright"><a href="https://beian.miit.gov.cn/">&copy;鄂ICP备20002542号</a></div>
      </el-col>
    </el-row>
  </div>
</template>

<script>

import axios from "axios";
import router from "../router";


export default {
  name: 'RegisterBox',
  data () {
    return {
      regForm: {
        email: '',
        name:'',
        password: '',
        emailCode: ''
      },
      rules: {
        email: [
          {required: true, message: '请输入邮箱', trigger: 'blur'}
        ],
        name: [
          {required: true, message: '请输入用户名', trigger: 'blur'}
        ],
        password: [
          {required: true, message: '请输入密码', trigger: 'blur'}
        ],
      },
      btnGetCode: {
        txt :'获取验证码',
        canGet:false,    //获取验证码按钮是否禁用
        time:0,
        timer:null,
      }
    }
  },
  methods: {
    timer1(){   //验证码60s内不能继续点击的函数
            if(this.btnGetCode.time > 0) {
                console.log(this.btnGetCode.time + "s后重新获取");
                this.btnGetCode.time--;
                this.btnGetCode.txt = "请等待" + this.btnGetCode.time + "s"
                this.btnGetCode.timer = setTimeout(this.timer1,1000)
            }else{
                this.btnGetCode.time = 0;
                this.btnGetCode.btnText = "获取验证码";
                this.btnGetCode.canGet = false;
                clearTimeout(this.btnGetCode.timer);
            }
    },  
    getEmailCode(){
      console.log(this.regForm.email)
      let ifEmal = /^[A-Za-z0-9\u4e00-\u9fa5]+@[a-z0-9_-]+(\.[a-z]+)+$/;
      if (ifEmal.test(this.regForm.email)){
        axios.get("http://yang719.cn:8086/user/getEmailCode?regEmail="+this.regForm.email).then(resp => {
          console.log('阿这')
          this.$message({
            message: '以请求发送邮箱验证码',
            type: 'success'
          })
        })
      console.log('倒数60s');
      this.btnGetCode.canGet = true;  //禁用按钮，防止多次触发
      this.btnGetCode.time = 60;  //60秒后能继续按按钮
      this.timer1();
      }
      else {
        console.log('没有邮箱')
        this.$alert('请输入邮箱','注意',{
          confirmButtonText: '确定',
        })
        return;
      }

    },
    submitForm (regForm) {
      let that = this;
      let ifEmal = /^[A-Za-z0-9\u4e00-\u9fa5]+@[a-z0-9_-]+(\.[a-z]+)+$/;
      let postDate;
      this.$refs[regForm].validate((valid) => {
        if (valid) {
          if (this.regForm.email.split(' ').join('').length !== 0) {
            if (ifEmal.test(this.regForm.email)) {
              console.log('输入的是邮箱')
              postDate = {email: this.regForm.email,
                          name: this.regForm.name,
                          password: this.regForm.password,
                          salt: this.regForm.emailCode
              };
            }else {
              this.$alert('请输入的是邮箱','注意',{
                confirmButtonText:'确定'
              })
            }
            console.log(sessionStorage.getItem("emailCode")+"---"+this.regForm.emailCode)
            console.log(postDate);
            //判断完毕开始输送post
            axios.post("http://yang719.cn:8086/user/register", postDate).then(function (resp){
              console.log(resp.data);
              if (resp.data.code>0){
                that.$alert('注册成功','恭喜',{
                  confirmButtonText:'确定'
                })
                console.log("注册成功");
                console.log('准备跳转')
                router.push({path:'/YangCraft/user/login',query: { user: resp.data.data }});
                console.log('跳转')
              }else {
                that.$alert(resp.data.msg, '注意', {
                  confirmButtonText: '确定',
                  callback: action => {}
                });
              }
            }).catch(function (error){
              console.info(error);
            });

          }
          else {
            this.$alert('邮箱输入格式错误', '注意', {
              confirmButtonText: '确定',
              callback: action => {}
            });
          }
        } else {
          this.$alert('信息请填写完全','注意',{
            confirmButtonText: '确定',
            callback: action => {}
          });
          return false;
        }
      })
    }
  }
}
</script>

<style scoped>
#LoginBox{
  margin: 0;
  padding: 0;
  height: 100%;
  width: 100%;

}

.login-box {
  padding: 30px;
  border-radius: 10px;
  box-shadow: 0 0 30px #404244;
  background: rgba(225, 225, 225, 0.3);
  align-items: center;
  backdrop-filter: blur(10px);
  text-align: center;
}

.login-box #title {
  text-align: center;
  margin-top: 0;
  margin-bottom: 30px;
  background: none;
}

.login-box #title el-divider{
  color: azure;
  background: none;
}

.login-box .logOrReg {
  margin-top: 16px;
  width: 60%;
}

.login-box .input {
  margin-bottom: 3px;
  margin-top: 3px;
}

.login-box .copyright {
  margin-top: 30px;
  text-align: center;
}

.login-box .copyright a{
  color: black;
  text-decoration:none;
}

</style>
