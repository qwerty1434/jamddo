<template>
  <div>
    <div v-if="authorizationToken == '' || authorizationToken == null">
      <b-button variant="outline-success" v-b-modal="'LoginModal'"
        >로그인</b-button
      >

      <b-modal
        id="LoginModal"
        ref="modal"
        title="로그인"
        @show="resetModal"
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
              type="password"
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
              type="password"
              required
              v-model="SignupPassword"
            ></b-form-input>
          </b-form-group>
          <b-form-group label="비밀번호확인" label-for="pwd-input">
            <b-form-input
              id="password-input"
              type="password"
              required
              v-model="SignupPasswordConfirm"
            ></b-form-input>
          </b-form-group>
        </form>
      </b-modal>
    </div>
    <div v-else>
      <b-button variant="outline-success" @click="Logout">로그아웃</b-button>
    </div>

    <div>{{ UserNickname }}</div>
    <img class="profile" src="@/assets/basicProfile.png" />
    <div>
      {{ UserPoint | numFilter }}원 | {{ UserCnt | numFilter }}회 |
      <span class="initialization" @click="resetPoint">초기화</span>
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
      UserNickname:
        localStorage.getItem("nickname") == null
          ? "익명"
          : localStorage.getItem("nickname"),
      UserPoint:
        localStorage.getItem("point") == null
          ? 0
          : localStorage.getItem("point"),
      UserCnt:
        localStorage.getItem("cnt") == null ? 0 : localStorage.getItem("cnt"),
      authorizationToken:
        localStorage.getItem("authorization") == null
          ? ""
          : localStorage.getItem("authorization"),
    };
  },

  props: {
    data: {
      default: () => {
        return {
          nickname: "",
          point: "",
          cnt: "",
        };
      },
    },
  },
  watch: {
    data() {
      this.UserNickname = this.data.nickname;
      this.UserPoint = this.data.point;
      this.UserCnt = this.data.cnt;
    },
  },

  methods: {
    Login() {
      axios
        .post(addr + "/login", {
          nickname: this.LoginNickname,
          password: this.LoginPassword,
        })
        .then((response) => {
          this.UserNickname = response.data.nickname;
          this.UserPoint = response.data.point;
          this.UserCnt = response.data.cnt;
          this.authorizationToken = response.headers["authorization"];
          // 로컬 스토리지 저장
          localStorage.setItem(
            "authorization",
            response.headers["authorization"]
          );
          localStorage.setItem("nickname", response.data.nickname);
          localStorage.setItem("point", response.data.point);
          localStorage.setItem("cnt", response.data.cnt);
        })
        .catch((error) => {
          console.log(error);
        });
    },
    Logout() {
      this.LoginNickname = "";
      this.LoginPassword = "";
      this.SignupNickname = "";
      this.SignupPassword = "";
      this.SignupPasswordConfirm = "";
      this.UserNickname = "익명";
      this.UserPoint = 0;
      this.UserCnt = 0;
      this.authorizationToken = "";
      // 로컬 스토리지 제거
      localStorage.removeItem("authorization");
      localStorage.removeItem("nickname");
      localStorage.removeItem("point");
      localStorage.removeItem("cnt");
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
      // this.LoginNickname = "";
      // this.LoginPassword = "";
      // this.SignupNickname = "";
      // this.SignupPassword = "";
      // this.SignupPasswordConfirm = "";
      // this.UserNickname = "익명";
      // this.UserPoint = 0;
      // this.authorizationToken = "";
    },
    resetPoint() {
      if (confirm("정말 초기화하시겠습니까??") == true) {
        //확인
        const headers = {
          Authorization: localStorage.getItem("authorization"),
        };
        axios
          .get(addr + "/resetpoint", {
            params: {},
            headers: headers,
          })
          .then((response) => {
            localStorage.setItem("point", response.data.point);
            localStorage.setItem("cnt", response.data.cnt);
            this.UserPoint = response.data.point;
            this.UserCnt = response.data.cnt;
            console.log(response);
          })
          .catch((error) => {
            console.log(error);
          });
      } else {
        //취소
        return;
      }
    },
  },
};
</script>
<style>
.profile {
  width: 50px;
  height: 50px;
}
.initialization {
  color: blue;
}
.initialization:hover {
  cursor: pointer;
  color: green;
}
</style>
