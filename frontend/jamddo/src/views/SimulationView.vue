<template>
  <div class="home">
    <h1>여기는 시뮬레이션</h1>
    <b-button variant="outline-primary" v-on:click="buyOne"
      >1게임 진행</b-button
    >
    <!-- <div>{{ buyOneData }}</div> -->
    <BuyoneComp :data="buyOneData" />
    <!-- <div v-for="(item, index) in buyOneData" :key="index">{{ item }}</div> -->

    <!-- <button v-on:click="buyBundle()">로또N번 구매</button> -->

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
    <!-- <div>{{ buyBundleData }}</div> -->

    <b-button variant="outline-primary" v-on:click="buyUntilFirstPlace">
      1등 나올때까지 진행
    </b-button>
    <div>{{ buyUntilFirstPlaceData }}</div>
  </div>
</template>

<script>
const addr = "http://localhost:8080/simulation";
import axios from "axios";
import BuyoneComp from "@/components/BuyoneComp";
import BuybundleComp from "@/components/BuybundleComp";
export default {
  name: "HomeView",
  components: { BuyoneComp, BuybundleComp },
  data() {
    return {
      buyOneData: null,
      buyBundleData: [],
      buyUntilFirstPlaceData: null,
      Cnt: 5,
    };
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
          console.log(response.data);
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
