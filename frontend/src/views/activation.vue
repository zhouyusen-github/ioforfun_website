<template>
  <div>{{ msg }}</div>
  <router-link to="/">return home</router-link>
</template>

<script>
import axios from "axios";

export default {
  data() {
    return {
      msg: ""
    }
  },
  computed: {
    params(){
      return this.getQueryVariable('activationCode')
    }
  },
  methods: {
    getQueryVariable(variable) {
      let query = window.location.search.substring(1);
      let vars = query.split("&");
      for (let i=0;i<vars.length;i++) {
        let pair = vars[i].split("=");
        if(pair[0] == variable){return pair[1];}
      }
      return(false);
    }
  },
  mounted () {
    let activationCode = this.getQueryVariable('activationCode')
    axios({
      method: 'put',
      url: `/user/activation`,
      params: {
        activationCode: activationCode
      }
    }).then(response => {
      if (response.data.flag==true) {
        this.msg = "activate success";
      } else {
        this.msg = "activate fail";
      }
    })
  },
}
</script>



