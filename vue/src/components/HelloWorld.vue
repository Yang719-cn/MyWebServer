<template>
  <div class="hello">
    <section class="showcase">
	<div class="video-container">
		<video id="mainBg" :autoplay="true" muted loop :src="bgUri"/>
	</div>		
	
	
	<div class="content" id="show"></div>
	<div class="content" id="welcomeTitle">
		<h1>欢迎来到我的个人主页</h1>
		<h3>by.&nbsp;&nbsp;Yang719</h3>
		<router-link to="YangCraft" class="btn">YangCraft</router-link>&nbsp;&nbsp;
		<a href="#about" class="btn">更多信息</a>
	</div>
</section>
	
<section id="about">
	<h1>关于此网站</h1>
	<p>一个新时代网络农民工的主页罢了</p>
	<h2>联系方式<br>
	邮箱:laoyang719@qq.com
	</h2>
	<h3>代码仓库<br>
	<a href="https://gitee.com/sister-97-i-like-you-so-much">Gitee</a>&nbsp;&nbsp;<a href="https://github.com/Yang719-cn">Github</a>
	</h3><br>
	<div>网站备案号：<a href="https://beian.miit.gov.cn/">鄂ICP备20002542号</a></div>	
</section>

  </div>
</template>

<script>
export default {
  name: 'HelloWorld',
  data () {
    return {
      msg: "Welcome to Yang719's home",
	  bgUri: "static/vdo/video3.mp4",
    }
  },
  mounted() {
    this.bgChange()
  },
  methods:{
    bgChange(){
      console.log("准备换壁纸视频")
	  	var idList = new Array("static/vdo/video1.mp4",
		"static/vdo/video2.mp4",
		"static/vdo/video3.mp4",
		"static/vdo/video4.mp4",
		"static/vdo/video5.mp4",
		"static/vdo/video6.mp4",
		"static/vdo/video7.mp4",);
		var idLen = idList.length;
		var num = Math.floor(Math.random()*idLen);
		this.$data.bgUri = idList[num];
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Open+Sans:wght@300;400&display=swap');

:root {
	--primary-color: #3a4052;
}

* {
	box-sizing: border-box;
	margin: 0;
	padding: 0;
}

body {
	font-family: 'Open Sans', sans-serif;
	line-height: 1.5;
}

a {
	text-decoration: none;
	color: var(--primary-color);
}

h1 {
	font-weight: 300;
	font-size: 60px;
	line-height: 1.2;
	margin-bottom: 15px;
}

.showcase {
	height: 100vh;
	display: flex;
	align-items: center;
	justify-content: center;
	text-align: center;
	color: #fff;
	padding: 0 20px;
}

.video-container {
	position: absolute;
	top: 0;
	left: 0;
	width: 100%;
	height: 100%;
	overflow: hidden;
	background: var(--primary-color) url('/static/img/cover.jpg') no-repeat center
		center/cover;
}

.video-container video {
	min-width: 100%;
	min-height: 100%;
    position: absolute;
	top: 50%;
	left: 50%;
	transform: translate(-50%, -50%);
	object-fit: cover;
}

.video-container:after {
	content: '';
	z-index: 1;
	height: 100%;
	width: 100%;
	top: 0;
	left: 0;
	background: rgba(0, 0, 0, 0.5);
	position: absolute;
}

.content {
	z-index: 2;
}

.btn {
	display: inline-block;
	padding: 10px 30px;
	background: var(--primary-color);
	color: #fff;
	border-radius: 5px;
	border: solid #fff 1px;
	margin-top: 25px;
	opacity: 0.7;
}

.btn:hover {
	transform: scale(0.98);
}

#about {
	padding: 40px;
	text-align: center;
}

#about p {
	font-size: 1.2rem;
	max-width: 600px;
	margin: auto;
}

#about h2 {
	margin: 30px 0;
	color: var(--primary-color);
}

#YangCraft{
	align-items: center;
	justify-content: center;
	text-align: center;
	padding: 40px;
	background: var(--primary-color) url('/static/img/15and45.png') no-repeat center
		center/cover;
	height: 100vh;
}

#YangCraft h1{
	font-size: 70px;
	color: #FFFFFF;
	
}

#YangCraft h4{
	font-size: 30px;
	color: #FFFFFF;
	
}

.social a {
	margin: 0 5px;
}
</style>
