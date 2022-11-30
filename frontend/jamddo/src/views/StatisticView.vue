<template>
  <div class="home">
    <h1>여기는 통계</h1>

    <h2>이번주 1위 당첨정보</h2>
    <div>{{ WinningNumData }}</div>

    <h2>이번주 회차, 당첨번호, 상금, 당첨자 수</h2>
    <div>{{ overviewData }}</div>

    <h2>당첨번호 통계</h2>
    <div>{{ numStatisticData }}</div>

    <h2>당첨번호 통계 - 색상별로 묶기</h2>
    <div>{{ ColorStatisticData }}</div>
  </div>
</template>

<script>
const addr = "http://localhost:8080/info";
import axios from "axios";
export default {
  name: "HomeView",
  data() {
    return {
      WinningNumData: "",
      overviewData: "",
      numStatisticData: "",
      ColorStatisticData: "",
    };
  },
  created() {
    axios
      .get(addr + "/winningNum")
      .then((response) => {
        console.log(response.data);
        this.WinningNumData = response.data;
      })
      .catch((error) => {
        console.log(error);
      });
    axios
      .get(addr + "/overview")
      .then((response) => {
        console.log(response.data);
        this.overviewData = response.data;
      })
      .catch((error) => {
        console.log(error);
      });
    axios
      .get(addr + "/statistics/number")
      .then((response) => {
        console.log(response.data);
        this.numStatisticData = response.data;
      })
      .catch((error) => {
        console.log(error);
      });
    axios
      .get(addr + "/statistics/number/color")
      .then((response) => {
        console.log(response.data);
        this.ColorStatisticData = response.data;
      })
      .catch((error) => {
        console.log(error);
      });
  },
  methods: {},
};
</script>
