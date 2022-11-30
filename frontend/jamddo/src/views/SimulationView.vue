<template>
  <div class="home">
    <h1>여기는 시뮬레이션</h1>
    <button v-on:click="buyOne">로또1번 구매</button>
    <div>{{ buyOneData }}</div>

    <button v-on:click="buyBundle()">로또N번 구매</button>
    <div>{{ buyBundleData }}</div>

    <button v-on:click="buyUntilFirstPlace">1등 나올때까지 구매</button>
    <div>{{ buyUntilFirstPlaceData }}</div>
  </div>
</template>

<script>
const addr = "http://localhost:8080/simulation";
import axios from "axios";
export default {
  name: "HomeView",
  data() {
    return {
      buyOneData: "",
      buyBundleData: "",
      buyUntilFirstPlaceData: "",
    };
  },
  methods: {
    buyOne() {
      axios
        .get(addr + "/buyOne")
        .then((response) => {
          console.log(response.data);
          this.buyOneData = response.data;
        })
        .catch((error) => {
          console.log(error);
        });
    },
    buyBundle() {
      var Cnt = prompt("시행횟수를 입력해 주세요", 0);
      axios
        .get(addr + "/buyBundle/" + Cnt)
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
