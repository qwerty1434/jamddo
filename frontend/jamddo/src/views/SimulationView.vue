<template>
  <div class="home">
    <h1>로또 시뮬레이션</h1>
    <ProfileComp :data="userData" />

    <b-button variant="outline-primary" v-on:click="buyOne">시작</b-button>
    <BuyoneComp :data="buyOneData" :winningNumOnly="winningNumOnly" />

    <hr />

    <h1>테스트만 해볼래요</h1>
    <div class="BundleGame">
      <form v-on:submit.prevent="buyBundle">
        연속
        <input
          v-model="Cnt"
          type="number"
          value="5"
          max="500"
          style="width: 70px"
        />
        게임
        <b-button variant="outline-primary" type="submit">진행</b-button>
      </form>
      <BuybundleComp :data="buyBundleData" />
    </div>

    <div class="UntilFirstGame">
      <b-button variant="outline-primary" v-on:click="buyUntilFirstPlace">
        1등 나올때까지 진행
      </b-button>
      <BuyUntilFirstComp :data="buyUntilFirstPlaceData" />
    </div>
  </div>
</template>

<script>
const addr = "http://localhost:8080/simulation";
import axios from "axios";
import BuyoneComp from "@/components/BuyoneComp";
import BuybundleComp from "@/components/BuybundleComp";
import BuyUntilFirstComp from "@/components/BuyUntilFirstComp";
import ProfileComp from "@/components/ProfileComp";

export default {
  name: "SimulationView",
  components: { BuyoneComp, BuybundleComp, BuyUntilFirstComp, ProfileComp },
  data() {
    return {
      winningNumOnly: null,
      buyOneData: null,
      buyBundleData: [],
      buyUntilFirstPlaceData: null,
      Cnt: 5,
      userData: "",
    };
  },
  created() {
    axios
      .get(addr + "/winningNumOnly")
      .then((response) => {
        this.winningNumOnly = response.data.winningNumArr;
      })
      .catch((error) => {
        alert(error.response.data);
      });
  },
  methods: {
    buyOne() {
      // 헤더에 토큰 담아서 보내기
      const headers = {
        Authorization: localStorage.getItem("authorization"),
      };
      axios
        .get(addr + "/buyOne", {
          params: {},
          headers: headers,
        })
        .then((response) => {
          this.buyOneData = response.data;
          axios
            .post(
              "http://localhost:8080/user/substractpoint",
              {}, // body부분, 빈값이라도 넣어두지 않으면 header를 body로 인식하는듯
              {
                headers: headers,
              }
            )
            .then((response) => {
              localStorage.setItem("nickname", response.data.nickname);
              localStorage.setItem("point", response.data.point);
              localStorage.setItem("cnt", response.data.cnt);
              this.userData = {
                nickname: response.data.nickname,
                point: response.data.point,
                cnt: response.data.cnt,
              };
            })
            .catch(() => {
              // 비로그인 상태로 실행했을 경우
            });
        })
        .catch((error) => {
          alert(error.response.data);
        });
    },
    buyBundle() {
      axios
        .get(addr + "/buyBundle/" + this.Cnt)
        .then((response) => {
          this.buyBundleData = response.data;
        })
        .catch((error) => {
          alert(error.response.data);
        });
    },
    buyUntilFirstPlace() {
      axios
        .get(addr + "/untilFirstPlace")
        .then((response) => {
          this.buyUntilFirstPlaceData = response.data;
        })
        .catch((error) => {
          alert(error.response.data);
        });
    },
  },
};
</script>
<style>
@keyframes blink-effect {
  50% {
    opacity: 0;
  }
}
.blink {
  animation: blink-effect 1s step-end infinite;
  /* 
        animation-name: blink-effect; 
        animation-duration: 1s; 
        animation-iteration-count:infinite; 
        animation-timing-function:step-end; 
    */
}
</style>
