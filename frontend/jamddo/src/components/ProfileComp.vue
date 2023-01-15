<template>
  <div>
    <div>닉네임</div>
    <img class="profile" src="@/assets/basicProfile.png" />
    <div>포인트</div>
    <div>
      <b-button variant="outline-success" v-b-modal="'LoginModal'"
        >로그인</b-button
      >

      <b-modal
        id="LoginModal"
        ref="modal"
        title="로그인"
        @show="resetModal"
        @hidden="resetModal"
        @ok="Login"
      >
        <form ref="form">
          <b-form-group label="닉네임" label-for="nickname-input">
            <b-form-input
              id="nickname-input"
              required
              v-model="LoginNickname"
            ></b-form-input>
          </b-form-group>
          <b-form-group label="비밀번호" label-for="pwd-input">
            <b-form-input
              id="password-input"
              required
              v-model="LoginPassword"
            ></b-form-input>
          </b-form-group>
        </form>
      </b-modal>

      &nbsp;
      <b-button variant="outline-success" v-b-modal="'SignupModal'"
        >회원가입</b-button
      >

      <b-modal
        id="SignupModal"
        ref="modal"
        title="회원가입"
        @show="resetModal"
        @hidden="resetModal"
        @ok="SignIn"
      >
        <form ref="form">
          <b-form-group label="닉네임" label-for="nickname-input">
            <b-form-input
              id="nickname-input"
              required
              v-model="SignupNickname"
            ></b-form-input>
          </b-form-group>
          <b-form-group label="비밀번호" label-for="pwd-input">
            <b-form-input
              id="password-input"
              required
              v-model="SignupPassword"
            ></b-form-input>
          </b-form-group>
          <b-form-group label="비밀번호확인" label-for="pwd-input">
            <b-form-input
              id="password-input"
              required
              v-model="SignupPasswordConfirm"
            ></b-form-input>
          </b-form-group>
        </form>
      </b-modal>
    </div>
  </div>
</template>
<script>
const addr = "http://localhost:8080/user";
import axios from "axios";
export default {
  name: "ProfileComp",
  data() {
    return {
      LoginNickname: "",
      LoginPassword: "",
      SignupNickname: "",
      SignupPassword: "",
      SignupPasswordConfirm: "",
    };
  },
  methods: {
    Login() {
      axios
        .post(addr + "/login", {
          nickname: this.LoginNickname,
          password: this.LoginPassword,
        })
        .then((response) => {
          console.log(response);
        })
        .catch((error) => {
          console.log(error);
        });
    },
    SignIn() {
      axios
        .post(addr + "/signup", {
          nickname: this.SignupNickname,
          password: this.SignupPassword,
          passwordConfirm: this.SignupPasswordConfirm,
        })
        .then((response) => {
          console.log(response);
        })
        .catch((error) => {
          console.log(error);
        });
    },
    resetModal() {
      this.LoginNickname = "";
      this.LoginPassword = "";
      this.SignupNickname = "";
      this.SignupPassword = "";
      this.SignupPasswordConfirm = "";
    },
  },
};
</script>
<style>
.profile {
  width: 300px;
  height: 300px;
}
</style>
