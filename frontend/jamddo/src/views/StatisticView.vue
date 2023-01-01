<template>
  <div class="home">
    <h1>여기는 통계</h1>

    <h2>이번주 1위 당첨정보</h2>
    <WinningNumComp :data="WinningNumData" />

    <h2>이번주 회차, 당첨번호, 상금, 당첨자 수</h2>
    <OverviewComp :data="OverviewData" />

    <h2>당첨번호 통계</h2>
    <NumStatisticDataComp :data="NumStatisticData" />
  </div>
</template>

<script>
const addr = "http://localhost:8080/info";
import axios from "axios";
import WinningNumComp from "@/components/statistic/WinningNumComp.vue";
import OverviewComp from "@/components/statistic/OverviewComp.vue";
import NumStatisticDataComp from "@/components/statistic/NumstatisticDataComp.vue";
export default {
  name: "StatisticView",
  components: {
    WinningNumComp,
    OverviewComp,
    NumStatisticDataComp,
  },
  data() {
    return {
      WinningNumData: "",
      OverviewData: "",
      NumStatisticData: "",
    };
  },
  created() {
    axios
      .get(addr + "/winningNum")
      .then((response) => {
        this.WinningNumData = response.data;
      })
      .catch((error) => {
        console.log(error);
      });
    axios
      .get(addr + "/overview")
      .then((response) => {
        this.OverviewData = response.data;
      })
      .catch((error) => {
        console.log(error);
      });
    axios
      .get(addr + "/statistics/number")
      .then((response) => {
        this.NumStatisticData = response.data;
      })
      .catch((error) => {
        console.log(error);
      });
  },
};
</script>
