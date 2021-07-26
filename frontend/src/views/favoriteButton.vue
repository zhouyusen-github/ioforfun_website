<template>
  <el-button :type="buttonType" icon="el-icon-star-off" @click="changeIsFavorite()" circle></el-button>
  <p>{{statusString}}</p>
</template>

<script>
import axios from "axios";

export default {
  name: 'favoriteButton',
  props: {
    propsGameId: Number,
    propsUsername: String
  },
  data() {
    return {
      gameId: this.propsGameId,
      username: this.propsUsername,
      status: {
        favorite: "Favorited",
        notFavorite: "Not Favorited"
      },
      isFavoriteBool: false,
    }
  },
  computed: {
    statusString(){
      if (this.isFavoriteBool) {
        return this.status.favorite
      } else {
        return this.status.notFavorite
      }
    },
    buttonType() {
      if (this.isFavoriteBool) {
        return "warning"
      } else {
        return ""
      }
    }
  },
  mounted () {
    this.isFavorite()
  },
  methods: {
    isFavorite() {
      if (this.username.length > 0) { // can not search without login
        axios({
          method: 'get',
          url: `/favorite/gameId/${this.gameId}`
        }).then(response => (this.isFavoriteBool = response.data))
      }
    },
    changeIsFavorite() {
      if (this.username.length > 0) { // can not change without login
        axios({
          method: 'put',
          url: `/favorite/gameId/${this.gameId}`,
          params: {
            isFavoriteBool: !this.isFavoriteBool
          }
        }).then(response => {
          if (response.data.flag==true) {
            this.isFavoriteBool = response.data.data;
          }
        })
      } else {
        alert("can not favorite without login")
      }
    }
  }
}
</script>
