<template>
  <div class="home">
    <h1>여기는 시뮬레이션</h1>
    <ProfileComp :data="userData" />

    <b-button variant="outline-primary" v-on:click="buyOne"
      >1게임 진행</b-button
    >
    <BuyoneComp :data="buyOneData" :winningNumOnly="winningNumOnly" />

    <h1>테스트만 해보기</h1>

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

    <b-button variant="outline-primary" v-on:click="buyUntilFirstPlace">
      1등 나올때까지 진행
    </b-button>
    <BuyUntilFirstComp :data="buyUntilFirstPlaceData" />
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
        console.log(error);
      });
  },
  methods: {
    buyOne() {
      if (localStorage.getItem("authorization") != null) {
        // 헤더에 토큰 담아서 보내기
        const headers = {
          Authorization: localStorage.getItem("authorization"),
        };
        console.log("헤더에 토큰담아서 보내기!!!!");
        axios
          .post(
            "http://localhost:8080/user/substractpoint",
            {}, // body부분, 빈값이라도 넣어두지 않으면 header를 body로 인식하는듯
            {
              headers: headers,
            }
          )
          .then((response) => {
            console.log(response);
            localStorage.setItem("nickname", response.data.nickname);
            localStorage.setItem("point", response.data.point);
            this.userData = {
              nickname: response.data.nickname,
              point: response.data.point,
            };
            console.log(this.userData);
          })
          .catch((error) => {
            console.log(error);
          });
      }
      axios
        .get(addr + "/buyOne")
        .then((response) => {
          this.buyOneData = response.data;
        })
        .catch((error) => {
          console.log(error);
        });
    },
    buyBundle() {
      axios
        .get(addr + "/buyBundle/" + this.Cnt)
        .then((response) => {
          this.buyBundleData = response.data;
        })
        .catch((error) => {
          console.log(error);
        });
    },
    buyUntilFirstPlace() {
      axios
        .get(addr + "/untilFirstPlace")
        .then((response) => {
          this.buyUntilFirstPlaceData = response.data;
        })
        .catch((error) => {
          console.log(error);
        });
    },
  },
};
</script>
