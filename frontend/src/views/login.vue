
<template>
  <el-row class="row-bg" justify="center">
    <el-col :span="8">
      <el-form
          :label-position="ruleForm.labelPosition"
          :model="ruleForm"
          :rules="rules"
          ref="ruleForm"
          label-width="100px" class="demo-ruleForm">
        <el-form-item label="username" prop="username">
          <el-input v-model="ruleForm.username"></el-input>
        </el-form-item>
        <el-form-item label="password" prop="password">
          <el-input v-model="ruleForm.password"></el-input>
        </el-form-item>
        <el-form-item label="verification" prop="verification">
          <el-input v-model="ruleForm.verification"></el-input>
          <img v-bind:src="verificationCodeUrl" v-on:click="refreshVerificationCode()">
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="submitForm('ruleForm')">Login</el-button>
          <el-button @click="resetForm('ruleForm')">Reset</el-button>
        </el-form-item>
      </el-form>
    </el-col>
  </el-row>
</template>

<script>
import axios from "axios";
export default {
  data() {
    return {
      verificationCodeUrl: "verificationCode",
      ruleForm: {
        username: '',
        password: '',
        verification: '',
        labelPosition: "right"
      },
      rules: {
        username: [
          { required: true, message: '', trigger: 'blur' },
          { min: 5, max: 20, message: '5 and 20 characters', trigger: 'blur' }
        ],
        password: [
          { required: true, message: '', trigger: 'blur' },
          { min: 5, max: 20, message: '5 and 20 characters', trigger: 'blur' }
        ],
        verification: [
          { required: true, message: '', trigger: 'blur' },
          { message: '', trigger: 'blur' }
        ]
      }
    };
  },
  methods: {
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          alert('submit!');
          this.login();
        } else {
          console.log('error submit!!');
          return false;
        }
      });
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
    },
    refreshVerificationCode: function() {
      this.verificationCodeUrl = 'verificationCode?time='+new Date().getTime();
    },
    login: function() {
      axios({
        method: 'get',
        url: '/user/session',
        params: {
          username: this.ruleForm.username,
          password: this.ruleForm.password,
          check: this.ruleForm.verification
        },
      }).then(res => {
        if (res.data.flag == true) {
          alert("login success")
          this.updateUser()
        } else {
          alert("login fail, reason: "+res.data.errorMsg)
        }
      }).catch(res => { // 如果请求失败
        alert(res.data.msg)
      })
    },
    updateUser: function() {
      this.$emit('enlargeText', this.ruleForm.username)
    }
  }
}
</script>

<style>

</style>
