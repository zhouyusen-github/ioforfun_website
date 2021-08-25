<template>
  <div>
    <div>
      <div v-for="searchResult in searchResultList.list" :key="searchResult">
        <el-row class="row-bg" justify="center">
          <el-col :span="6"><img :src="'img/'+searchResult.image" alt=""></el-col>
          <el-col :span="8">
            <h2 class="textLeft">{{ searchResult.name }}</h2>
            <p class="textLeft">{{ searchResult.introduction }}</p>
            <a :href="searchResult.url" class="textLeft">play</a>
<!--            <p>-->
<!--              <span>rating</span>-->
<!--              <span>{{ searchResult.rate }}</span>-->
<!--            </p>-->
          </el-col>
          <el-col :span="4">
            <favoriteButton :propsGameId="searchResult.gameId" :propsUsername="username" />
          </el-col>
        </el-row>
      </div>
    </div>
    <div>
      <span>There are {{ pageLength }} pages and {{ searchResultList.totalCount }} games</span>
    </div>
    <div>
      <el-pagination
          background
          layout="pager"
          :page-count="pageLength"
          :current-page="currentPage"
          @current-change="handleCurrentChange"
      >
      </el-pagination>
    </div>
  </div>
</template>

<script>
// @ is an alias to /src
import favoriteButton from '@/views/favoriteButton.vue'
import axios from "axios";

export default {
  name: 'gameList',
  props: {
    propsCategoryId: String,
    propsSearchWord: String,
    propsUsername: String,
    propsIsFavorite: Boolean
  },
  components: {
    favoriteButton
  },
  data() {
    return {
      categoryId: this.propsCategoryId,
      searchResultList: [],
      pageSize: 5,
      currentPage: 1,
      searchWord: this.propsSearchWord,
      username: this.propsUsername,
      isFavorite: this.propsIsFavorite
    }
  },
  computed: {
    pageLength() {
      return Math.ceil((this.searchResultList.totalCount)/this.pageSize)
    }
  },
  watch: {
    propsCategoryId (newV) {
      this.categoryId = newV
      if (this.categoryId!=0) {
        this.currentPage = 1
        this.searchWord = ""
        this.isFavorite = false
        this.search()
      }
    },
    propsSearchWord (newV) {
      this.searchWord = newV
      if (this.searchWord!="") {
        this.currentPage = 1
        this.categoryId = 0
        this.isFavorite = false
        this.search()
      }
    },
    propsIsFavorite (newV) {
      this.isFavorite = newV
      if (this.isFavorite==true) {
        this.currentPage = 1
        this.searchWord = ""
        this.categoryId = 0
        this.getFavoriteList()
      }
    },
  },
  mounted () {
    if (this.isFavorite==true) {
      this.getFavoriteList()
    } else {
      this.search()
    }
  },
  methods: {
    search() {
      axios({
        method: 'get',
        url: '/game',
        params: {
          categoryId: this.categoryId,
          currentPage: this.currentPage,
          name: this.searchWord
        }
      }).then(response => (this.searchResultList = response.data))
    },
    handleCurrentChange(val) {
      this.currentPage = val;
      this.search()
    },
    getFavoriteList() {
      axios({
        method: 'get',
        url: '/favorite',
        params: {
          currentPage: this.currentPage,
        }
      }).then(response => (this.searchResultList = response.data))
    }
  }
}
</script>
<style scoped>
.el-row{
  margin-bottom: +30px;
  display: flex;
  flex-wrap: wrap
}

.page_num {
  text-align:center
}

.textLeft {text-align: left}
</style>
