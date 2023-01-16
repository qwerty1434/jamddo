<template>
  <div class="home">
    <h1>여기는 시뮬레이션</h1>
    <ProfileComp />

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
