
<template>
  <div v-if="waitingStatus">{{waitingMessage}}</div>
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
        <el-form-item label="email" prop="email">
          <el-input v-model="ruleForm.email"></el-input>
        </el-form-item>
        <el-form-item label="verification" prop="verification">
          <el-input v-model="ruleForm.verification"></el-input>
          <img v-bind:src="verificationCodeUrl" v-on:click="refreshVerificationCode()">
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="submitForm('ruleForm')">Register</el-button>
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
      waitingMessage: "Registration in progress, please wait",
      waitingStatus: false,
      ruleForm: {
        username: '',
        password: '',
        email: '',
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
        email: [
          { required: true, message: '', trigger: 'blur' },
          {validator: this.validateEmail, message: 'wrong email format',trigger: 'blur'}
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
          this.waitingStatus = true
          this.register();
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
    register: function() {
      let user = {
        username: this.ruleForm.username,
        password: this.ruleForm.password,
        email: this.ruleForm.email,
        check: this.ruleForm.verification
      }

      axios({
        method: 'post',
        url: '/user',
        data: user
      }).then(res => {
        if (res.data.flag == true) {
          alert("Registration is success, please follow the instruction of email to activate your account ")
          this.waitingStatus = false
        } else {
          alert("Registration is fail, reason: "+res.data.errorMsg)
        }
      }).catch(res => {
        alert(res.data.msg)
      })
    },
    validateEmail: function() {
      return /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((.[a-zA-Z0-9_-]{2,3}){1,2})$/.test(this.ruleForm.email)
    }
  }
}
</script>

<style>

</style>
