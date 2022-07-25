<template>
  <div id="LoginBox">
    <el-row type="flex" justify="center" align="middle" style="height: 100%">
      <el-col align="middle" :xs="22" :sm="16" :md="8" class="login-box">
        <div id="title">
          <el-divider content-position="left"><h3>欢迎来到Yangcraft</h3></el-divider>
        </div>
        <el-form ref="loginForm" :model="loginForm" :rules="rules">
          <el-form-item prop="userI" >
            <el-input class="input" prefix-icon="el-icon-user-solid" v-model="loginForm.userI" clearable placeholder="请输入用户名或邮箱"></el-input>
          </el-form-item>
          <el-form-item prop="password">
            <el-input class="input" prefix-icon="el-icon-lock" v-model="loginForm.password" type="password" placeholder="请输入密码" show-password></el-input>
          </el-form-item>
          <p style="padding: 0;text-align: left">
            还没有账号？<router-link to="register" style="color: deepskyblue;text-decoration: none">立即注册</router-link>
            <a href="" style="color: greenyellow;text-decoration: none;float: right">忘记密码？</a>
          </p>
          <el-form-item>
            <el-button type="primary" @click="submitForm('loginForm')" plain  class="logOrReg">登录</el-button>
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
  name: 'LoginBox',
  data () {
    return {
      loginForm: {
        userI: '',
        password: ''
      },
      rules: {
        userI: [
          {required: true, message: '请输入用户名', trigger: 'blur'}
        ],
        password: [
          {required: true, message: '请输入密码', trigger: 'blur'}
        ]
      },
    }
  },
  methods: {
    submitForm (loginForm) {
      let ifEmal = /^[A-Za-z0-9\u4e00-\u9fa5]+@[a-z0-9_-]+(\.[a-z]+)+$/;
      let postDate;
      this.$refs[loginForm].validate((valid) => {
        if (valid) {
          if (this.loginForm.userI.split(' ').join('').length !== 0) {
            if (ifEmal.test(this.loginForm.userI)) {
              console.log('输入的是邮箱')
              postDate = {email: this.loginForm.userI,password: this.loginForm.password};
            } else {
              console.log('输入的是用户名')
              postDate = {name: this.loginForm.userI,password: this.loginForm.password};
            }

            console.log(postDate);

            //判断完毕开始输送post
            axios.post("http://yang719.cn:8086/user/login", postDate).then(function (resp){
              console.log(resp.data);
              if (resp.data.code>0){
                console.log("登录成功");
                sessionStorage.setItem('userInfo', JSON.stringify(resp.data.data))
                console.log(JSON.parse(sessionStorage.getItem('userInfo')))
                console.log('准备跳转')
                router.push({path:'/YangCraft/user/backend',query: { user: resp.data.data }});
                console.log('跳转')
              }else {
                alert("登陆失败");
              }
            }).catch(function (error){
              console.info(error);
            });

          } else {
            alert('请不要输入空格')
          }
        } else {
          alert('error submit!!')
          return false
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
