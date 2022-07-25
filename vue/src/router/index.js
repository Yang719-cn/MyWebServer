import Vue from 'vue'
import Router from 'vue-router'
import HelloWorld from '@/components/HelloWorld'
import Login from '../components/Login'
import Backend from "../components/Backend";
import userImgList from "../views/user/img/UserImgList";
import userImgAdd from "../views/user/img/UserImgAdd";
import mcMap from "../views/McMap";
import userBackendHome from "../views/user/UserBackendHome";
import yangCraft from "../components/YangCraft";
import music163 from "../components/Music163";
import cityPlanning from "../views/user/report/suggest/CityPlanning";
import register from "../components/Register";
import loginOrRegister from "../components/LoginOrRegister"
import webPage from "../views/user/WebPage"

Vue.use(Router)

export default new Router({
  mode: "hash",
  routes: [
    {
      path: '/',
      name: 'HelloWorld',
      component: HelloWorld
    },
    {
      path: '/YangCraft',
      name: 'YangCraft',
      component: yangCraft,
      meta:{
        title: 'YangCraft'
      }
    },
    {
      path: '/YangCraft/user',
      name: 'user',
      component: loginOrRegister,
    },
    {
      path: '/YangCraft/user/login',
      name: 'Login',
      component: Login,
      meta:{
        title: '用户登录'
      },
    },
    {
      path: '/YangCraft/user/register',
      name: 'register',
      component: register,
      meta:{
        title: '用户注册'
      },
    },
    {
      path: '/YangCraft/user/backend',
      name: 'backend',
      component: Backend,
      meta:{
        title: '用户后台'
      },
      children: [
        {
          path: '',
          name: '',
          component: userBackendHome
        },
        {
          path: 'userImgAdd',
          name: 'userImgAdd',
          component: userImgAdd
        },
        {
          path: 'userImgList',
          name: 'userImgList',
          component: userImgList
        },
        {
          path: 'mcMap',
          name: 'mcMap',
          component: mcMap
        },
        {
          path: 'music163',
          name: 'music163',
          component: music163
        },
        {
          path: 'cityPlanning',
          name: 'cityPlanning',
          component: cityPlanning
        },
        {
          path: 'webPage',
          name: 'webPage',
          component: webPage
        },
      ]
    }

  ]
})
