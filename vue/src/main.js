// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'

import VueRouter from 'vue-router'
import router from './router'

import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'

import Video from 'video.js'
import 'video.js/dist/video-js.css'

import boxIconElement from "boxicons/src/box-icon-element";
import moment from "moment";

import '../static/css/boxicons.min.css';

import axios from "axios";

Vue.use(ElementUI)
Vue.use(VueRouter)

Vue.config.productionTip = false

Vue.filter('dateFormat', function (dateStr,pattern = "YYYY-MM-DD HH:mm:ss") {
  return moment(dateStr).format(pattern);
})

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  render: h => h(App)
})
