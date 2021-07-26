<template>
  <div class="shortcut">
    <div class="login_out">
      <el-button-group>
        <el-button type="text" v-if="isUserShow" class="tabButton">{{username}}</el-button>
        <el-button type="text" v-on:click="changeFavouritePage()"  class="tabButton">My Favorites</el-button>
        <el-button type="text" v-if="isUserShow" v-on:click="exit()" class="tabButton">Exit</el-button>
        <el-button type="text" v-if="!isUserShow" v-on:click="this.page='login'" class="tabButton">Login</el-button>
        <el-button type="text" v-if="!isUserShow" v-on:click="this.page='register'" class="tabButton">Register</el-button>
      </el-button-group>
    </div>
  </div>
  <div>
    <el-row class="row-bg" justify="center">
      <el-col :span="4">
        <div>
          <h1>ioforfun</h1>
        </div>
      </el-col>
      <el-col :span="10">
        <el-form :inline="true">
          <el-form-item>
            <el-input v-model="searchWordInput" placeholder="Game name"></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" v-on:click="changeSearch()">Search</el-button>
          </el-form-item>
        </el-form>
      </el-col>
      <el-col :span="4">
        <div class="hot_tel">
          <a href="http://www.linkedin.com/in/yusen-zhou">LinkedIn</a>
          <p> email: zhoumulin@gmail.com </p>
        </div></el-col>
    </el-row>
  </div>
  <div>
    <el-button-group>
      <el-button v-on:click="this.page='Home'" class="tabButton">Home</el-button>
      <el-button
          v-for="categoryData in categoryDataList"
          :key="categoryData"
          v-on:click="changeCategory(categoryData.categoryId)"
          class="tabButton"
      >{{ categoryData.categoryName }}</el-button>
    </el-button-group>
  </div>
  <component
      v-bind:is="page"
      :propsUsername="username"
      :propsCategoryId="categoryId"
      :propsSearchWord="searchWord"
      :propsIsFavorite="isFavorite"
      @enlarge-text="username = $event"
  ></component>
  <!--  <component v-bind:is="page"></component>-->
</template>

<script>
// @ is an alias to /src
import Home from '@/views/Home.vue'
import gameList from '@/views/gameList.vue'
import login from '@/views/login.vue'
import register from '@/views/register.vue'

import axios from 'axios';

export default {
  name: 'content',
  components: {
    Home,
    gameList,
    login,
    register
  },
  data() {
    return {
      Home: "Home",
      gameListPage: "gameList",
      categoryDataList: [],
      page: "Home",
      categoryId: 0,
      searchWord:"",
      searchWordInput: "",
      login: "login",
      register: "register",
      username: "",
      isFavorite: false
    }
  },
  computed: {
    isUserShow() {
      return this.username.length > 0 ? true : false
    }
  },
  mounted () {
    axios({
      method: 'get',
      url: '/category',
    }).then(response => (this.categoryDataList = response.data))
    axios
      .get('/user')
      .then(response => {
        if (response.data.username != null) {
          this.username = response.data.username;
        }
      })
  },
  methods: {
    changeCategory(categoryId) {
      this.page = this.gameListPage
      this.categoryId = categoryId
      this.searchWord = ""
      this.isFavorite = false
    },
    changeFavouritePage() {
      if (this.username.length>0) {
        this.page = this.gameListPage
        this.categoryId = 0
        this.searchWord = ""
        this.isFavorite = true
      } else {
        alert("can not access my favorites before login")
      }
    },
    changeSearch() {
      this.page = this.gameListPage
      this.categoryId = 0
      this.searchWord = this.searchWordInput
      this.isFavorite = false
    },
    exit() {
      axios({
        method: 'delete',
        url: '/user/session'
      })
      this.username = ""
      this.page='Home'
    }
  }
}
</script>
<style>
.el-tabs__item {
  width:100px
}
.tabButton {
  width:120px;
}
h1 {font-size:2.5em;}
</style>
